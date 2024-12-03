package lk.ijse.gdse68.Crop.monitoring.system.controller;

import lk.ijse.gdse68.Crop.monitoring.system.customObj.EquipmentResponse;
import lk.ijse.gdse68.Crop.monitoring.system.dto.EquipmentDto;
import lk.ijse.gdse68.Crop.monitoring.system.exception.DataPersistFailException;
import lk.ijse.gdse68.Crop.monitoring.system.service.EquipmentBo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/equipment")
@RequiredArgsConstructor
@CrossOrigin
public class EquipmentController {
    private final EquipmentBo equipmentBo;

    @PostMapping
    public ResponseEntity<?> saveEquipment(@RequestBody EquipmentDto equipmentDto) {

        try {
            equipmentBo.saveEquipment(equipmentDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistFailException e) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{equipmentCode}")
    public ResponseEntity<EquipmentResponse> getEquipment(@PathVariable String equipmentCode) {
        try {
           return new ResponseEntity<>(equipmentBo.getEquipment(equipmentCode),HttpStatus.OK);
        } catch (DataPersistFailException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "allequipment", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllEquipment(){
        try {
            return new ResponseEntity<>(equipmentBo.getAllEquipment(),HttpStatus.OK);
        } catch (DataPersistFailException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/{equipmentCode}")
    public ResponseEntity<Void> deleteEquipment(@PathVariable String equipmentCode){
        try {
            equipmentBo.deleteEquipment(equipmentCode);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DataPersistFailException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping(value = "/{equipmentCode}",params = {"staffId","fieldCode"})
    public ResponseEntity<?> updateEquipment(
            @PathVariable("equipmentCode") String equipmentCode,
            @RequestBody EquipmentDto equipmentDto,
            @RequestParam("staffId") String staffId,
            @RequestParam("fieldCode") String fieldCode){
        try {
            equipmentBo.updateEquipment(equipmentDto,staffId,fieldCode,equipmentCode);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DataPersistFailException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
