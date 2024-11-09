package lk.ijse.gdse68.Crop.monitoring.system.service;

import lk.ijse.gdse68.Crop.monitoring.system.Repository.EquipmentDao;
import lk.ijse.gdse68.Crop.monitoring.system.customObj.EquipmentErrorResponse;
import lk.ijse.gdse68.Crop.monitoring.system.customObj.EquipmentResponse;
import lk.ijse.gdse68.Crop.monitoring.system.dto.EquipmentDto;
import lk.ijse.gdse68.Crop.monitoring.system.entity.Equipment;
import lk.ijse.gdse68.Crop.monitoring.system.exception.DataPersistFailException;
import lk.ijse.gdse68.Crop.monitoring.system.util.AppUtil;
import lk.ijse.gdse68.Crop.monitoring.system.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.yaml.snakeyaml.nodes.NodeId.mapping;

@Service
@Transactional
@RequiredArgsConstructor
public class EquipmentBoImpl implements EquipmentBo{

    private final EquipmentDao equipmentDao;
    private final Mapping mapping;

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
}
