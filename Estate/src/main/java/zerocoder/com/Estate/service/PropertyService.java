package zerocoder.com.Estate.service;

import zerocoder.com.Estate.dto.request.PropertyRequest;
import zerocoder.com.Estate.dto.response.PropertyResponse;

import java.util.List;

public interface PropertyService {
    Long saveProperty(PropertyRequest propertyRequest);
    List<PropertyResponse> findAll();
}
