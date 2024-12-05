package lk.ijse.gdse68.Crop.monitoring.system.controller;

import jakarta.validation.Valid;
import lk.ijse.gdse68.Crop.monitoring.system.dto.UserDto;
import lk.ijse.gdse68.Crop.monitoring.system.exception.DataPersistFailException;
import lk.ijse.gdse68.Crop.monitoring.system.exception.NotFoundException;
import lk.ijse.gdse68.Crop.monitoring.system.service.UserBo;
import lk.ijse.gdse68.Crop.monitoring.system.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.yaml.snakeyaml.nodes.NodeId.mapping;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@CrossOrigin
public class UserController {

    private final UserBo userBo;


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveUser(@Valid @RequestBody UserDto userDto) {
        if (userDto == null) {
            return new  ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            try {
                userBo.saveUser(userDto);
                return new ResponseEntity<>(HttpStatus.CREATED);
            }catch (DataPersistFailException e){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }catch (RuntimeException e){
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

            }
        }
    }

    @GetMapping("/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email) {
        return new ResponseEntity<>(userBo.getUserByEmail(email), HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<?> updateUser(@Valid @RequestBody UserDto userDto) {
        try {
            userBo.updateUser(userDto);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (NotFoundException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
