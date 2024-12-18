package lk.ijse.gdse68.Crop.monitoring.system.service;

import lk.ijse.gdse68.Crop.monitoring.system.Repository.CropDao;
import lk.ijse.gdse68.Crop.monitoring.system.Repository.FieldDao;
import lk.ijse.gdse68.Crop.monitoring.system.customObj.CropErrorResponse;
import lk.ijse.gdse68.Crop.monitoring.system.customObj.CropResponse;
import lk.ijse.gdse68.Crop.monitoring.system.dto.CropDto;
import lk.ijse.gdse68.Crop.monitoring.system.entity.Crop;
import lk.ijse.gdse68.Crop.monitoring.system.entity.Field;
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

    @Override
    public CropResponse findCrop(String cropCode) {
        Optional<Crop> ByCropCode=cropDao.findById(cropCode);
        if (ByCropCode.isPresent()){
            CropDto cropDto=mapping.convertCropToCropDto(ByCropCode.get());
            return cropDto;
        }else {
            return new CropErrorResponse(0,"Crop not found");
        }
    }

    @Override
    public void deleteCrop(String cropCode) {
        Optional<Crop> ByCropCode=cropDao.findById(cropCode);
        if (ByCropCode.isPresent()){
            cropDao.deleteById(cropCode);
        }else {
            throw new NotFoundException("Crop not found");
        }
    }

    @Override
    public List<CropDto> getAllCrop() {
        return mapping.convertCropListToCropDtoList(cropDao.findAll());
    }

    @Override
    public void updateCrop(CropDto cropDto, String fieldCode, String cropCode) {
        Optional<Crop> byCropCode=cropDao.findById(cropCode);
        if(byCropCode.isPresent()){
            Field field=fieldDao.findById(fieldCode).orElseThrow(
                    ()->new NotFoundException("Field not found")
            );
            byCropCode.get().setField(field);
            byCropCode.get().setCropCommonName(cropDto.getCropCommonName());
            byCropCode.get().setCategory(cropDto.getCategory());
            byCropCode.get().setCropSeason(cropDto.getCropSeason());
            byCropCode.get().setCropScientificName(cropDto.getCropScientificName());
            byCropCode.get().setCropImage(cropDto.getCropImage());

        }else {
            throw new NotFoundException("Crop not found");
        }
    }


}
