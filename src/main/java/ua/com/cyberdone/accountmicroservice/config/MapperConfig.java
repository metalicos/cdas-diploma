package ua.com.cyberdone.accountmicroservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.modelmapper.config.Configuration.AccessLevel.PACKAGE_PRIVATE;
import static org.modelmapper.convention.MatchingStrategies.STRICT;

@Configuration
public class MapperConfig {
    @Bean(value = "strictStrategyModelMapper")
    public ModelMapper getStrictStrategyModelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(STRICT)
                .setMethodAccessLevel(PACKAGE_PRIVATE)
                .setFieldAccessLevel(PACKAGE_PRIVATE);
        return modelMapper;
    }

    public ObjectMapper getObjectMapper() {
        return new ObjectMapper();
    }
}
