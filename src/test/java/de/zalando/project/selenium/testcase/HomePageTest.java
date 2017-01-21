package de.zalando.project.selenium.testcase;

import de.zalando.project.selenium.SpringSeleniumTest;
import de.zalando.project.selenium.domain.User;
import de.zalando.project.selenium.localization.LocaleText;
import de.zalando.project.selenium.pageobject.HomePage;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class HomePageTest extends SpringSeleniumTest {
    
    @Autowired
    @Qualifier("admin")
    private User user;

    @Test(dataProvider = DRIVER)
    public void thatHTMLHasTitle(final WebDriver webDriver) {
        final HomePage homePage = new HomePage(webDriver, appUrl);
        assertThat(homePage.getTitle(), equalTo(LocaleText.get("homePage.title")));
    }

}
