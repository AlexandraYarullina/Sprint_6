package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class IncorrectDataOrderContent {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new FirefoxDriver();
    }

    @Test
    public void orderStatusNavigation() {

        HomePage homePage = new HomePage(driver);
        // открытие главной страницы Яндекс Самокат
        homePage.openHomePage();
        // метод для выбора кнопки Заказать на странице
        homePage.chooseOrderButton("up");
        // метод ожидания загрузки страницы
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("Order_Content__bmtHS")));

        //создание объекта для предоставления персональных данных на странице заказа
        OrderContentPage orderContentPage=new OrderContentPage(driver);
        // ввод данных на странице
        orderContentPage.orderPersonalInfo("name","surname","place","phoneNumber");
        orderContentPage.orderNextButtonClick();
        // проверка, что отображаются ошибки для всех полей формы заказа
        assertTrue(orderContentPage.isErrorNameVisible());
        assertTrue(orderContentPage.isErrorSurnameVisible());
        assertTrue(orderContentPage.isErrorPhoneNumberVisible());
        assertTrue(orderContentPage.isErrorAddressVisible());
        assertTrue(orderContentPage.isErrorMetroStationVisible());
    }

    // Закрытие браузера
    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}

