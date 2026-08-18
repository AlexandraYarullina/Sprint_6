package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static utils.Constants.URL_HOME_PAGE;
import static utils.Constants.URL_ORDER_PAGE;

public class ScooterLogoTest extends BaseTest {

    @Test
    public void checkScooterLogoClickPageOpen() {

        HomePage homePage=new HomePage(driver);
         // Открытие главной страницы «Заказать»
        driver.get(URL_ORDER_PAGE);

        // нажатие на логотип Самокат
        homePage.logoScooterClick();

        // текущий URL
        String currentUrl = driver.getCurrentUrl();
        // ожидаемый URL
        String expectedUrl = URL_HOME_PAGE;

       assertTrue(currentUrl.equals(expectedUrl),"Ожидался переход на главную страницу Самоката , но переход не произошёл. Текущий URL: " + currentUrl);
    }
}