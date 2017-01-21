package de.zalando.project.selenium.config;

import de.zalando.project.selenium.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class UserConfiguration {

    @Autowired
    private Environment env;

    @Bean(name = "admin")
    public User admin() {
        return new User(
                env.getProperty("user.admin.alias"),
                env.getProperty("user.admin.password")
        );
    }

}
