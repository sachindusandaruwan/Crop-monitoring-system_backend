package lk.ijse.gdse68.Crop.monitoring.system.controller;

import jakarta.validation.Valid;
import lk.ijse.gdse68.Crop.monitoring.system.customObj.VehicleResponse;
import lk.ijse.gdse68.Crop.monitoring.system.dto.VehicleDto;
import lk.ijse.gdse68.Crop.monitoring.system.exception.AlreadyExistsException;
import lk.ijse.gdse68.Crop.monitoring.system.exception.DataPersistFailException;
import lk.ijse.gdse68.Crop.monitoring.system.exception.NotFoundException;
import lk.ijse.gdse68.Crop.monitoring.system.service.VehicleBo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/vehicle")
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleBo vehicleBo;

    @PostMapping
    public ResponseEntity<?> saveVehicle(@Valid @RequestBody VehicleDto vehicleDto) {
        try {
            vehicleBo.saveVehicle(vehicleDto);

            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (AlreadyExistsException e) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (DataPersistFailException e) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping(value = "allvehicle", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllVehicles() {
        try {
            return new ResponseEntity<>(vehicleBo.getAllVehicles(), HttpStatus.OK);
        }catch (NotFoundException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
