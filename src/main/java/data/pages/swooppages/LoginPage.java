package data.pages.swooppages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private final SelenideElement
            formContent  = $(".form-content");

    public SelenideElement
            loginPasswordInput = formContent.$("input#Password"),
            loginText = formContent.$(byText("ავტორიზაცია"));
}
