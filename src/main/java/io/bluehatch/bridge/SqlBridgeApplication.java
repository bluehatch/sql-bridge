package io.bluehatch.bridge;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SqlBridgeApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(SqlBridgeApplication.class);
		app.setBannerMode(Banner.Mode.CONSOLE);
		app.run(args);
	}

}
