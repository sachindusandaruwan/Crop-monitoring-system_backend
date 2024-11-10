package lk.ijse.gdse68.Crop.monitoring.system.Repository;

import lk.ijse.gdse68.Crop.monitoring.system.dto.MonitoringLogDto;
import lk.ijse.gdse68.Crop.monitoring.system.entity.MonitoringLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonitoringLogDao extends JpaRepository<MonitoringLog,String> {
}
