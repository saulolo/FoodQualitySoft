package edu.Sena.FoodQualitySoft.services;


import edu.Sena.FoodQualitySoft.entities.Empresa;
import edu.Sena.FoodQualitySoft.repositories.EmpresaApiExternaRepository;
import edu.Sena.FoodQualitySoft.repositories.EmpresaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service("API")
@ConditionalOnProperty(
        value = "empresas.estrategia",
        havingValue = "EN_OTRA_API")
public class EmpresaApiExternaService extends EmpresaService {

    private final EmpresaApiExternaRepository empresaApiExternaRepository;

    public EmpresaApiExternaService(EmpresaRepository empresaRepository, EmpresaApiExternaRepository empresaApiExternaRepository) {
        super(empresaRepository);
        this.empresaApiExternaRepository = empresaApiExternaRepository;
    }



    /* --VER TODAS LAS EMPRESAS "DUMMY" (API EXTERNA)-- */
    public List<Empresa> getEmpresas() {

        // Crear una instancia de RestTemplate para hacer solicitudes HTTP
        RestTemplate restTemplate = new RestTemplate();

        // Realizar una solicitud HTTP GET a la API externa para obtener la lista de empresas ficticias
        ResponseEntity<List<Empresa>> response = restTemplate
                .exchange("http://localhost:8080/FQS/api/v1/enterprises/fake-empresas",  // URL de la API externa (puede ser cualquier tipo de contenido como HTML, XML, Texto Plano, etc este caso es de tipo Json)
                        HttpMethod.GET,  // Método HTTP utilizado (GET en este caso)
                        null, // Cuerpo de la solicitud (en este caso, no se envía ningún cuerpo)
                        new ParameterizedTypeReference<List<Empresa>>() {  // Tipo de respuesta esperada (lista de Empresas) spring se encargara de deserializar ese array Json en una List de empresas y lo hara con la libreria Jackson
                        });

        // Extraer la lista de empresas ficticias de la respuesta HTTP
        List<Empresa> empresaList = response.getBody();

        // Devolver la lista de empresas ficticias obtenida de la API externa
        return empresaList;

    }



}
