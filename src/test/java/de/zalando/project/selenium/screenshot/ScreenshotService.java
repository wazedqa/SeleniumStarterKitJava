package de.zalando.project.selenium.screenshot;

import de.zalando.project.selenium.properties.SystemProperties;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

public class ScreenshotService {

    private final String screenshotsDirectory;

    public ScreenshotService(final String screenshotsDirectory) {
        this.screenshotsDirectory = screenshotsDirectory;
    }

    public void write(final String fileName, final String imageBase64Screenshot) {
        try {
            FileUtils.writeByteArrayToFile(createScreenshotFile(fileName), Base64.getDecoder().decode(imageBase64Screenshot));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private File createScreenshotFile(String fileName) {
        return new File(screenshotsDirectory + SystemProperties.getProperty("file.separator") + fileName);
    }
}