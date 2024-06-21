package data.pages.swooppages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class NightClubOffersPage {

    private final SelenideElement
            nightClubOfferNoSales = $$(".special-offer").filterBy(text("გაყიდულია 0")).first();

    public SelenideElement
            nightClubOffer = $(".special-offer"),
            favoriteIcon = $(".deal-box-wishlist"),
            voucherDiagram = nightClubOffer.$(".voucher-diagram").$("div"),
            offerSalesProgressBar = nightClubOfferNoSales.$(".voucher-diagram").$("div");
}
