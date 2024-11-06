package lk.ijse.gdse68.Crop.monitoring.system.Repository;

import lk.ijse.gdse68.Crop.monitoring.system.entity.User;
import lk.ijse.gdse68.Crop.monitoring.system.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleDao extends JpaRepository<Vehicle,String> {
    boolean existsByLicensePlateNumber(String licensePlateNumber);
}
