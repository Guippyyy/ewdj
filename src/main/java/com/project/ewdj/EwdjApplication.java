package com.project.ewdj;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import classes.Boek;
import domein.AlbumExpertBean;
import domein.ExpertBean;
import persistence.BoekRepository;

@SpringBootApplication
public class EwdjApplication implements WebMvcConfigurer {

	private static final Logger log = LoggerFactory.getLogger(EwdjApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(EwdjApplication.class, args);
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addRedirectViewController("/", "/welcome");
	}

	@Bean
	ExpertBean expertBean() {
		return new AlbumExpertBean();
	}

	@Bean
	public CommandLineRunner demo(BoekRepository repository) {
		return (args) -> {
			// save a few Boeks String name, int ISBNnr, double price, int sterren
			repository.save(new Boek("a", 234, 23.23, 3));
			repository.save(new Boek("b", 235, 23.23, 3));
			repository.save(new Boek("c", 236, 23.23, 3));
			repository.save(new Boek("d", 237, 23.23, 3));
			repository.save(new Boek("e", 238, 23.23, 3));

			// fetch all Boeks
			log.info("Boeks found with findAll():");
			log.info("-------------------------------");
			for (Boek Boek : repository.findAll()) {
				log.info(Boek.toString());
			}
			log.info("");

			// fetch an individual Boek by ID
			Boek Boek = repository.findById(1L);
			log.info("Boek found with findById(1L):");
			log.info("--------------------------------");
			log.info(Boek.toString());
			log.info("");

			// fetch Boeks by last name
			log.info("Boek found with findByLastName('Bauer'):");
			log.info("--------------------------------------------");
			repository.findByName("Bauer").forEach(bauer -> {
				log.info(bauer.toString());
			});
			// for (Boek bauer : repository.findByLastName("Bauer")) {
			// log.info(bauer.toString());
			// }
			log.info("");
		};
	}

}
