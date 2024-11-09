package lk.ijse.gdse68.Crop.monitoring.system.service;

import lk.ijse.gdse68.Crop.monitoring.system.Repository.EquipmentDao;
import lk.ijse.gdse68.Crop.monitoring.system.Repository.FieldDao;
import lk.ijse.gdse68.Crop.monitoring.system.Repository.StaffDao;
import lk.ijse.gdse68.Crop.monitoring.system.customObj.EquipmentErrorResponse;
import lk.ijse.gdse68.Crop.monitoring.system.customObj.EquipmentResponse;
import lk.ijse.gdse68.Crop.monitoring.system.dto.EquipmentDto;
import lk.ijse.gdse68.Crop.monitoring.system.entity.Equipment;
import lk.ijse.gdse68.Crop.monitoring.system.entity.Field;
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

import static org.yaml.snakeyaml.nodes.NodeId.mapping;

@Service
@Transactional
@RequiredArgsConstructor
public class EquipmentBoImpl implements EquipmentBo{

    private final EquipmentDao equipmentDao;
    private final Mapping mapping;
    private final StaffDao staffDao;
    private  final FieldDao fieldDao;


    @Override
    public void saveEquipment(EquipmentDto equipmentDto) {
        equipmentDto.setEquipmentId(AppUtil.createEquipmentCode());
        Equipment save = equipmentDao.save(mapping.convertEquipmentDtoToEquipment(equipmentDto));
        if (save == null) {
            throw new DataPersistFailException("Equipment not saved");
        }
    }

    @Override
    public EquipmentResponse getEquipment(String equipmentCode) {
        Optional<Equipment> equipment = equipmentDao.findById(equipmentCode);
        if (equipment.isPresent()) {
            return mapping.convertEquipmentToEquipmentDto(equipment.get());
        }
        return new EquipmentErrorResponse(404,"Equipment not found");
    }

    @Override
    public List<EquipmentDto> getAllEquipment() {
        return mapping.convertEquipmentListToEquipmentDtoList(equipmentDao.findAll());
    }

    @Override
    public void deleteEquipment(String equipmentCode) {
        Optional<Equipment> equipment=equipmentDao.findById(equipmentCode);
        if (equipment.isPresent()){
            equipmentDao.deleteById(equipmentCode);
        }else {
            throw new NotFoundException("Equipment not found");
        }
    }

    @Override
    public void updateEquipment(EquipmentDto equipmentDto, String staffId, String fieldCode, String equipmentCode) {
        Equipment equipment=equipmentDao.findById(equipmentCode).orElse(null);
        if(equipment != null){
             equipment=mapping.convertEquipmentDtoToEquipment(equipmentDto);
                equipment.setEquipmentId(equipmentCode);
                if (staffId.equals("N/A")) {
                    equipment.setStaff(null);
                }else {
                    Optional<Staff> optional=staffDao.findById(staffId);
                    if (optional.isPresent()) {
                       Staff staff=optional.get();
                       equipment.setStaff(staff);
                    }else {
                        throw new NotFoundException("Staff not found");
                    }
                }
                if (fieldCode.equals("N/A")) {
                    equipment.setField(null);
                }else {
                    Optional<Field> optional=fieldDao.findById(fieldCode);
                    if (optional.isPresent()) {
                        Field field=optional.get();
                        equipment.setField(field);
                    }else {
                        throw new NotFoundException("Field not found");
                    }
                }
        }
        if (equipment != null) {
            Equipment save = equipmentDao.save(equipment);
            if (save == null) {
                throw new DataPersistFailException("Equipment update failed");
            }
            }else {
                throw new NotFoundException("Equipment not found");
            }

    }
}
