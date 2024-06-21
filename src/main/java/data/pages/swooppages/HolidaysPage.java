package data.pages.swooppages;

import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Selenide.$$;

public class HolidaysPage {
    public ElementsCollection
            offersPrices = $$(".discounted-prices .deal-voucher-price:not([style*='text-decoration: line-through;'])");
}
