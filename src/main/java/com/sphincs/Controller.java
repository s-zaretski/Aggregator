package com.sphincs;

import java.util.ArrayList;
import java.util.List;

import com.sphincs.model.Provider;
import com.sphincs.vo.Vacancy;

public class Controller {

    private List<Provider> providers;
    private List<Vacancy> vacancies;

    public Controller(List<Provider> providers) {
        this.providers = providers;
    }

    public List<Vacancy> getVacancies() {
        return vacancies;
    }

    @Override
    public String toString() {
        return "Controller{" + "providers=" + providers + '}';
    }

    public void scan() {
        vacancies = new ArrayList<>();
        for (Provider currentProvider : providers) {
            vacancies.addAll(currentProvider.getJavaVacancies("java"));
        }
    }
}
