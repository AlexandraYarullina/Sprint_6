package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static utils.Constants.MakeOrderButton.DOWN_BUTTON;
import static utils.Constants.MakeOrderButton.TOP_BUTTON;
import static utils.Constants.URL_HOME_PAGE;

import java.time.Duration;

// класс главной страницы Яндекс Самокат
public class HomePage {
    private WebDriver driver;
    // локатор для кнопки заказать вверху страницы
    private By orderUpButton = By.xpath("//div[@class='Header_Nav__AGCXC']/button[(@class='Button_Button__ra12g' and text()='Заказать')]");
    // локатор для кнопки заказать внизу страницы
    private By orderDowmButton = By.xpath("//div[@class='Home_FinishButton__1_cWm']/button");
    // локатор для поиска вопросов
    private final String questionLocator = "accordion__heading-%s";
    // локатор для поиска ответов, в соответствии с вопросами
    private final String answerLocator = "accordion__panel-%s";

    // локатор для поиска логотипа Самокат
    private By logoScooterLocator=By.xpath("//div[@class='Header_Logo__23yGT']/a[2]/img[@alt='Scooter']");
    // локатор для поиска логотипа Яндекс
    private By logoYandexLocator=By.xpath("//img[@alt='Yandex']");
    // локатор для поиска кнопки Статус заказа
    private By orderStatusLocator=By.className("Header_Link__1TAG7");
    // локатор для поиска ввода номера заказа
    private By numberOrderLocator=By.xpath("//div[@class='Input_InputContainer__3NykH']/input");
    // локатор для кнопки Go!
    private By goLocator=By.xpath("//div[@class='Header_SearchInput__3YRIQ']/button");
    // локатор для отображении картинки, что с надписью "Такого заказа нет"
    private By orderVerificationLocator=By.xpath("//div[@class='Track_NotFound__6oaoY']/img");

    // конструктор класса
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }
    // открытие главной страницы Яндекс Самокат
    public void openHomePage(){
        driver.get(URL_HOME_PAGE);
    }

    // метод для нажатия на кнопку заказать в вверху страницы
    public void orderUpButtonClick() {
        driver.findElement(orderUpButton).click();
    }

    // метод для нажатия на кнопку заказать внизу страницы
    public void orderDownButtonClick() {
        // прокрутить страницу вниз до кнопки Заказать
        WebElement element = driver.findElement(orderDowmButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        driver.findElement(orderDowmButton).click();
    }

    // метод для выбора кнопки Заказать, в зависимости от расположения
    public void chooseOrderButton(Enum chooseButton) {
        if (chooseButton.equals(TOP_BUTTON)) {
            orderUpButtonClick();
        } else if (chooseButton.equals(DOWN_BUTTON)) {
            orderDownButtonClick();
        }
    }
    // метод для на нажатия на кнопки, проход по вопросам
    public void scrollPageDownToFAQ(){
        WebElement element = driver.findElement(By.id(format(questionLocator, 0)));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    // метод для на нажатия на кнопки, проход по вопросам
    public void questionButtonClick(int index) {
        driver.findElement(By.id(String.format(questionLocator, index))).click();
    }

    // метод для получения ответов и проверка на равенство значений
    public void checkAnswerText(int index,String expectedText) {
        String resultText = driver.findElement(By.id(String.format(answerLocator, index))).getText();
        assertEquals(expectedText, resultText,"Текст ответа содержит ошибку");
    }

//    Дополнительные тестовые сценарии
//
//    Проверить: если нажать на логотип «Самоката», попадёшь на главную страницу «Самоката».
//    Проверить: если нажать на логотип Яндекса, в новом окне откроется главная страница Яндекса.
//    Проверить ошибки для всех полей формы заказа.
//    Проверить: если ввести неправильный номер заказа, попадёшь на страницу статуса заказа. На ней должно быть написано, что такого заказа нет.

    // нажатие на логотип самокат, переход на главную страницу Яндекс.Самокат
    public void logoScooterClick(){
        driver.findElement(logoScooterLocator).click();
    }
    // нажатие на логотип Яндекса, в новом окне откроется главная страница Яндекса
    public void logoYandexClick(){
        driver.findElement(logoYandexLocator).click();
    }
    // нажатие на кнопку Статус заказа
    public void orderStatusButtonClick(){
        driver.findElement(orderStatusLocator).click();
    }
    // нажатие на поле номер заказа
    public void numberOrderClick(){
        var wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(numberOrderLocator));
        driver.findElement(numberOrderLocator).click();
    }
    // метод для ввода номера заказа
    public void setNumberOrder(String number){
        driver.findElement(numberOrderLocator).sendKeys(number);
    }
    // нажатие на кнопку Go!
    public void goButtonClick(){
        var wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(goLocator));
        driver.findElement(goLocator).click();
    }
    //метод возвращает истину, если на форме отображена картинка с текстом "Такого заказа нет"
    public boolean orderStatusIsDisplayed() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("Track_NotFound__6oaoY")));
        return driver.findElement(orderVerificationLocator).isDisplayed();
    }
}
