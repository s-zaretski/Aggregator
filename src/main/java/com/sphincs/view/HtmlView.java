package com.sphincs.view;

import java.util.List;

import com.sphincs.Controller;
import com.sphincs.vo.Vacancy;

public class HtmlView implements View {

    private Controller controller;

    @Override
    public void update(List<Vacancy> vacancies) {
        System.out.println(vacancies.size());
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void userCitySelectEmulationMethod() {
        controller.onCitySelect("java Брест");
    }
}
