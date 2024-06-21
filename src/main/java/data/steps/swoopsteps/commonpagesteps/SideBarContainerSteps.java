package data.steps.swoopsteps.commonpagesteps;

import com.codeborne.selenide.ClickOptions;
import data.pages.swooppages.commonpages.SideBarContainer;
import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.empty;
import static data.constants.SwoopData.*;

public class SideBarContainerSteps {
    SideBarContainer sideBarContainer = new SideBarContainer();

    @Step("Scrolls to price filter container")
    public SideBarContainerSteps scrollToPriceContainer() {
        sideBarContainer.pricesContainer.scrollIntoView(SCROLL_TO_CENTER);
        return this;
    }
    @Step("Sets the min price: '{0}'")
    public SideBarContainerSteps setMinPrice(int price) {
        sideBarContainer.minPriceInput.sendKeys(String.valueOf(price));
        return this;
    }
    @Step("Sets the max price: '{0}'")
    public SideBarContainerSteps setMaxPrice(int price) {
        sideBarContainer.maxPriceInput.sendKeys(String.valueOf(price));
        return this;
    }
    @Step("Clicks on search button")
    public void clickOnSearchButton() {
        sideBarContainer.searchButton.click();
    }
    @Step("Changes location to: '{0}'")
    public SideBarContainerSteps changeLocation(String optionText) {
        sideBarContainer.dropDownOpenArrow.scrollIntoView(SCROLL_TO_CENTER).click();
        sideBarContainer.allOptions.filterBy(text(optionText)).first().click();
        return this;
    }
    @Step("Changes the payment method to: '{0}'")
    public SideBarContainerSteps changePaymentMethod(String paymentMethod) {
        sideBarContainer.allPaymentMethods.filterBy(text(paymentMethod)).first().click(ClickOptions.usingJavaScript());
        return this;
    }
    @Step("Clicks on remove button and clears all filters")
    public SideBarContainerSteps clickRemoveFiltersButton() {
        sideBarContainer.removeButton.scrollIntoView(SCROLL_TO_CENTER).click();
        return this;
    }
    @Step("Checks that offer location is set to default")
    public SideBarContainerSteps checkNoDropdownLocationOptionIsSelected() {
        sideBarContainer.allOptions.shouldHave(noneMatch(DROPDOWN_NO_SELECTION_ASSERTION, option -> option.getAttribute(CLASS_ATTRIBUTE).contains(SELECTED_CLASS)));
        return this;
    }
    @Step("Checks that default payment method is selected")
    public SideBarContainerSteps checkDefaultPaymentMethodIsSelected() {
        sideBarContainer.getDefaultPaymentMethod().shouldBe(selected);
        return this;
    }
    @Step("Checks that min price filter is removed")
    public SideBarContainerSteps checkMinPriceFilterIsRemoved() {
        sideBarContainer.minPriceInput.shouldBe(empty);
        return this;
    }
    @Step("Checks that max price filter is removed")
    public void checkMaxPriceFilterIsRemoved() {
        sideBarContainer.maxPriceInput.shouldBe(empty);
    }
}
