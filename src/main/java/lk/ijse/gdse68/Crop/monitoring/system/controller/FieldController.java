package lk.ijse.gdse68.Crop.monitoring.system.controller;

import lk.ijse.gdse68.Crop.monitoring.system.dto.FieldDto;
import lk.ijse.gdse68.Crop.monitoring.system.dto.StaffDto;
import lk.ijse.gdse68.Crop.monitoring.system.exception.DataPersistFailException;
import lk.ijse.gdse68.Crop.monitoring.system.exception.NotFoundException;
import lk.ijse.gdse68.Crop.monitoring.system.service.FieldBo;
import lk.ijse.gdse68.Crop.monitoring.system.util.AppUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/field")
@RequiredArgsConstructor
public class FieldController {

    private final FieldBo fieldBo;

    private static final Logger logger = Logger.getLogger(FieldController.class.getName());

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> saveField(
            @RequestParam("fieldName") String fieldName,
            @RequestParam("fieldLocationX") int fieldLocationX,
            @RequestParam("fieldSize") double fieldSize,
            @RequestParam("image1") MultipartFile image1,
            @RequestParam("image2") MultipartFile image2,
            @RequestParam("fieldLocationY") int fieldLocationY
    ) {
        logger.info("y" + fieldLocationY + "x" + fieldLocationX);
        FieldDto fieldDTO = new FieldDto();
        fieldDTO.setFieldName(fieldName);
        fieldDTO.setFieldLocation(new Point(fieldLocationX, fieldLocationY));
        fieldDTO.setFieldSize(fieldSize);
        fieldDTO.setImage1(AppUtil.toBase64(image1));
        fieldDTO.setImage2(AppUtil.toBase64(image2));
        try {
            fieldBo.saveField(fieldDTO);
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
