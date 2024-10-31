package lk.ijse.gdse68.Crop.monitoring.system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "crops")
public class Crop {
    @Id
    @Column(name = "crop_code")
    private String cropCode;
    @Column(name = "crop_common_name")
    private String cropCommonName;
    @Column(name = "crop_scientific_name")
    private String cropScientificName;
    @Column(name = "crop_image", columnDefinition = "LONGTEXT")
    private String cropImage;
    @Column(name = "category")
    private String category;
    @Column(name = "crop_season")
    private String cropSeason;

}