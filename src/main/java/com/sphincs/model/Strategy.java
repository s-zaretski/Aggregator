package com.sphincs.model;

import java.util.List;

public interface Strategy {

    String[] getInvoice();

    void setInvoice(String[] invoice);

    <T> List<T> runInvoice(String... searchRequest);

}
