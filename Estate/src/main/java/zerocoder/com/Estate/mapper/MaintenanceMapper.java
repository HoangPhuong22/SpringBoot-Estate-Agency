package zerocoder.com.Estate.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import zerocoder.com.Estate.dto.request.MaintenanceRequest;
import zerocoder.com.Estate.dto.response.MaintenanceResponse;
import zerocoder.com.Estate.enums.MaintenanceStatus;
import zerocoder.com.Estate.model.Maintenance;
import zerocoder.com.Estate.service.AccountService;

@Component
@RequiredArgsConstructor
public class MaintenanceMapper {
    private final AccountService accountService;

    public Maintenance toEntity(MaintenanceRequest maintenanceRequest) {
        return Maintenance.builder()
                .description(maintenanceRequest.getDescription())
                .estimatedCost(maintenanceRequest.getEstimatedCost())
                .status(MaintenanceStatus.PENDING)
                .build();
    }
    public void updateEntity(Maintenance maintenance, MaintenanceRequest maintenanceRequest) {
        maintenance.setDescription(maintenanceRequest.getDescription());
        if(!maintenanceRequest.getStatus().isEmpty())
            maintenance.setStatus(MaintenanceStatus.valueOf(maintenanceRequest.getStatus()));
        maintenance.setEstimatedCost(maintenanceRequest.getEstimatedCost());
        maintenance.setActualCost(maintenanceRequest.getActualCost());
        maintenance.setCompletedAt(maintenanceRequest.getCompletedAt());
    }
    public MaintenanceResponse toResponse(Maintenance maintenance) {
        return MaintenanceResponse.builder()
                .id(maintenance.getId())
                .description(maintenance.getDescription())
                .status(MaintenanceStatus.valueOf(maintenance.getStatus().name()))
                .estimatedCost(maintenance.getEstimatedCost())
                .actualCost(maintenance.getActualCost())
                .completedAt(maintenance.getCompletedAt())
                .createdBy(accountService.getUserName(maintenance.getAccount().getId()))
                .createdAt(maintenance.getCreatedAt())
                .updatedBy(accountService.getUserName(maintenance.getUpdatedBy()))
                .updatedAt(maintenance.getUpdatedAt())
                .build();
    }
}
