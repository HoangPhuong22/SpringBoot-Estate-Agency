package zerocoder.com.Estate.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import zerocoder.com.Estate.dto.request.ContractRequest;
import zerocoder.com.Estate.dto.response.ContractResponse;
import zerocoder.com.Estate.enums.ContractStatus;
import zerocoder.com.Estate.enums.ContractType;
import zerocoder.com.Estate.model.Contract;
import zerocoder.com.Estate.service.AccountService;

@Component
@RequiredArgsConstructor
public class ContractMapper {
    private final AccountService accountService;

    public Contract toEntity(ContractRequest contractRequest) {
        return Contract.builder()
                .code(generateContractCode())
                .type(ContractType.valueOf(contractRequest.getContractType()))
                .startDate(contractRequest.getStartDate())
                .endDate(contractRequest.getEndDate())
                .value(contractRequest.getValue())
                .build();
    }

    public void updateEntity(Contract contract, ContractRequest contractRequest) {
        contract.setType(ContractType.valueOf(contractRequest.getContractType()));
        contract.setStartDate(contractRequest.getStartDate());
        contract.setEndDate(contractRequest.getEndDate());
        contract.setValue(contractRequest.getValue());
    }

    public ContractResponse toResponse(Contract contract) {
        return ContractResponse.builder()
                .id(contract.getId())
                .code(contract.getCode())
                .propertyId(contract.getProperty().getId())
                .customerId(contract.getCustomer().getId())
                .contractType(contract.getType())
                .startDate(contract.getStartDate())
                .endDate(contract.getEndDate())
                .value(contract.getValue())
                .status(contract.getContractStatus())
                .createdBy(accountService.getUserName(contract.getCreatedBy()))
                .updatedBy(accountService.getUserName(contract.getUpdatedBy()))
                .createdAt(contract.getCreatedAt())
                .updatedAt(contract.getUpdatedAt())
                .fullName(contract.getCustomer().getFullName())
                .idNumber(contract.getCustomer().getIdNumber())
                .phoneNumber(contract.getCustomer().getPhone())
                .address(contract.getCustomer().getAddress())
                .build();
    }

    private String generateContractCode() {
        return "HD" + System.currentTimeMillis();
    }
}
