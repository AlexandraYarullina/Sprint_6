package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static utils.Constants.NUMBER_ORDER;

public class OrderStatusTest extends BaseTest{

    @Test
    public void orderStatusNavigation() {

        HomePage homePage=new HomePage(driver);
        // открытие главной страницы Яндекс Самокат
        homePage.openHomePage();
        // нажатие на кнопку Статус заказа
        homePage.orderStatusButtonClick();
        // нажатие на поле номер заказа
        homePage.numberOrderClick();
        // ввода номера заказа
        homePage.setNumberOrder(NUMBER_ORDER);
        // нажатие на кнопку Go!
        homePage.goButtonClick();
        // проверить, что отобразилась картинка с текстом "Такого заказа нет"
        assertTrue(homePage.orderStatusIsDisplayed());
    }
}
