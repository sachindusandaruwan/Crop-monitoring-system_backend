package lk.ijse.gdse68.Crop.monitoring.system;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CropMonitoringSystemApplication {

	public static void main(String[] args) {
		System.out.println("enawadoooo");
		SpringApplication.run(CropMonitoringSystemApplication.class, args);

	}
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}


}
