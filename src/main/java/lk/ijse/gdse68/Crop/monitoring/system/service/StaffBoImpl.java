package lk.ijse.gdse68.Crop.monitoring.system.service;

import lk.ijse.gdse68.Crop.monitoring.system.Repository.StaffDao;
import lk.ijse.gdse68.Crop.monitoring.system.dto.StaffDto;
import lk.ijse.gdse68.Crop.monitoring.system.entity.Staff;
import lk.ijse.gdse68.Crop.monitoring.system.exception.DataPersistFailException;
import lk.ijse.gdse68.Crop.monitoring.system.util.AppUtil;
import lk.ijse.gdse68.Crop.monitoring.system.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class StaffBoImpl implements StaffBo {
    private final StaffDao staffDao;

    private final Mapping mapping;

    @Override
    public void saveStaff(StaffDto staffDto) {
        String staffID = AppUtil.createStaffID();
        while (staffDao.existsById(staffID)) {
            staffID = AppUtil.createStaffID();
        }
        staffDto.setId(staffID);
        Staff save = staffDao.save(mapping.convertStaffDtoToStaff(staffDto));
        if (save == null){
            throw new DataPersistFailException("Staff save failed");
        }

    }
}
