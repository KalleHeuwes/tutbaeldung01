package de.kheuwes.tutbaeldung01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("de.kheuwes.tutbaeldung01")
@EntityScan("de.kheuwes.tutbaeldung01")
@SpringBootApplication
//Wenn BasicConfiguration laufen soll:
//@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class Tutbaeldung01Application {

	public static void main(String[] args) {
		SpringApplication.run(Tutbaeldung01Application.class, args);
	}

}
