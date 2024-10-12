package com.example.DreamBig.service.implementations;

import com.example.DreamBig.config.PriceConfig;
import org.springframework.stereotype.Service;

@Service
public class PriceService {

    private final PriceConfig priceConfig;

    public PriceService(PriceConfig priceConfig) {
        this.priceConfig = priceConfig;
    }

    public int calculateTotalPrice(int subscriptionLength) {
        int standardPrice = priceConfig.getStandardPrice();
        return subscriptionLength * standardPrice;
    }
}
