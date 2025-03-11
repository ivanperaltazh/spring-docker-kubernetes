package org.iperalta.springcloud.microservicios.usuarios.clientsHttp;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

// name lo copiamos del applications.properties de msvc-cursos
// para localhost usamos : url = "localhost:8002"
// Para comunicarse desde contenedor Docker msvc-usuarios a un micro servicio exterior msvc-cursos: host.docker.internal:8002
// @FeignClient(name="msvc-cursos", url = "localhost:8002")
// @FeignClient(name="msvc-cursos", url = "host.docker.internal:8002")
@FeignClient(name="msvc-cursos", url = "msvc-cursos:8002") //"msvc-cursos" corresponde al --name en: docker run -p 8002:8002 - -rm -d - -name msvc-cursos  cursos:v2 // es bueno tambien que coincida con el nombre del micro-servicio en el "application.propertie"
public interface CursoClienteRest {

    @DeleteMapping("/eliminar-curso-usuario/{id}") // ruta del endpoind en  controlador
    void eliminarCursoUsuarioPorId(@PathVariable Long id);
}