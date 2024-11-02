package lk.ijse.gdse68.Crop.monitoring.system.Repository;

import lk.ijse.gdse68.Crop.monitoring.system.entity.Field;
import lk.ijse.gdse68.Crop.monitoring.system.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FieldDao extends JpaRepository<Field,String> {
}
