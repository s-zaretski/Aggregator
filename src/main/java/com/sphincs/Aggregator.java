package com.sphincs;

import java.util.ArrayList;

import com.sphincs.model.HHStrategy;
import com.sphincs.model.Model;
import com.sphincs.model.Provider;
import com.sphincs.view.HtmlView;

public class Aggregator {

    public static void main(String[] args) {
        Provider hhProvider = new Provider(new HHStrategy());
        HtmlView view = new HtmlView();

        ArrayList<Provider> providers = new ArrayList<>();
        providers.add(hhProvider);

        Model model = new Model(view, providers);
        Controller controller = new Controller(model);

        view.setController(controller);
        view.findJob("java Брест");

    }

}
