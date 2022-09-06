package com.test.thread.pool;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;

/**
 * @author trangle
 */
public class AddCallableImpl implements Callable<BigDecimal> {

    public AddCallableImpl(List<BigDecimal> valueList) {
        this.valueList = valueList;
    }

    private List<BigDecimal> valueList;

    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public BigDecimal call() throws Exception {
        BigDecimal result = new BigDecimal(2);
        Random random = new Random();
        for (BigDecimal value : valueList) {
            int index = random.nextInt(4);
            if (index == 0) {
                result = result.add(value);
            } else if (index == 1) {
                result = result.subtract(value);
            } else if (index == 2) {
                result = result.multiply(value);
            } else {
                result = result.divide(value, 2, RoundingMode.HALF_UP);
            }
        }
        return result;
    }


}
