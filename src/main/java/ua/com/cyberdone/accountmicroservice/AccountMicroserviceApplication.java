package ua.com.cyberdone.accountmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableEurekaClient
@EnableJpaRepositories
@SpringBootApplication
public class AccountMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountMicroserviceApplication.class, args);
    }
}
