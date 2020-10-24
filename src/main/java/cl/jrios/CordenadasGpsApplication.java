package cl.jrios;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import cl.jrios.service.IFileService;

@SpringBootApplication
public class CordenadasGpsApplication implements CommandLineRunner{

	@Resource
	IFileService fileService;

	public static void main(String[] args) {
		SpringApplication.run(CordenadasGpsApplication.class, args);
	}

	@Override
	public void run(String... arg) throws Exception {
		fileService.deleteAllFile();
		fileService.init();
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "POST", "PUT", "DELETE");
			}
		};
	}

}
