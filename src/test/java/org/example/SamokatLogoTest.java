package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SamokatLogoTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp(){
        driver=new ChromeDriver();
    }

    @Test
    public void testLogoClickNavigation() {

        HomePage homePage=new HomePage(driver);
         // Открытие главной страницы «Заказать»
        driver.get("https://qa-scooter.education-services.ru/order");

        // нажатие на логотип Самокат
        homePage.logoScooterClick();

        // Проверка, что URL изменился и ведёт на главную
        String currentUrl = driver.getCurrentUrl();

        String expectedUrl = "https://qa-scooter.education-services.ru/";

       assertTrue(currentUrl.equals(expectedUrl),"Ожидался переход на главную страницу Самоката , но переход не произошёл. Текущий URL: " + currentUrl);

    }
    // Закрытие браузера
    @AfterEach
    public void tearDown(){
        driver.quit();
    }
}