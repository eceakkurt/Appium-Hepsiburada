import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
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
        clickButtonByXpath(ViewPaths.splashPermissionYesXpath);
        clickButtonByXpath(ViewPaths.splashAllowPermissionXpath);
        clickButtonByID("ivLoginClose");
        clickButtonByID("showcase_button");
        clickButtonByID("showcase_button");
        clickButtonByID("showcase_button");
        register();
        //  login();
       // addProductCart();
        System.out.println("setup tamam");
    }

    private void register() {
        clickButtonByID("iv_toolbar_user_account_menu"); //Hesabım sayfasını açmak için
        clickButtonByID("llUserAccountLogin"); //Login sayfasını açmak için
        clickButtonByID("tvLoginSignup"); //Üye olmak için
        sleepALitle();
        pressBack();
        sleepALitle();
        driver.findElementById("etRegisterFirstName").sendKeys("Ayça");
        driver.findElementById("etRegisterLastName").sendKeys("Ayçan");
        driver.findElementById("etRegisterEmail").sendKeys("aa1234321a@yandex.com");
        driver.findElementById("etRegisterPassword").sendKeys("a159369");
        driver.findElementById("etRegisterRePassword").sendKeys("a159369");
        pressBack();
        clickButtonByID("btnRegisterSend"); //Bilgileri kaydedip hesabı oluşturmak için
        clickButtonByID("button1");//Hoşgeldiniz sayfasına tıklamak için
        clickButtonByID("btnUserAccountClose");
    }

    private void login() {
        clickButtonByID("iv_toolbar_user_account_menu");
        clickButtonByID("llUserAccountLogin");
        sleepALitle();
        driver.findElementById("etLoginEmail").sendKeys("aa1234321a@yandex.com");
        driver.findElementById("etLoginPassword").sendKeys("a159369");
        pressBack();
        clickButtonByID("btnLoginLogin");
        clickButtonByID("button1");
        pressBack();

    }

    private void addProductCart() {
        //Sepete ürün ekleme kısmı devam etmektedir
        sleepALitle();
        scrollScreen();
        scrollScreen();
        clickButtonByID("ivHomeGridViewItemImage");
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

    private void scrollScreen() {
        touchAction.press(120, 1000).waitAction(400).moveTo(120, 600).release();
        touchAction.perform();
    }

    @AfterMethod
    public void teardown() {
        // driver.quit();
    }
}
