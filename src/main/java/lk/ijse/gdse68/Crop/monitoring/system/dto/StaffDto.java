package lk.ijse.gdse68.Crop.monitoring.system.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lk.ijse.gdse68.Crop.monitoring.system.customObj.StaffResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StaffDto implements SuperDto, StaffResponse {
    private String id;

    @NotBlank
    @Pattern(regexp = "^[A-Z][a-z]*$")
    private String firstName;

    @NotBlank
    @Pattern(regexp = "^[A-Z][a-z]*$")
    private String lastName;

    @NotBlank
    private String designation;

    @NotBlank
    private String gender;

    @NotBlank
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String joinedDate;

    @NotBlank
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String DOB;

    @NotBlank
    private String addressLine1;

    @NotBlank
    private String addressLine2;

    @NotBlank
    private String addressLine3;

    private String addressLine4;
    private String addressLine5;

    @NotBlank
    @Pattern(regexp = "^[0-9]{10}$")
    private String contactNo;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String role;
    private String equipmentIds;
    private List<String> fieldCodes;
    private List<String> vehicleCodes;
    private List<String> logCodes;
}