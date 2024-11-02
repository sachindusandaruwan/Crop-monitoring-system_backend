package lk.ijse.gdse68.Crop.monitoring.system.util;

import java.util.UUID;

public class AppUtil {
    public static String createStaffID() {
        return "ST-" + UUID.randomUUID();
    }

    public static String createFieldCode(){
        return "F-" + UUID.randomUUID();
    }

}
