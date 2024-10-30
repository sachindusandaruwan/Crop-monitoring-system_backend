package lk.ijse.gdse68.Crop.monitoring.system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "vehicle")
public class Vehicle {
    @Id
    @Column(name = "vehicle_code")
    private String vehicleCode;
    @Column(name = "licence_plate_number")
    private String licensePlateNumber;
    @Column(name = "vehicle_category")
    private String vehicleCategory;
    @Column(name = "fuel_type")
    private String fuelType;
    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "staff_member_id")
//    @Column(name = "allocated_staff_members")
    private Staff allocatedStaffMemberDetails;
    @Column(name = "remarks")
    private String remarks;
}
