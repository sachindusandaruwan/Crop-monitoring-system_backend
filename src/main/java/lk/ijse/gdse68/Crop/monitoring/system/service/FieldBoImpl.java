package lk.ijse.gdse68.Crop.monitoring.system.service;

import lk.ijse.gdse68.Crop.monitoring.system.Repository.FieldDao;
import lk.ijse.gdse68.Crop.monitoring.system.customObj.FieldErrorResponse;
import lk.ijse.gdse68.Crop.monitoring.system.customObj.FieldResponse;
import lk.ijse.gdse68.Crop.monitoring.system.dto.FieldDto;
import lk.ijse.gdse68.Crop.monitoring.system.entity.Field;
import lk.ijse.gdse68.Crop.monitoring.system.exception.DataPersistFailException;
import lk.ijse.gdse68.Crop.monitoring.system.util.AppUtil;
import lk.ijse.gdse68.Crop.monitoring.system.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class FieldBoImpl implements FieldBo {
    private final FieldDao fieldDao;

    private final Mapping mapping;

    @Override
    public void saveField(FieldDto fieldDto) {
        fieldDto.setFieldCode(AppUtil.createFieldCode());
        Field save = fieldDao.save(mapping.convertFieldDtoToField(fieldDto));
        if (save == null){
            throw new DataPersistFailException("Field save failed");
        }
    }

    @Override
    public FieldResponse getField(String fieldCode) {
        Optional<Field> field = fieldDao.findById(fieldCode);
        if (field.isPresent()) {
            return mapping.convertFieldToFieldDto(field.get());
        }else {
            return new FieldErrorResponse("Field not found", 404);
        }

    }
}
