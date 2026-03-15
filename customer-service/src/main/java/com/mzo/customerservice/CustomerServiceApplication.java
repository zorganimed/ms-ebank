package com.mzo.customerservice;

import com.mzo.customerservice.config.GlobalConfig;
import com.mzo.customerservice.entities.Customer;
import com.mzo.customerservice.services.CustomerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@EnableConfigurationProperties(GlobalConfig.class)
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(CustomerService service){
        return args -> {
            List<String> names = List.of("Mohamed", "Ahmed", "Ali");
            names.forEach(name->
                    service.saveCustomer(Customer.builder()
                                    .firstName(name)
                                    .lastName(" NOM "+name)
                                    .email(name+"@gmail.com")
                            .build())
                    );
        };
    }
}
