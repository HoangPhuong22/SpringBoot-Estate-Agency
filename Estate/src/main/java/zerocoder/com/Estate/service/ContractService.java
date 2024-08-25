package zerocoder.com.Estate.service;

import zerocoder.com.Estate.dto.request.ContractRequest;
import zerocoder.com.Estate.dto.response.ContractResponse;

import java.util.List;

public interface ContractService {
    Long saveOrUpdateContract(ContractRequest contractRequest);
    Long deleteContract(Long id);
    List<ContractResponse> findAllContractByPropertyId(Long propertyId);
}
