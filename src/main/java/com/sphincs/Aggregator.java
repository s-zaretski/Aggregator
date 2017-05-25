package com.sphincs;

import java.util.Arrays;

import com.sphincs.model.HHStrategy;
import com.sphincs.model.Provider;

public class Aggregator {

    public static void main(String[] args) {
        Provider hhProvider = new Provider(new HHStrategy());
        Controller controller = new Controller(Arrays.asList(hhProvider));
        controller.scan();
        controller.getVacancies()
                .stream()
                .forEach(System.out::println);
    }

}
