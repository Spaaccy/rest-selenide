package data.steps.swoopsteps.popuppagesteps;

import data.pages.swooppages.popuppages.FacebookPopUpPage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.closeWindow;
import static com.codeborne.selenide.Selenide.switchTo;

public class FacebookWindowSteps {
    FacebookPopUpPage facebookPopUpPage = new FacebookPopUpPage();
    @Step("Verifies that Facebook email input field is present")
    public FacebookWindowSteps checkFacebookInputField() {
        facebookPopUpPage.emailInputField.shouldBe(visible);
        return this;
    }
    @Step("Verifies that Facebook login button is present")
    public FacebookWindowSteps checkFacebookLoginButton() {
        facebookPopUpPage.loginButton.shouldBe(visible);
        return this;
    }
    @Step("Closes Facebook popup window")
    public FacebookWindowSteps closeFacebookWindow() {
        closeWindow();
        return this;
    }
    @Step("Switches back to the nightclub page")
    public void switchToNightClubPage() {
        switchTo().window(0);
    }
}
