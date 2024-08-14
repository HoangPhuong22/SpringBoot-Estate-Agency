package zerocoder.com.Estate.service;

import zerocoder.com.Estate.dto.request.AmenityRequest;
import zerocoder.com.Estate.dto.response.AmenityResponse;
import zerocoder.com.Estate.dto.response.PageResponse;
import zerocoder.com.Estate.dto.search.AmenitySearchDTO;

import java.util.List;

public interface AmenityService {
    Integer addAmenity(AmenityRequest request);
    Integer editAmenity(AmenityRequest request);
    Integer deleteAmenity(Integer id);
    AmenityResponse findAmenityById(Integer id);
    List<AmenityResponse> findAllAmenities();
    PageResponse<?> findAmenitiesAndSearch(AmenitySearchDTO searchDTO);
}
