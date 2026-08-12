package org.example;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static utils.Constants.MakeOrderButton.TOP_BUTTON;

public class MetroStationIncorrectDataTest extends BaseTest {

    public static Object[][] getMetroStationIncorrectData() {
        return new Object[][]{
                {TOP_BUTTON, "Иван", "Иванов", "ул. Ленина, 1","79990000000",
                        null, null, null, "Выберите станцию", null}
        };
    }

    @ParameterizedTest
    @MethodSource("getMetroStationIncorrectData")
    public void checkIncorrectDataMetroStation(Enum chooseButton, String name, String surname, String address, String phoneNumber,
                                               String expectedNameError, String expectedSurnameError,
                                               String expectedAddressError, String expectedMetroError,
                                               String expectedPhoneNumberError)
                                                {

        HomePage homePage = new HomePage(driver);
        // открытие главной страницы Яндекс Самокат
        homePage.openHomePage();
        // метод для выбора кнопки Заказать на странице
        homePage.chooseOrderButton(chooseButton);
        // метод ожидания загрузки страницы
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("Order_Content__bmtHS")));

        //создание объекта для предоставления персональных данных на странице заказа
        OrderContentPage orderContentPage = new OrderContentPage(driver);
        // нажатие на кнопку далее, поле метро будет пустым, отоброзится подсказка "Выберите станцию"
        orderContentPage.orderNextButtonClick();
        // при незаполненном поле должна отобразится ошибка
        orderContentPage.checkMetroStationFieldError(expectedMetroError);
    }
}

