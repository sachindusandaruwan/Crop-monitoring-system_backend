package lk.ijse.gdse68.Crop.monitoring.system.service;

import lk.ijse.gdse68.Crop.monitoring.system.Repository.FieldDao;
import lk.ijse.gdse68.Crop.monitoring.system.Repository.StaffDao;
import lk.ijse.gdse68.Crop.monitoring.system.customObj.FieldErrorResponse;
import lk.ijse.gdse68.Crop.monitoring.system.customObj.FieldResponse;
import lk.ijse.gdse68.Crop.monitoring.system.dto.FieldDto;
import lk.ijse.gdse68.Crop.monitoring.system.entity.Field;
import lk.ijse.gdse68.Crop.monitoring.system.entity.Staff;
import lk.ijse.gdse68.Crop.monitoring.system.exception.DataPersistFailException;
import lk.ijse.gdse68.Crop.monitoring.system.exception.NotFoundException;
import lk.ijse.gdse68.Crop.monitoring.system.util.AppUtil;
import lk.ijse.gdse68.Crop.monitoring.system.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class FieldBoImpl implements FieldBo {
    private final FieldDao fieldDao;

    private final StaffDao staffDao;

    private final Mapping mapping;

    @Override
    public void saveField(FieldDto fieldDto) {
        fieldDto.setFieldCode(AppUtil.createFieldCode());
        Field save = fieldDao.save(mapping.convertFieldDtoToField(fieldDto));
        if (save == null){
            throw new DataPersistFailException("Field save failed");
        }
    }

//    @Override
//    public FieldResponse getField(String fieldCode) {
//        Optional<Field> field = fieldDao.findById(fieldCode);
//        if (field.isPresent()) {
//            return mapping.convertFieldToFieldDto(field.get());
//        }else {
//            return new FieldErrorResponse("Field not found", 404);
//        }
//
//    }

    @Override

    public FieldResponse getField(String fieldCode) {

        Optional<Field> field = fieldDao.findById(fieldCode);

        if (field.isPresent()) {

            FieldDto fieldDTO = mapping.convertFieldToFieldDto(field.get());

            List<String> staffIds = new ArrayList<>();

            field.get().getStaff().forEach(

                    staff -> staffIds.add(staff.getId())

            );

            fieldDTO.setStaffIds(staffIds);

            return fieldDTO;

        }else {

            return new FieldErrorResponse("Field not found", 404);

        }

    }



    @Override
    public List<FieldDto> getAllField() {
        return mapping.convertFieldListToFieldDtoList(fieldDao.findAll());
    }

    @Override
    public void deleteField(String fieldCode) {
        Optional<Field> field = fieldDao.findById(fieldCode);
        if (field.isPresent()) {
            fieldDao.deleteById(fieldCode);
        }else {
            throw new NotFoundException("Field not found");
        }
    }

    @Override
    public void updateField(FieldDto fieldDTO, List<String> staffIds) {
        Optional<Field> field = fieldDao.findById(fieldDTO.getFieldCode());
        if (field.isPresent()) {
            Field TempField1 = mapping.convertFieldDtoToField(fieldDTO);
            List<Staff> staff = new ArrayList<>();
            for (String staffId : staffIds) {
                Optional<Staff> optional = staffDao.findById(staffId);
                optional.ifPresent(staff::add);
            }
            TempField1.setStaff(staff);
            Field save = fieldDao.save(TempField1);
            if (save == null) {
                throw new DataPersistFailException("Field update failed");
            }
        }else {
            throw new NotFoundException("Field not found");
        }
    }


}
