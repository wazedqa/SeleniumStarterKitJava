package de.zalando.project.selenium.driver.factory;

import de.zalando.project.selenium.driver.InvalidURLException;
import de.zalando.project.selenium.driver.ParameterKey;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

@Component
public class RemoteDriverFactory {

    private final DesiredCapabilitiesFactory desiredCapabilitiesFactory;

    @Autowired
    public RemoteDriverFactory(final DesiredCapabilitiesFactory desiredCapabilitiesFactory) {
        this.desiredCapabilitiesFactory = desiredCapabilitiesFactory;
    }

    public WebDriver create(final Map<String, String> driverParameters) {
        try {
            return tryToCreateRemoteDriver(driverParameters);
        } catch (MalformedURLException e) {
            throw new InvalidURLException(e);
        }
    }

    private WebDriver tryToCreateRemoteDriver(final Map<String, String> driverParameters) throws MalformedURLException {
        final URL remoteAddress = new URL(driverParameters.get(ParameterKey.DRIVER_URL));
        return new RemoteWebDriver(remoteAddress, desiredCapabilitiesFactory.createDesiredCapabilities(driverParameters));
    }

}
