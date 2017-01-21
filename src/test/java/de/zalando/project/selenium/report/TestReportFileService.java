package de.zalando.project.selenium.report;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import com.google.common.io.Files;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class TestReportFileService {

    void writeTemplate(String templatePath, String targetPath, Object scope) {
        MustacheFactory mf = new DefaultMustacheFactory();
        Mustache mustache = mf.compile(templatePath);
        try {
            createTargetFileIfItDoesNotExist(targetPath);
            mustache.execute(new FileWriter(targetPath), scope).flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createTargetFileIfItDoesNotExist(String targetPath) throws IOException {
        final File targetFile = new File(targetPath);
        if (!targetFile.exists()) {
            Files.createParentDirs(targetFile);
            Files.touch(targetFile);
        }
    }

    void copyCSSResources(String targetDirectoryPath) {
        try {
            File sourceReportDirectory = new File("src/test/resources/report/css");
            FileUtils.copyDirectory(sourceReportDirectory, new File(targetDirectoryPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

