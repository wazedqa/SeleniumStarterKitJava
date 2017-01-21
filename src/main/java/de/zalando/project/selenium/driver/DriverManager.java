package de.zalando.project.selenium.driver;

import de.zalando.project.selenium.driver.factory.DriverFactory;
import de.zalando.project.selenium.properties.SystemProperties;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.ITestContext;
import org.testng.xml.XmlTest;

import java.util.Map;
import java.util.stream.Collectors;

@Component
public class DriverManager {

    private final ThreadLocal<WebDriver> driverInCurrentThread = new ThreadLocal<>();
    private final DriverFactory driverFactory;

    @Autowired
    public DriverManager(final DriverFactory driverFactory) {
        this.driverFactory = driverFactory;
    }

    public WebDriver create(final ITestContext testContext, final String testName) {
        final WebDriver driver = createDriver(testContext, testName);
        driverInCurrentThread.set(driver);
        return driver;
    }

    private WebDriver createDriver(final ITestContext testContext, String testName) {
        final Map<String, String> driverParameters = getDriverParameters(testContext, testName);

        return driverFactory.create(driverParameters);
    }

    private Map<String, String> getDriverParameters(ITestContext testContext, String testName) {
        final Map<String, String> driverParameters = getTestNgParameters(testContext.getCurrentXmlTest());
        driverParameters.putAll(SystemProperties.getDriverSystemProperties());
        driverParameters.put(ParameterKey.TEST_NAME, testName);

        return driverParameters;
    }

    private Map<String, String> getTestNgParameters(XmlTest xmlTest) {
        return xmlTest.getAllParameters().entrySet().stream()
                .filter(entry -> entry.getKey().startsWith(ParameterKey.DRIVER_PREFIX))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public void quit() {
        driverInCurrentThread.get().quit();
    }
}