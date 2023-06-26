package edu.Sena.FoodQualitySoft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;



@SpringBootApplication (exclude = {SecurityAutoConfiguration.class})
public class SoftwareControlCalidadApplication {

	@GetMapping("/holas")
	public String GetSaludar() {
		return "Hola mundo...Saldremos vivos de esta...sisisi";
	};



	public static void main(String[] args) {
		SpringApplication.run(SoftwareControlCalidadApplication.class, args);
	}

}
