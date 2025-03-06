package org.iperalta.springcloud.microservicios.usuarios.clientsHttp;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

// name lo copiamos del applications.properties de msvc-cursos
// para localhost usamos : url = "localhost:8002"
// Para comunicarse desde contenedor Docker msvc-usuarios a un micro servicio exterior msvc-cursos: host.docker.internal:8002
@FeignClient(name="msvc-cursos", url = "host.docker.internal:8002")
public interface CursoClienteRest {

    @DeleteMapping("/eliminar-curso-usuario/{id}") // ruta del endpoind en  controlador
    void eliminarCursoUsuarioPorId(@PathVariable Long id);
}