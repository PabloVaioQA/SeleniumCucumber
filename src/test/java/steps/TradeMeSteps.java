package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import pages.TradeMePage;


import java.util.List;
import static io.restassured.RestAssured.*;

public class TradeMeSteps {

    private ValidatableResponse json;
    private static RequestSpecification request;
    private Response response;

    TradeMePage trademe = new TradeMePage();

    @Given("^I navigate to the TradeMe Motor page$")
    public void navigateToTradeMeMotor(){
        trademe.navigateToTradeMeMotor();
    }
    @When("^I select the car make (.+)$")
    public void selectMake(String make){
        trademe.selectMakeFromDropdown(make);
    }

    @Then("^I can see it has (.+) records in the results$")
    public void printAmount(String expectedAmountOfResults){
        trademe.clickSearch();
        Assert.assertTrue(trademe.resultAmount().contains(expectedAmountOfResults));
        Assert.assertEquals("Showing "+expectedAmountOfResults+" results", trademe.resultAmount());
    }

    @Then("^I can verify that number of car makes is (\\d+)$")
    public void returnAmountOfMakes(int makeAmount){
        int expectedAmount = makeAmount;
        int actualAmount = trademe.makeDropdownSize();
        Assert.assertEquals(expectedAmount,actualAmount);
    }

    @Given("^I send the request to the endpoint$")
        public void sendGETRequest(){
            request= given()
                    .log().all()
                    .param("","");

    }

    @Then("^I can see there are (\\d+) car makes$")
    public void validateAmountOfMakes(int expectedMakeAmount){
        response = request
                    .when()
                    .get("http://api.trademe.co.nz/v1/Categories/UsedCars.json");
                    json = response.then().statusCode(200);
                    List<String> jsonResponse = response.jsonPath().getList("Subcategories.Name");
                    Assert.assertEquals("Mismatch on the expected total.", expectedMakeAmount,jsonResponse.size());
    }


}
