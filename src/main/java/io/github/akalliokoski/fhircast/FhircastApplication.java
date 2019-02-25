package io.github.akalliokoski.fhircast;

import io.github.akalliokoski.fhircast.hello.GreetingWebClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FhircastApplication {

	public static void main(String[] args) {
		SpringApplication.run(FhircastApplication.class, args);

		GreetingWebClient gwc = new GreetingWebClient();
		System.out.println(gwc.getResult());
	}

}
