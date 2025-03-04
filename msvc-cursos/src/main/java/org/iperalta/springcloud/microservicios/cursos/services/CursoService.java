package org.iperalta.springcloud.microservicios.cursos.services;

import org.iperalta.springcloud.microservicios.cursos.models.Usuario;
import org.iperalta.springcloud.microservicios.cursos.models.entity.Curso;

import java.util.List;
import java.util.Optional;

public interface CursoService {
    List<Curso> listar();
    Optional<Curso> porId(Long id);
    Optional<Curso> porIdConUsuarios(Long id);
    Curso guardar(Curso curso);
    void eliminar(Long id);

    void eliminarCursoUsuarioPorId(Long id);

    /* METODOS REMOTOS API REST  SE COMUNICARAN CON EL OTRO MICROSERVICIO (msvc-usuarios): */

    // Al "Curso" con este id "cursoId" le asignamos un Usuario que ya existe en la base de datos:
    Optional<Usuario> asignarUsuario (Usuario usuario, Long cursoId);

    // Este Usuario es un Usuario que todavia no existe en el microservicio  msvc-usuarios
    // Entonces desde microservico  msvc-cursos enviamos un nuevo Usuario para que lo cree
    Optional<Usuario> crearUsuario(Usuario usuario, Long cursoId);

    //Desasignar usuario del Curso.
    Optional<Usuario> eliminarUsuario(Usuario usuario, Long cursoId);

}
