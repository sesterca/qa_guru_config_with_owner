package configuration;

import org.aeonbits.owner.Config;

@Config.Sources(
        {"file:/tmp/token.properties",
        "classpath:api.properties"})
public interface ApiConfig extends Config{

    @Key("base.url")
    String baseUrl();

    @Key("token")
    String token();
}
