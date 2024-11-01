package lk.ijse.gdse68.Crop.monitoring.system.service;

import lk.ijse.gdse68.Crop.monitoring.system.Repository.UserDao;
import lk.ijse.gdse68.Crop.monitoring.system.customObj.UserErrorResponse;
import lk.ijse.gdse68.Crop.monitoring.system.customObj.UserResponse;
import lk.ijse.gdse68.Crop.monitoring.system.dto.UserDto;
import lk.ijse.gdse68.Crop.monitoring.system.entity.User;
import lk.ijse.gdse68.Crop.monitoring.system.exception.AlreadyExistsException;
import lk.ijse.gdse68.Crop.monitoring.system.exception.DataPersistFailException;
import lk.ijse.gdse68.Crop.monitoring.system.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserBoImpl implements UserBo {

    @Autowired
    private  UserDao userDao;
    @Autowired
    private  Mapping mapping;

    @Override
    public void saveUser(UserDto userDto) {
        System.out.println("hiii mn awa");
            if (!userDao.existsByEmail(userDto.getEmail())) {
            var UserEntity = mapping.convertUserDtoToUser(userDto);
            var saveUser = userDao.save(UserEntity);
            if (saveUser == null) {
                throw new DataPersistFailException("Save user failed");
            }

            }else {
                throw new AlreadyExistsException("Email is already exists!!");
            }
        }

    @Override
    public UserResponse getUserByEmail(String email) {
        Optional<User> user = userDao.findByEmail(email);
        if (user.isPresent()) {
            return mapping.convertUserToUserDTO(user.get());
        }else {
            return new UserErrorResponse(0,"User not found");
        }
    }

}
