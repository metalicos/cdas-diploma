package ua.com.cyberdone.accountmicroservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Value("${spring.application.version}")
    private String applicationVersion;

    @Bean
    OpenAPI cyberDoneOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Cyberdone Account Microservice API")
                        .version(applicationVersion)
                        .description("CyberDone Account Microservice Interaction API"))
                .servers(List.of(openApiServer()));
    }

    @Bean
    Server openApiServer() {
        var server = new Server();
        server.setUrl("http://localhost:5051");
        return server;
    }
}
