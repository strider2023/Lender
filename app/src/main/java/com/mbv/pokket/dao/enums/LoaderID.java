package com.mbv.pokket.dao.enums;

/**
 * Created by arindamnath on 18/01/16.
 */
public enum LoaderID {
    EVENTS(1),
    TIMELINE(2),
    OPEN_TRANSACTIONS(3),
    CLOSE_TRANSACTION(4),
    PENDING_TRANSACTIONS(5),
    AVAILABLE_BORROWER(6);

    private final int value;
    LoaderID (final int newValue) {
        value = newValue;
    }
    public int getValue() { return value; }
}
