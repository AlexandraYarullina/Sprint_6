package org.example;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static utils.Constants.*;

public class QuestionsImportantThingsTest extends BaseTest{

    public static Object[][] getFAQInformation() {
        return new Object[][] {
                {0, ANSWER_0},
                {1, ANSWER_1},
                {2, ANSWER_2},
                {3, ANSWER_3},
                {4, ANSWER_4},
                {5, ANSWER_5},
                {6, ANSWER_6},
                {7, ANSWER_7}
        };
    }
    @ParameterizedTest
    @MethodSource("getFAQInformation")
    public void checkAnswerFAQ(int index,String expectedText){
        HomePage homePage=new HomePage(driver);
        // открытие главной страницы
        homePage.openHomePage();
        // прокрутка до вопросов FAQ
        homePage.scrollPageDownToFAQ();
        // нажатие на вопросы
        homePage.questionButtonClick(index);
        // проверка, что при клики на вопросы появляется ожидаемый ответ
        homePage.checkAnswerText(index,expectedText);
    }
}
