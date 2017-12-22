package com.sdepta.filmwebmoviepicker.algorithm;

import org.apache.commons.lang.math.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.function.Function;

public class WeightedRandom {

    private static final Logger LOG = LoggerFactory.getLogger(WeightedRandom.class);

    public static <T> T getWeightedRandom(List<T> list, Function<T, Integer> weight) {
        int total = getTotalWeight(list, weight);
        LOG.debug("totalWeight=" + total);

        int random = RandomUtils.nextInt(total);
        LOG.debug("random=" + random);

        for (int i = 0; i < list.size(); i++) {
            final T elem = list.get(i);
            final Integer elemWeight = weight.apply(elem);
            if (random < elemWeight) {
                return elem;
            } else {
                random -= elemWeight;
            }
        }


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
