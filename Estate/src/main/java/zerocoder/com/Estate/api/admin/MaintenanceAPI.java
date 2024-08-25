package zerocoder.com.Estate.api.admin;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import zerocoder.com.Estate.dto.request.MaintenanceRequest;
import zerocoder.com.Estate.dto.response.ResponseData;
import zerocoder.com.Estate.service.MaintenanceService;

@Slf4j
@Validated
@RequiredArgsConstructor
@RestController(value = "MaintenanceAPIAdmin")
@RequestMapping("/api/maintenance")
public class MaintenanceAPI {

    private final MaintenanceService maintenanceService;

    @PostMapping("/add")
    public ResponseData<?> saveMaintenance(@Valid @RequestBody MaintenanceRequest request) {
        Long id = maintenanceService.saveOrUpdate(request);
        return new ResponseData<>(HttpStatus.OK.value(), "Maintenance saved successfully", id);
    }

    @PutMapping("/edit")
    public ResponseData<?> updateMaintenance(@Valid @RequestBody MaintenanceRequest request) {
        Long id = maintenanceService.saveOrUpdate(request);
        return new ResponseData<>(HttpStatus.OK.value(), "Maintenance updated successfully", id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseData<?> deleteMaintenance(@PathVariable Long id) {
        Long deletedId = maintenanceService.delete(id);
        return new ResponseData<>(HttpStatus.OK.value(), "Maintenance deleted successfully", deletedId);
    }
}
