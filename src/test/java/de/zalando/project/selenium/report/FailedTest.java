package de.zalando.project.selenium.report;

import org.apache.commons.lang3.RandomStringUtils;

class FailedTest {

    final String testName;
    final String screenshotFileName;
    final String stackTrace;
    final String message;
    final String id;

    private FailedTest(final Builder builder) {
        this.testName = builder.testName;
        this.screenshotFileName = builder.screenshotFileName;
        this.stackTrace = builder.stackTrace;
        this.id = RandomStringUtils.randomAlphabetic(10);
        this.message = builder.message;
    }

    static class Builder {

        private String testName;
        private String screenshotFileName;
        private String stackTrace;
        private String message;

        Builder testName(final String testName) {
            this.testName = testName;
            return this;
        }

        Builder screenshotFileName(final String screenshotFileName) {
            this.screenshotFileName = screenshotFileName;
            return this;
        }

        Builder stackTrace(String stackTrace) {
            this.stackTrace = stackTrace;
            return this;
        }

        Builder message(String message) {
            this.message = message;
            return this;
        }

        FailedTest build() {
            return new FailedTest(this);
        }
    }
}

