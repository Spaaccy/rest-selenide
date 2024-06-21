package data.pages.swooppages.commonpages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static data.constants.SwoopData.DEFAULT_DROPDOWN_LOCATION;
public class SideBarContainer {
    public SelenideElement
            categoryDesk = $(".category-filter-desk"),
            pricesContainer = categoryDesk.$(".price-inputs"),
            minPriceInput = pricesContainer.find("#minprice"),
            maxPriceInput = pricesContainer.find("#maxprice"),
            searchButton = categoryDesk.$(".submit-button"),
            removeButton = categoryDesk.$(".delete-search-button"),
            dropDownOpenArrow = $(".category-filter-desk .ms-choice");
    public ElementsCollection
            allPaymentMethods = $$(".category-filter-desk .location-label"),
            allOptions = $$(".category-filter-desk .ms-drop li");

    public SelenideElement getDefaultPaymentMethod() {
            return allPaymentMethods.filterBy(text(DEFAULT_DROPDOWN_LOCATION)).first().$("input");
    }
}
