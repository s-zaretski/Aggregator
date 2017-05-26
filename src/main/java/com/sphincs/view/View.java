package com.sphincs.view;

import java.util.List;

import com.sphincs.Controller;

public interface View<T> {

    void update(List<T> vacancies);

    void setController(Controller controller);

}
