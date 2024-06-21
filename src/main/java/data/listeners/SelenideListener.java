package data.listeners;

import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import data.configs.BaseSelenideConfig;
import io.qameta.allure.Attachment;
import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

public class SelenideListener extends BaseSelenideConfig implements ITestListener {
    private final ThreadLocal<WebDriver> webDriverThreadLocal = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult iTestResult) {
        webDriverThreadLocal.set(WebDriverRunner.getWebDriver());
        SelenideLogger.addListener("name", new AllureSelenide().screenshots(true).savePageSource(false));
    }

    @Override
    public void onTestFailure(ITestResult iTestResult){
        takeScreenshotOnFail(webDriverThreadLocal.get());
        saveLogs(iTestResult.getMethod().getConstructorOrMethod().getName());
    }
    @Attachment(value = "Screenshot", type = "image/png")
    public static byte[] takeScreenshotOnFail(WebDriver driver){
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }
    @Attachment(value = "Stacktrace", type = "text/plain")
    public static String saveLogs(String message){
        return message;
    }

    @Override
    public void onFinish(ITestContext context) {
        for (ITestNGMethod method: context.getAllTestMethods()) {
            for (ITestResult result: context.getPassedTests().getResults(method)) {
                WebDriver driver = (WebDriver) result.getAttribute("threadLocalDriver");
                if (driver != null) {
                    driver.quit();
                }
            }
        }
    }
}
