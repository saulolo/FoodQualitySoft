package edu.Sena.FoodQualitySoft.configurations;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "app")
public class ConfigurationsParameters {

    private String nombre;
    private String lenguaje;
    private String pais;
    private String author;







}
