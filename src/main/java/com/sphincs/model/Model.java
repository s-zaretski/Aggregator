package com.sphincs.model;

import java.util.ArrayList;
import java.util.List;

import com.sphincs.view.View;

public class Model<T> {

    private View view;
    private List<Provider> providers;

    public Model(View view, List<Provider> providers) {
        this.view = view;
        this.providers = providers;
    }

    public List<Provider> getProviders() {
        return providers;
    }

    public void runInvoice(String... searchRequest) {
        List<T> result = new ArrayList<>();
        providers.forEach(provider -> result.addAll(provider.runInvoice(searchRequest)));
        view.update(result);
    }

}
