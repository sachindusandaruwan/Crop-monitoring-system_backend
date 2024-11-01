package lk.ijse.gdse68.Crop.monitoring.system.util;

import lk.ijse.gdse68.Crop.monitoring.system.customObj.StaffResponse;
import lk.ijse.gdse68.Crop.monitoring.system.customObj.UserResponse;
import lk.ijse.gdse68.Crop.monitoring.system.dto.StaffDto;
import lk.ijse.gdse68.Crop.monitoring.system.dto.UserDto;
import lk.ijse.gdse68.Crop.monitoring.system.entity.Staff;
import lk.ijse.gdse68.Crop.monitoring.system.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapping {
    @Autowired
    private ModelMapper modelMapper;

    public User convertUserDtoToUser(UserDto userDto) {

        return modelMapper.map(userDto, User.class);
    }

    public UserDto convertUserToUserDTO(User user) {
        return modelMapper.map(user, UserDto.class);
    }


    public Staff convertStaffDtoToStaff(StaffDto staffDto) {
        return modelMapper.map(staffDto, Staff.class);
    }

    public StaffDto convertStaffToStaffDto(Staff staff) {
        return modelMapper.map(staff, StaffDto.class);
    }

    public List<StaffDto> convertStaffListToStaffDTOList(List<Staff> all) {
        return modelMapper.map(all, List.class);
    }


}
