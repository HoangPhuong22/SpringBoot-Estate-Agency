package zerocoder.com.Estate.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zerocoder.com.Estate.dto.request.ContractRequest;
import zerocoder.com.Estate.dto.response.ContractResponse;
import zerocoder.com.Estate.mapper.ContractMapper;
import zerocoder.com.Estate.model.Contract;
import zerocoder.com.Estate.model.Customer;
import zerocoder.com.Estate.model.Property;
import zerocoder.com.Estate.repository.ContractRepository;
import zerocoder.com.Estate.repository.CustomerRepository;
import zerocoder.com.Estate.repository.PropertyRepository;
import zerocoder.com.Estate.service.ContractService;
import zerocoder.com.Estate.utils.SecurityUtils;

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
}
