package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

// класс страницы Заказ оформлен
public class WaitingOrderProcessing {

    private WebDriver driver;
    // локатор для поля Заказ оформлен
    private By waitingOrder = By.xpath("//*[@id=\"root\"]/div/div[2]/div[5]/div[1]");

    // конструкор класса
    public WaitingOrderProcessing(WebDriver driver) {
        this.driver = driver;
    }

    //метод возвращает истину, если на форме отображен текст "Заказ оформлен"
    public boolean orderIsProcessedTextIsDisplayed() {
        return driver.findElement(waitingOrder).isDisplayed();
    }
}

