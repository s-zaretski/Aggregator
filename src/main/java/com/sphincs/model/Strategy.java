package com.sphincs.model;

import java.util.List;

import com.sphincs.vo.Vacancy;

public interface Strategy {

    List<Vacancy> getVacancies(String searchRequest);

}
