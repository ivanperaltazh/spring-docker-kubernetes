package org.iperalta.springcloud.microservicios.cursos.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import org.iperalta.springcloud.microservicios.cursos.models.Usuario;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cursos")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String nombre;

    // En cascada guarda curso y usurios igual para eliminar, si eliminamos curso elimina usuarios registrados
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "curso_id") // Esta columna es la llave foranea con la que se unira y se creara en CursoUsuario
    private List<CursoUsuario> cursoUsuarios;

    // este campo no sera persistido en base de datos, se usara solo para poblar los datos de los usuarios
   // Este campo sera solo para poblar datos de la comunicación con el microservicio "msvc-usuarios"
    @Transient
    private List<Usuario> usuarios;

    public Curso() {
        cursoUsuarios = new ArrayList<>();
        usuarios = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void addCursoUsuario(CursoUsuario cursoUsuario){
        cursoUsuarios.add(cursoUsuario);
    }

    public void removeCursoUsuario(CursoUsuario cursoUsuario) {
        cursoUsuarios.remove(cursoUsuario);
        // aqui necesita comprar por lo que sobre escribirmos metodo equals en "CursoUsuario"
        // para enves de comparar por instancia compare por id
    }

    public List<CursoUsuario> getCursoUsuarios() {
        return cursoUsuarios;
    }

    public void setCursoUsuarios(List<CursoUsuario> cursoUsuarios) {
        this.cursoUsuarios = cursoUsuarios;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
