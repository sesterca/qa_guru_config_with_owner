import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import configuration.WebConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;
import static org.assertj.core.api.Assertions.assertThat;

public class SimpleTests {

    @BeforeEach
    public void setUp(){
        System.setProperty("host", "remote");
        WebConfig config = ConfigFactory.create(WebConfig.class, System.getProperties());


        Configuration.browser = config.browser();
        Configuration.browserVersion = config.browserVersion();
        Configuration.browserSize=config.browserSize();
        Configuration.remote = config.remoteUrl();
    }
    @AfterEach
    public void tearDown(){
        closeWebDriver();
    }

    @Test
    public void simpleTest(){
        open("https://www.google.com/");
        $(By.name("q")).setValue("Яндекс").pressEnter();
        List<String> menu = $$x(".//div[@class='hdtb-mitem']").texts();
        assertThat(menu).contains("Новости", "Карты", "Картинки", "Видео");
    }
}
