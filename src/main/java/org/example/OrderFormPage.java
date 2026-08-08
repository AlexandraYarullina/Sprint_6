package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

// класс для оформления заказа
public class OrderFormPage {
    private WebDriver driver;

    //локатор для кнопки Да оформить заказ
    private By yesButton=By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Да']");

    //конструктор класса
    public OrderFormPage(WebDriver driver){
        this.driver=driver;
    }

    //метод для нажатия на кнопку Да
    public void yesOrderButtonClick(){
        driver.findElement(yesButton).click();
    }
}
