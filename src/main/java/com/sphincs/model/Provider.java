package com.sphincs.model;

import java.util.List;

public class Provider {

    private Strategy strategy;

    public Provider(Strategy strategy) {
        this.strategy = strategy;
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public <T> List<T> runInvoice(String... searchRequest) {
        return strategy.runInvoice(searchRequest);
    }
}
