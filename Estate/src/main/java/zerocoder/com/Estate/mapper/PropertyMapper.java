package zerocoder.com.Estate.mapper;

import org.springframework.stereotype.Component;
import zerocoder.com.Estate.dto.request.PropertyRequest;
import zerocoder.com.Estate.dto.response.PropertyResponse;
import zerocoder.com.Estate.enums.PropertyDirection;
import zerocoder.com.Estate.enums.PropertyStatus;
import zerocoder.com.Estate.enums.PropertyType;
import zerocoder.com.Estate.model.Property;

@Component
public class PropertyMapper {
    public Property toProperty(PropertyRequest propertyRequest) {
        return Property.builder()
                .type(PropertyType.valueOf(propertyRequest.getType()))
                .code("PROP")
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
                .status(PropertyStatus.valueOf(propertyRequest.getStatus()))
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
                .direction(PropertyDirection.valueOf(property.getDirection().name()))
                .type(PropertyType.valueOf(property.getType().name()))
                .status(PropertyStatus.valueOf(property.getStatus().name()))
                .createdAt(property.getCreatedAt())
                .updatedAt(property.getUpdatedAt())
                .build();
    }
}
