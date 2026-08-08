package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderStatusTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp(){
        driver=new ChromeDriver();
    }

    @Test
    public void orderStatusNavigation() {

        HomePage homePage=new HomePage(driver);
        // открытие главной страницы Яндекс Самокат
        homePage.openHomePage();
        // нажатие на кнопку Статус заказа
        homePage.orderStatusButtonClick();
        homePage.numberOrderClick();
        homePage.setNumberOrder("645738");
        // нажатие на кнопку Go!
        homePage.goButtonClick();
        // проверить, что отобразилась картинка с текстом "Такого заказа нет"
        assertTrue(homePage.orderVerificationIsDisplayed());
    }
    // Закрытие браузера
    @AfterEach
    public void tearDown(){
        driver.quit();
    }
}
