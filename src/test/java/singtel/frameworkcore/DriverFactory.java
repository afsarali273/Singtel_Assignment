package singtel.frameworkcore;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;


public class DriverFactory {

    private static DriverFactory instance = null;
    ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

    private DriverFactory() {

    }

    public static DriverFactory getInstance() {
        if (instance == null) {
            instance = new DriverFactory();
        }
        return instance;
    }

    public final void setDriver(String BROWSER) throws Exception {

        DesiredCapabilities caps = null;

        if(BROWSER.equals("Mozilla")){
            driver.set(new FirefoxDriver());
            // Log.info("New driver instantiated");
        }else if(BROWSER.equalsIgnoreCase("Chrome")){
            driver.set(new ChromeDriver(DesiredCapabilitiesFactory.getDesiredCapabilities(BrowserType.chrome)));
        }else if(BROWSER.equalsIgnoreCase("Mobile")){
            String deviceType = System.getProperty("deviceType");
            String appiumUrl = "http://localhost:4723/wd/hub";
                if(StringUtils.isEmpty(deviceType)){
                    deviceType ="ANDROID" ;
                }
            if(deviceType.toUpperCase().contentEquals(DeviceType.ANDROID.toString())){  //If Android
                driver.set(new AndroidDriver(new URL(appiumUrl),DesiredCapabilitiesFactory.getDesiredCapabilities(BrowserType.ANDROID)));
            }else if(deviceType.toUpperCase().contentEquals(DeviceType.IOS.toString())){
                driver.set(new IOSDriver(new URL(appiumUrl),DesiredCapabilitiesFactory.getDesiredCapabilities(BrowserType.IOS)));
            }

        }

    }

    public WebDriver getDriver() {
        return driver.get();
    }

    public  void clearCurrentDriver() {

        System.out.println("Current thread removed and clearcurrentDriver() called.. ..");
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }

        driver.remove();
    }
}
