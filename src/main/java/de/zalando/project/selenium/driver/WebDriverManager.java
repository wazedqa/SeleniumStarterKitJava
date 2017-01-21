package de.zalando.project.selenium.driver;


import org.apache.commons.lang3.SystemUtils;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class WebDriverManager {
    private static final String CHROME_DRIVER_NAME = File.separator + "chromedriver.exe";
    private static final String MAC_CHROME_DRIVER_NAME = File.separator + "chromedriver";
    private static final String IE_DRIVER_NAME = File.separator + "IEDriverServer.exe";
    private static final String WIN_PATH = "driver"+ File.separator + "windows";
    private static final String MAC_PATH = "driver" + File.separator + "mac";

    public String getChromeDriverPath(){
        if(SystemUtils.IS_OS_WINDOWS)
            return WIN_PATH+CHROME_DRIVER_NAME;
        else if(SystemUtils.IS_OS_MAC)
            return MAC_PATH+MAC_CHROME_DRIVER_NAME;
        else
            throw new IllegalArgumentException();
    }

    public String getIEDriverPath(){
        if(SystemUtils.IS_OS_WINDOWS)
            return WIN_PATH+IE_DRIVER_NAME;
        else
            throw new IllegalArgumentException();
    }

}
