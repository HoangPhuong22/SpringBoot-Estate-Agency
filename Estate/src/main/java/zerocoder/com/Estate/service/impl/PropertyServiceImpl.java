package zerocoder.com.Estate.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import zerocoder.com.Estate.dto.request.PropertyRequest;
import zerocoder.com.Estate.dto.response.PageResponse;
import zerocoder.com.Estate.dto.response.PropertyResponse;
import zerocoder.com.Estate.dto.search.PropertySearchDTO;
import zerocoder.com.Estate.exception.EmptyFileException;
import zerocoder.com.Estate.mapper.PropertyMapper;
import zerocoder.com.Estate.model.Amenity;
import zerocoder.com.Estate.model.Property;
import zerocoder.com.Estate.model.PropertyImage;
import zerocoder.com.Estate.repository.AmenityRepository;
import zerocoder.com.Estate.repository.PropertyImageRepository;
import zerocoder.com.Estate.repository.PropertyRepository;
import zerocoder.com.Estate.service.PropertyService;
import zerocoder.com.Estate.utils.FileUtils;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PropertyServiceImpl implements PropertyService {

    private final PropertyRepository propertyRepository;
    private final PropertyMapper propertyMapper;
    private final AmenityRepository amenityRepository;
    private final PropertyImageRepository propertyImageRepository;

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Override
    public Long saveProperty(PropertyRequest propertyRequest, List<MultipartFile> images, Integer mainImageIndex) {
        if(images == null || images.isEmpty()) {
            throw new EmptyFileException("Ảnh tài sản không được để trống", "images");
        }
        List<Integer> amenityIds = propertyRequest.getAmenityIds();
        List<Amenity> amenities = amenityIds == null ? null : amenityRepository.findAllById(amenityIds);
        Property property = propertyMapper.toProperty(propertyRequest);
        property.setAmenities(amenities);
        List<PropertyImage> lstImage = saveImages(images, property, mainImageIndex);
        property.setImages(lstImage);
        propertyRepository.save(property);
        return property.getId();
    }

    private List<PropertyImage> saveImages(List<MultipartFile> images, Property property, Integer mainImageIndex) {
        List<PropertyImage> lstImages = new ArrayList<>();
        for(int i = 0; i < images.size(); i++) {
            MultipartFile image = images.get(i);
            String fileName = FileUtils.save(uploadDir, image);
            PropertyImage propertyImage = PropertyImage.builder()
                    .property(property)
                    .imageUrl(fileName)
                    .isMain(i == mainImageIndex)
                    .build();
            lstImages.add(propertyImage);
        }
        return lstImages;
    }

    @Override
    @Transactional
    public Long updateProperty(PropertyRequest propertyRequest, List<MultipartFile> images, Integer mainImageIndex) {
        Property property = propertyRepository.findById(propertyRequest.getId()).orElseThrow();
        propertyMapper.updateProperty(property, propertyRequest);
        List<Integer> amenityIds = propertyRequest.getAmenityIds();
        List<Amenity> amenities = amenityIds == null ? null : amenityRepository.findAllById(amenityIds);
        property.setAmenities(amenities);
        List<PropertyImage> lstImage;
        if(images != null && !images.isEmpty()) {
            propertyImageRepository.deleteAllByPropertyId(property.getId());
            lstImage = saveImages(images, property, mainImageIndex);
            deleteImages(property.getImages());
            property.setImages(lstImage);
        }
        propertyRepository.save(property);
        return property.getId();
    }

    private void deleteImages(List<PropertyImage> images) {
        for(PropertyImage image : images) {
            FileUtils.delete(uploadDir, image.getImageUrl());
        }
    }

    @Override
    public Long updateIsDeleted(Long id) {
        Property property = propertyRepository.findById(id).orElseThrow();
        property.setIsDeleted(!property.getIsDeleted());
        propertyRepository.save(property);
        return property.getId();
    }

    @Override
    public PropertyResponse findById(Long id) {
        Property property = propertyRepository.findById(id).orElseThrow();
        return propertyMapper.toPropertyResponse(property);
    }

    @Override
    public List<PropertyResponse> findTop5Properties() {
        List<Property> properties = propertyRepository.findTop5ByIsDeletedOrderByCreatedAtDesc(false);
        return properties.stream().map(propertyMapper::toPropertyResponse).toList();
    }

    @Override
    public List<PropertyResponse> findTop3Properties() {
        List<Property> properties = propertyRepository.findTop3ByIsDeletedOrderByCreatedAtDesc(false);
        return properties.stream().map(propertyMapper::toPropertyResponse).toList();
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
