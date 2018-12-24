import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class HbTest {
    public AppiumDriver<WebElement> driver;
    public WebDriverWait wait;
    public TouchAction touchAction;
    private WebElement webElement;

    private String splashPermissionYesXpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.support.v7.widget.LinearLayoutCompat/android.widget.ScrollView/android.widget.LinearLayout/android.widget.Button[2]";
    private String splashAllowPermissionXpath = "/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.Button[2]";
    private String text = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.TextView";

    @BeforeMethod
    public void setupDevice() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("deviceName", "General Mobile 4G");
        desiredCapabilities.setCapability("udid", "79dfda34");
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("platformVersion", "7.1.1");
        desiredCapabilities.setCapability("appPackage", "com.pozitron.hepsiburada");
        desiredCapabilities.setCapability("automationName", "uiautomotor2");
        desiredCapabilities.setCapability("appActivity", "com.hepsiburada.ui.startup.SplashActivity");
        desiredCapabilities.setCapability("skipUnlock", "true");
        desiredCapabilities.setCapability("noReset", "false");
        driver = new AndroidDriver<WebElement>((new URL("http://0.0.0.0:4723/wd/hub")), desiredCapabilities);
        wait = new WebDriverWait(driver, 20);
        touchAction = new TouchAction(driver);
    }

    @Test
    public void testExample() {
        sleepALitle();
        clickButtonByXpath(splashPermissionYesXpath);
        clickButtonByXpath(splashAllowPermissionXpath);
        clickButtonByID("ivLoginClose");
        clickButtonByID("showcase_button");
        clickButtonByID("showcase_button");
        clickButtonByID("showcase_button");
        clickButtonByID("iv_toolbar_user_account_menu");
        clickButtonByID("llUserAccountLogin");
        //    register();
        login();
        System.out.println("setup tamam");
    }

    private void register() {
        clickButtonByID("tvLoginSignup");
        sleepALitle();
        pressBack();
        sleepALitle();
        driver.findElementById("etRegisterFirstName").sendKeys("Ayça");
        driver.findElementById("etRegisterLastName").sendKeys("Ayçan");
        driver.findElementById("etRegisterEmail").sendKeys("aa1234321a@yandex.com");
        driver.findElementById("etRegisterPassword").sendKeys("a159369");
        driver.findElementById("etRegisterRePassword").sendKeys("a159369");
        pressBack();
        clickButtonByID("btnRegisterSend");
        clickButtonByID("button1");
        clickButtonByID("btnUserAccountClose");
    }

    private void login() {
        sleepALitle();
        driver.findElementById("etLoginEmail").sendKeys("aa1234321a@yandex.com");
        driver.findElementById("etLoginPassword").sendKeys("a159369");
        pressBack();
        clickButtonByID("btnLoginLogin");
        clickButtonByID("button1");
        scrollScreen();

        webElement = driver.findElement(By.className("android.widget.TextView"));
        String el_text = "Kapat";
        if (el_text.equals(webElement.getText())) {
            webElement.click();
        }

        clickButtonByID("showcase_button");
        clickButtonByID("showcase_button");
        clickButtonByID("showcase_button");
    }

    private void scrollScreen() {
        touchAction.press(120, 1000).waitAction(400).moveTo(120, 600).release();
        touchAction.perform();
    }

    private void pressBack() {
        driver.navigate().back();
    }

    private void clickButtonByID(String id) {
        sleepALitle();
        while (driver.findElements(MobileBy.id(id)).size() == 0) {
            System.out.println("Waiting for click");
        }
        driver.findElement(MobileBy.id(id)).click();
    }

    private void clickButtonByXpath(String xPath) {
        sleepALitle();
        while (driver.findElements(MobileBy.xpath(xPath)).size() == 0) {
            System.out.println("Waiting for click");
        }
        driver.findElement(MobileBy.xpath(xPath)).click();
    }

    private void sleepALitle() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @AfterMethod
    public void teardown() {
        // driver.quit();
    }
}
