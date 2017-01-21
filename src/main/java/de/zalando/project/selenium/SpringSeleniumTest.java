package de.zalando.project.selenium;

import de.zalando.project.selenium.driver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

@SpringApplicationConfiguration(Application.class)
public abstract class SpringSeleniumTest extends AbstractTestNGSpringContextTests {

    protected static final String DRIVER = "driver";
    protected static final String PROJECT = "ZALANDO";

    @Autowired
    @Qualifier("appUrl")
    protected String appUrl;

    @Autowired
    protected DriverManager driverManager;

    @DataProvider(name = DRIVER)
    public Object[][] driverProvider(final ITestContext testContext, final Method method) {
        try {
            return tryToCreateDriver(testContext, method);
        } catch (Exception e) {
            e.printStackTrace();
            return new Object[][]{{}};
        }
    }

    private Object[][] tryToCreateDriver(final ITestContext testContext, final Method method) {
        final Object[][] objects = new Object[1][1];

        final WebDriver driver = driverManager.create(testContext, PROJECT + " : " + method.getName());
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        objects[0][0] = driver;

        return objects;
    }

    @AfterMethod
    public void afterMethod() {
        driverManager.quit();
    }
}
