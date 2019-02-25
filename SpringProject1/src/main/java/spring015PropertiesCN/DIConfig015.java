package spring015PropertiesCN;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:PropertiesCN.properties")
public class DIConfig015 {

    @Value("${name}")
    private String name;

    @Bean(name = "PropertiesCN")
    public PropertiesCN getHouseA(){
        PropertiesCN propertiesCN=new PropertiesCN();
        propertiesCN.setName(name);
        return propertiesCN;
    }

}
