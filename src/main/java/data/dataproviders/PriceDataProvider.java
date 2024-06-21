package data.dataproviders;
import org.testng.annotations.DataProvider;

public class PriceDataProvider {
    @DataProvider(name = "searchRangeData")
    public static Object[][] priceData() {
        return new Object[][]{
                {100, 250},
                {110, 300},
                {50, 90},
                {155, 190},
                {300, 450},
        };
    }
}
