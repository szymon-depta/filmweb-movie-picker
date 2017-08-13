package com.sdepta.filmwebmoviepicker.algorithm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.function.Function;

public class WeightedRandom {

    private static final Logger LOG = LoggerFactory.getLogger(WeightedRandom.class);

    public static <T> Object getWeightedRandom(List<T> list, Function<T, Integer> weight) {
        int total = getTotalWeight(list, weight);

        return list.get(0);
    }

    private static <T> int getTotalWeight(List<T> list, Function<T, Integer> weight) {
        int total = 0;
        for (T elem : list) {
            total += weight.apply(elem);
        }
        return total;
    }
}
