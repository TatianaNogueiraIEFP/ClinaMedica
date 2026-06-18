package com.iefp.ClinaMedica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principal da aplicação Clínica Médica.
 *
 * A anotação @SpringBootApplication combina:
 * - @Configuration: permite definir configurações da aplicação.
 * - @EnableAutoConfiguration: configura automaticamente o Spring Boot.
 * - @ComponentScan: procura componentes, controladores e serviços no projeto.
 */
@SpringBootApplication
public class ClinaMedicaApplication {

	/**
	 * Método principal que inicia a aplicação Spring Boot.
	 *
	 * @param args argumentos passados pela linha de comandos
	 */
	public static void main(String[] args) {

		// Arranca a aplicação e o servidor embutido (Tomcat)
		SpringApplication.run(ClinaMedicaApplication.class, args);
	}
}