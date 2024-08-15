package zerocoder.com.Estate.service;

import zerocoder.com.Estate.dto.request.PropertyRequest;
import zerocoder.com.Estate.dto.response.PageResponse;
import zerocoder.com.Estate.dto.response.PropertyResponse;
import zerocoder.com.Estate.dto.search.PropertySearchDTO;

import java.util.List;

public interface PropertyService {
    Long saveProperty(PropertyRequest propertyRequest);
    Long updateProperty(PropertyRequest propertyRequest);
    Long updateIsDelted(Long id);

    PropertyResponse findById(Long id);
    List<PropertyResponse> findAll();
    PageResponse<?> findPropertiesAndSearch(PropertySearchDTO searchDTO);
}
