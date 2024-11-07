package com.example.DreamBig.controller;
import com.dreambigproject.dreambigstarter.service.SeasonalDiscountService;
import com.example.DreamBig.service.implementations.PriceService;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @GetMapping("/calculate-final-price")
    @PreAuthorize("hasRole('USER')")  // Тільки для користувачів з певною роллю
    public double calculateFinalPrice(@RequestParam int subscriptionLength) {
        double originalPrice = priceService.calculateTotalPrice(subscriptionLength);
        return discountService.calculateDiscountedPrice(originalPrice);
    }

}
