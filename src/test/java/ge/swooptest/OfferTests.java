package ge.swooptest;

import data.configs.BaseSelenideConfig;
import data.dataproviders.PriceDataProvider;
import data.steps.swoopsteps.HolidaysPageSteps;
import data.steps.swoopsteps.LoginPageSteps;
import data.steps.swoopsteps.NightClubOffersPageSteps;
import data.steps.swoopsteps.NightClubPageSteps;
import data.steps.swoopsteps.commonpagesteps.HeaderContainerSteps;
import data.steps.swoopsteps.commonpagesteps.SideBarContainerSteps;
import data.steps.swoopsteps.popuppagesteps.FacebookWindowSteps;
import io.qameta.allure.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.open;
import static data.constants.SwoopData.*;

@Epic("Swoop.ge Functionality Tests")
public class OfferTests extends BaseSelenideConfig {
    HeaderContainerSteps headerContainerSteps;
    HolidaysPageSteps holidaysPageSteps;
    NightClubOffersPageSteps nightClubOffersPageSteps;
    LoginPageSteps loginPageSteps;
    SideBarContainerSteps sideBarContainerSteps;
    NightClubPageSteps nightClubPageSteps;
    FacebookWindowSteps facebookWindowSteps;

    @BeforeClass(alwaysRun = true)
    public void setUpSteps() {
        headerContainerSteps = new HeaderContainerSteps();
        holidaysPageSteps = new HolidaysPageSteps();
        nightClubOffersPageSteps = new NightClubOffersPageSteps();
        loginPageSteps = new LoginPageSteps();
        sideBarContainerSteps = new SideBarContainerSteps();
        nightClubPageSteps = new NightClubPageSteps();
        facebookWindowSteps = new FacebookWindowSteps();
    }
    @BeforeMethod(alwaysRun = true)
    public void openURL() {
        open(SWOOP_URL);
    }

    @Test(dataProvider = "searchRangeData", dataProviderClass = PriceDataProvider.class, priority = 1, description = "NightClub offers price filtering")
    @Feature("Filter products depending on price range provided")
    @Severity(SeverityLevel.NORMAL)
    @Story("Verifies that products which fall within the specified price range are displayed on the first page.")
    @Description("Tests the price range filtering functionality, " +
            "ensuring that only products within the specified price range " +
            "(provided by the 'searchRangeData' data provider) are displayed on the page.")
    public void rangeTest(int minPrice, int maxPrice) {
        headerContainerSteps.goToHolidaysPage();
        sideBarContainerSteps
                .scrollToPriceContainer()
                .setMinPrice(minPrice)
                .setMaxPrice(maxPrice)
                .clickOnSearchButton();
        holidaysPageSteps
                .validateElementsPriceRange(minPrice, maxPrice);
    }
    @Test(priority = 2, description = "Login page and voucher validation", groups = "SwoopTest")
    @Feature("Functional tests for the add to favorites button and voucher count")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Check that adding an item to favorites and monitoring the availability of vouchers work as intended.")
    @Description("This test opens the swoop.ge, then chooses 'ღამის კლუბი' from 'კატეგორიები' dropdown, then adds offer to the favorites and validates the Login Window appearance" +
            "Finally, validates that vouchers of the first offer are not sold out")
    public void favouriteOfferTest() {
        headerContainerSteps
                .clickOnCategory()
                .hoverFunCenterElement()
                .clickNightClubButton();
        nightClubOffersPageSteps
                .addFirstItemToFavorites();
        loginPageSteps
                .verifyLoginPageRedirect()
                .goBackOnePage();
        nightClubOffersPageSteps
                .checkVouchersAreNotSoldOut();
    }
    @Test(priority = 3, description = "Share functionality test")
    @Feature("First offer's share functionality tests")
    @Severity(SeverityLevel.NORMAL)
    @Story("Test the first nightclub offer's share function.")
    @Description("This test opens swoop.ge, selects 'ღამის კლუბი' from the 'კატეგორიები' dropdown, " +
            "opens the first nightclub offer, and clicks on the 'გაზიარება' button. " +
            "Verifies that the window containing the Facebook login page has finally appeared.")
    public void shareOfferTest() {
        headerContainerSteps
                .clickOnCategory()
                .hoverFunCenterElement()
                .clickNightClubButton();
        nightClubOffersPageSteps
                .clickOnFirstOffer();
        nightClubPageSteps
                .clickOnShareButton()
                .switchToFacebookWindow();
        facebookWindowSteps
                .checkFacebookInputField()
                .checkFacebookLoginButton()
                .closeFacebookWindow()
                .switchToNightClubPage();;
    }
    @Test(priority = 4, description = "Nightclub offer status test")
    @Feature("Nightclub offer availability display")
    @Severity(SeverityLevel.NORMAL)
    @Story("Test the nightclub's progressbar availability function.")
    @Description("This test opens swoop.ge, selects 'ღამის კლუბი' from the 'კატეგორიები' dropdown, " +
            "finds first night club offer where the progress bar correctly displays 'no offers' status for nightclubs.")
    public void noOffersSoldTest() {
        headerContainerSteps
                .clickOnCategory()
                .hoverFunCenterElement()
                .clickNightClubButton();
        nightClubOffersPageSteps
                .checkProgressBarIsEmptyForUnsoldOffer();
    }
    @Test(priority = 5, description = "Filter application and reset")
    @Feature("Sidebar Filters")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Ensure search filters can be reset after modification.")
    @Description("This test opens swoop.ge, selects 'ღამის კლუბი' from the 'კატეგორიები' dropdown, " +
            "changes the following filters: location, payment method and price range, finally it clears these filters and validates default behavior")
    public void clearFilterTest() {
        headerContainerSteps
                .clickOnCategory()
                .hoverFunCenterElement()
                .clickNightClubButton();
        sideBarContainerSteps
                .changeLocation(DROPDOWN_LOCATION)
                .changePaymentMethod(PAYMENT_METHOD)
                .setMinPrice(MIN_PRICE)
                .setMaxPrice(MAX_PRICE)
                .clickRemoveFiltersButton()
                .checkNoDropdownLocationOptionIsSelected()
                .checkDefaultPaymentMethodIsSelected()
                .checkMinPriceFilterIsRemoved()
                .checkMaxPriceFilterIsRemoved();
    }
}
