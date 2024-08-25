package zerocoder.com.Estate.service;

import zerocoder.com.Estate.dto.request.InspectionRequest;
import zerocoder.com.Estate.dto.response.InspectionResponse;

import java.util.List;

public interface InspectionService {
    Long saveOrUpdate(InspectionRequest inspectionRequest);
    Long delete(Long id);
    List<InspectionResponse> getAllInspection();
    List<InspectionResponse> findInspectionsByPropertyId(Long propertyId);
}
