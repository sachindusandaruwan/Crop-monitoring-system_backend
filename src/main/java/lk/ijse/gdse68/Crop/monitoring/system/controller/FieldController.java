package lk.ijse.gdse68.Crop.monitoring.system.controller;

import lk.ijse.gdse68.Crop.monitoring.system.dto.FieldDto;
import lk.ijse.gdse68.Crop.monitoring.system.dto.StaffDto;
import lk.ijse.gdse68.Crop.monitoring.system.exception.DataPersistFailException;
import lk.ijse.gdse68.Crop.monitoring.system.exception.NotFoundException;
import lk.ijse.gdse68.Crop.monitoring.system.service.FieldBo;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/field")
@RequiredArgsConstructor
public class FieldController {

    private final FieldBo fieldBo;

    private static final Logger logger = Logger.getLogger(FieldController.class.getName());

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveField(@RequestBody FieldDto fieldDto){
        try {
            fieldBo.saveField(fieldDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistFailException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{fieldCode}")
    public ResponseEntity<?> getField(@PathVariable String fieldCode){
        try {
            return new ResponseEntity<>(fieldBo.getField(fieldCode), HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "allfield", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<FieldDto> getAllField(){
        return fieldBo.getAllField();
    }


}
