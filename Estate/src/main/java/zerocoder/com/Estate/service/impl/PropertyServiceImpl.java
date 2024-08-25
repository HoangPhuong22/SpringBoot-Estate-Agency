package zerocoder.com.Estate.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import zerocoder.com.Estate.dto.request.PropertyRequest;
import zerocoder.com.Estate.dto.response.PageResponse;
import zerocoder.com.Estate.dto.response.PropertyResponse;
import zerocoder.com.Estate.dto.search.PropertySearchDTO;
import zerocoder.com.Estate.mapper.PropertyMapper;
import zerocoder.com.Estate.model.Amenity;
import zerocoder.com.Estate.model.Property;
import zerocoder.com.Estate.repository.AmenityRepository;
import zerocoder.com.Estate.repository.PropertyRepository;
import zerocoder.com.Estate.service.PropertyService;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PropertyServiceImpl implements PropertyService {

    private final PropertyRepository propertyRepository;
    private final PropertyMapper propertyMapper;
    private final AmenityRepository amenityRepository;

    @Override
    public Long saveProperty(PropertyRequest propertyRequest) {
        List<Integer> amenityIds = propertyRequest.getAmenityIds();
        List<Amenity> amenities = amenityRepository.findAllById(amenityIds);
        Property property = propertyMapper.toProperty(propertyRequest);
        property.setAmenities(amenities);
        propertyRepository.save(property);
        return property.getId();
    }

    @Override
    public Long updateProperty(PropertyRequest propertyRequest) {
        Property property = propertyRepository.findById(propertyRequest.getId()).orElseThrow();
        propertyMapper.updateProperty(property, propertyRequest);
        List<Integer> amenityIds = propertyRequest.getAmenityIds();
        List<Amenity> amenities = amenityRepository.findAllById(amenityIds);
        property.setAmenities(amenities);
        propertyRepository.save(property);
        return property.getId();
    }

    @Override
    public Long updateIsDelted(Long id) {
        Property property = propertyRepository.findById(id).orElseThrow();
        property.setIsDeleted(true);
        propertyRepository.save(property);
        return property.getId();
    }

    @Override
    public PropertyResponse findById(Long id) {
        Property property = propertyRepository.findById(id).orElseThrow();
        return propertyMapper.toPropertyResponse(property);
    }

    @Override
    public List<PropertyResponse> findAll() {
        List<Property> properties = propertyRepository.findAll();
        return properties.stream().map(propertyMapper::toPropertyResponse).toList();
    }

    @Override
    public PageResponse<?> findPropertiesAndSearch(PropertySearchDTO searchDTO) {
        PageResponse<?> response = propertyRepository.findPropertiesAndSearch(searchDTO);
        List<Property> properties = (List<Property>) response.getContent();
        List<PropertyResponse> propertyResponses = properties.stream().map(propertyMapper::toPropertyResponse).toList();
        return PageResponse.builder()
                .content(propertyResponses)
                .pageNo(response.getPageNo())
                .pageSize(response.getPageSize())
                .totalElements(response.getTotalElements())
                .totalPages(response.getTotalPages())
                .build();
    }
}
