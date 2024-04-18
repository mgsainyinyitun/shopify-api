package com.shopify.api.constant;

public enum TYPE {
    USER,
    PRODUCT,
    MERCHANT;
    public static TYPE fromString(String value) {
        if (value != null) {
            for (TYPE type : TYPE.values()) {
                if (value.equalsIgnoreCase(type.name())) {
                    return type;
                }
            }
        }
        throw new IllegalArgumentException("No Type Match!" + TYPE.class + " with name " + value);
    }
}
