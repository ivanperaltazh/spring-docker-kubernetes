package org.iperalta.springcloud.microservicios.usuarios.services;

import org.iperalta.springcloud.microservicios.usuarios.models.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    List<Usuario> listar();
    Optional<Usuario> porId(Long id);  // Optional Evita null pointer exception
    Usuario guardar(Usuario usuario); // Sive para guardar o editar segun haya o no id
    void eliminar(Long id);

    //Aqui Usaremos un metodo del CrudRepository: Iterable<T> findAllById(Iterable<ID> ids); Iterable = List
    List<Usuario> listarPorIds(Iterable <Long> ids);

    Optional<Usuario> porEmail(String email);
    boolean existePorEmail(String email);
}
