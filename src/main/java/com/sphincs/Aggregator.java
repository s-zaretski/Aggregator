package com.sphincs;

import static java.util.Collections.singletonList;

import java.util.List;

import com.sphincs.model.HHStrategy;
import com.sphincs.model.Model;
import com.sphincs.model.Provider;
import com.sphincs.view.HtmlView;

public class Aggregator {

    public static void main(String[] args) {
        HtmlView view = new HtmlView();
        List<Provider> providers = singletonList(new Provider(new HHStrategy()));

        Model model = new Model(view, providers);
        Controller controller = new Controller(model);

        view.setController(controller);
        view.runInvoice("java Брест");

    }

}
