package com.guy.common.utils;

public class ConverterUtil {

    public static double cmToFeet(double cm) {
        double inches = cm / 2.54; // convert cm to inches
        double feet = inches / 12; // convert inches to feet
        return feet;
    }

    public static double feetToCm(double feet) {
        double inches = feet * 12; // convert feet to inches
        double cm = inches * 2.54; // convert inches to cm
        return cm;
    }

    public static double kgToLbs(double kg) {
        return kg * 2.20462;
    }

    public static double lbsToKg(double lbs) {
        return lbs / 2.20462;
    }
}
