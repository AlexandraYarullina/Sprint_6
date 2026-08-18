package org.example;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import static utils.Constants.MakeOrderButton.TOP_BUTTON;

public class IncorrectDataOrderContentTest extends BaseTest {

    public static Object[][] getIncorrectData() {
        return new Object[][]{
                {TOP_BUTTON, "name", "Иванов", "ул. Ленина, 1", "+79990000000",
                        "Введите корректное имя", null, null, null, null},
                {TOP_BUTTON, "Иван", "surname", "ул. Ленина, 1", "+79990000000",
                         null, "Введите корректную фамилию", null, null, null},
                {TOP_BUTTON, "Иван", "Иванов", "address", "+79990000000",
                         null, null, "Введите корректный адрес", null, null},
                {TOP_BUTTON, "Иван", "Иванов", "ул. Ленина, 1", "numberPhone",
                        null, null, null, null, "Введите корректный номер"}

        };
    }

    @ParameterizedTest
    @MethodSource("getIncorrectData")
    public void checkIncorrectDataOrderContent(Enum chooseButton, String name, String surname, String address, String phoneNumber,
                                               String expectedNameError, String expectedSurnameError,
                                               String expectedAddressError, String expectedMetroError,
                                               String expectedPhoneNumberError) {

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

        // некорректный ввод имени и проверка отображения ошибки
        orderContentPage.checkNameFieldError(name,expectedNameError);
        // некорректный ввод фамилии и проверка отображения ошибки
         orderContentPage.checkSurnameFieldError(surname,expectedSurnameError);
        // некорректный ввод адреса и проверка отображения ошибки
         orderContentPage.checkAddressFieldError(address,expectedAddressError);
        // некорректный ввод номера телефона и проверка отображения ошибки
         orderContentPage.checkPhoneNumberFieldError(phoneNumber,expectedPhoneNumberError);
    }
}

