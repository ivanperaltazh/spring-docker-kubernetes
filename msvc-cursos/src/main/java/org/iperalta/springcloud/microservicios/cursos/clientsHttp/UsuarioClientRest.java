package org.iperalta.springcloud.microservicios.cursos.clientsHttp;

import org.iperalta.springcloud.microservicios.cursos.models.Usuario;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Aqui nombre del microservicio que vamos a consumir ("msvc-usuarios")
// Las anotaciones son iguales a las que se usan en el controllador GetMapping, etc.
// Y la ruta del microservicio podemos ver estos datos en el pom.xml
// Y esto ya se puede Inyectar ya es un bean de Spring.
@FeignClient(name="msvc-usuarios", url="localhost:8001")
public interface UsuarioClientRest {

    /* los Metodos deben tener una firma similar es decir:
    - El nombre del método: Es el identificador que usas para llamar al método.
    - La lista de tipos de parámetros en el orden en que aparecen: Esto incluye el tipo de dato de cada parámetro.
    */
    @GetMapping("/{id}")
    public Usuario detalle(@PathVariable Long id);

   /* Aqui no hace falta validar porque el cliente http lo que hace es solo consumir el servicio
    la validacion la hace el controlador respectivo, por defecto son public asi que se puede omitir el public*/
    @PostMapping("/")
    Usuario crear(@RequestBody Usuario usuario);

    /*Es casi igual a la implementacion en UsuarioController, en los parametros solo cambia
    List por Iterable porque en feing puede dar problemas */
    @GetMapping("/usuarios-por-curso")
    List<Usuario> obtenerAlumnosPorCurso(@RequestParam Iterable<Long> ids);

}
