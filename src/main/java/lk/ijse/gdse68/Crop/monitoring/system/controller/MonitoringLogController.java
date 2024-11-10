package lk.ijse.gdse68.Crop.monitoring.system.controller;

import lk.ijse.gdse68.Crop.monitoring.system.customObj.MonitoringLogResponse;
import lk.ijse.gdse68.Crop.monitoring.system.dto.MonitoringLogDto;
import lk.ijse.gdse68.Crop.monitoring.system.exception.DataPersistFailException;
import lk.ijse.gdse68.Crop.monitoring.system.exception.NotFoundException;
import lk.ijse.gdse68.Crop.monitoring.system.service.MonitoringLogBo;
import lk.ijse.gdse68.Crop.monitoring.system.util.AppUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/monitoringLog")
@RequiredArgsConstructor
@Slf4j
public class MonitoringLogController {
    private final MonitoringLogBo monitoringLogBo;

    @PostMapping
    public ResponseEntity<Void> saveMonitoringLog(
            @RequestParam("observation") String observation,
            @RequestParam("observedImage") MultipartFile observedImage,
            @RequestParam("fieldCodes") List<String> fieldCodes,
            @RequestParam("cropCodes") List<String> cropCodes,
            @RequestParam("staffIds") List<String> staffIds){

        try {
            String base64Image= AppUtil.toBase64(observedImage);
            MonitoringLogDto monitoringLogDto = new MonitoringLogDto();
            monitoringLogDto.setLogDate(new Date());
            monitoringLogDto.setObservation(observation);
            monitoringLogDto.setObservedImage(base64Image);
            monitoringLogDto.setFieldCodes(fieldCodes);
            monitoringLogDto.setCropCodes(cropCodes);
            monitoringLogDto.setStaffIds(staffIds);
            monitoringLogBo.saveMonitoringLog(monitoringLogDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersistFailException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }

    }
    @GetMapping("/{logCode}")
    public ResponseEntity<?> getMonitoringlogByLogCode(@PathVariable String logCode){
        try{
            MonitoringLogResponse getMonitoringLogbyLogCode=monitoringLogBo.getMonitorimgLogCodeByLogCode(logCode);
            return new ResponseEntity<>(getMonitoringLogbyLogCode,HttpStatus.OK);
        }catch (NotFoundException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
