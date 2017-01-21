package de.zalando.project.selenium.driver.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class FirefoxDriverFactory {

    private final DesiredCapabilitiesFactory desiredCapabilitiesFactory;

    @Autowired
    public FirefoxDriverFactory(final DesiredCapabilitiesFactory desiredCapabilitiesFactory) {
        this.desiredCapabilitiesFactory = desiredCapabilitiesFactory;
    }

    public WebDriver create(final Map<String, String> driverParameters) {
        return new FirefoxDriver(desiredCapabilitiesFactory.createDesiredCapabilities(driverParameters));
    }
}
