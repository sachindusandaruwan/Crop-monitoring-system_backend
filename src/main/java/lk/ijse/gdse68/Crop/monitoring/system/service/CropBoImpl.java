package lk.ijse.gdse68.Crop.monitoring.system.service;

import lk.ijse.gdse68.Crop.monitoring.system.Repository.CropDao;
import lk.ijse.gdse68.Crop.monitoring.system.Repository.FieldDao;
import lk.ijse.gdse68.Crop.monitoring.system.dto.CropDto;
import lk.ijse.gdse68.Crop.monitoring.system.entity.Crop;
import lk.ijse.gdse68.Crop.monitoring.system.entity.Field;
import lk.ijse.gdse68.Crop.monitoring.system.exception.NotFoundException;
import lk.ijse.gdse68.Crop.monitoring.system.util.AppUtil;
import lk.ijse.gdse68.Crop.monitoring.system.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CropBoImpl implements CropBo{
    private final CropDao cropDao;
    private final Mapping mapping;
    private final FieldDao fieldDao;

    @Override
    public void saveCrop(CropDto cropDto, String fieldCode) {
        cropDto.setCropCode(AppUtil.generateCropCode());
        Crop crop=mapping.convertCropDtoToCrop(cropDto);
        Field field=fieldDao.findById(fieldCode).orElseThrow(
                ()->new NotFoundException("Field not found")
        );
        crop.setField(field);
        Crop save=cropDao.save(crop);
        if (save==null){
            throw new NotFoundException("Crop not saved");
        }
    }
}
