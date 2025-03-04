package org.iperalta.springcloud.microservicios.usuarios.controllers;

import jakarta.validation.Valid;
import org.iperalta.springcloud.microservicios.usuarios.models.entity.Usuario;
import org.iperalta.springcloud.microservicios.usuarios.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

// @RequestMapping("/api") //podemos colocar una ruta base
@RestController
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    // localhost:8001
    @GetMapping
    public List<Usuario> listar(){
        return service.listar();
    }

    //localhost:8001/3
    @GetMapping("/{id}")
    //@PathVariable  inyecta de forma automatica el valor que llega por la ruta
    //ResponseEntity <?> : puede ser de tipo Usuario pero si no esta presente es sin contenido
    public ResponseEntity<?> detalle (@PathVariable  Long id) {
        Optional<Usuario> usuarioOptional = service.porId(id);
        if(usuarioOptional.isPresent()){
          // return ResponseEntity.ok().body(usuarioOptional.get()) ;
            return ResponseEntity.ok(usuarioOptional.get()) ; //igual a anterior, significa http 200
        }
        return  ResponseEntity.notFound().build(); // no se encontro elobjero
    }

    // localhost:8001
    // con @RequestBody : los datos de usuario que lleguen poblaran la variable si coicien los nombres de atributos
    // Aqui tambien podriamos usar ResponseEntity<?> igual que el Get.
    //BindingResult: manejar resultado de validaciones
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // indica create hpp 201
    public ResponseEntity<?> crear (@Valid @RequestBody Usuario usuario, BindingResult result){
        if(result.hasErrors()){
            return validar(result);
        }
        // if(!usuario.getEmail().isEmpty() &&  service.porEmail(usuario.getEmail()).isPresent()){ //validar si email ya existe
        if(!usuario.getEmail().isEmpty() &&  service.existePorEmail(usuario.getEmail())){ // otra forma de hacerlo
            return  ResponseEntity.badRequest()
                    .body(Collections.singletonMap("mensaje","Ya existe un usuario con este email"));
        }
       return  ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(usuario));
       // return service.guardar(usuario); // retorna Usuario
    }

    // localhost:8001/3
    //BindingResult result  tiene que ir junto al Usuario

    @PutMapping("/{id}")
    public ResponseEntity<?> editar (@Valid  @RequestBody Usuario usuario, BindingResult result, @PathVariable Long id){
        if(result.hasErrors()){
            return validar(result);
        }
        Optional<Usuario> o = service.porId(id);
        if(o.isPresent()){
            Usuario usuarioDb = o.get();
            if(!usuario.getEmail().isEmpty() && !usuario.getEmail().equalsIgnoreCase(usuarioDb.getEmail()) && service.porEmail(usuario.getEmail()).isPresent()){ //validar si email ya existe
                return  ResponseEntity.badRequest()
                        .body(Collections.singletonMap("mensaje","Ya existe un usuario con este email"));
            }
            usuarioDb.setNombre(usuario.getNombre());
            usuarioDb.setEmail(usuario.getEmail());
            usuarioDb.setPassword(usuario.getPassword());
            return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(usuarioDb));
        }
        return ResponseEntity.notFound().build();
    }
    // localhost:8001/3

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Optional<Usuario> o = service.porId(id); // esto solo lo hacemos para validar que esxista.
        if (o.isPresent()){
            service.eliminar(id);
            return  ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/usuarios-por-curso")
    public  ResponseEntity<?> obtenerAlumnoPorCurso(@RequestParam List<Long> ids){
        return ResponseEntity.ok(service.listarPorIds(ids));
    }

    private static ResponseEntity<Map<String, String>> validar(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(err ->{
            errores.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }
}
