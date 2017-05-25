package com.sphincs.model;

import java.util.ArrayList;
import java.util.List;

import com.sphincs.view.View;
import com.sphincs.vo.Vacancy;

public class Model {

    private View view;
    private List<Provider> providers;

    public Model(View view, List<Provider> providers) {
        this.view = view;
        this.providers = providers;
    }

    public void selectCity(String cityName) {
        List<Vacancy> vacancies = new ArrayList<>();
        providers.stream()
                .forEach(provider -> vacancies.addAll(provider.getJavaVacancies(cityName)));
        view.update(vacancies);
    }

}
