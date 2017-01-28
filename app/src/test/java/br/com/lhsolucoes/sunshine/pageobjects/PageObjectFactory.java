package br.com.lhsolucoes.sunshine.pageobjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;

/**
 * Created by henrique on 25/01/17.
 */
public class PageObjectFactory {

    private static PageObjectFactory instance;

    private AppiumDriver<AndroidElement> driver;

    public static PageObjectFactory getInstance() {
        if (instance == null) {
            instance = new PageObjectFactory();
        }
        return instance;
    }

    public void setDriver(AppiumDriver<AndroidElement> driver) {
        this.driver = driver;
    }
}
