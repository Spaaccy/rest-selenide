package data.steps.swoopsteps;
import data.pages.swooppages.NightClubPage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;

public class NightClubPageSteps {
    NightClubPage nightClubPage = new NightClubPage();

    @Step("Clicks on 'გაზიარება'")
    public NightClubPageSteps clickOnShareButton() {
        nightClubPage.offerShareButton.click();
        return this;
    }
    @Step("Switches to Facebook window")
    public void switchToFacebookWindow() {
        switchTo().window(1);
    }
}
