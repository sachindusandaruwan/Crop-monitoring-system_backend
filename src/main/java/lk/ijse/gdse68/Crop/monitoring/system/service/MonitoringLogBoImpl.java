package lk.ijse.gdse68.Crop.monitoring.system.service;

import lk.ijse.gdse68.Crop.monitoring.system.Repository.CropDao;
import lk.ijse.gdse68.Crop.monitoring.system.Repository.FieldDao;
import lk.ijse.gdse68.Crop.monitoring.system.Repository.MonitoringLogDao;
import lk.ijse.gdse68.Crop.monitoring.system.Repository.StaffDao;
import lk.ijse.gdse68.Crop.monitoring.system.dto.MonitoringLogDto;
import lk.ijse.gdse68.Crop.monitoring.system.entity.Crop;
import lk.ijse.gdse68.Crop.monitoring.system.entity.Field;
import lk.ijse.gdse68.Crop.monitoring.system.entity.MonitoringLog;
import lk.ijse.gdse68.Crop.monitoring.system.entity.Staff;
import lk.ijse.gdse68.Crop.monitoring.system.util.AppUtil;
import lk.ijse.gdse68.Crop.monitoring.system.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MonitoringLogBoImpl implements MonitoringLogBo {
    private final MonitoringLogDao monitoringLogDao;
    private final FieldDao fieldDao;
    private final StaffDao staffDao;
    private final CropDao cropDao;

    private final Mapping mapping;

    @Override
    public void saveMonitoringLog(MonitoringLogDto monitoringLogDto) {

        List<Field> field=new ArrayList<>();
        List<Crop> crops=new ArrayList<>();
        List<Staff> staff=new ArrayList<>();

        for(String fieldCode:monitoringLogDto.getFieldCodes()){
            fieldDao.findById(fieldCode).ifPresent(field::add);
    }
        for(String cropCode:monitoringLogDto.getCropCodes()){
            cropDao.findById(cropCode).ifPresent(crops::add);
        }
        for(String staffId:monitoringLogDto.getStaffIds()){
            staffDao.findById(staffId).ifPresent(staff::add);
        }
        String logCode= AppUtil.generateMonitoringLogCode();
        monitoringLogDto.setLogCode(logCode);
        MonitoringLog monitoringLog=mapping.convertMonitoringLogDtoToMonitoringLog(monitoringLogDto);
        monitoringLog.setLogDetails(monitoringLogDto.getObservation());
        monitoringLog.setField(field);
        monitoringLog.setCrop(crops);
        monitoringLog.setStaff(staff);
        MonitoringLog saveMonitoringLog=monitoringLogDao.save(monitoringLog);
        if(saveMonitoringLog==null){
            throw new RuntimeException("Failed to save the monitoring log");
        }
    }
}
