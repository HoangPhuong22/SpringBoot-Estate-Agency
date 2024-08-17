package zerocoder.com.Estate.repository.criteria;

import zerocoder.com.Estate.dto.response.PageResponse;
import zerocoder.com.Estate.dto.search.CustomerSearchDTO;

public interface CustomerCriteriaRepository {
    PageResponse<?> search(CustomerSearchDTO customerSearchDTO);
}
