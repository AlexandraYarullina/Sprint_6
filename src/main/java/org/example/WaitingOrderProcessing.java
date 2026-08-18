package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

// класс страницы Заказ оформлен
public class WaitingOrderProcessing {

    private WebDriver driver;
    // локатор для поля Заказ оформлен
    private By waitingOrderLocator = By.xpath(".//div[text()='Заказ оформлен']");

    // конструкор класса
    public WaitingOrderProcessing(WebDriver driver) {
        this.driver = driver;
    }

    //метод возвращает истину, если на форме отображен текст "Заказ оформлен"
    public String orderIsProcessedText() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver -> (driver.findElement(waitingOrderLocator).getText() != null
                && !driver.findElement(waitingOrderLocator).getText().isEmpty()
        ));
        return driver.findElement(waitingOrderLocator).getText();
    }
}

