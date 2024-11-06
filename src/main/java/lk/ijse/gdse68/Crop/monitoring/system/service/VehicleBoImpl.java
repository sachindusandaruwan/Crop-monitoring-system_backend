package lk.ijse.gdse68.Crop.monitoring.system.service;

import lk.ijse.gdse68.Crop.monitoring.system.Repository.VehicleDao;
import lk.ijse.gdse68.Crop.monitoring.system.customObj.FieldErrorResponse;
import lk.ijse.gdse68.Crop.monitoring.system.customObj.VehicleErrorResponse;
import lk.ijse.gdse68.Crop.monitoring.system.customObj.VehicleResponse;
import lk.ijse.gdse68.Crop.monitoring.system.dto.VehicleDto;
import lk.ijse.gdse68.Crop.monitoring.system.entity.Field;
import lk.ijse.gdse68.Crop.monitoring.system.entity.Vehicle;
import lk.ijse.gdse68.Crop.monitoring.system.exception.AlreadyExistsException;
import lk.ijse.gdse68.Crop.monitoring.system.exception.DataPersistFailException;
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

public class VehicleBoImpl implements VehicleBo{
   private  final VehicleDao vehicleDao;
    private final Mapping mapping;

    @Override
    public void saveVehicle(VehicleDto vehicleDto) {
        String vehicleCode = AppUtil.createVehicleCode();
        vehicleDto.setVehicleCode(vehicleCode);
        if (vehicleDao.existsByLicensePlateNumber(vehicleDto.getLicensePlateNumber())){
            throw new AlreadyExistsException("vehicle plate number already used");
        }else {
              Vehicle save=vehicleDao.save(mapping.convertVehicleDtoToVehicle(vehicleDto));
            if (save == null){
                throw new DataPersistFailException("vehicle save failed");
            }
        }
    }



    @Override
    public List<VehicleDto> getAllVehicles() {
        return mapping.convertVehicleListToVehicleDTOList(vehicleDao.findAll());
    }

    @Override
    public VehicleResponse getVehicle(String vehicleCode) {
        Optional<Vehicle> vehicle = vehicleDao.findById(vehicleCode);
        if (vehicle.isPresent()){
            return mapping.convertVehicleToVehicleDto(vehicle.get());
        }else {
            return new VehicleErrorResponse(404,"vehicle not found");
        }
    }

    @Override
    public void deleteVehicle(String vehicleCode) {
        if (vehicleDao.existsById(vehicleCode)){
            vehicleDao.deleteById(vehicleCode);
        }else {
            throw new DataPersistFailException("vehicle delete failed");
        }
    }


}
