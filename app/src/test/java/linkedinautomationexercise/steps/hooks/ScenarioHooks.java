package linkedinautomationexercise.steps.hooks;

import linkedinautomationexercise.framework.selenium.DriverManager;
import io.cucumber.java.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import linkedinautomationexercise.ui.PageTransporter;
import linkedinautomationexercise.ui.controller.UIController;
import linkedinautomationexercise.utils.LoggerManager;

import java.io.File;
import java.util.logging.Level;

import static org.openqa.selenium.chrome.ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY;
import static org.openqa.selenium.edge.EdgeDriverService.EDGE_DRIVER_SILENT_OUTPUT_PROPERTY;

public class ScenarioHooks {
    private static final PageTransporter pageTransporter = PageTransporter.getInstance();
    private static final LoggerManager log = LoggerManager.getInstance();
    private static final UIController controller = UIController.getInstance();
    private static final DriverManager driverManager = DriverManager.getInstance();
    private static final String firefoxLogFilePath = System.getProperty("user.dir") + File.separator + "logs"
            + File.separator + "firefox.log";
    private static boolean isAUIScenario = false;

    public void disableOtherJavaLoggers() {
        System.setProperty(CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
        System.setProperty(EDGE_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
        System.setProperty("webdriver.firefox.logfile", firefoxLogFilePath);

        java.util.logging.Logger.getLogger("").setLevel(Level.OFF);
    }

    @Before(order = 1)
    public void beforeScenario(Scenario scenario) {
        log.info("Scenario: --> " + scenario.getName());
        disableOtherJavaLoggers();
        isAUIScenario = scenario.getSourceTagNames().contains("@UI");
    }

    @After(order = 1)
    public void afterScenario(Scenario scenario) {
        log.info("Scenario: --> " + scenario.getStatus() + " : " + scenario.getName());
        if (scenario.isFailed() && scenario.getSourceTagNames().contains("@UI")) {
            TakesScreenshot screenshotManager = (TakesScreenshot) driverManager.getWebDriver();
            byte[] screenshot = screenshotManager.getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getId());
        }
    }

    @AfterAll
    public static void afterAll() {
        if (isAUIScenario) {
            driverManager.quitWebDriver();
        }
    }

    @BeforeAll
    public static void beforeAll() {
        pageTransporter.navigateToHomePage();
    }

}
