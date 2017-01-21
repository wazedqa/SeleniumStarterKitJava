package de.zalando.project.selenium.driver.factory;

import de.zalando.project.selenium.driver.ParameterKey;
import de.zalando.project.selenium.driver.WebDriverManager;
import de.zalando.project.selenium.properties.SystemProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class IEDriverFactory {

    private final static String IE_DRIVER_PATH_SYSTEM_PROPERTY = "webdriver.ie.driver";

    private final DesiredCapabilitiesFactory desiredCapabilitiesFactory;

    @Autowired
    private WebDriverManager webDriverManager;

    @Autowired
    public IEDriverFactory(final DesiredCapabilitiesFactory desiredCapabilitiesFactory) {
        this.desiredCapabilitiesFactory = desiredCapabilitiesFactory;
    }

    public WebDriver create(final Map<String, String> driverParameters) {

        if (driverParameters.containsKey(ParameterKey.DRIVER_PATH)) {
            SystemProperties.setProperty(IE_DRIVER_PATH_SYSTEM_PROPERTY, driverParameters.get(ParameterKey.DRIVER_PATH));
        } else {
            SystemProperties.setProperty(IE_DRIVER_PATH_SYSTEM_PROPERTY, webDriverManager.getIEDriverPath());
        }

        return new InternetExplorerDriver(desiredCapabilitiesFactory.createDesiredCapabilities(driverParameters));
    }
}

