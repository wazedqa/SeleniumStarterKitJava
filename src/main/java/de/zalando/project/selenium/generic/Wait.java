package de.zalando.project.selenium.generic;

import com.google.common.base.Predicate;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class Wait {

    private static final int GENERAL_TIMEOUT_SECONDS = 15;

    public static void visible(final WebDriver webDriver, final By by) {
        createWait(webDriver).until(visibilityOfElementLocated(by));
    }

    public static boolean isVisible(final WebDriver webDriver, final By by, final int timeOutInSeconds) {
        try {
            setImplicitWait(webDriver, timeOutInSeconds);
            createWait(webDriver, timeOutInSeconds).until(visibilityOfElementLocated(by));
        } catch (final TimeoutException te) {
            return false;
        } finally {
            resetImplicitWait(webDriver);
        }
        return true;
    }

    public static boolean isVisibleNow(final WebDriver webDriver, final By by) {
        return isVisible(webDriver, by, 0);
    }

    private static void resetImplicitWait(WebDriver webDriver) {
        webDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    public static void allVisible(final WebDriver webDriver, final By by) {
        createWait(webDriver).until(visibilityOfAllElementsLocatedBy(by));
    }

    public static void clickable(final WebDriver webDriver, final WebElement element) {
        createWait(webDriver).until(elementToBeClickable(element));
    }

    public static void visible(final WebDriver webDriver, final WebElement element) {
        createWait(webDriver).until(visibilityOf(element));
    }

    public static void invisible(final WebDriver webDriver, final By by) {
        webDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        createWait(webDriver).until(invisibilityOfElementLocated(by));
        resetImplicitWait(webDriver);
    }

    private static void setImplicitWait(final WebDriver webDriver, final int timeOutInSeconds) {
        webDriver.manage().timeouts().implicitlyWait(timeOutInSeconds, TimeUnit.SECONDS);
    }

    private static WebDriverWait createWait(final WebDriver webDriver) {
        return new WebDriverWait(webDriver, GENERAL_TIMEOUT_SECONDS);
    }

    private static WebDriverWait createWait(final WebDriver webDriver, final int timeOutInSeconds) {
        return new WebDriverWait(webDriver, timeOutInSeconds);
    }

    public static void textElementToHaveValue(final WebDriver webDriver, final WebElement textElement, final String targetValue) {
        createWait(webDriver).until((Predicate<WebDriver>) driver -> targetValue.equals(textElement.getAttribute("value")));
    }

    public static void elementToHaveText(final WebDriver webDriver, final WebElement textElement, final String targetText) {
        createWait(webDriver).until((Predicate<WebDriver>) driver -> targetText.equalsIgnoreCase(textElement.getText()));
    }

    public static void until(final WebDriver webDriver, final Predicate<WebDriver> predicate, int timeOutInSeconds) {
        createWait(webDriver, timeOutInSeconds).until(predicate);
    }

    public static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void disabled(WebDriver webDriver, WebElement saveButton) {
        createWait(webDriver).until((Predicate<WebDriver>) input -> saveButton.getAttribute("disabled") != null);
    }

}
