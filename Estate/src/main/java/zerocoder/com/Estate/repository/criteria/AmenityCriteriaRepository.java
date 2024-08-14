package zerocoder.com.Estate.repository.criteria;

import zerocoder.com.Estate.dto.response.PageResponse;
import zerocoder.com.Estate.dto.search.AmenitySearchDTO;

public interface AmenityCriteriaRepository {
    PageResponse<?> findAmenitiesAndSearch(AmenitySearchDTO searchDTO);
}
