package zerocoder.com.Estate.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import zerocoder.com.Estate.dto.request.InspectionRequest;
import zerocoder.com.Estate.dto.response.InspectionResponse;
import zerocoder.com.Estate.enums.InspectionStatus;
import zerocoder.com.Estate.model.Inspection;
import zerocoder.com.Estate.service.AccountService;

@Component
@RequiredArgsConstructor
public class InspectionMapper {
    private final AccountService accountService;
    public Inspection toInspection(InspectionRequest inspectionRequest) {
        return Inspection.builder()
                .inspectionDate(inspectionRequest.getInspectionDate())
                .type(inspectionRequest.getType())
                .report(inspectionRequest.getReport())
                .status(InspectionStatus.valueOf(inspectionRequest.getStatus()))
                .build();
    }

    public void updateInspection(Inspection inspection, InspectionRequest inspectionRequest) {
        inspection.setInspectionDate(inspectionRequest.getInspectionDate());
        inspection.setReport(inspectionRequest.getReport());
        inspection.setStatus(InspectionStatus.valueOf(inspectionRequest.getStatus()));
        inspection.setType(inspectionRequest.getType());
    }

    public InspectionResponse toInspectionResponse(Inspection inspection) {
        return InspectionResponse.builder()
                .id(inspection.getId())
                .inspectionDate(inspection.getInspectionDate())
                .report(inspection.getReport())
                .type(inspection.getType())
                .status(inspection.getStatus())
                .createdAt(inspection.getCreatedAt())
                .createdBy(accountService.getUserName(inspection.getCreatedBy()))
                .updatedAt(inspection.getUpdatedAt())
                .updatedBy(accountService.getUserName(inspection.getUpdatedBy()))
                .build();
    }
}
