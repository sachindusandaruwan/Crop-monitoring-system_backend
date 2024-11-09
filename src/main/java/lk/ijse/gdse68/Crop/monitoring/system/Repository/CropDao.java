package lk.ijse.gdse68.Crop.monitoring.system.Repository;

import lk.ijse.gdse68.Crop.monitoring.system.entity.Crop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CropDao extends JpaRepository<Crop, String> {
}
