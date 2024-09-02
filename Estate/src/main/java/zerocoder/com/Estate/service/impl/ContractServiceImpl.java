package zerocoder.com.Estate.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zerocoder.com.Estate.dto.request.ContractRequest;
import zerocoder.com.Estate.dto.response.ContractResponse;
import zerocoder.com.Estate.enums.ContractType;
import zerocoder.com.Estate.exception.ContractValidException;
import zerocoder.com.Estate.mapper.ContractMapper;
import zerocoder.com.Estate.model.Contract;
import zerocoder.com.Estate.model.Customer;
import zerocoder.com.Estate.model.Property;
import zerocoder.com.Estate.repository.ContractRepository;
import zerocoder.com.Estate.repository.CustomerRepository;
import zerocoder.com.Estate.repository.PropertyRepository;
import zerocoder.com.Estate.service.ContractService;
import zerocoder.com.Estate.utils.SecurityUtils;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContractServiceImpl implements ContractService {

    private final ContractRepository contractRepository;
    private final PropertyRepository propertyRepository;
    private final CustomerRepository customerRepository;
    private final ContractMapper contractMapper;
    private final SecurityUtils securityUtils;

    @Override
    public Long saveOrUpdateContract(ContractRequest contractRequest) {
        if(contractRequest.getId() == null) {
            return save(contractRequest);
        }
        return update(contractRequest);
    }

    @Override
    public Long deleteContract(Long id) {
        contractRepository.deleteById(id);
        return id;
    }

    @Override
    public List<ContractResponse> findAllContractByPropertyId(Long propertyId) {
        List<Contract> contracts;
        if(securityUtils.getRole().equals("CUSTOMER")) {
            Long customerId = securityUtils.getPrincipal().getCustomer().getId();
            contracts = contractRepository.findAllByPropertyIdAndCustomerId(propertyId, customerId);
        } else {
            contracts = contractRepository.findAllByPropertyId(propertyId);
        }
        return contracts.stream().map(contractMapper::toResponse).toList();
    }

    private Long save(ContractRequest contractRequest) {
        validateContractRequest(contractRequest, -1L);
        Long propertyId = contractRequest.getPropertyId();
        Long customerId = contractRequest.getCustomerId();
        Property property = propertyRepository.findById(propertyId).orElseThrow();
        Customer customer = customerRepository.findById(customerId).orElseThrow();

        Contract contract =  contractMapper.toEntity(contractRequest);
        contract.setProperty(property);
        contract.setCustomer(customer);

        return contractRepository.save(contract).getId();
    }

    private Long update(ContractRequest contractRequest) {
        validateContractRequest(contractRequest, contractRequest.getId());
        Long contractId = contractRequest.getId();
        Long propertyId = contractRequest.getPropertyId();
        Long customerId = contractRequest.getCustomerId();
        Property property = propertyRepository.findById(propertyId).orElseThrow();
        Customer customer = customerRepository.findById(customerId).orElseThrow();

        Contract contract = contractRepository.findById(contractId).orElseThrow();
        contractMapper.updateEntity(contract, contractRequest);
        contract.setProperty(property);
        contract.setCustomer(customer);

        return contractRepository.save(contract).getId();
    }

    private void validateContractRequest(ContractRequest contractRequest, Long contractId) {
        validateContractSale(contractRequest);
        validateStartDateBeforeEndDate(contractRequest.getStartDate(), contractRequest.getEndDate());
        if(contractId == -1) validateStartDateNotInPast(contractRequest.getStartDate());
        validateNoActiveContract(contractRequest.getPropertyId(), contractRequest.getStartDate(), contractRequest.getEndDate(), contractId);
    }

    private void validateContractSale(ContractRequest contractRequest) {
        if(contractRequest.getId() == null) {
            if(contractRepository.existsByTypeAndPropertyId(ContractType.SALE, contractRequest.getPropertyId())) {
                throw new ContractValidException("Tòa nhà đã bán không thể tạo hợp đồng mới", "message");
            }
        } else {
            if(contractRepository.existsByTypeAndPropertyIdAndIdNot(ContractType.SALE, contractRequest.getPropertyId(), contractRequest.getId())) {
                throw new ContractValidException("Tòa nhà đã bán không thể tạo hợp đồng mới", "message");
            }
        }
    }

    private void validateStartDateBeforeEndDate(LocalDate startDate, LocalDate endDate) {
        if (startDate.isAfter(endDate)) {
            throw new ContractValidException("Ngày bắt đầu không thể sau ngày kết thúc", "startDate");
        }
    }

    private void validateStartDateNotInPast(LocalDate startDate) {
        if (startDate.isBefore(LocalDate.now())) {
            throw new ContractValidException("Ngày bắt đầu không thể trước ngày hiện tại", "startDate");
        }
    }

    private void validateNoActiveContract(Long propertyId, LocalDate startDate, LocalDate endDate,  Long contractId) {
        if (!isContractActive(propertyId, startDate, endDate, contractId)) {
            throw new ContractValidException("Tòa đang có hợp đồng hoạt động trong thời gian này", "message");
        }
    }

    private boolean isContractActive(Long propertyId, LocalDate startDate, LocalDate endDate, Long contractId) {
        List<Contract> contracts = contractId == -1 ?
                contractRepository.findAllByPropertyId(propertyId) :
                contractRepository.findAllByPropertyIdAndIdNot(propertyId, contractId);
        for(Contract contract : contracts) {
            if(!isWithinActivePeriod(contract, startDate, endDate)) {
                return false;
            }
        }
        return true;
    }

    private boolean isWithinActivePeriod(Contract contract, LocalDate startDate, LocalDate endDate) {
        return startDate.isAfter(contract.getEndDate()) || endDate.isBefore(contract.getStartDate());
    }
}
