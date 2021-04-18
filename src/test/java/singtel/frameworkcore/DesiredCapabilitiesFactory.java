package singtel.frameworkcore;

import io.appium.java_client.remote.MobileCapabilityType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

public class DesiredCapabilitiesFactory {


    public static DesiredCapabilities getDesiredCapabilities(BrowserType browserType) {
        switch (browserType) {
            case chrome:
                return getChromeDesiredCapabilities();
            case firefox:
                return getFirefoxDesiredCapabilities();
            case edge:
                return getEdgeDesiredCapabilities();
            case internetExplorer:
                return getInternetExplorerDesiredCapabilities();
            case IOS:
                return getIOSDesiredCapabilities();
            case ANDROID:
                return getAndroidDesiredCapabilities();
            default:
                return null;
        }
    }

    private static DesiredCapabilities getChromeDesiredCapabilities() {

        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
        DesiredCapabilities desiredCapabilities = DesiredCapabilities.chrome();
        desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        return desiredCapabilities;
    }

    private static DesiredCapabilities getFirefoxDesiredCapabilities() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxProfile firefoxProfile = new FirefoxProfile();
        firefoxProfile.setPreference("browser.download.folderList", 2);
        firefoxProfile.setPreference("browser.download.manager.showWhenStarting", false);
        firefoxProfile.setPreference("browser.helperApps.alwaysAsk.force", false);
        firefoxProfile.setPreference("browser.download.manager.alertOnEXEOpen", false);
        firefoxProfile.setPreference("browser.download.manager.focusWhenStarting", false);
        firefoxProfile.setPreference("browser.download.manager.useWindow", false);
        firefoxProfile.setPreference("browser.download.manager.showAlertOnComplete", false);
        firefoxProfile.setPreference("browser.download.manager.closeWhenDone", false);
        firefoxProfile.setPreference("pdfjs.disabled", true);
        DesiredCapabilities desiredCapabilities = DesiredCapabilities.firefox();
        desiredCapabilities.setCapability(FirefoxDriver.PROFILE, firefoxProfile);
        return desiredCapabilities;
    }

    //Android

    private static DesiredCapabilities getAndroidDesiredCapabilities() {

        DesiredCapabilities  caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME,"UiAutomator2");
        caps.setCapability("appActivity","com.singtel.MainActivity");
        caps.setCapability("appPackage","com.singtel.someapp");
        caps.setCapability("deviceName","emulator-5554");
        caps.setCapability("platformVersion","9");
        caps.setCapability("platformName","Android");
        caps.setCapability("app",System.getProperty("user.dir")+"/src/test/resources/app/singtel_android.apk");

        return caps;
    }

    //IOS

    private static DesiredCapabilities getIOSDesiredCapabilities() {

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME,"XCUITest");
        caps.setCapability("bundleId","com.singtel.someapp");
        caps.setCapability("deviceName","iPhone XS");
        caps.setCapability("platformVersion","13.2");
        caps.setCapability("platformName","iOS");

        return caps;
    }

    private static DesiredCapabilities getEdgeDesiredCapabilities() {
        return DesiredCapabilities.edge();
    }

    private static DesiredCapabilities getInternetExplorerDesiredCapabilities() {
        return DesiredCapabilities.internetExplorer();
    }
}
