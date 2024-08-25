package zerocoder.com.Estate.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import zerocoder.com.Estate.dto.request.InspectionRequest;
import zerocoder.com.Estate.dto.response.InspectionResponse;
import zerocoder.com.Estate.mapper.InspectionMapper;
import zerocoder.com.Estate.model.Employee;
import zerocoder.com.Estate.model.Inspection;
import zerocoder.com.Estate.model.Property;
import zerocoder.com.Estate.repository.EmployeeRepository;
import zerocoder.com.Estate.repository.InspectionRepository;
import zerocoder.com.Estate.repository.PropertyRepository;
import zerocoder.com.Estate.service.InspectionService;
import zerocoder.com.Estate.utils.SecurityUtils;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class InspectionServiceImpl implements InspectionService {

    private final InspectionMapper inspectionMapper;
    private final InspectionRepository inspectionRepository;
    private final PropertyRepository propertyRepository;
    private final EmployeeRepository employeeRepository;
    private final SecurityUtils securityUtils;

    @Override
    public Long saveOrUpdate(InspectionRequest inspectionRequest) {
        if (inspectionRequest.getId() == null) return save(inspectionRequest);
        return update(inspectionRequest);
    }

    @Override
    public Long delete(Long id) {
        inspectionRepository.deleteById(id);
        return id;
    }

    @Override
    public List<InspectionResponse> getAllInspection() {
        List<Inspection> inspections = inspectionRepository.findAll();
        return inspections.stream().map(inspectionMapper::toInspectionResponse).toList();
    }

    @Override
    public List<InspectionResponse> findInspectionsByPropertyId(Long propertyId) {
        List<Inspection> inspections = inspectionRepository.findByPropertyId(propertyId);
        return inspections.stream().map(inspectionMapper::toInspectionResponse).toList();
    }

    public Long save(InspectionRequest inspectionRequest) {
        Long propertyId = inspectionRequest.getPropertyId();
        Property property = propertyRepository.findById(propertyId).orElseThrow();
        Inspection inspection = inspectionMapper.toInspection(inspectionRequest);
        inspection.setProperty(property);
        Long employeeId = securityUtils.getPrincipal().getEmployee().getId();
        Employee employee = employeeRepository.findById(employeeId).orElseThrow();
        inspection.setEmployee(employee);
        return inspectionRepository.save(inspection).getId();
    }
    public Long update(InspectionRequest inspectionRequest) {
        Long inspectionId = inspectionRequest.getId();
        Inspection inspection = inspectionRepository.findById(inspectionId).orElseThrow();
        inspectionMapper.updateInspection(inspection, inspectionRequest);
        return inspectionRepository.save(inspection).getId();
    }
}
