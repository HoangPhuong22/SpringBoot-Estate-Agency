package zerocoder.com.Estate.api.admin;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import zerocoder.com.Estate.dto.request.ContractRequest;
import zerocoder.com.Estate.dto.response.ResponseData;
import zerocoder.com.Estate.service.ContractService;

@Slf4j
@Validated
@RequiredArgsConstructor
@RestController(value = "ContractAPIAdmin")
@RequestMapping("/api/contract")
public class ContractAPI {

    private final ContractService contractService;

    @PostMapping("/save")
    public ResponseData<?> add(@Valid @RequestBody ContractRequest contractRequest) {
        Long id = contractService.saveOrUpdateContract(contractRequest);
        return new ResponseData<>(HttpStatus.CREATED.value(), "Add contract successfully", id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseData<?> delete(@PathVariable Long id) {
        return new ResponseData<>(HttpStatus.OK.value(), "Delete contract successfully", contractService.deleteContract(id));
    }
}
