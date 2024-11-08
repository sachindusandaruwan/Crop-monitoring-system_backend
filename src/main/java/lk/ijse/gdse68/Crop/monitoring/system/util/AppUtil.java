package lk.ijse.gdse68.Crop.monitoring.system.util;

import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.UUID;

public class AppUtil {
    public static String createStaffID() {
        return "ST-" + UUID.randomUUID();
    }

    public static String createFieldCode(){
        return "F-" + UUID.randomUUID();
    }

    public static String toBase64(MultipartFile profilePic){
        String proPicBase64 = null;
        try {
            byte [] proPicBytes = profilePic.getBytes();
            proPicBase64 =  Base64.getEncoder().encodeToString(proPicBytes);
        }catch (Exception e){
            e.printStackTrace();
        }
        return proPicBase64;
    }


    public static String createVehicleCode() {

            return "V-" + UUID.randomUUID();
    }

    public static String createEquipmentCode(){
        return "EQ-" + UUID.randomUUID();
    }
}
