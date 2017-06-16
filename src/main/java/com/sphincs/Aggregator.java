package com.sphincs;

import java.util.Arrays;
import java.util.List;

import com.sphincs.model.*;
import com.sphincs.view.HtmlView;

public class Aggregator {

    public static void main(String[] args) {
        HtmlView view = new HtmlView();
        Provider hhProvider = new Provider(new HHStrategy());
        Provider datingProvider = new Provider(new DatingStrategy());
        Provider advertProvider = new Provider(new AdvertStrategy());
        advertProvider.setInvoice(new String[]{"2", "2"});
        List<Provider> providers = Arrays.asList(
                hhProvider,
                datingProvider,
                advertProvider);

        providers.forEach(provider -> {
            Model model = new Model(view, provider);
            Controller controller = new Controller(model);
            view.setController(controller);
            view.runInvoice(provider.getInvoice());
        });
    }
}
