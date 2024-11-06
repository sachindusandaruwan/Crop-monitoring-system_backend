package lk.ijse.gdse68.Crop.monitoring.system.service;

import jakarta.validation.Valid;
import lk.ijse.gdse68.Crop.monitoring.system.dto.VehicleDto;

public interface VehicleBo {
    void saveVehicle(@Valid VehicleDto vehicleDto);
}
