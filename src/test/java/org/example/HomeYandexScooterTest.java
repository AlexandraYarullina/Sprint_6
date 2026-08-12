package org.example;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static utils.Constants.MakeOrderButton.DOWN_BUTTON;
import static utils.Constants.MakeOrderButton.TOP_BUTTON;
import static utils.Constants.ScooterColors.BLACK;
import static utils.Constants.ScooterColors.GREY;
import static utils.Constants.TWO_DAYS;
import static utils.Constants.FOUR_DAYS;

public class HomeYandexScooterTest extends BaseTest{

    public static Object[][] getOrderInformation() {
        return new Object[][] {
                { TOP_BUTTON,"Валерия","Павлова","Новорижская, д.16","+7831535392",TWO_DAYS,BLACK,"Доставьте самокат не позднее 13:00"},
                { DOWN_BUTTON,"Дмитрий","Зайцев","Орловская, д.3","+78472656598",FOUR_DAYS,GREY,"Не забудьте учесть, что мне нужен самокат серого цвета"}
        };
    }
    @ParameterizedTest
    @MethodSource("getOrderInformation")
    public void checkOrderContent(Enum chooseButton, String name, String surname, String address, String phoneNumber, String rentalPeriod, Enum color, String comment) {
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
        orderContentPage.orderPersonalInfo(name,surname,address,phoneNumber);
        orderContentPage.setMetroStation();
        // нажатие на кнопку Далее
        orderContentPage.orderNextButtonClick();
        // метод ожидания загрузки страницы
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("Order_Content__bmtHS")));
        //создание объекта для условий аренды
        RentalConditions rentalConditions=new RentalConditions(driver);
        //ввод и выбор двнных на странице
        rentalConditions.rentalInfo(rentalPeriod,color,comment);
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
        waitingOrderProcessing.orderIsProcessedText();
    }
}
