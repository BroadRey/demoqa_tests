package com.demoqa;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;

import static com.codeborne.selenide.Selenide.*;

public class StudentRegistrationFormTests {
    static {
        ChromeOptions options = new ChromeOptions();
        options.setBinary("/Applications/Google Chrome.app/Contents/MacOS/Google Chrome");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        Configuration.browserCapabilities = capabilities;
    }

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void whenFillFormShouldSuccess() {
        // Arrange
        open("/automation-practice-form");
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        String firstName = "Andrey";
        String secondName = "BroadRey";
        String email = "andrey@gmail.com";
        String phoneNumber = "8005553535";
        String gender = "Male";
        String subject = "Maths";
        String hobbies = "Sports, Reading, Music";
        byte dayOfBirth = 11;
        String monthOfBirth = "March";
        short yearOfBirth = 2000;
        String imagePath = "/Users/larginator/Documents/qaguru/selectors.png";
        String imageName = "selectors.png";
        String currentAddress = "Some Address";
        String state = "NCR";
        String city = "Delhi";

        // Act
        $x("//input[@id='firstName']").setValue(firstName);
        $x("//input[@id='lastName']").setValue(secondName);
        $x("//input[@id='userEmail']").setValue(email);
        $x(
                String.format("//label[contains(text(),'%s')]", gender)
        ).click();
        $x("//input[@id='userNumber']").setValue(phoneNumber);
        $x("//input[@id='dateOfBirthInput']").click();
        $x(
                String.format("//option[@value=2 and contains(text(), '%s')]", monthOfBirth)
        ).click();
        $x(
                String.format("//option[@value=%1$d and contains(text(), '%1$d')]", yearOfBirth)
        ).click();
        $x(
                String.format("//div[contains(@aria-label, '%s %dth, %d')]", monthOfBirth, dayOfBirth, yearOfBirth)
        ).click();
        $x("//input[@id='subjectsInput']").setValue("M").pressEnter();
        $$x("//input[contains(@id, 'hobbies')]//../..//label").forEach(SelenideElement::click);
        $x("//input[@id='uploadPicture']").
                uploadFile(new File(imagePath));
        $x("//textarea[@id='currentAddress']").setValue(currentAddress);
        $x("//div[@id='state']").scrollTo().click();
        $x(
                String.format("//div[@id='state']//*[contains(text(), '%s')]", state)
        ).click();
        $x("//div[@id='city']").scrollTo().click();
        $x(
                String.format("//div[@id='city']//*[contains(text(), '%s')]", city)
        ).click();
        $x("//button[@id='submit']").scrollTo().click();

        // Assert
        $x("//div[@class='modal-content']").should(Condition.exist);
        $x("//td[contains(text(), 'Student Name')]/following-sibling::td")
                .shouldHave(Condition.text(firstName));
        $x("//td[contains(text(), 'Student Email')]/following-sibling::td")
                .shouldHave(Condition.text(email));
        $x("//td[contains(text(), 'Gender')]/following-sibling::td")
                .shouldHave(Condition.text(gender));
        $x("//td[contains(text(), 'Date of Birth')]/following-sibling::td")
                .shouldHave(Condition.text(String.format("%d %s,%d", dayOfBirth, monthOfBirth, yearOfBirth)));
        $x("//td[contains(text(), 'Mobile')]/following-sibling::td")
                .shouldHave(Condition.text(phoneNumber));
        $x("//td[contains(text(), 'Subjects')]/following-sibling::td")
                .shouldHave(Condition.text(subject));
        $x("//td[contains(text(), 'Hobbies')]/following-sibling::td")
                .shouldHave(Condition.text(hobbies));
        $x("//td[contains(text(), 'Picture')]/following-sibling::td")
                .shouldHave(Condition.text(imageName));
        $x("//td[contains(text(), 'Address')]/following-sibling::td")
                .shouldHave(Condition.text(currentAddress));
        $x("//td[contains(text(), 'State and City')]/following-sibling::td")
                .shouldHave(Condition.text(String.join(" ", state, city)));
        $x("//button[@id='closeLargeModal']").should(Condition.exist);
    }
}
