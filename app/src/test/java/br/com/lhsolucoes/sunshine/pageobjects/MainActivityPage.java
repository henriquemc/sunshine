package br.com.lhsolucoes.sunshine.pageobjects;

import android.widget.ListView;
import android.widget.TextView;

import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidElement;

/**
 * Created by henrique on 22/01/17.
 */
public class MainActivityPage {

    private AppiumDriver<AndroidElement> driver;

    public MainActivityPage(AppiumDriver<AndroidElement> driver) {
        this.driver = driver;
    }

    public MainActivityPage openApplication() {

        return this;
    }

    public MainActivityPage refreshData() {
        AndroidElement toolbarMenu = driver.findElement(By.className("android.widget.ImageView"));
        toolbarMenu.click();


        AndroidElement refreshBtn = driver.findElement(By.xpath(".//*[@text='Refresh']"));
        refreshBtn.click();

        return this;
    }


    public List<String> getWeatherItems() {
        AndroidElement listView = driver.findElement(By.className(ListView.class.getCanonicalName()));
        List<MobileElement> weatherItems = listView.findElementsByClassName(TextView.class.getCanonicalName());

        List<String> items = new ArrayList<>();
        for (MobileElement weatherItem : weatherItems) {
            items.add(weatherItem.getText());
        }

        return items;

    }
}
