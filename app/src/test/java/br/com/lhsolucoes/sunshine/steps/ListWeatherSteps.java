package br.com.lhsolucoes.sunshine.steps;

import br.com.lhsolucoes.sunshine.pageobjects.MainActivityPage;
import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * Created by henrique on 22/01/17.
 */

public class ListWeatherSteps {


    private MainActivityPage mainActivityPage;

    public ListWeatherSteps(MainActivityPage mainActivityPage) {
        this.mainActivityPage = mainActivityPage;
    }

    @When("^he click on 'Refresh' menu item\\.$")
    public void he_click_on_Refresh_menu_item() throws Throwable {
        mainActivityPage.refreshData();
    }

    @Then("^one list with weather data of week should be shown$")
    public void one_list_with_weather_data_of_week_should_be_shown() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

}
