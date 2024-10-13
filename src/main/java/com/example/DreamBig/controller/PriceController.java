package com.example.DreamBig.controller;
import com.dreambigproject.DreamBigStarter.service.SeasonalDiscountService;
import com.example.DreamBig.service.implementations.PriceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PriceController {

    private final SeasonalDiscountService discountService;
    private final PriceService priceService;

    public PriceController(SeasonalDiscountService discountService, PriceService priceService) {
        this.discountService = discountService;
        this.priceService = priceService;
    }

    /**
     * Метод для обчислення кінцевої вартості абонементу з урахуванням стандартної ціни,
     * довжини підписки та сезонної знижки.
     * @param subscriptionLength тривалість підписки у місяцях
     * @return кінцева ціна з урахуванням знижки
     */
    @GetMapping("/calculate-final-price")
    public double calculateFinalPrice(@RequestParam int subscriptionLength) {
        double originalPrice = priceService.calculateTotalPrice(subscriptionLength);
        return discountService.calculateDiscountedPrice(originalPrice);
    }
}
