package com.example.DreamBig.service.implementations;

import com.example.DreamBig.config.PriceConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class PriceService {

    private final PriceConfig priceConfig;

    public PriceService(PriceConfig priceConfig) {
        this.priceConfig = priceConfig;
    }

    @Cacheable(value = "priceCache", key = "#subscriptionLength")
    public int calculateTotalPrice(int subscriptionLength) {
        int standardPrice = priceConfig.getStandardPrice();
        return subscriptionLength * standardPrice;
    }
}
