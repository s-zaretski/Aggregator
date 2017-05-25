package com.sphincs;

import java.util.List;

import com.sphincs.model.Model;
import com.sphincs.vo.Vacancy;

public class Controller {

    private List<Vacancy> vacancies;
    private Model model;

    public Controller(Model model) {
        this.model = model;
    }

    public List<Vacancy> getVacancies() {
        return vacancies;
    }

    public void onCitySelect(String cityName) {
        model.selectCity(cityName);
    }
}
