package zerocoder.com.Estate.service.iml;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import zerocoder.com.Estate.dto.request.PropertyRequest;
import zerocoder.com.Estate.dto.response.PropertyResponse;
import zerocoder.com.Estate.mapper.PropertyMapper;
import zerocoder.com.Estate.model.Property;
import zerocoder.com.Estate.repository.PropertyRepository;
import zerocoder.com.Estate.service.PropertyService;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PropertyServiceImpl implements PropertyService {

    private final PropertyRepository propertyRepository;
    private final PropertyMapper propertyMapper;

    @Override
    public Long saveProperty(PropertyRequest propertyRequest) {
        Property property = propertyMapper.toProperty(propertyRequest);
        propertyRepository.save(property);
        return property.getId();
    }

    @Override
    public List<PropertyResponse> findAll() {
        List<Property> properties = propertyRepository.findAll();
        return properties.stream().map(propertyMapper::toPropertyResponse).toList();
    }
}
