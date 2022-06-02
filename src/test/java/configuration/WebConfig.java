package configuration;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:${host}.properties"})
public interface WebConfig extends Config {

    //Имя браузера
    @Key("browser")
    @DefaultValue("OPERA")
    String browser();

    //Версия браузера
    @Key("browser.version")
    @DefaultValue("100.0")
    String browserVersion();

    //Размер окна
    @Key("browser.size")
    @DefaultValue("1920x1080")
    String browserSize();

    //Расположение окна браузера
    @Key("browser.position")
    @DefaultValue("0x0")
    String browserPosition();

    //Локальный или удаленный (RemoteWebDriver)
    @Key("remote.url")
    String remoteUrl();
}