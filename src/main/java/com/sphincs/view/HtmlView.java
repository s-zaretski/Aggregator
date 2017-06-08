package com.sphincs.view;

import java.util.List;

import com.sphincs.Controller;
import com.sphincs.view.parsers.AdvertParser;
import com.sphincs.view.parsers.DatingParser;
import com.sphincs.view.parsers.VacancyParser;
import com.sphincs.vo.Advert;
import com.sphincs.vo.Contact;
import com.sphincs.vo.Vacancy;

public class HtmlView<T> implements View<T> {

    private Controller controller;

    @Override
    public void update(List<T> responses) {
        if (responses.get(0) instanceof Vacancy) {
            new VacancyParser().produceVacancyFile((List<Vacancy>) responses);
            return;
        }
        if (responses.get(0) instanceof Contact) {
            new DatingParser().produceDatingFile((List<Contact>) responses);
            return;
        }
        if (responses.get(0) instanceof Advert) {
            new AdvertParser().produceAdvertFile((List<Advert>) responses);
        }
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void runInvoice(String... searchRequest) {
        controller.runInvoice(searchRequest);
    }

}
