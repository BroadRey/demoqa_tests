package com.demoqa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TextBoxTests {
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
    void fillFormTest() {
        open("/text-box");

        $("#userName").setValue("Andrey BroadRey");
        $("#userEmail").setValue("andrey@gmail.com");
        $("#currentAddress").setValue("Some current address");
        $("#permanentAddress").setValue("Some permanent address");
        $("#submit").click();

        $("#output #name").shouldHave(text("Andrey BroadRey"));
        $("#output #email").shouldHave(text("andrey@gmail.com"));
        $("#output #currentAddress").shouldHave(text("Some current address"));
        $("#output #permanentAddress").shouldHave(text("Some permanent address"));
    }
}
