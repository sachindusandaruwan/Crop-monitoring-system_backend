package lk.ijse.gdse68.Crop.monitoring.system.controller;

import lk.ijse.gdse68.Crop.monitoring.system.Repository.CropDao;
import lk.ijse.gdse68.Crop.monitoring.system.dto.CropDto;
import lk.ijse.gdse68.Crop.monitoring.system.exception.DataPersistFailException;
import lk.ijse.gdse68.Crop.monitoring.system.exception.NotFoundException;
import lk.ijse.gdse68.Crop.monitoring.system.service.CropBo;
import lk.ijse.gdse68.Crop.monitoring.system.util.AppUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/crop")
@RequiredArgsConstructor
public class CropController {
    private final CropBo cropBo;
    private final CropDao cropDao;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    private ResponseEntity<?> saveCrop(
           @RequestParam("cropName") String cropName,
           @RequestParam("cropType") String cropCategory,
           @RequestParam("cropSeason") String cropSeason,
           @RequestParam("cropScientificName") String cropScientificName,
           @RequestParam("cropImage") MultipartFile cropImage,
           @RequestParam("fieldCode") String fieldCode

            ){
        CropDto cropDto = new CropDto();
        cropDto.setCropCommonName(cropName);
        cropDto.setCategory(cropCategory);
        cropDto.setCropSeason(cropSeason);
        cropDto.setCropScientificName(cropScientificName);
        cropDto.setCropImage(AppUtil.toBase64(cropImage));

        try{
            cropBo.saveCrop(cropDto,fieldCode);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (DataPersistFailException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{cropCode}")
    public ResponseEntity<?> findCrop(@PathVariable String cropCode){
        try{
            return new ResponseEntity<>(cropBo.findCrop(cropCode), HttpStatus.OK);

        }catch (NotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{cropCode}")
    public ResponseEntity<?> deleterop(@PathVariable String cropCode){
        try{
            cropBo.deleteCrop(cropCode);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (NotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
