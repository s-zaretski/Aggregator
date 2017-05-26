package com.sphincs.model;

import java.util.List;

import com.sphincs.vo.Vacancy;

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

    public List<Vacancy> getVacancies(String searchRequest) {
        return strategy.getVacancies(searchRequest);
    }
}
