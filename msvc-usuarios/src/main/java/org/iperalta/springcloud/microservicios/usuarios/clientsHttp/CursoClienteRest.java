package org.iperalta.springcloud.microservicios.usuarios.clientsHttp;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="msvc-cursos", url = "localhost:8002") // name lo copiamos del applications.properties de msvc-cursos
public interface CursoClienteRest {

    @DeleteMapping("/eliminar-curso-usuario/{id}") // ruta del endpoind en  controlador
    void eliminarCursoUsuarioPorId(@PathVariable Long id);
}
