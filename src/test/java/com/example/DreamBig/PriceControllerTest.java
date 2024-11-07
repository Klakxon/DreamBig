package com.example.dreambig;

import com.dreambigproject.dreambigstarter.service.SeasonalDiscountService;
import com.example.DreamBig.controller.PriceController;
import com.example.DreamBig.repository.UserRepository;
import com.example.DreamBig.security.JwtUtil;
import com.example.DreamBig.service.implementations.PriceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PriceController.class)
@Import({TestSecurityConfig.class, JwtUtil.class})
public class PriceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SeasonalDiscountService discountService;

    @MockBean
    private PriceService priceService;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void whenCalculateFinalPrice_thenExecuteBusinessLogic() throws Exception {

        int subscriptionLength = 12;
        double basePricePerMonth = 10.0;
        double originalPrice = basePricePerMonth * subscriptionLength;

        when(priceService.calculateTotalPrice(subscriptionLength)).thenReturn((int) originalPrice);

        double discountRate = 0.2;
        double discountedPrice = originalPrice * (1 - discountRate);

        when(discountService.calculateDiscountedPrice(originalPrice)).thenReturn(discountedPrice);

        mockMvc.perform(get("/calculate-final-price")
                        .param("subscriptionLength", String.valueOf(subscriptionLength))
                        .with(user("admin").password("password").roles("ADMIN")))
                .andExpect(status().isOk())
                .andExpect(content().string(String.valueOf(discountedPrice)));

    }
}
