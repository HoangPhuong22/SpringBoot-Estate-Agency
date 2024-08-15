package zerocoder.com.Estate.repository.criteria;

import zerocoder.com.Estate.dto.response.PageResponse;
import zerocoder.com.Estate.dto.search.PropertySearchDTO;

public interface PropertyCriteriaRepository {
    PageResponse<?> findPropertiesAndSearch(PropertySearchDTO searchDTO);
}
