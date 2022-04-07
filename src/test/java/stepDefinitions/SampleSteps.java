package stepDefinitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SampleSteps {
    private WebDriver driver;

    public SampleSteps() {
        this.driver = Hooks.driver;
    }

    @Given("^I am on the home page$")
    public void iAmOnTheHomePage() throws Throwable {
        driver.get("https://kristinek.github.io/site");
    }

    @When("^I am on the locators page$")
    public void iAmOnTheLocatorsPage() throws Throwable {
        driver.get("https://kristinek.github.io/site/examples/locators");
    }

    @Then("^I should see both locators page headers$")
    public void i_should_see_both_locators_page_headers() throws Throwable {

        WebElement heading1 = driver.findElement(By.id("heading_1"));
        WebElement heading2 = driver.findElement(By.id("heading_2"));

        assertTrue(heading1.isDisplayed());
        assertEquals("Heading 1", heading1.getText());

        assertTrue(heading2.isDisplayed());
        assertEquals("Heading 2 text", heading2.getText());

    }

    @Then("^Buttons in Locators page are clickable$")
    public void buttons_in_Locators_page_are_clickable() throws Throwable {
        assertTrue(driver.findElement(By.name("randomButton1")).isEnabled());
        assertTrue(driver.findElement(By.name("randomButton2")).isEnabled());
    }

    @Then("^I should see home page header$")
    public void iShouldSeeHomePageHeader() throws Throwable {
        assertEquals("This is a home page",
                driver.findElement(By.cssSelector("h1")).getText());
    }

    @And("^I should see home page description$")
    public void iShouldSeeHomePageDescription() throws Throwable {
        assertEquals("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                driver.findElement(By.cssSelector("p")).getText());
    }

    @When("^I enter name: \"([^\"]*)\"$")
    public void iEnterName(String name) throws Throwable {
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys(name);
    }

    @And("^I enter age: (\\d+)$")
    public void iEnterAge(int age) throws Throwable {
        driver.findElement(By.id("age")).sendKeys(String.valueOf(age));
    }

    @Given("^I (?:am on|open) age page$")
    public void iAmOnAgePage() throws Throwable {
        driver.get("https://kristinek.github.io/site/examples/age");
    }

    @And("^I click submit age$")
    public void iClickSubmitAge() throws Throwable {
        driver.findElement(By.id("submit")).click();
    }

    @Then("^I see message: \"([^\"]*)\"$")
    public void iSeeMessage(String message) throws Throwable {
        assertEquals(message, driver.findElement(By.id("message")).getText());
    }

    @When("^I enter values:$")
    public void iEnterValues(Map<String, String> valuesToEnter) throws Throwable {
        for (Map.Entry<String, String> e : valuesToEnter.entrySet()) {
            driver.findElement(By.id(e.getKey())).clear();
            driver.findElement(By.id(e.getKey())).sendKeys(e.getValue());
            System.out.println("key is " + e.getKey());
            System.out.println("value is " + e.getValue());
        }
    }

    @And("^I should see menu$")
    public void iShouldSeeMenu() throws Throwable {
        assertTrue(driver.findElement(By.className("w3-navbar")).isDisplayed());
    }

    @And("^I click the result checkbox button$")
    public void iClickTheResultCheckboxButton() throws Throwable {
        driver.findElement(By.id("result_button_checkbox")).click();
    }

    @When("^I clicked on checkboxes:$")
    public void iClickedOnCheckboxes(List<String> values) throws Throwable {
        for (String value : values) {
            driver.findElement(By.cssSelector("[value='" + value + "']")).click();
        }
    }

    @Then("^message for checkboxes \"([^\"]*)\" is seen$")
    public void messageForCheckboxesIsSeen(String message) throws Throwable {
        assertEquals(message, driver.findElement(By.id("result_checkbox")).getText());
    }

    @Given("^I am on action page$")
    public void iAmOnActionPage() {
        driver.get("https://kristinek.github.io/site/examples/actions");
    }

    @Then("^I see error: \"([^\"]*)\"$")
    public void i_see_error(String errorMsg) {
        String actualErrorMsg = driver.findElement(By.id("error")).getText();
        assertEquals(errorMsg, actualErrorMsg);
    }

    @Then("^I am not navigated to age message page$")
    public void i_am_not_navigated_to_age_message_page() {
        assertEquals("https://kristinek.github.io/site/examples/age", driver.getCurrentUrl());
    }


    @Given("^I am on the feedback page$")
    public void i_am_on_the_feedback_page() {
        driver.get("https://kristinek.github.io/site/tasks/provide_feedback");
    }

    @When("^I enter name: \"([^\"]*)\" on feedback page$")
    public void i_enter_name_on_feedback_page(String name) {
        driver.findElement(By.name("name")).sendKeys(name);
    }

    @When("^I enter age: \"([^\"]*)\" on feedback page$")
    public void i_enter_age_on_feedback_page(String age) {
        driver.findElement(By.name("age")).sendKeys(age);
    }

    @When("^I click submit on feedback page$")
    public void i_click_submit_on_feedback_page() {
        driver.findElement(By.xpath("//button[.='Send']")).click();
    }

    @Then("^I see \"([^\"]*)\" in Your name: field$")
    public void i_see_in_Your_name_field(String name) {
        assertEquals("Name is incorrect", name, driver.findElement(By.id("name")).getText());
    }

    @Then("^I see \"([^\"]*)\" in Your age: field$")
    public void i_see_in_Your_age_field(String age) {
        assertEquals("Age is incorrect", age, driver.findElement(By.id("age")).getText());
    }

    // Task 1 steps

    @Given("^I am on Enter a number page$")
    public void i_am_on_Enter_a_number_page() {
        driver.get("https://kristinek.github.io/site/tasks/enter_a_number");
    }

    @When("^I enter \"([^\"]*)\" in Enter a number page$")
    public void i_enter_in_Enter_a_number_page(String number) {
        System.out.println(number);
        driver.findElement(By.id("numb")).sendKeys(number);
    }

    @When("^I click Submit button in Enter a number page$")
    public void i_click_Submit_button_in_Enter_a_number_page() throws Throwable {
        driver.findElement(By.xpath("//button[.='Submit']")).click();
    }

    @Then("^Error message \"([^\"]*)\" appears in Enter a number page$")
    public void error_message_appears_in_Enter_a_number_page(String errorMessage) throws Throwable {
        WebElement errorElement = driver.findElement(By.id("ch1_error"));
        assertTrue(errorElement.isDisplayed());
        assertEquals(errorMessage, errorElement.getText());
    }

    @Then("^Alert message \"([^\"]*)\" appears$")
    public void alert_message_appears(String message) throws Throwable {
        Alert alert = driver.switchTo().alert();
        assertEquals(message, alert.getText());
        alert.accept();
    }

//    Sample 4 feature steps

    @When("^I select feedback languages$")
    public void i_select_feedback_languages(List<String> languages) {
        for (String language : languages) {
            String cssSelector = String.format("input[name='language'][value='%s']", language);
            driver.findElement(By.cssSelector(cssSelector)).click();
        }
    }

    @Then("^I can see languages \"([^\"]*)\" in feedback check$")
    public void i_can_see_languages_in_feedback_check(String expectedLanguages) {
        String actualLanguages = driver.findElement(By.id("language")).getText();
        assertEquals("Language list", expectedLanguages, actualLanguages);
    }

    @When("^I enter values in feedback page:$")
    public void i_enter_values_in_feedback_page(Map<String, String> fieldsData) {
        for (String field : fieldsData.keySet()) {
            if (field.equals("name") || field.equals("age")) {
                driver.findElement(By.name(field)).sendKeys(fieldsData.get(field));
            }
            if (field.equals("gender")) {
                String genreCssSelector = String.format("input[name='gender'][value='%s']", fieldsData.get("gender"));
                driver.findElement(By.cssSelector(genreCssSelector)).click();
            }
        }
    }

    @Then("^I see data in summary page$")
    public void i_see_data_in_summary_page(Map<String, String> fieldsData) {
        for (String field : fieldsData.keySet()) {
            String expectedValue = fieldsData.get(field);
            String actualValue = driver.findElement(By.id(field)).getText();
            assertEquals(field, expectedValue, actualValue);
        }
    }

}
