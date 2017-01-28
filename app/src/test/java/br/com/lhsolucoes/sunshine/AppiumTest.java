package br.com.lhsolucoes.sunshine;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.URL;
import java.util.List;

import br.com.lhsolucoes.sunshine.pageobjects.MainActivityPage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

/**
 * Created by henrique on 14/01/17.
 */
public class AppiumTest {

    private AppiumDriver<AndroidElement> driver;

    @Before
    public void setUp() throws Exception {
        // set up appium
        File classpathRoot = new File(System.getProperty("user.dir"));

//        File classpathRoot = new File("/home/vagrant/");

        File appDir = new File(classpathRoot, "build/outputs/apk");
        File app = new File(appDir, "app-debug.apk");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "Nexus_5_API_22");
        capabilities.setPlatform(Platform.ANDROID);
        capabilities.setCapability("platformVersion", "5.1.1");


        capabilities.setCapability("app", app.getAbsolutePath());
        capabilities.setCapability("appPackage", "br.com.lhsolucoes.sunshine");
        capabilities.setCapability("appActivity", ".activity.MainActivity");
        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown() throws Exception {
        if (driver != null) {
            driver.quit();
        }

    }

    @Test
    public void listWeatherItems() throws InterruptedException {

        MainActivityPage main = new MainActivityPage(driver);

        List<String> weatherItems = main.refreshData().getWeatherItems();
        Assert.assertEquals(7, weatherItems.size());

    }
}