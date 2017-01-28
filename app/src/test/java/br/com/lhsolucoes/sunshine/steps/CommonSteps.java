package br.com.lhsolucoes.sunshine.steps;

import br.com.lhsolucoes.sunshine.pageobjects.MainActivityPage;
import cucumber.api.java.en.Given;


public class CommonSteps {

    private MainActivityPage mainActivityPage;

    public CommonSteps(MainActivityPage mainActivityPage) {
        this.mainActivityPage = mainActivityPage;
    }

    @Given("^that user is on first screen of the application\\.$")
    public void that_user_is_on_first_screen_of_the_application() throws Throwable {
        mainActivityPage.openApplication();
    }
}
