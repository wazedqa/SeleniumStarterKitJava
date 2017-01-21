package de.zalando.project.selenium.saucelabs;

import com.saucelabs.saucerest.SauceREST;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Arrays;
import java.util.Optional;

public class SauceLabsTestsReporter implements ITestListener {

    private static final String PROPERTY_SAUCE_LABS_URL = "driver.url";

    private final SauceREST sauceREST = new SauceREST(getUsername(), getAccessKey());

    @Override
    public void onTestStart(final ITestResult result) {
    }

    @Override
    public void onTestSuccess(final ITestResult result) {
        sauceREST.jobPassed(getSessionId(getDriver(result)));
    }

    @Override
    public void onTestFailure(final ITestResult result) {
        sauceREST.jobFailed(getSessionId(getDriver(result)));
    }

    private WebDriver getDriver(ITestResult result) {
        return Arrays.asList(result.getParameters()).stream()
                .filter(parameter -> parameter instanceof WebDriver)
                .findFirst()
                .map(WebDriver.class::cast)
                .orElseThrow(() -> new IllegalStateException("WebDriver is not available"));
    }

    @Override
    public void onTestSkipped(final ITestResult result) {
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(final ITestResult result) {
    }

    @Override
    public void onStart(final ITestContext context) {
    }

    @Override
    public void onFinish(final ITestContext context) {
    }

    private String getSessionId(final WebDriver webDriver) {
        return Optional.of(webDriver)
                .map(RemoteWebDriver.class::cast)
                .map(RemoteWebDriver::getSessionId)
                .map(SessionId::toString)
                .orElseThrow(() -> new IllegalStateException("SessionId is not available"));
    }

    private String getUsername() {
        return Optional.of(System.getProperty(PROPERTY_SAUCE_LABS_URL))
                .map(sauceLabsUrl -> sauceLabsUrl.substring(0, sauceLabsUrl.indexOf("@")))
                .map(sauceLabsUrl -> sauceLabsUrl.substring(0, sauceLabsUrl.lastIndexOf(":")))
                .map(sauceLabsUrl -> sauceLabsUrl.substring(sauceLabsUrl.lastIndexOf("/") + 1))
                .orElseThrow(() -> new IllegalStateException("Sauce Labs user is not available"));
    }

    private String getAccessKey() {
        return Optional.of(System.getProperty(PROPERTY_SAUCE_LABS_URL))
                .map(sauceLabsUrl -> sauceLabsUrl.substring(0, sauceLabsUrl.indexOf("@")))
                .map(sauceLabsUrl -> sauceLabsUrl.substring(sauceLabsUrl.lastIndexOf(":") + 1))
                .orElseThrow(() -> new IllegalStateException("Sauce Labs API key is not available"));
    }
}