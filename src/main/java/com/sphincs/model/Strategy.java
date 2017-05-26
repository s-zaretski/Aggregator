package com.sphincs.model;

import java.util.List;

public interface Strategy {

    <T> List<T> runInvoice(String... searchRequest);

}
