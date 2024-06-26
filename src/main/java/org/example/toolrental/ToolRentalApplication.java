package org.example.toolrental;

import org.example.toolrental.model.RentalAgreement;
import org.example.toolrental.service.CheckoutService;

import java.time.LocalDate;

public class ToolRentalApplication {
    public static void main(String[] args) {
        CheckoutService checkoutService = new CheckoutService();

        try {
            RentalAgreement agreement = checkoutService.processCheckout("LADW", 3, 10, LocalDate.of(2020, 7, 2));
            agreement.printAgreement();
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
