package zerocoder.com.Estate.service;

import org.springframework.web.multipart.MultipartFile;
import zerocoder.com.Estate.dto.request.PropertyRequest;
import zerocoder.com.Estate.dto.response.PageResponse;
import zerocoder.com.Estate.dto.response.PropertyResponse;
import zerocoder.com.Estate.dto.search.PropertySearchDTO;

import java.util.List;

public interface PropertyService {
    Long saveProperty(PropertyRequest propertyRequest, List<MultipartFile> images, Integer mainImageIndex);
    Long updateProperty(PropertyRequest propertyRequest, List<MultipartFile> images, Integer mainImageIndex);
    Long updateIsDeleted(Long id);

    PropertyResponse findById(Long id);
    List<PropertyResponse> findTop5Properties();
    List<PropertyResponse> findTop3Properties();
    List<PropertyResponse> findAll();
    PageResponse<?> findPropertiesAndSearch(PropertySearchDTO searchDTO);
}
