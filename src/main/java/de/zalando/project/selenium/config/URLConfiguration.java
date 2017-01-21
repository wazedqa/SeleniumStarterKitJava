package de.zalando.project.selenium.config;

import com.google.common.base.Preconditions;
import de.zalando.project.selenium.localization.Country;
import de.zalando.project.selenium.properties.SystemProperties;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class URLConfiguration {

    private static final String COUNTRY_PARAMETER_NAME = "country";
    public static final Country COUNTRY = getCountry();

    @Autowired
    private Environment env;

    private static Country getCountry() {
        final String countryCode = SystemProperties.getProperty(COUNTRY_PARAMETER_NAME);
        if (countryCode == null) {
            throw new BeanInitializationException(String.format("\"%s\" system property missing", COUNTRY_PARAMETER_NAME));
        }

        return Country.fromCountryCode(countryCode);
    }

    @Bean(name = "appUrl")
    public String appUrl() {
        return env.getProperty("appUrl." + COUNTRY.getCountryCode());
    }

}
