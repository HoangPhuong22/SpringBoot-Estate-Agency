package zerocoder.com.Estate.service;

import zerocoder.com.Estate.dto.request.EmployeeRequest;
import zerocoder.com.Estate.dto.response.EmployeeResponse;
import zerocoder.com.Estate.dto.response.PageResponse;
import zerocoder.com.Estate.dto.search.EmployeeSearchDTO;

import java.util.List;

public interface EmployeeService {
    Long save(EmployeeRequest request);
    Long update(EmployeeRequest request);

    EmployeeResponse getById(Long id);
    PageResponse<?> search(EmployeeSearchDTO searchDTO);
    List<EmployeeResponse> getAll();
}
