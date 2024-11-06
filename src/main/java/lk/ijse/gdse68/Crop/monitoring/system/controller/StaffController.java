package lk.ijse.gdse68.Crop.monitoring.system.controller;

import jakarta.validation.Valid;
import lk.ijse.gdse68.Crop.monitoring.system.customObj.StaffResponse;
import lk.ijse.gdse68.Crop.monitoring.system.dto.StaffDto;
import lk.ijse.gdse68.Crop.monitoring.system.exception.DataPersistFailException;
import lk.ijse.gdse68.Crop.monitoring.system.exception.NotFoundException;
import lk.ijse.gdse68.Crop.monitoring.system.service.StaffBo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/staff")
@RequiredArgsConstructor
public class StaffController {

    private final StaffBo staffBo;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveStaff(@Valid @RequestBody StaffDto staffDto){
        try {
            System.out.println("StaffDto: " + staffDto);
            staffBo.saveStaff(staffDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersistFailException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<StaffResponse> getStaff( @PathVariable String id){
        try {
            return new ResponseEntity<>(staffBo.getStaff(id), HttpStatus.OK);
        }catch (DataPersistFailException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "allstaff", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StaffDto> getAllStaff(){
        return staffBo.getAllStaff();
    }

//    @PatchMapping
//    public ResponseEntity<?> updateStaff(@Valid @RequestBody StaffDto staffDto){
//        try {
//            staffBo.updateStaff(staffDto);
//            return new ResponseEntity<>(HttpStatus.OK);
//        }catch (NotFoundException e){
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//        catch (DataPersistFailException e){
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//    }

    @PatchMapping(value ="/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateStaff(@PathVariable String id, @Valid @RequestBody StaffDto staffDto) {
        try {
            staffBo.updateStaff(id,staffDto);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (DataPersistFailException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value ="/{id}" )
    public ResponseEntity<Void> deleteStaff(@PathVariable ("id") String id) {
        try {
            staffBo.deleteStaff(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);


        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
