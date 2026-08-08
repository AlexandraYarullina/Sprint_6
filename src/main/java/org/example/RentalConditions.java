package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
// класс страницы для заполнения формы аренды
public class RentalConditions {
    private WebDriver driver;

    // локатор для поля ввода Когда привезти самокат
    private By datePicker= By.xpath("//div[@class='react-datepicker__input-container']/input");
    // локатор для выпадающего списока Срок аренды
    private By selectRentalPeriod=By.className("Dropdown-root");
    //локатор для самоката в черном цвете
    private By blackColorScooter = By.xpath(".//input[@id='black']");
    //локатор для самоката в сером цвете
    private By grayColorScooter = By.xpath(".//input[@id='grey']");
    //локатор для поля ввода комментарий для курьера
    private By commentForTheCourier=By.xpath("//div[@class='Order_Form__17u6u']/div[4]/input[@placeholder='Комментарий для курьера']");
    //локатор для кнопки Заказать
    private By rentalOrderButton=By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Заказать']");

    //конструктор класса
    public RentalConditions(WebDriver driver){
        this.driver=driver;
    }

    //метод для выбора даты доставки заказа
    public void chooseDatePicker(String date) {
        driver.findElement(datePicker).clear();
        driver.findElement(datePicker).sendKeys(date);
        driver.findElement(datePicker).sendKeys(Keys.ENTER);
    }
    //метод для выбора срока аренды
    public void selectRentalPeriod(String rentalPeriod) {
        var wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        WebElement trigger = wait.until(ExpectedConditions.elementToBeClickable(selectRentalPeriod));
        trigger.click();
        wait.until(ExpectedConditions.attributeContains(selectRentalPeriod, "class", "is-open"));
        String xpath = String.format(
                "//div[@class='Dropdown-option' and @role='option'][normalize-space(.)='%s']", rentalPeriod);

        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        option.click();
    }
     //метод для выбора цветовой гаммы у самоката
    public void chooseColorScooter(String color){
        if(color.equals("grey")){
            driver.findElement(grayColorScooter).click();
        }else if (color.equals("black")){
            driver.findElement(blackColorScooter).click();
        }
    }
    //меод для поля ввода комментарий для курьера
    public void setCommentForTheCourier(String comment) {
        driver.findElement(commentForTheCourier).clear();
        driver.findElement(commentForTheCourier).sendKeys(comment);
    }
    // нажатие на кнопку Заказать
    public void rentalOrderButtonClick(){
        driver.findElement(rentalOrderButton).click();
    }
    // метод, который объединяет ввод даты, срока оренды, цвета самоката, комментарий курьеру
    public void rentalInfo(String date,String rentalPeriod,String color,String comment){
        chooseDatePicker(date);
        selectRentalPeriod(rentalPeriod);
        chooseColorScooter(color);
        setCommentForTheCourier(comment);
    }
}
