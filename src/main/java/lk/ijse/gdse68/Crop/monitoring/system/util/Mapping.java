package lk.ijse.gdse68.Crop.monitoring.system.util;

import lk.ijse.gdse68.Crop.monitoring.system.customObj.*;
import lk.ijse.gdse68.Crop.monitoring.system.dto.*;
import lk.ijse.gdse68.Crop.monitoring.system.entity.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapping {
    @Autowired
    private ModelMapper modelMapper;



    public User convertUserDtoToUser(UserDto userDto) {

        return modelMapper.map(userDto, User.class);
    }

    public UserDto convertUserToUserDTO(User user) {
        return modelMapper.map(user, UserDto.class);
    }


    public Staff convertStaffDtoToStaff(StaffDto staffDto) {
        return modelMapper.map(staffDto, Staff.class);
    }

    public StaffDto convertStaffToStaffDto(Staff staff) {
        return modelMapper.map(staff, StaffDto.class);
    }

    public List<StaffDto> convertStaffListToStaffDTOList(List<Staff> all) {
        return modelMapper.map(all, List.class);
    }


    public Field convertFieldDtoToField(FieldDto fieldDto) {
        return modelMapper.map(fieldDto, Field.class);
    }

    public FieldDto convertFieldToFieldDto(Field field) {
        return modelMapper.map(field, FieldDto.class);
    }

    public List<FieldDto> convertFieldListToFieldDtoList(List<Field> all) {
        return modelMapper.map(all, List.class);
    }

    public Vehicle convertVehicleDtoToVehicle(VehicleDto vehicleDto){
        return modelMapper.map(vehicleDto, Vehicle.class);
    }


    public List<VehicleDto> convertVehicleListToVehicleDTOList(List<Vehicle> all) {
        return modelMapper.map(all, List.class);
    }


    public VehicleDto convertVehicleToVehicleDto(Vehicle vehicle) {
        return modelMapper.map(vehicle, VehicleDto.class);


    }

    public Equipment convertEquipmentDtoToEquipment(EquipmentDto equipmentDto) {
        return modelMapper.map(equipmentDto, Equipment.class);
    }

    public EquipmentDto convertEquipmentToEquipmentDto(Equipment equipment) {
        return modelMapper.map(equipment, EquipmentDto.class);
    }

    public List<EquipmentDto> convertEquipmentListToEquipmentDtoList(List<Equipment> all) {
        return modelMapper.map(all, List.class);
    }

    public Crop convertCropDtoToCrop(CropDto cropDto) {
        return modelMapper.map(cropDto, Crop.class);
    }

    public CropDto convertCropToCropDto(Crop crop) {
        return modelMapper.map(crop, CropDto.class);
    }


    public List<CropDto> convertCropListToCropDtoList(List<Crop> all) {
        return modelMapper.map(all, List.class);
    }

    public MonitoringLog convertMonitoringLogDtoToMonitoringLog(MonitoringLogDto monitoringLogDto) {
        return modelMapper.map(monitoringLogDto, MonitoringLog.class);
    }



    public MonitoringLogDto convertCropDetailsToCropDetailsDTO(MonitoringLog monitoringLog) {
        return modelMapper.map(monitoringLog, MonitoringLogDto.class);
    }

    public List<MonitoringLogDto> convertMonitoringLogListToMonitoringLogDtoList(List<MonitoringLog> allMonitoringLogs) {
    return modelMapper.map(allMonitoringLogs, List.class);
    }
}
