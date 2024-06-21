package data.pages.swooppages.commonpages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

public class HeaderContainer {
    private final SelenideElement
            mainHeader = $(".NewHeader"),
            mainNavContainer = $(".Menus");
    public SelenideElement
            holidaysButton = mainNavContainer.$(byText("დასვენება")),
            categoryButton = mainHeader.$(".NewCategories"),
            funcCenterElement = mainHeader.$x(".//a[contains(@class, 'mainCategories') and contains(text(), 'გართობა')]"),
            nightClubButton = mainHeader.$x(".//div[contains(@class, 'mobileSubCategories') and contains(@class, 'subCategory-5')]//a[text()='ღამის კლუბი']");
}
