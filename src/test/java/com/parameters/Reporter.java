package com.parameters;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

public class Reporter {

    // Change folder if you want; using project-dir/reports/screenshots
    private static final String SCREENSHOTS_DIR = System.getProperty("user.dir")
            + File.separator + "reports" + File.separator + "screenshots";

    public static void generateReport(WebDriver driver, ExtentTest test, Status status, String message) {
        try {
            String screenshotPath = captureScreenshot(driver, message);
            if (screenshotPath != null) {
                test.log(status, message, MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
            } else {
                test.log(status, message);
            }
        } catch (Exception e) {
            // If anything goes wrong attaching screenshot, still log the step.
            test.log(status, message);
            test.log(Status.WARNING, "Could not attach screenshot: " + e.getMessage());
        }
    }

    public static String captureScreenshot(WebDriver driver, String screenshotName) {
        if (driver == null) return null;
        // sanitize name (remove windows-invalid filename characters)
        String safeName = sanitizeFileName(screenshotName);

        try {
            File dir = new File(SCREENSHOTS_DIR);
            if (!dir.exists()) dir.mkdirs();

            String fileName = safeName + "_" + System.currentTimeMillis() + ".png";
            File destFile = new File(dir, fileName);

            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            // ensure parent exists (redundant but safe)
            File parent = destFile.getParentFile();
            if (parent != null && !parent.exists()) parent.mkdirs();

            // Copy screenshot
            FileUtils.copyFile(srcFile, destFile);

            return destFile.getAbsolutePath();
        } catch (IOException ioe) {
            // fallback: try a safe default filename (no message text)
            try {
                File dir = new File(SCREENSHOTS_DIR);
                if (!dir.exists()) dir.mkdirs();
                File destFile = new File(dir, "screenshot_" + System.currentTimeMillis() + ".png");
                File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(srcFile, destFile);
                return destFile.getAbsolutePath();
            } catch (Exception ex) {
                // last resort: give up attaching screenshot
                return null;
            }
        } catch (Exception ex) {
            return null;
        }
    }

    private static String sanitizeFileName(String name) {
        if (name == null) return "screenshot";
        String s = name.trim();
        if (s.isEmpty()) return "screenshot";
        // Replace invalid Windows filename characters with underscore
        s = s.replaceAll("[\\\\/:*?\"<>|]", "_");
        // Collapse long whitespace
        s = s.replaceAll("\\s+", " ");
        // Limit filename length to avoid very long paths
        if (s.length() > 120) s = s.substring(0, 120);
        return s;
    }
}
