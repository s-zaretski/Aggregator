package com.sphincs.model;

import java.util.List;

public class Provider {

    private Strategy strategy;
    private String[] invoice;

    public Provider(Strategy strategy) {
        this.strategy = strategy;
        this.invoice = strategy.getInvoice();
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public String[] getInvoice() {
        return invoice;
    }

    public void setInvoice(final String[] invoice) {
        this.invoice = invoice;
    }

    public <T> List<T> runInvoice(String... searchRequest) {
        return strategy.runInvoice(searchRequest);
    }
}
