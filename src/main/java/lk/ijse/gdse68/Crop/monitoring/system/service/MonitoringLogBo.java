package lk.ijse.gdse68.Crop.monitoring.system.service;

import lk.ijse.gdse68.Crop.monitoring.system.customObj.MonitoringLogResponse;
import lk.ijse.gdse68.Crop.monitoring.system.dto.MonitoringLogDto;

public interface MonitoringLogBo {
    void saveMonitoringLog(MonitoringLogDto monitoringLogDto);

    MonitoringLogResponse getMonitorimgLogCodeByLogCode(String logCode);
}
