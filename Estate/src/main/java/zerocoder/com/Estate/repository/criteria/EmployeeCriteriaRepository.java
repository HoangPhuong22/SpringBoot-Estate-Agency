package zerocoder.com.Estate.repository.criteria;

import zerocoder.com.Estate.dto.response.PageResponse;
import zerocoder.com.Estate.dto.search.EmployeeSearchDTO;

public interface EmployeeCriteriaRepository {
    PageResponse<?> search(EmployeeSearchDTO searchDTO);
}
