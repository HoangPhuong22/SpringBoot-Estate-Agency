package zerocoder.com.Estate.dto.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import zerocoder.com.Estate.enums.ContractStatus;
import zerocoder.com.Estate.enums.ContractType;
import zerocoder.com.Estate.validator.EnumNamePattern;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@SuperBuilder
public class ContractResponse extends AbstractResponse implements Serializable {
    private Long id;
    private Long propertyId;
    private Long customerId;
    private String code;
    private ContractType contractType;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long value;
    private ContractStatus status;
    private String fullName;
    private String idNumber;
    private String phoneNumber;
    private String address;
}
