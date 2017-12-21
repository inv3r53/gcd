package au.com.acme.gcd;

import au.com.acme.gcd.model.persistence.Number;
import au.com.acme.gcd.repository.NumberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GcdApplication  extends SpringBootServletInitializer {

	private static final Logger LOG = LoggerFactory.getLogger(GcdApplication.class);

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(GcdApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(GcdApplication.class, args);
	}

	@Bean
	public CommandLineRunner runAfterStart(final NumberRepository repository) {
		return args -> {
			Number i1 = new Number();
			i1.setN("12000");
			repository.save(i1);
			LOG.info("Id = {}",i1.getId());

			i1 = new Number();
			i1.setN("3200");

			repository.save(i1);
			LOG.info("Id = {}",i1.getId());

			repository.flush();
		};
	}
}
