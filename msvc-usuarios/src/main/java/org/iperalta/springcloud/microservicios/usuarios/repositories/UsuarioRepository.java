package org.iperalta.springcloud.microservicios.usuarios.repositories;

import org.iperalta.springcloud.microservicios.usuarios.models.entity.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/*
   Este "UsuarioRepository" es una implementación, aunque es una interfaz por debajo en tiempo
   de ejecución (run time) se creara un objeto y se implementaran los métodos del "CrudRepository"
   según el modelo (en este caso "Usurio").(Es la implementación que nos ofrece Spring del CRUD).
 */
/*
Por tanto "UsuarioRepository" se podria ya inyectar en algun service  con @Autowired
Y tampoco es necesario anotarla o registrar esta interfaz con algun @Componente. porque ya por defecto es un
componete manejado por Spring
 Por tanto "UsuarioRepository" se podria ya inyectar en algun service  con @Autowired
*/
public interface UsuarioRepository extends CrudRepository <Usuario, Long>{

    //Consulta personalizada
    Optional<Usuario>findByEmail(String email);

    //Ejemplo cansulta personalizada, con query el ?1 sera reemplazado u el "email" que llega como parametro
    @Query("select u from Usuario u where u.email=?1")
    Optional<Usuario> porEmail(String email);

    // Otra forma de hacerlo personaliza con palabras clave, devuelve solo true o false
    boolean existsByEmail(String email);
}
