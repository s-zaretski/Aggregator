package com.sphincs.view;

import java.util.List;

import com.sphincs.Controller;
import com.sphincs.vo.Vacancy;

public interface View {

    void update(List<Vacancy> vacancies);

    void setController(Controller controller);

}
