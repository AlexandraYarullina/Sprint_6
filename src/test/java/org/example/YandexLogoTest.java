package org.example;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class YandexLogoTest extends BaseTest{

    @Test
    public void checkYandexLogoClickPageOpen() {
        HomePage homePage = new HomePage(driver);
        // открытие главной страницы «Яндекс Самокат»
        homePage.openHomePage();

        var wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        String currentHandle = driver.getWindowHandle();

        // нажатие на кнопку логотипа Яндекс
        homePage.logoYandexClick();

        // ждём появления второй вкладки, главная страница Яндекса
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.numberOfWindowsToBe(2));

        // ищем новую вкладку и переключаемся
        String newHandle = driver.getWindowHandles()
                .stream()
                .filter(handle -> !handle.equals(currentHandle))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Новая вкладка не появилась"));

        driver.switchTo().window(newHandle);

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("ya.ru"));
        // текущая страница
        String currentUrl = driver.getCurrentUrl();

        // проверка, что текущая страница содержит в URL ya.ru
        assertTrue(driver.getCurrentUrl().contains("ya.ru"), "URL не содержит ya.ru: " + currentUrl);
    }
}
