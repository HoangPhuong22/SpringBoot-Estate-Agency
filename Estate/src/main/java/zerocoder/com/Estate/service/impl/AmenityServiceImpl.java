package zerocoder.com.Estate.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import zerocoder.com.Estate.dto.request.AmenityRequest;
import zerocoder.com.Estate.dto.response.AmenityResponse;
import zerocoder.com.Estate.dto.response.PageResponse;
import zerocoder.com.Estate.dto.search.AmenitySearchDTO;
import zerocoder.com.Estate.mapper.AmenityMapper;
import zerocoder.com.Estate.model.Amenity;
import zerocoder.com.Estate.repository.AmenityRepository;
import zerocoder.com.Estate.service.AmenityService;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AmenityServiceImpl implements AmenityService {

    private final AmenityRepository amenityRepository;
    private final AmenityMapper amenityMapper;

    @Override
    public Integer addAmenity(AmenityRequest request) {
        Amenity amenity = amenityMapper.toAmenity(request);
        amenityRepository.save(amenity);
        log.info("Amenity added successfully");
        return amenity.getId();
    }

    @Override
    public Integer editAmenity(AmenityRequest request) {
        Amenity amenity = amenityRepository.findById(request.getId()).orElseThrow();
        amenityMapper.toAmenity(request, amenity);
        amenityRepository.save(amenity);
        log.info("Amenity updated successfully");
        return amenity.getId();
    }

    @Override
    public Integer deleteAmenity(Integer id) {
        amenityRepository.deleteById(id);
        log.info("Amenity deleted successfully");
        return id;
    }

    @Override
    public AmenityResponse findAmenityById(Integer id) {
        Amenity amenity = amenityRepository.findById(id).orElseThrow();
        return amenityMapper.toAmenityResponse(amenity);
    }

    @Override
    public List<AmenityResponse> findAllAmenities() {
        List<Amenity> amenities = amenityRepository.findAll();
        return amenities.stream().map(amenityMapper::toAmenityResponse).toList();
    }

    @Override
    public PageResponse<?> findAmenitiesAndSearch(AmenitySearchDTO searchDTO) {
        PageResponse<?> pageResponse = amenityRepository.findAmenitiesAndSearch(searchDTO);
        List<AmenityResponse> amenityResponses = ((List<Amenity>) pageResponse.getContent()).stream()
                .map(amenityMapper::toAmenityResponse)
                .toList();
        return PageResponse.builder()
                .content(amenityResponses)
                .pageNo(pageResponse.getPageNo())
                .pageSize(pageResponse.getPageSize())
                .totalPages(pageResponse.getTotalPages())
                .totalElements(pageResponse.getTotalElements())
                .build();
    }
}
