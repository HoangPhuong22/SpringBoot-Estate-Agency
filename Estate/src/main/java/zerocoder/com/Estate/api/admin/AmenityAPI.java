package zerocoder.com.Estate.api.admin;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import zerocoder.com.Estate.dto.request.AmenityRequest;
import zerocoder.com.Estate.dto.response.ResponseData;
import zerocoder.com.Estate.service.AmenityService;

@Slf4j
@Validated
@RequiredArgsConstructor
@RestController(value = "AmenityAPIAdmin")
@RequestMapping("/api/amenity")
public class AmenityAPI {

    private final AmenityService amenityService;

    @PostMapping("/add")
    public ResponseData<?> addAmenity(@Valid @RequestBody AmenityRequest request) {
        try {
            Integer id = amenityService.addAmenity(request);
            return new ResponseData<>(HttpStatus.CREATED.value(), "Amenity added successfully", id);
        } catch (Exception e) {
            return new ResponseData<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
        }
    }

    @PutMapping("/edit")
    public ResponseData<?> editAmenity(@Valid @RequestBody AmenityRequest request) {
        try {
            Integer id = amenityService.editAmenity(request);
            return new ResponseData<>(HttpStatus.OK.value(), "Amenity updated successfully", id);
        } catch (Exception e) {
            return new ResponseData<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseData<?> deleteAmenity(@PathVariable Integer id) {
        try {
            Integer idDeleted = amenityService.deleteAmenity(id);
            return new ResponseData<>(HttpStatus.OK.value(), "Amenity deleted successfully", idDeleted);
        } catch (Exception e) {
            return new ResponseData<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
        }
    }
}
