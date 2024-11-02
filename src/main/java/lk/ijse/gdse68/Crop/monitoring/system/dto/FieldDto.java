package lk.ijse.gdse68.Crop.monitoring.system.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lk.ijse.gdse68.Crop.monitoring.system.customObj.FieldResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data

public class FieldDto implements SuperDto, FieldResponse {

    private String fieldCode;

    @NotBlank
    @Pattern(regexp = "^[A-Za-z0-9 ]+$")
    private String fieldName;

    @NotBlank
    private Point fieldLocation;

    @Positive
    @NotNull
    private double fieldSize;

    @NotNull
    private String image1;

    @NotNull
    private String image2;


    private List<String> staffIds;

    private List<String> cropCodes;

    private List<String> equipmentCodes;

    private List<String> monitoringLogCodes;

}
