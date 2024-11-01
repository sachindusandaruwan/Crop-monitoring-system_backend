package lk.ijse.gdse68.Crop.monitoring.system.service;

import lk.ijse.gdse68.Crop.monitoring.system.Repository.StaffDao;
import lk.ijse.gdse68.Crop.monitoring.system.customObj.StaffErrorResponse;
import lk.ijse.gdse68.Crop.monitoring.system.customObj.StaffResponse;
import lk.ijse.gdse68.Crop.monitoring.system.dto.StaffDto;
import lk.ijse.gdse68.Crop.monitoring.system.entity.Gender;
import lk.ijse.gdse68.Crop.monitoring.system.entity.Staff;
import lk.ijse.gdse68.Crop.monitoring.system.exception.DataPersistFailException;
import lk.ijse.gdse68.Crop.monitoring.system.exception.NotFoundException;
import lk.ijse.gdse68.Crop.monitoring.system.util.AppUtil;
import lk.ijse.gdse68.Crop.monitoring.system.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    @Override
    public StaffResponse getStaff(String id) {
        Optional<Staff> staff = staffDao.findById(id);
        if (staff.isPresent()){
            System.out.println(mapping.convertStaffToStaffDto(staff.get()));
            return mapping.convertStaffToStaffDto(staff.get());
        }else {
            return new StaffErrorResponse(404, "Staff not found");
        }
    }

    @Override
    public List<StaffDto> getAllStaff() {
        return mapping.convertStaffListToStaffDTOList(staffDao.findAll());
    }

    @Override
    public void updateStaff(StaffDto staffDto) {
        Optional<Staff> staff = staffDao.findById(staffDto.getId());
        if (staff.isPresent()){
            Staff save = staffDao.save(mapping.convertStaffDtoToStaff(staffDto));
            if (save == null){
                throw new DataPersistFailException("Staff update failed");
            }
        }else {
            throw new NotFoundException("Staff not found");
        }
    }


}
