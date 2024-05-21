package com.shopify.api.utils;

import java.text.DecimalFormat;

public class NumberFormatUtils {
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        DecimalFormat df = new DecimalFormat("#.#");
        df.setMaximumFractionDigits(places);
        df.setGroupingUsed(false);
        return Double.parseDouble(df.format(value));
    }
}
