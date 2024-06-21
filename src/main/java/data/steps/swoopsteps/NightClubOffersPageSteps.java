package data.steps.swoopsteps;

import data.pages.swooppages.NightClubOffersPage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.attribute;
import static data.constants.SwoopData.*;

public class NightClubOffersPageSteps {
    NightClubOffersPage nightClubOffersPage = new NightClubOffersPage();

    @Step("Tries to add first item to favorites")
    public void addFirstItemToFavorites() {
        nightClubOffersPage.favoriteIcon.click();
    }
    @Step("Verifies voucher diagram does not indicate 'sold out")
    public void checkVouchersAreNotSoldOut() {
        nightClubOffersPage.voucherDiagram.shouldNotHave(attribute(DATA_WIDTH_ATTRIBUTE, DATA_WIDTH_SOLD_OUT));
    }
    @Step("Finds offer which contains text 'გაყიდულია 0' and checks that it's progress bar is empty '0%'")
    public void checkProgressBarIsEmptyForUnsoldOffer() {
        nightClubOffersPage.offerSalesProgressBar.shouldHave(attribute(DATA_WIDTH_ATTRIBUTE, DATA_WIDTH_ZERO_SOLD));
    }
    @Step("Clicks on the first nightclub offer")
    public void clickOnFirstOffer() {
        nightClubOffersPage.nightClubOffer.click();
    }
}
