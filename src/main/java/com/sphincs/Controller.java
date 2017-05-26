package com.sphincs;

import com.sphincs.model.Model;
import com.sphincs.model.Provider;

public class Controller {

    private Model model;

    public Controller(Model model) {
        this.model = model;
    }

    public Model<Provider> getModel() {
        return model;
    }

    public void runInvoice(String... searchRequest) {
        model.runInvoice(searchRequest);
    }
}
