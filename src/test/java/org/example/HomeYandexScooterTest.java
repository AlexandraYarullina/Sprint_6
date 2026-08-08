package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class HomeYandexScooterTest {
    private WebDriver driver;

    // Создание драйвера перед каждым тестом
    @BeforeEach
    public void setup(){
        driver = new ChromeDriver();
    }

    public static Object[][] getOrderInformation() {
        return new Object[][] {
                { "up","Валерия","Павлова","Новорижская, д.16","+7831535392","18.07.2026","двое суток","black","Доставьте самокат не позднее 13:00"},
                { "down","Дмитрий","Зайцев","Орловская, д.3","+78472656598","03.12.2025","четверо суток","grey","Не забудьте учесть, что мне нужен самокат серого цвета"}
        };
    }
    @ParameterizedTest
    @MethodSource("getOrderInformation")
    public void checkOrderContent(String chooseButton, String name, String surname, String place, String phoneNumber, String date,String rentalPeriod, String color, String comment) {
        // создание объета главной страницы
        HomePage homePage=new HomePage(driver);
        // открыть главную страницу
        homePage.openHomePage();
        // метод для выбора кнопки Заказать на странице
        homePage.chooseOrderButton(chooseButton);
        // метод ожидания загрузки страницы
        new WebDriverWait(driver, Duration.ofSeconds(3))
               .until(ExpectedConditions.visibilityOfElementLocated(By.className("Order_Content__bmtHS")));

        //создание объекта для предоставления персональных данных на странице заказа
        OrderContentPage orderContentPage=new OrderContentPage(driver);
        // ввод данных на странице
        orderContentPage.orderPersonalInfo(name,surname,place,phoneNumber);
        orderContentPage.setMetroStation();
        // нажатие на кнопку Далее
        orderContentPage.orderNextButtonClick();
        // метод ожидания загрузки страницы
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("Order_Content__bmtHS")));
        //создание объекта для условий аренды
        RentalConditions rentalConditions=new RentalConditions(driver);
        //ввод и выбор двнных на странице
        rentalConditions.rentalInfo(date,rentalPeriod,color,comment);
        // нажатие на кнопку Заказать
        rentalConditions.rentalOrderButtonClick();

        OrderFormPage orderFormPage=new OrderFormPage(driver);
        // создан экземпляра класса WebDriverWait с таймаутом 5 секунд
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("Order_Content__bmtHS")));
        // нажатие на кнопку Да, подтверждение заказа
        orderFormPage.yesOrderButtonClick();

        // создание объекта страницы об успешном создании заказа
        WaitingOrderProcessing waitingOrderProcessing=new WaitingOrderProcessing(driver);
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("Order_Modal__YZ-d3")));
        // проверить, что появилось окно с сообщением об успешном создании заказа
        assertTrue(waitingOrderProcessing.orderIsProcessedTextIsDisplayed());
    }

    // Закрытие браузера
    @AfterEach
    public void tearDown(){
        driver.quit();
    }
}
