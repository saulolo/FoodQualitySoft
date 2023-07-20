package edu.Sena.FoodQualitySoft.controllers;

import edu.Sena.FoodQualitySoft.dto.ResponseDTO;
import edu.Sena.FoodQualitySoft.entities.Empresa;
import edu.Sena.FoodQualitySoft.exceptions.ResourceNotFoundException;
import edu.Sena.FoodQualitySoft.services.EmpresaService;
import edu.Sena.FoodQualitySoft.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class EmpresaController {

    private final EmpresaService empresaService;


    /* --CONTROLADOR PARA VER TODAS LAS EMPRESAS-- */
    @GetMapping("/enterprises")
    public List<Empresa> verEmpresas() {
        return empresaService.getAllEmpresas();
    }


    /* --CONTROLADOR PARA VER TODAS LAS EMPRESAS (Método 2 Utilizando ResponseDTO)-- */
    @GetMapping("/enterprisesResponseDTO")
    public ResponseEntity<Object> verEmpresasResponseDTO() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(empresaService.getAllEmpresasDTO());
        } catch (Exception err) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO(HttpStatus.BAD_REQUEST, err.getMessage(), null));
        }
    }


    /* --CONTROLADOR PARA VER TODAS LAS EMPRESAS (Método 3 Utilizando ResponseEntity)-- */
    //ResponseEntity permite construir respuestas HTTP personalizadas en una aplicación web.
    @GetMapping("/enterprisesResEntity")
    public ResponseEntity<?> verEmpresasResponseEntity() {  //?(wildcard) permite construir respuestas HTTP personalizadas en una aplicación web.
        return ResponseEntity.ok(empresaService.getAllEmpresas());
    }


    /* --CONTROLADOR PARA VER EMPRESAS POR ID-- */
    @GetMapping("/enterprises/{id}")
    public Empresa verEmpresaById(@PathVariable Long id) {
        return empresaService.getEmpresaById(id);
    }


    /* --CONTROLADOR PARA VER EMPRESAS POR ID (Método 2 [Profesional] Utilizando ResponseEntity con manejo de lambdas{Libro})-- */
    @GetMapping("/enterprisesResEntity/{id}")
    public ResponseEntity<Empresa> verEmpresasResponseEntityById(@PathVariable Long id) {
        // Obtener la lista de todas las empresas
        List<Empresa> empresas = empresaService.getAllEmpresas();

        // Iterar sobre cada empresa en la lista utilizando lambdas con el metodo stream
        //se convierte la lista empresas a tipo stream, para darnos poibilidad de ejecutar algunos métodos útiles
        return empresas.stream()
                //en este caso utilizamos filter que es el que necesitamos para realizar un filtrado por ID
                // Comprobar si el id de la empresa coincide con el id proporcionado
                .filter(emp -> emp.getEmpresaId().equals(id))
                //En segundo termino se ejecuta el método findFirst que retornará un opcional de ID
                //en este caso la empresa fue encontrada y por ello retornamos el codigo
                .findFirst()
                //El método map, transformará el Optional<Id> en un Optional<ResponseEntity<Cliente>> y retornará este objeto directamente
                //200 ok con la empresa en el body de respuesta
                .map(ResponseEntity::ok)
                // Si no se encuentra ninguna empresa con el id proporcionado, lanzar una excepción
                // ResourceNotFoundException que será manejada por spring en algún lugar superior para devolver una respuesta de error (NOT FOUND)
                .orElseThrow(() -> new ResourceNotFoundException(Constants.COMPANY + Constants.SPACE_SEPARATOR + id + Constants.SPACE_SEPARATOR + Constants.NOT_FOUND));
    }


    /* --CONTROLADOR PARA VER EMPRESAS POR ID (Método 3 Utilizando el Optional)-- */
    @GetMapping("/enterprisesOptional/{id}")
    public Optional<Empresa> verEmpresaByIdOpt(@PathVariable Long id) {
        return empresaService.getEmpresaByIdOpt(id);
    }


    /* --CONTROLADOR PARA VER  EMPRESAS POR ID (MÉTODO 4)-- */
    //Metodo opcional # 4 para ver empresas por Id usando el ciclo for each
/*    @GetMapping("/enterprises/{id}")
    public Empresa verEmpresaByIdForEach(@PathVariable Long id) {
        List<Empresa> empresaList = empresaService.getAllEmpresas();
        Empresa empresaEncontrada = null;

        for (Empresa empresa : empresaList) {
            if (empresa.getEmpresaId().equals(id)) {
                empresaEncontrada = empresa;
                break;
            }
        }
        return empresaEncontrada;
    }*/


    /* --CONTROLADOR PARA VER EMPRESAS POR ID (MÉTODO 5)-- */
    //Metodo opcional # 3 para ver empresas por Id usando streams con funciones lamda
    /*
    @GetMapping("/enterprises/{id}")
    public Empresa verEmpresaByIdUsandoStream(@PathVariable Long id) {
        List<Empresa> empresaList = empresaService.getAllEmpresas();
        return empresaList.stream()
                .filter(empresa -> empresa.getEmpresaId().equals(id))
                .findFirst()
                .orElse(null);
    }
*/


    /* --CONTROLADOR PARA CREAR UNA EMPRESA-- */
    @PostMapping("/enterprises")
    public Empresa crearEmpresa(@RequestBody Empresa empresa) {
        return empresaService.saveOrUpdateEmpresa(empresa);
    }
    //Puedo utlizar un post tambien para actualizar y cuando le ponga el id de la empresa en el postman
    //él reconoce que se va a actulizar esa empresa y no ha crearla, pero si quiere decirle especificamente
    // que actualizar, lo hago con un Patch, y le seteo los atributos que quiero actualizar


    /* --CONTROLADOR PARA CREAR UNA EMPRESA (MÉTODO 2)-- */
    //Metodo opcional # 2 para crear empresas usando el método Optional
/*    @PostMapping("/enterprises")
    public Optional<Empresa> crearEmpresaUsandoOptional(@RequestBody Empresa empresa) {
        return Optional.ofNullable(empresaService.saveOrUpdateEmpresa(empresa));
    }*/


    /* --CONTROLADOR PARA CREAR UNA EMPRESA (MÉTODO 3)-- */
    //Metodo opcional # 3 para crear empresas usando el método add(solo para crear en memoria)
/*    @PostMapping("/enterprises")
    public Empresa crearEmpresaUsandoAdd(@RequestBody Empresa empresa) {
        List<Empresa> listaEmpresas = new ArrayList<>();
        listaEmpresas.add(empresa);
        return empresa;
    }*/


    /* --CONTROLADOR PARA ACTUALIZAR UNA EMPRESA-- */
    @PatchMapping("/enterprises/{id}")
    public Empresa actualizarEmpresa(@PathVariable Long id, @RequestBody Empresa empresa) {
        Empresa emp = empresaService.getEmpresaById(id);
        emp.setNit(empresa.getNit()); //Si le seteo el Id, me creara una nueva empresa, por eso no lo traigo
        emp.setNombreEmpresa(empresa.getNombreEmpresa());
        emp.setDireccion(empresa.getDireccion());
        emp.setTelefono(empresa.getTelefono());
        emp.setEmail(empresa.getEmail());
        emp.setCategoriaAlimentos(empresa.getCategoriaAlimentos());
        return empresaService.saveOrUpdateEmpresa(emp);
    }


    /* --CONTROLADOR PARA ACTUALIZAR UNA EMPRESA (MÉTODO 2 Utilizando Stream)-- */
    //Se difiere del Path en que PUT se utiliza para modoficar el objeto completo, con pacth es cambios parciales
    @PutMapping("/enterprises")
    public Empresa actualizarEmpresa(@RequestBody Empresa empresa) {

        // Buscar la empresa existente con el mismo ID en la lista de empresas
        Empresa empresaEncontrada = verEmpresas().stream()
                .filter(emp -> emp.getEmpresaId().equals(empresa.getEmpresaId())) // Filtrar por ID de empresa
                .findFirst() // Obtener la primera empresa que cumpla el filtro
                .orElseThrow(); // Lanzar una excepción si no se encuentra ninguna empresa

        // Actualizar los campos de la empresa encontrada con los nuevos valores proporcionados
        empresaEncontrada.setNit(empresa.getNit());
        empresaEncontrada.setNombreEmpresa(empresa.getNombreEmpresa());
        empresaEncontrada.setDireccion(empresa.getDireccion());
        empresaEncontrada.setTelefono(empresa.getTelefono());
        empresaEncontrada.setEmail(empresa.getEmail());
        empresaEncontrada.setCategoriaAlimentos(empresa.getCategoriaAlimentos());

        // Devolver la empresa actualizada como respuesta de la solicitud
        return empresaEncontrada;
    }




    /* --CONTROLADOR PARA ELIMINAR EMPRESA POR ID-- */
    //En caso de que me devuelva un string
/*    @DeleteMapping("/enterprises/{id}")
    public String eliminarEmpresa(@PathVariable Long id) {
        boolean respuesta = empresaService.deleteEmpresaById(id);
        if (respuesta) {
            return "Se elimino la empresa con id " + id;
        }
        else {
            return "No se pudo eliminar la empresa con el id " + id;
        }
    }*/


    /* --CONTROLADOR PARA ELIMINAR EMPRESA POR ID (MÉTODO 2)-- */
    //Metodo eliminar en caso de que me retorne un Boolean
/*    @DeleteMapping("/enterprises/{id}")
    public boolean eliminarEmpresa(@PathVariable Long id) {
        Empresa emp = empresaService.getEmpresaById(id);
        return  empresaService.deleteEmpresaById(id);
    }*/


    /* --CONTROLADOR PARA ELIMINAR EMPRESA POR ID (MÉTODO 3 [Profesional] Usando Stream)-- */
    @DeleteMapping("/enterprises/{id}")
    public ResponseEntity<Empresa> eliminarEmpresa(@PathVariable Long id) {
        List<Empresa> empresaList = empresaService.getAllEmpresas();

        // Utilizar Stream y lambdas para buscar la empresa por su ID
        // y lanzar una excepción si no se encuentra
        Empresa empresaEncontrada = empresaList.stream()
                .filter(emp -> emp.getEmpresaId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException(Constants.COMPANY + Constants.SPACE_SEPARATOR + id + Constants.SPACE_SEPARATOR + Constants.NOT_FOUND));

        // Si se encuentra una empresa con el ID proporcionado,
        // procedemos a eliminarla utilizando el método deleteEmpresaById
        empresaService.deleteEmpresaById(id);

        // Devolver una respuesta exitosa con la empresa eliminada
        return ResponseEntity.ok(empresaEncontrada);
    }
















}






