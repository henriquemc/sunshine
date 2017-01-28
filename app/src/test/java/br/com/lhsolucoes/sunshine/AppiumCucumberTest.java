package br.com.lhsolucoes.sunshine;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.URL;

import br.com.lhsolucoes.sunshine.pageobjects.PageObjectFactory;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:")
public class AppiumCucumberTest {

    private AppiumDriver<AndroidElement> driver;

    @Before
    public void setUp() throws Exception {
        // set up appium
//        File classpathRoot = new File(System.getProperty("user.dir"));

        File classpathRoot = new File("/home/vagrant/");

        File appDir = new File(classpathRoot, "build/outputs/apk");
        File app = new File(appDir, "app-debug.apk");
        DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setCapability("deviceName","Nexus_5_API_22");
//        capabilities.setCapability("platformVersion", "5.1.1");

        capabilities.setCapability("deviceName", "Nexus");

        capabilities.setCapability("app", app.getAbsolutePath());
        capabilities.setCapability("appPackage", "br.com.lhsolucoes.sunshine");
        capabilities.setCapability("appActivity", ".activity.MainActivity");
        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

        PageObjectFactory.getInstance().setDriver(driver);

    }

    @After
    public void tearDown() throws Exception {
        if (driver != null) {
            PageObjectFactory.getInstance().setDriver(null);
            driver.quit();
        }

    }

}