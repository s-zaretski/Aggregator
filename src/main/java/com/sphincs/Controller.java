package com.sphincs;

import com.sphincs.model.Model;

public class Controller {

    private Model model;

    public Controller(Model model) {
        this.model = model;
    }

    public Model getModel() {
        return model;
    }

    public void findJob(String searchRequest) {
        model.findJob(searchRequest);
    }
}
