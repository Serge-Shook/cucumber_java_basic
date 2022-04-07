package stepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.*;

import static org.junit.Assert.*;

public class Task2Steps {

    private WebDriver driver;

    public Task2Steps() {
        this.driver = Hooks.driver;
    }

    @When("^Wait for (\\d+) seconds$")
    public void wait_for_seconds(int seconds) throws Throwable {
        Thread.sleep(seconds * 1000);
    }

    @Given("^I am on People with jobs page$")
    public void i_am_on_People_with_jobs_page() {
        driver.get("https://kristinek.github.io/site/tasks/list_of_people.html");
    }

//    @When("^I click on \"([^\"]*)\" button on People with jobs$")
//    public void i_click_on_button_on_People_with_jobs(String buttonText) {
//        driver.findElement(By.xpath(String.format("//button[.='%s']", buttonText))).click();
//    }

    @When("^I enter following info in corresponding fields$")
    public void i_enter_following_info_in_corresponding_fields(Map<String, String> userData) throws Throwable {
        driver.findElement(By.id("name")).sendKeys(userData.get("Name"));
        driver.findElement(By.id("surname")).sendKeys(userData.get("Surname"));
        driver.findElement(By.id("job")).sendKeys(userData.get("Job"));
        driver.findElement(By.id("dob")).sendKeys(userData.get("Date of birth") + Keys.ENTER);
        new WebDriverWait(driver, 3).
                until(ExpectedConditions.invisibilityOfElementLocated(By.id("ui-datepicker-div")));
    }

    @When("^I choose languages: \"([^\"]*)\"$")
    public void i_choose_languages(String languages) {
        List<String> languageList = Arrays.asList(languages.toLowerCase().split(", "));
        for (WebElement languageCheckbox : driver.findElements(By.name("language"))) {
            String currentLanguage = languageCheckbox.getAttribute("id");
            if (languageList.contains(currentLanguage) != languageCheckbox.isSelected()) {
                languageCheckbox.click();
            }
        }

    }

    @When("^I choose gender: \"([^\"]*)\"$")
    public void i_choose_gender(String gender) {
        String genderRadioCss = String.format("input[name='gender'][id='%s']", gender.toLowerCase());
        // next line waits for element with class ui-state-default to disappear
        new WebDriverWait(driver, 3).
                until(ExpectedConditions.invisibilityOfElementLocated(By.className("ui-state-default")));
        driver.findElement(By.cssSelector(genderRadioCss)).click();
    }

    @When("^I select \"([^\"]*)\" from employee status$")
    public void i_select_from_employee_status(String status) {
        Select employeeStatuses = new Select(driver.findElement(By.id("status")));
        employeeStatuses.selectByVisibleText(status);
    }

    @When("^I click \"([^\"]*)\" button$")
    public void i_click_button(String buttonText) {
        driver.findElement(By.xpath(String.format("//button[.='%s']", buttonText))).click();
    }


    @Then("^I can see \"([^\"]*)\" in people list$")
    public void i_can_see_in_people_list(String fullName) {
        String xPath = String.format("//div[contains(@class,'w3-xlarge') and .='%s']", fullName);
        WebElement nameElement = driver.findElement(By.xpath(xPath));
        assertTrue(nameElement.isDisplayed());
        assertEquals(fullName, nameElement.getText());
    }

    @Then("^persons info is as in list$")
    public void persons_info_is_as_in_list(Map<String, String> userDataMap) {
        String fullName = userDataMap.get("Full name");
        String xPath = String.format("//div[contains(@class,'w3-xlarge') and .='%s']/parent::li", fullName);
        WebElement personElement = driver.findElement(By.xpath(xPath));
        assertEquals(userDataMap.get("Job"), personElement.findElement(By.className("job")).getText());
        assertEquals(userDataMap.get("Date of birth"), personElement.findElement(By.className("dob")).getText());
        assertEquals(
                userDataMap.get("Knows language(s)"),
                personElement.findElement(By.className("language")).getText());
        assertEquals(
                userDataMap.get("Gender").toLowerCase(),
                personElement.findElement(By.className("gender")).getText());
        assertEquals(
                userDataMap.get("Employee status").toLowerCase(),
                personElement.findElement(By.className("status")).getText());
    }

    @When("^I click on pencil icon at right of \"([^\"]*)\"$")
    public void i_click_on_pencil_icon_at_right_of(String fullName) {
        String xPath = String.format("//div[contains(@class,'w3-xlarge') and .='%s']/parent::li", fullName);
        WebElement personElement = driver.findElement(By.xpath(xPath));
        personElement.findElement(By.className("fa-pencil")).click();
    }

    @When("^I clear \"([^\"]*)\" field$")
    public void i_clear_field(String id) {
        driver.findElement(By.id(id.toLowerCase())).clear();
    }

    @When("^I enter \"([^\"]*)\" in \"([^\"]*)\" field$")
    public void i_enter_in_field(String value, String id) {
        driver.findElement(By.id(id.toLowerCase())).sendKeys(value);
    }

    @When("^I click on cross icon at right of \"([^\"]*)\"$")
    public void i_click_on_cross_icon_at_right_of(String fullName) {
        String xPath = String.format("//div[contains(@class,'w3-xlarge') and .='%s']/parent::li", fullName);
        WebElement personElement = driver.findElement(By.xpath(xPath));
        personElement.findElement(By.className("closebtn")).click();
    }

    @Then("^I can not see \"([^\"]*)\" person record$")
    public void i_can_not_see_person_record(String fullName) {
        String xPath = String.format("//div[contains(@class,'w3-xlarge') and .='%s']/parent::li", fullName);
        List<WebElement> emptyList = driver.findElements(By.xpath(xPath));
        assertTrue(emptyList.isEmpty());
    }


    @When("^I add a person with data:$")
    public void i_add_a_person_with_data(Map<String, String> userData) {
        i_click_button("Add person");
        driver.findElement(By.id("name")).sendKeys(userData.get("Name"));
        driver.findElement(By.id("surname")).sendKeys(userData.get("Surname"));
        driver.findElement(By.id("job")).sendKeys(userData.get("Job"));
        driver.findElement(By.id("dob")).sendKeys(userData.get("Date of birth") + Keys.ENTER);
        new WebDriverWait(driver, 3).
                until(ExpectedConditions.invisibilityOfElementLocated(By.id("ui-datepicker-div")));
        i_choose_languages(userData.get("Languages"));
        i_choose_gender(userData.get("Gender"));
        i_select_from_employee_status(userData.get("Employee status"));
        i_click_button("Add");
    }

    @Then("^I can see person list:$")
    public void i_can_see_person_list(List<String> fullNames) {
        List<String> actualNames = new ArrayList<>();

        for (WebElement element : driver.findElements(By.className("w3-xlarge"))) {
            actualNames.add(element.getText());
        }

        assertEquals(actualNames, fullNames);
    }

    @Then("^I can see \"([^\"]*)\" field is empty$")
    public void i_can_see_field_is_empty(String fieldName) {
        assertTrue(driver.findElement(By.id(fieldName.toLowerCase())).getAttribute("value").isEmpty());
    }

    @Then("^I can see Date of birth field is empty$")
    public void i_can_see_Date_of_birth_field_is_empty() {
        assertTrue(driver.findElement(By.id("dob")).getAttribute("value").isEmpty());
    }

    @Then("^Only English language is checked$")
    public void only_English_language_is_checked() {
        for (WebElement languageCheckbox : driver.findElements(By.cssSelector("input[name='language']"))) {
            if (languageCheckbox.getAttribute("id").equals("english")) {
                assertTrue(languageCheckbox.isSelected());
            } else {
                assertFalse(languageCheckbox.isSelected());
            }
        }
    }

    @Then("^Gender radios are unchecked$")
    public void gender_radios_are_unchecked() {
        for (WebElement genderRadioButton : driver.findElements(By.cssSelector("input[name='gender']"))) {
            assertFalse(genderRadioButton.isSelected());
        }
    }

    @Then("^Employee status is Employee$")
    public void employee_status_is_Employee() {
        Select employeeStatusSelect = new Select(driver.findElement(By.id("status")));
        assertEquals("Employee", employeeStatusSelect.getFirstSelectedOption().getText());
    }

}
