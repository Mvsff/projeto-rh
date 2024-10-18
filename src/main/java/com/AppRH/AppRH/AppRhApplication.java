//O spring boot é um framework que cria as tabelas e os registros automaticamente
//a partir de uma instrução do programador (como vemos em Vagas.java, no outro pacote)

package com.AppRH.AppRH;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppRhApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppRhApplication.class, args);
	}

}



