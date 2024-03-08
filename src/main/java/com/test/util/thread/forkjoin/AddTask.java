package com.test.util.thread.forkjoin;

import java.util.List;
import java.util.concurrent.RecursiveTask;

/**
 * @author trangle
 */
public class AddTask extends RecursiveTask<Long> {

    List<Long> numList;

    public AddTask(List<Long> numList) {
        this.numList = numList;
    }

    /**
     * The main computation performed by this task.
     *
     * @return the result of the computation
     */
    @Override
    protected Long compute() {
        if (numList.size() <= ForkJoinConstants.BATCH_NUMBER) {
            return add(numList);
        } else {
            long startTime = System.currentTimeMillis();
            AddTask aTask = new AddTask(numList.subList(0, numList.size() / 2));
            AddTask bTask = new AddTask(numList.subList(numList.size() / 2, numList.size()));
            invokeAll(aTask, bTask);
            System.out.println(Thread.currentThread().getName() + " >> 花费的时间(ms) >> " + (System.currentTimeMillis() - startTime));
            return aTask.join() + bTask.join();
        }
    }

    private Long add(List<Long> valueList){
        Long result = 0L;
        for (Long aLong : valueList) {
            try {
                result = ForkJoinUtils.add(result,aLong);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
