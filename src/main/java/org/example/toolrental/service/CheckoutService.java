package org.example.toolrental.service;

import org.example.toolrental.model.RentalAgreement;
import org.example.toolrental.model.Tool;
import org.example.toolrental.model.ToolType;
import org.example.toolrental.util.DateCalculator;

import java.time.LocalDate;

public class CheckoutService {
    public RentalAgreement processCheckout(String toolCode, int rentalDays, int discountPercent, LocalDate checkoutDate) {
        validateInput(rentalDays, discountPercent);
        Tool tool = getToolByCode(toolCode);

        LocalDate dueDate = calculateDueDate(checkoutDate, rentalDays);
        int chargeDays = calculateChargeDays(tool.getType(), checkoutDate, dueDate);
        double preDiscountCharge = calculatePreDiscountCharge(tool.getType().getDailyCharge(), chargeDays);
        double discountAmount = calculateDiscountAmount(preDiscountCharge, discountPercent);
        double finalCharge = preDiscountCharge - discountAmount;

        return new RentalAgreement(tool, rentalDays, checkoutDate, dueDate, chargeDays, preDiscountCharge, discountPercent, discountAmount, finalCharge);
    }

    private void validateInput(int rentalDays, int discountPercent) {
        if (rentalDays < 1) {
            throw new IllegalArgumentException("Rental day count must be 1 or greater");
        }
        if (discountPercent < 0 || discountPercent > 100) {
            throw new IllegalArgumentException("Discount percent must be in the range 0-100");
        }
    }

    private Tool getToolByCode(String toolCode) {
        switch (toolCode) {
            case "LADW":
                return new Tool("LADW", ToolType.LADDER, "Werner");
            case "CHNS":
                return new Tool("CHNS", ToolType.CHAINSAW, "Stihl");
            case "JAKD":
                return new Tool("JAKD", ToolType.JACKHAMMER, "DeWalt");
            case "JAKR":
                return new Tool("JAKR", ToolType.JACKHAMMER, "Ridgid");
            default:
                throw new IllegalArgumentException("Invalid tool code");
        }
    }

    private LocalDate calculateDueDate(LocalDate checkoutDate, int rentalDays) {
        return checkoutDate.plusDays(rentalDays);
    }

    private int calculateChargeDays(ToolType toolType, LocalDate checkoutDate, LocalDate dueDate) {
        int chargeDays = 0;
        LocalDate currentDate = checkoutDate.plusDays(1);
        while (!currentDate.isAfter(dueDate)) {
            if (DateCalculator.isChargeableDay(currentDate, toolType)) {
                chargeDays++;
            }
            currentDate = currentDate.plusDays(1);
        }
        return chargeDays;
    }

    private double calculatePreDiscountCharge(double dailyCharge, int chargeDays) {
        return dailyCharge * chargeDays;
    }

    private double calculateDiscountAmount(double preDiscountCharge, int discountPercent) {
        return preDiscountCharge * discountPercent / 100;
    }
}
