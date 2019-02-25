package javatestproject.springbootproject3;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class Springbootproject3Application {

	@Value("${welcome.txt}")
	private String welcometxt;

	public static void main(String[] args) {
		SpringApplication.run(Springbootproject3Application.class, args);
	}

	@RequestMapping("/")
	String index(){
		return welcometxt;
	}
}
