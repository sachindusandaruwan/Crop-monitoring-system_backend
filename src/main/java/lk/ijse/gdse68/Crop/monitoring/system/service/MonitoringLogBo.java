package lk.ijse.gdse68.Crop.monitoring.system.service;

import jdk.dynalink.linker.LinkerServices;
import lk.ijse.gdse68.Crop.monitoring.system.customObj.MonitoringLogResponse;
import lk.ijse.gdse68.Crop.monitoring.system.dto.MonitoringLogDto;

import java.util.List;

public interface MonitoringLogBo {
    void saveMonitoringLog(MonitoringLogDto monitoringLogDto);

    MonitoringLogResponse getMonitorimgLogCodeByLogCode(String logCode);

    List<MonitoringLogDto> getAllMonitoringLogs();

    void deleteMonitoringLog(String logCode);

    void updateMonitoringLog(MonitoringLogDto monitoringLogDto, String logCode);
}
