package de.zalando.project.selenium.pageobject;

import com.thoughtworks.selenium.webdriven.commands.WaitForPageToLoad;
import de.zalando.project.selenium.generic.Wait;
import de.zalando.project.selenium.localization.LocaleText;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    private static final Logger LOGGER = LoggerFactory.getLogger(HomePage.class);

    private final WebDriver webDriver;

    public HomePage(final WebDriver webDriver, final String url) {
        this.webDriver = webDriver;

        webDriver.get(url);
        PageFactory.initElements(webDriver, this);
    }

    public String getTitle() {
        return webDriver.getTitle();
    }



}