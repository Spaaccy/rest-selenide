package data.steps.swoopsteps.commonpagesteps;
import data.pages.swooppages.commonpages.HeaderContainer;
import io.qameta.allure.Step;

public class HeaderContainerSteps {
    HeaderContainer headerContainer = new HeaderContainer();

    @Step("Clicks on 'დასვენება' in header and goes to that page")
    public void goToHolidaysPage() {
        headerContainer.holidaysButton.click();
    }
    @Step("Clicks on 'კატეგორიები' in header and opens custom dropdown")
    public HeaderContainerSteps clickOnCategory() {
        headerContainer.categoryButton.click();
        return this;
    }
    @Step("Hovers over on 'გართობა' in custom dropdown")
    public HeaderContainerSteps hoverFunCenterElement() {
        headerContainer.funcCenterElement.hover();
        return this;
    }
    @Step("Clicks on 'ღამის კლუბი' in custom dropdown")
    public void clickNightClubButton() {
        headerContainer.nightClubButton.click();
    }
}
