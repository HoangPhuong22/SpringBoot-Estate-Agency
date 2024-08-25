package zerocoder.com.Estate.api.admin;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import zerocoder.com.Estate.dto.request.InspectionRequest;
import zerocoder.com.Estate.dto.response.ResponseData;
import zerocoder.com.Estate.service.InspectionService;

@Slf4j
@Validated
@RequiredArgsConstructor
@RestController(value = "InspectionAPIAdmin")
@RequestMapping("/api/inspection")
public class InspectionAPI {

    private final InspectionService inspectionService;

    @PostMapping("/save")
    public ResponseData<?> saveInspection(@Valid @RequestBody InspectionRequest request) {
        Long id = inspectionService.saveOrUpdate(request);
        return new ResponseData<>(HttpStatus.OK.value(), "Inspection saved successfully", id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseData<?> deleteInspection(@PathVariable Long id) {
        return new ResponseData<>(HttpStatus.OK.value(), "Inspection deleted successfully", inspectionService.delete(id));
    }
}
