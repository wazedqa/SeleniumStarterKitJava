package de.zalando.project.selenium.driver.factory;

import de.zalando.project.selenium.driver.Browser;
import de.zalando.project.selenium.driver.DriverNotSupportedException;
import de.zalando.project.selenium.driver.ParameterKey;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class DriverFactory {

    private final ChromeDriverFactory chromeDriverFactory;
    private final FirefoxDriverFactory firefoxDriverFactory;
    private final IEDriverFactory ieDriverFactory;
    private final RemoteDriverFactory remoteDriverFactory;

    @Autowired
    public DriverFactory(final ChromeDriverFactory chromeDriverFactory,
                         final FirefoxDriverFactory firefoxDriverFactory,
                         final RemoteDriverFactory remoteDriverFactory,
                         final IEDriverFactory ieDriverFactory) {
        this.chromeDriverFactory = chromeDriverFactory;
        this.firefoxDriverFactory = firefoxDriverFactory;
        this.remoteDriverFactory = remoteDriverFactory;
        this.ieDriverFactory = ieDriverFactory;
    }

    public WebDriver create(final Map<String, String> driverParameters) {

        String browser = driverParameters.get(ParameterKey.DRIVER_BROWSER);
        if(browser == null || browser.trim().length()==0)
            browser=Browser.CHROME;

        final String driverURL = driverParameters.get(ParameterKey.DRIVER_URL);
        if (driverURL != null) {
            return remoteDriverFactory.create(driverParameters);
        }

        switch (browser.toLowerCase()) {
            case Browser.CHROME:
                return chromeDriverFactory.create(driverParameters);
            case Browser.FIREFOX:
                return firefoxDriverFactory.create(driverParameters);
            case Browser.IE:
                return ieDriverFactory.create(driverParameters);
            default:
                throw new DriverNotSupportedException(browser + " browser is not supported");
        }
    }
}
