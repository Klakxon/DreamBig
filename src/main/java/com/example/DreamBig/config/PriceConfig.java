package com.example.DreamBig.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PriceConfig {
    @Value ("${business.feature.subscription.standardprice:1000}")
    private int standardPrice;

    public void setStandardPrice(int standardPrice){
        this.standardPrice = standardPrice;
    }

    public int getStandardPrice(){
        return standardPrice;
    }

}
