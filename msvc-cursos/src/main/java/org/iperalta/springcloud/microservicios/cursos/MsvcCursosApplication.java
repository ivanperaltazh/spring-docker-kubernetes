package org.iperalta.springcloud.microservicios.cursos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients // Con esto habilitamos Feign, para la API REST declarativa, similar a crudRepository
// Es decir habilito la comunicacion entre microservicios:Feign Clients, es una forma declarativa y simplificada de realizar llamadas HTTP
// Las anotaciones son muy similates o casi iguales a las que se usan en el controllador GetMapping, etc.
@SpringBootApplication
public class MsvcCursosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvcCursosApplication.class, args);
	}

}
