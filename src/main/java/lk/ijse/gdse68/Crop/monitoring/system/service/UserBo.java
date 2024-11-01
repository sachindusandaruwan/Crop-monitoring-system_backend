package lk.ijse.gdse68.Crop.monitoring.system.service;

import jakarta.validation.Valid;
import lk.ijse.gdse68.Crop.monitoring.system.customObj.UserResponse;
import lk.ijse.gdse68.Crop.monitoring.system.dto.UserDto;
import lk.ijse.gdse68.Crop.monitoring.system.entity.User;

public interface UserBo {
    void saveUser(UserDto userDto);

    UserResponse getUserByEmail(String email);

    void updateUser(@Valid UserDto userDto);
}
