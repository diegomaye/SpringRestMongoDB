package com.diego.pm.entities;

public enum SaleStatus {
    PAID("PAID"),
    PENDING("PENDING"),
    REJECTED("REJECTED");
    private String description = null;

    private SaleStatus(String description){
        this.description = description;
    }

    @Override
    public String toString() {
        return this.description;
    }

}
