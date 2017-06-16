package com.sphincs.model;

import java.util.ArrayList;
import java.util.List;

import com.sphincs.view.View;

public class Model<T> {

    private View view;
//    private List<Provider> providers;
    private Provider provider;

    public Model(View view, Provider provider) {
        this.view = view;
        this.provider = provider;
    }

    public Provider getProvider() {
        return provider;
    }

    public void runInvoice(String... searchRequest) {
        List<T> result = new ArrayList<>();
        result.addAll(provider.runInvoice(searchRequest));
        view.update(result);
    }

}
