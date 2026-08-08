package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class YandexLogoTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
    }

    @Test
    public void testLogoClickNavigation() {
        HomePage homePage = new HomePage(driver);
        // открытие главной страницы «Яндекс Самокат»
        homePage.openHomePage();

        var wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        String currentHandle = driver.getWindowHandle();

        // нажатие на кнопку логотипа Яндекс
        homePage.logoYandexClick();

        // ждём появления второй вкладки, главная страница Яндекса
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        // ищем новую вкладку и переключаемся
        String newHandle = driver.getWindowHandles()
                .stream()
                .filter(handle -> !handle.equals(currentHandle))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Новая вкладка не появилась"));

        driver.switchTo().window(newHandle);

        // текущая страница
        String currentUrl = driver.getCurrentUrl();
        // ожидаемый результат URL
        String expectedUrl = "https://ya.ru/";

        assertTrue(currentUrl.equals(expectedUrl), "Ожидался переход на главную страницу Яндекса, но переход не произошёл. Текущий URL: " + currentUrl);
    }

    // Закрытие браузера
    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
