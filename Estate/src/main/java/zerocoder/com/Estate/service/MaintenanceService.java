package zerocoder.com.Estate.service;

import zerocoder.com.Estate.dto.request.MaintenanceRequest;
import zerocoder.com.Estate.dto.response.MaintenanceResponse;

import java.util.List;

public interface MaintenanceService {
    Long saveOrUpdate(MaintenanceRequest maintenanceRequest);
    Long delete(Long id);

    List<MaintenanceResponse> getAll();
    List<MaintenanceResponse> findAllByPropertyId(Long propertyId);
}
