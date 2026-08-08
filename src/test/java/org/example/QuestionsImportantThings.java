package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class QuestionsImportantThings {
    private WebDriver driver;

    // Создание драйвера перед каждым тестом
    @BeforeEach
    public void setUp(){
        driver=new ChromeDriver();
    }
    @Test
    public void checkQuestionsContent(){
        HomePage homePage=new HomePage(driver);
        homePage.openHomePage();
        homePage.questionButtonClick();
    }
    // Закрытие браузера
    @AfterEach
    public void tearDown(){
        driver.quit();
    }
}
