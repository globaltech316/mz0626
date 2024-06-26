package org.example.toolrental.service;

import org.example.toolrental.model.RentalAgreement;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.time.LocalDate;

public class CheckoutServiceTest {

    private CheckoutService checkoutService;

    @Before
    public void setUp() {
        checkoutService = new CheckoutService();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidDiscount() {
        checkoutService.processCheckout("JAKR", 5, 101, LocalDate.of(2015, 9, 3));
    }

    @Test
    public void testValidCheckouts() {
        Object[][] testCases = {
                {"LADW", LocalDate.of(2020, 7, 2), 3, 10, 3.58},
                {"CHNS", LocalDate.of(2015, 7, 2), 5, 25, 3.35},
                {"JAKD", LocalDate.of(2015, 9, 3), 6, 0, 8.97},
                {"JAKR", LocalDate.of(2015, 7, 2), 9, 0, 14.95},
                {"JAKR", LocalDate.of(2020, 7, 2), 4, 50, 1.49}
        };

        for (Object[] testCase : testCases) {
            RentalAgreement agreement = checkoutService.processCheckout(
                    (String) testCase[0],
                    (int) testCase[2],
                    (int) testCase[3],
                    (LocalDate) testCase[1]
            );

            assertEquals(testCase[0], agreement.getTool().getCode());
            assertEquals(testCase[2], agreement.getRentalDays());
            assertEquals(testCase[1], agreement.getCheckoutDate());
            assertEquals(testCase[3], agreement.getDiscountPercent());
            assertEquals((double) testCase[4], agreement.getFinalCharge(), 0.01);
        }
    }
}
