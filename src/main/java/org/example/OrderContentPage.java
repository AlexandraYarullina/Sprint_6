package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static utils.Constants.MESSAGE;

// класс для заполнения формы заказа
public class OrderContentPage {
    private WebDriver driver;

    // локатор для поля ввода имени
    private By nameField = By.xpath("//input[@placeholder='* Имя']");
    // локатор для поля ввода фамилии
    private By surnameField = By.xpath("//div[@class='Order_Form__17u6u']/div[2]/input");
    // локатор для поля адреса: куда привести заказ
    private By addressField = By.xpath("//div[@class='Order_Form__17u6u']/div[3]/input");
    // локатор для поля ввода станции меторо
    private By metroStationField = By.className("select-search__input");
    //локатор для выбора станции метро
    private By selectMetroStation = By.className("select-search__select");
    // локатор для поля ввода номера телефона
    private By phoneNumberField = By.xpath("//div[@class='Order_Form__17u6u']/div[5]/input");
    // локатор для кнопки "Далее"
    private By orderNextButton = By.xpath("//button[(@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Далее')]");

    //введите корректное имя
    private By errorNameLocator = By.xpath(" //div[normalize-space(text())='Введите корректное имя']");
    //Введите корректную фамилию
    private By errorSurnameLocator = By.xpath(" //div[normalize-space(text())='Введите корректную фамилию']");
    //Введите корректный адрес
    private By errorAddressLocator = By.xpath(" //div[normalize-space(text())='Введите корректный адрес']");
    //Выберите станцию
    private By errorMetroStationLocator = By.xpath(" //div[normalize-space(text())='Выберите станцию']");
    //Введите корректный номер
    private By errorPhoneNumberLocator = By.xpath(" //div[normalize-space(text())='Введите корректный номер']");

    // конструктор класса
    public OrderContentPage(WebDriver driver) {
        this.driver = driver;
    }

    //метод для ввода имени
    public void setName(String name) {
        driver.findElement(nameField).sendKeys(name, Keys.TAB);
    }

    //метод для ввода фамилии
    public void setSurname(String surname) {
        driver.findElement(surnameField).sendKeys(surname, Keys.TAB);
    }

    //метод для ввода адреса доставки
    public void setAddress(String address) {
        driver.findElement(addressField).sendKeys(address, Keys.TAB);
    }

    //метод для ввода станции метро
    public void setMetroStation() {
        driver.findElement(metroStationField).click();
        driver.findElement(selectMetroStation).isDisplayed();
        driver.findElement(metroStationField).sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
    }

    //метод для ввода номера телефона
    public void setPhoneNumber(String phoneNumber) {
        driver.findElement(phoneNumberField).sendKeys(phoneNumber, Keys.TAB);
    }

    // нажатие на кнопку "Далее"
    public void orderNextButtonClick() {
        driver.findElement(orderNextButton).click();
    }

    // метод, который объединяет ввод имени, фамилии, адреса и номера телефона
    public void orderPersonalInfo(String name, String surname, String address, String phoneNumber) {
        setName(name);
        setSurname(surname);
        setAddress(address);
        setPhoneNumber(phoneNumber);
    }

    public void checkNameFieldError(String name, String expectedText) {
        setName(name);
        if(expectedText!=null) {
            String resultText = driver.findElement(errorNameLocator).getText();
            assertEquals(expectedText, resultText, MESSAGE);
        }
    }

    public void checkSurnameFieldError(String surname, String expectedText) {
        setSurname(surname);
        if(expectedText!=null) {
            String resultText = driver.findElement(errorSurnameLocator).getText();
            assertEquals(expectedText, resultText, MESSAGE);
        }
    }

    public void checkAddressFieldError(String address, String expectedText) {
        setAddress(address);
        if(expectedText!=null) {
            String resultText = driver.findElement(errorAddressLocator).getText();
            assertEquals(expectedText, resultText, MESSAGE);
        }
    }

    public void checkPhoneNumberFieldError(String phoneNumber, String expectedText) {
        setPhoneNumber(phoneNumber);
        if(expectedText!=null) {
            String resultText = driver.findElement(errorPhoneNumberLocator).getText();
            assertEquals(expectedText, resultText, MESSAGE);
        }
    }

    public void checkMetroStationFieldError(String expectedText) {
        if(expectedText!=null) {
            String resultText = driver.findElement(errorMetroStationLocator).getText();
            assertEquals(expectedText, resultText, MESSAGE);
        }
    }
}



