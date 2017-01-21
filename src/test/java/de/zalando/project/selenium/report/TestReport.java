package de.zalando.project.selenium.report;

import org.apache.commons.lang3.time.DurationFormatUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class TestReport {

    final List<FailedTest> failedTests = new ArrayList<>();
    int totalNumberOfTests;
    String durationAsText;

    void addFailedTest(final FailedTest failedTest) {
        failedTests.add(failedTest);
    }

    int totalNumberOfTests() {
        return totalNumberOfTests;
    }

    int numberOfFailedTests() {
        return failedTests.size();
    }

    int percentageOfSuccessfulTests() {
        int countOfSuccessfulTests = totalNumberOfTests() - numberOfFailedTests();
        return (int) ((((double) countOfSuccessfulTests / (double) totalNumberOfTests)) * 100);
    }

    void setTotalNumberOfTests(int totalNumberOfTests) {
        this.totalNumberOfTests = totalNumberOfTests;
    }

    void setDuration(Date startDate, Date endDate) {
        final long interval = endDate.getTime() - startDate.getTime();
        durationAsText = DurationFormatUtils.formatDurationWords(interval, true, true);
    }
}
