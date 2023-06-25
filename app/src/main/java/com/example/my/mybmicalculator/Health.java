package com.example.my.mybmicalculator;

public class Health {

    private String errorMsg;

    public String getErrorMsg() {
        return errorMsg;
    }

    public double calculateBMI(double heightCm, double weightKg) {
        double bmiIndex = -1;
        if (heightCm <= 0 && weightKg <= 0) {
            errorMsg = "Height and weight cannot be zero or less!";
        } else {
            bmiIndex = weightKg / ((heightCm / 100) * (heightCm / 100));
        }
        return bmiIndex;
    }

    public String determineCategory(double bmiIndex) {
        String bmiCategory = "";
        if (bmiIndex < 18.5) {
            bmiCategory = "Underweight";
        } else if (bmiIndex >= 18.5 && bmiIndex < 25) {
            bmiCategory = "Normal Weight";
        } else if (bmiIndex >= 25 && bmiIndex < 30) {
            bmiCategory = "Overweight";
        } else if (bmiIndex >= 30 && bmiIndex < 35) {
            bmiCategory = "Moderately Obese";
        } else if (bmiIndex >= 35 && bmiIndex < 40) {
            bmiCategory = "Severely Obese";
        } else if (bmiIndex >= 40) {
            bmiCategory = "Very Severely Obese";
        }
        return bmiCategory;
    }

    public String determineHealthRisk(double bmiIndex) {
        String bmiHealthRisk = "";
        if (bmiIndex < 18.5) {
            bmiHealthRisk = "Malnutrition Risk";
        } else if (bmiIndex >= 18.5 && bmiIndex < 25) {
            bmiHealthRisk = "Low Risk";
        } else if (bmiIndex >= 25 && bmiIndex < 30) {
            bmiHealthRisk = "Enhanced Risk";
        } else if (bmiIndex >= 30 && bmiIndex < 35) {
            bmiHealthRisk = "Medium Risk";
        } else if (bmiIndex >= 35 && bmiIndex < 40) {
            bmiHealthRisk = "High Risk";
        } else if (bmiIndex >= 40) {
            bmiHealthRisk = "Very High Risk";
        }
        return bmiHealthRisk;
    }

}