package de.zalando.project.selenium.report;

import de.zalando.project.selenium.properties.SystemProperties;
import de.zalando.project.selenium.screenshot.ScreenshotService;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

import java.util.Arrays;
import java.util.Date;

class TestReportContext {

    private final TestReport testReport = new TestReport();

    private static final String REPORT_TEMPLATE_PATH = "report/report.mustache";
    private static final String REPORT_TARGET_PATH = getReportDirectoryPath() + SystemProperties.getProperty("file.separator") + "report.html";
    private static final String TARGETLOCATION = "target";

    private static String getReportDirectoryPath() {
        final String fileSeparator = SystemProperties.getProperty("file.separator");
        return TARGETLOCATION + fileSeparator + "selenium-report";
    }

    void addFailure(ITestResult iTestResult) {
        final WebDriver driver = getRemoteDriver(iTestResult);
        final ScreenshotService screenshotService = createScreenshotService();
        final String testName = iTestResult.getMethod().getMethodName();
        final String screenshotFileName = testName + ".png";

        screenshotService.write(screenshotFileName, ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64));

        testReport.addFailedTest(new FailedTest.Builder()
                .testName(testName)
                .screenshotFileName(screenshotFileName)
                .stackTrace(ExceptionUtils.getStackTrace(iTestResult.getThrowable()))
                .message(iTestResult.getThrowable().getMessage().split("\\n")[0])
                .build());
    }

    private WebDriver getRemoteDriver(ITestResult iTestResult) {
        return Arrays.asList(iTestResult.getParameters()).stream()
                .filter(parameter -> parameter instanceof WebDriver)
                .findFirst()
                .map(WebDriver.class::cast)
                .orElseThrow(() -> new IllegalStateException("WebDriver is not available"));
    }

    private ScreenshotService createScreenshotService() {
        return new ScreenshotService(getReportDirectoryPath());
    }

    void setTotalNumberOfTests(int totalNumberOfTests) {
        testReport.setTotalNumberOfTests(totalNumberOfTests);
    }

    void setDuration(Date startDate, Date endDate) {
        testReport.setDuration(startDate, endDate);
    }

    void writeReport() {
        final TestReportFileService testReportFileService = new TestReportFileService();
        testReportFileService.writeTemplate(REPORT_TEMPLATE_PATH, REPORT_TARGET_PATH, testReport);
        testReportFileService.copyCSSResources(getReportDirectoryPath() + SystemProperties.getProperty("file.separator") + "css");
    }
}

