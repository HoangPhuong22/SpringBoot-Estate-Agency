package zerocoder.com.Estate.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import zerocoder.com.Estate.dto.request.AmenityRequest;
import zerocoder.com.Estate.dto.response.AmenityResponse;
import zerocoder.com.Estate.enums.AmenityType;
import zerocoder.com.Estate.model.Account;
import zerocoder.com.Estate.model.Amenity;
import zerocoder.com.Estate.repository.AccountRepository;
import zerocoder.com.Estate.service.AccountService;
import zerocoder.com.Estate.utils.SecurityUtils;
import zerocoder.com.Estate.utils.TitleUtils;

@Component
@RequiredArgsConstructor
public class AmenityMapper {

    private final SecurityUtils securityUtils;
    private final AccountService accountService;

    public Amenity toAmenity(AmenityRequest request) {
        return Amenity.builder()
                .name(TitleUtils.toTitleCase(request.getName()))
                .type(Enum.valueOf(AmenityType.class, request.getType()))
                .description(request.getDescription())
                .build();
    }
    public AmenityResponse toAmenityResponse(Amenity amenity) {
        return AmenityResponse.builder()
                .id(amenity.getId())
                .name(amenity.getName())
                .type(amenity.getType())
                .description(amenity.getDescription())
                .createdBy(accountService.getUserName(amenity.getCreatedBy()))
                .updatedBy(accountService.getUserName(amenity.getUpdatedBy()))
                .createdAt(amenity.getCreatedAt())
                .updatedAt(amenity.getUpdatedAt())
                .build();
    }
    public void toAmenity(AmenityRequest request, Amenity amenity) {
        amenity.setName(TitleUtils.toTitleCase(request.getName()));
        amenity.setType(Enum.valueOf(AmenityType.class, request.getType()));
        amenity.setDescription(request.getDescription());
    }
}
