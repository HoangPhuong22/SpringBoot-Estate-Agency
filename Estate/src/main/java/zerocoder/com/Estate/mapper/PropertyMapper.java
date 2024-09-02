package zerocoder.com.Estate.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import zerocoder.com.Estate.dto.request.PropertyRequest;
import zerocoder.com.Estate.dto.response.PropertyResponse;
import zerocoder.com.Estate.enums.ContractType;
import zerocoder.com.Estate.enums.PropertyDirection;
import zerocoder.com.Estate.enums.PropertyStatus;
import zerocoder.com.Estate.enums.PropertyType;
import zerocoder.com.Estate.model.Amenity;
import zerocoder.com.Estate.model.Contract;
import zerocoder.com.Estate.model.Property;
import zerocoder.com.Estate.model.PropertyImage;
import zerocoder.com.Estate.repository.ContractRepository;
import zerocoder.com.Estate.service.AccountService;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PropertyMapper {

    private final AccountService accountService;
    private final ContractRepository contractRepository;

    public Property toProperty(PropertyRequest propertyRequest) {
        String Code = PropertyType.valueOf(propertyRequest.getType()).getCode() + System.currentTimeMillis();
        return Property.builder()
                .type(PropertyType.valueOf(propertyRequest.getType()))
                .code(Code)
                .name(propertyRequest.getName())
                .address(propertyRequest.getAddress())
                .city(propertyRequest.getCity())
                .district(propertyRequest.getDistrict())
                .ward(propertyRequest.getWard())
                .area(propertyRequest.getArea())
                .floor(propertyRequest.getFloor())
                .bedRooms(propertyRequest.getBedroom())
                .bathRooms(propertyRequest.getBathroom())
                .builtYear(propertyRequest.getBuiltYear())
                .direction(PropertyDirection.valueOf(propertyRequest.getDirection()))
                .description(propertyRequest.getDescription())
                .salePrice(propertyRequest.getSalePrice())
                .rentPrice(propertyRequest.getRentPrice())
                .build();
    }
    public PropertyResponse toPropertyResponse(Property property) {
        return PropertyResponse.builder()
                .id(property.getId())
                .code(property.getCode())
                .name(property.getName())
                .address(property.getAddress())
                .city(property.getCity())
                .district(property.getDistrict())
                .ward(property.getWard())
                .area(property.getArea())
                .floor(property.getFloor())
                .bedRooms(property.getBedRooms())
                .bathRooms(property.getBathRooms())
                .builtYear(property.getBuiltYear())
                .description(property.getDescription())
                .salePrice(property.getSalePrice())
                .isDeleted(property.getIsDeleted())
                .rentPrice(property.getRentPrice())
                .direction(PropertyDirection.valueOf(property.getDirection().name()))
                .type(PropertyType.valueOf(property.getType().name()))
                .status(getPropertyStatus(property.getId()))
                .createdBy(accountService.getUserName(property.getCreatedBy()))
                .updatedBy(accountService.getUserName(property.getUpdatedBy()))
                .createdAt(property.getCreatedAt())
                .updatedAt(property.getUpdatedAt())
                .amenityIds(property.getAmenities().stream().map(Amenity::getId).toList())
                .mainImages(mainImage(property.getImages()))
                .images(listImage(property.getImages()))
                .build();
    }

    private PropertyStatus getPropertyStatus(Long id) {
        LocalDate now = LocalDate.now();
        List<Contract> contracts = contractRepository.findAllByPropertyId(id);
        if (!contracts.isEmpty()) {
            for (Contract contract : contracts) {
                if (contract.getType().equals(ContractType.SALE) && isBeforeEqual(contract.getStartDate(), now))
                    return PropertyStatus.SOLD;
                if (contract.getType().equals(ContractType.RENT) && isAfterEqual(contract.getEndDate(), now) && isBeforeEqual(contract.getStartDate(), now))
                    return PropertyStatus.RENTED;
            }
        }
        return PropertyStatus.AVAILABLE;
    }
    private boolean isBeforeEqual(LocalDate a, LocalDate b) {
        return a.isBefore(b) || a.isEqual(b);
    }
    private boolean isAfterEqual(LocalDate a, LocalDate b) {
        return a.isAfter(b) || a.isEqual(b);
    }
    private String mainImage(List<PropertyImage> images) {
        if(images == null || images.isEmpty()) {
            return null;
        }
        return images.stream().filter(PropertyImage::getIsMain).findFirst().map(PropertyImage::getImageUrl).orElse(null);
    }

    private List<String> listImage(List<PropertyImage> images) {
        if(images == null || images.isEmpty()) {
            return null;
        }
        return images.stream().map(PropertyImage::getImageUrl).toList();
    }

    public void updateProperty(Property property, PropertyRequest propertyRequest) {
        property.setType(PropertyType.valueOf(propertyRequest.getType()));
        property.setName(propertyRequest.getName());
        property.setAddress(propertyRequest.getAddress());
        property.setCity(propertyRequest.getCity());
        property.setDistrict(propertyRequest.getDistrict());
        property.setWard(propertyRequest.getWard());
        property.setArea(propertyRequest.getArea());
        property.setFloor(propertyRequest.getFloor());
        property.setBedRooms(propertyRequest.getBedroom());
        property.setBathRooms(propertyRequest.getBathroom());
        property.setBuiltYear(propertyRequest.getBuiltYear());
        property.setDirection(PropertyDirection.valueOf(propertyRequest.getDirection()));
        property.setDescription(propertyRequest.getDescription());
        property.setSalePrice(propertyRequest.getSalePrice());
        property.setRentPrice(propertyRequest.getRentPrice());
    }
}
