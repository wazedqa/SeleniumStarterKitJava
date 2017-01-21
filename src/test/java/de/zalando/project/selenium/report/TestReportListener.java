package de.zalando.project.selenium.report;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestReportListener implements ITestListener {

    private final TestReportContext testReportService = new TestReportContext();

    @Override
    public void onTestStart(final ITestResult iTestResult) {}

    @Override
    public void onTestSuccess(final ITestResult iTestResult) {}

    @Override
    public void onTestFailure(final ITestResult iTestResult) {
        testReportService.addFailure(iTestResult);
    }

    @Override
    public void onTestSkipped(final ITestResult iTestResult) {}

    @Override
    public void onTestFailedButWithinSuccessPercentage(final ITestResult iTestResult) {}

    @Override
    public void onStart(final ITestContext iTestContext) {}

    @Override
    public void onFinish(final ITestContext iTestContext) {
        testReportService.setTotalNumberOfTests(iTestContext.getPassedTests().size() + iTestContext.getFailedTests().size());
        testReportService.setDuration(iTestContext.getStartDate(), iTestContext.getEndDate());
        testReportService.writeReport();
    }
}
