package lk.ijse.gdse68.Crop.monitoring.system.service;

import jakarta.validation.Valid;
import lk.ijse.gdse68.Crop.monitoring.system.customObj.VehicleResponse;
import lk.ijse.gdse68.Crop.monitoring.system.dto.VehicleDto;

import java.util.List;

public interface VehicleBo {
    void saveVehicle(@Valid VehicleDto vehicleDto);



    List<VehicleDto> getAllVehicles();


    VehicleResponse getVehicle(String vehicleCode);

    void deleteVehicle(String vehicleCode);
}
