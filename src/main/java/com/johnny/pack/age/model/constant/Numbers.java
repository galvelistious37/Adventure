package com.johnny.pack.age.model.constant;

public enum Numbers {
    NEGATIVE_ONE(-1),
    ZERO(0),
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    SEVENTEEN(17),
    EIGHTEEN(18),
    TWENTY(20),
    NINETY_NINE(99),
    ONE_HUNDRED(100);

    private int value;

    Numbers(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
