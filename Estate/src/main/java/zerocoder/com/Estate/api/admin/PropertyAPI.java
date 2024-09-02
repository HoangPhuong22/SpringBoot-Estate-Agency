package zerocoder.com.Estate.api.admin;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import zerocoder.com.Estate.dto.request.PropertyRequest;
import zerocoder.com.Estate.dto.response.ResponseData;
import zerocoder.com.Estate.service.PropertyService;

import java.util.List;

@Slf4j
@Validated
@RequiredArgsConstructor
@RestController(value = "PropertyAPIAdmin")
@RequestMapping("/api/property")
public class PropertyAPI {
    private final PropertyService propertyService;

    @PostMapping("/add")
    public ResponseData<?> addProperty(@Valid @ModelAttribute PropertyRequest propertyRequest,
                                       @RequestParam(value = "images", required = false) List<MultipartFile> images,
                                       @RequestParam(value = "mainImageIndex", defaultValue = "1") Integer mainImageIndex
    ) {
        return new ResponseData<>(HttpStatus.CREATED.value(), "Property added successfully", propertyService.saveProperty(propertyRequest, images, mainImageIndex));
    }

    @PutMapping("/edit")
    public ResponseData<?> editProperty(@Valid @ModelAttribute PropertyRequest propertyRequest,
                                        @RequestParam(value = "images", required = false) List<MultipartFile> images,
                                        @RequestParam(value = "mainImageIndex", defaultValue = "1") Integer mainImageIndex
    ) {
        try {
            return new ResponseData<>(HttpStatus.OK.value(), "Property updated successfully", propertyService.updateProperty(propertyRequest, images, mainImageIndex));
        } catch (Exception e) {
            log.error("Error while updating property: {}", e.getMessage());
            return new ResponseData<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error while updating property");
        }
    }

    @PatchMapping("/delete/{id}")
    public ResponseData<?> deleteProperty(@PathVariable Long id) {
        try {
            Long idUpdated = propertyService.updateIsDeleted(id);
            return new ResponseData<>(HttpStatus.OK.value(), "Updated isDeleted successfully", idUpdated);
        } catch (Exception e) {
            log.error("Error updating isDeleted: {}", e.getMessage());
            return new ResponseData<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error updating isDeleted");
        }
    }
}
