package com.wangwenjun.java8;

import java.util.concurrent.RecursiveTask;

/***************************************
 * @author:Alex Wang
 * @Date:2016/11/2 QQ:532500648
 * QQ交流群:286081824
 ***************************************/
public class AccumulatorRecursiveTask extends RecursiveTask<Integer> {

    private final int start;

    private final int end;

    private final int[] data;

    private final int LIMIT = 3;

    public AccumulatorRecursiveTask(int start, int end, int[] data) {
        this.start = start;
        this.end = end;
        this.data = data;
    }


    @Override
    protected Integer compute() {
        //满足分片之后，执行业务
        if ((end - start) <= LIMIT) {
            int result = 0;
            for (int i = start; i < end; i++) {
                result += data[i];
            }
            return result;
        }
        //不满足分片，将数据继续分割，一个对象一个小片

        int mid = (start + end) / 2;
        AccumulatorRecursiveTask left = new AccumulatorRecursiveTask(start, mid, data);
        left.fork();//拆分，并压入线程双端队列
        AccumulatorRecursiveTask right = new AccumulatorRecursiveTask(mid, end, data);
        right.fork();//拆分，并压入线程双端队列


        return left.join() + right.join();
    }
}
