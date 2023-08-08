package edu.Sena.FoodQualitySoft.services;


import edu.Sena.FoodQualitySoft.entities.Empresa;
import edu.Sena.FoodQualitySoft.repositories.EmpresaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;



@SpringBootTest
public class EmpresaServiceTest {

    @InjectMocks
    public EmpresaService empresaService;

    @Mock
    public EmpresaRepository empresaRepository;



    @Test
    @DisplayName("Prueba del método getAllEmpresas")
    public void testGetAllEmpresas() {

        //1. Arrenge: Preparo las pruebas de entrada

        //Creo una lista de empresas simulada
        List<Empresa> empresaList = Arrays.asList(
                new Empresa(1L, "nit test", "Empresa Test 1: Empresa de prueba", "Calle", "674737626", "email@prueba.com", "categoria"),
                new Empresa(1L, "nit test 2", "Empresa Test 2: Empresa de prueba", "Calle2", "3453324", "email@prueba2.com", "categoria2")
        );

        // Simulamos los objetos relacionados con empresaRepository
        when(empresaRepository.findAll()).thenReturn(empresaList);

        //2. Act: Llamada al método a probar
        List<Empresa> result = empresaService.getAllEmpresas();

        //3. Assert: Realizar las comprobaciones
        assertNotNull(result);
        assertEquals(empresaList.size(), result.size());

        //4. Verify (opcional): verificar las llamadas
        verify(empresaRepository, times(1)).findAll();

    }

    @Test
    @DisplayName("Prueba del método getEmpresaById")
    public void testGetEmpresaById() {
        Long empresaId = 1L;

        Empresa empresaSimulada = new Empresa();
        empresaSimulada.setEmpresaId(1L);
        empresaSimulada.setNit("123456789");
        empresaSimulada.setNombreEmpresa("Empresa Test");
        empresaSimulada.setDireccion("Dirección de Prueba");
        empresaSimulada.setTelefono("1234567890");
        empresaSimulada.setEmail("test@example.com");
        empresaSimulada.setCategoriaAlimentos("Categoría Test");


        when(empresaRepository.findById(1L)).thenReturn(Optional.of(empresaSimulada));

        Empresa empresa = empresaService.getEmpresaById(empresaId);

    }

    @Test
    @DisplayName("Prueba del método testSaveOrUpdateEmpresa")
    public void testSaveOrUpdateEmpresa() {

        Empresa empresaSimulada = new Empresa();
        empresaSimulada.setEmpresaId(1L);
        empresaSimulada.setNit("123456789");
        empresaSimulada.setNombreEmpresa("Empresa Test");
        empresaSimulada.setDireccion("Dirección de Prueba");
        empresaSimulada.setTelefono("1234567890");
        empresaSimulada.setEmail("test@example.com");
        empresaSimulada.setCategoriaAlimentos("Categoría Test");

        when(empresaRepository.save(any(Empresa.class))).thenReturn(empresaSimulada);

        Empresa empresa = new Empresa();
        empresa.setNit("123456789");
        empresa.setNombreEmpresa("Empresa Test");
        empresa.setDireccion("Dirección de Prueba");
        empresa.setTelefono("1234567890");
        empresa.setEmail("test@example.com");
        empresa.setCategoriaAlimentos("Categoría Test");

        Empresa result = empresaService.saveOrUpdateEmpresa(empresa);
        assertEquals(empresaSimulada.getNit(), result.getNit());
        assertEquals(empresaSimulada.getNombreEmpresa(), result.getNombreEmpresa());
        assertEquals(empresaSimulada.getDireccion(), result.getDireccion());
        assertEquals(empresaSimulada.getTelefono(), result.getTelefono());
        assertEquals(empresaSimulada.getEmail(), result.getEmail());
        assertEquals(empresaSimulada.getCategoriaAlimentos(), result.getCategoriaAlimentos());

        assertNotNull(result);
        assertEquals(empresaSimulada.getEmpresaId(), result.getEmpresaId());

        verify(empresaRepository, times(1)).save(eq(empresa));

    }


    @Test
    @DisplayName("Prueba del método deleteEmpresaById")
    public void testDeleteEmpresaById() {

        Long empresaId = 1L;

        // Simulación de la eliminación de una empresa y verificación
        when(empresaRepository.findById(anyLong())).thenReturn(Optional.of(new Empresa()));
        empresaService.deleteEmpresaById(empresaId);

        // Verificación de que el método deleteById haya sido llamado
        verify(empresaRepository, times(1)).deleteById(eq(empresaId));
    }

}
