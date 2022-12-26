package springbook.user.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestFactory {

    @Bean
    public testCode testCode() {
        return new testCode();
    }
}
