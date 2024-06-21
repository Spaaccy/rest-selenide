package data.pages.swooppages.popuppages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class FacebookPopUpPage {
    public SelenideElement
            emailInputField = $("#email"),
            loginButton = $("#loginbutton");
}
