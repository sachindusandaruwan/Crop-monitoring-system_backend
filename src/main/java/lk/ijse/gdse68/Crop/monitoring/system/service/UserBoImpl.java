package lk.ijse.gdse68.Crop.monitoring.system.service;

import lk.ijse.gdse68.Crop.monitoring.system.Repository.UserDao;
import lk.ijse.gdse68.Crop.monitoring.system.dto.UserDto;
import lk.ijse.gdse68.Crop.monitoring.system.entity.User;
import lk.ijse.gdse68.Crop.monitoring.system.exception.AlreadyExistsException;
import lk.ijse.gdse68.Crop.monitoring.system.exception.DataPersistFailException;
import lk.ijse.gdse68.Crop.monitoring.system.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}