package com.shopify.api.constant;

public enum IMAGE_TYPE {
    USER,
    PRODUCT,
    MERCHANT,
    BANK;
    public static IMAGE_TYPE fromString(String value) {
        if (value != null) {
            for (IMAGE_TYPE type : IMAGE_TYPE.values()) {
                if (value.equalsIgnoreCase(type.name())) {
                    return type;
                }
            }
        }
        throw new IllegalArgumentException("No Type Match!" + IMAGE_TYPE.class + " with name " + value);
    }
}
