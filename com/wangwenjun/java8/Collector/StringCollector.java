package com.wangwenjun.java8.Collector;

import java.util.EnumSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class StringCollector implements Collector<String,StringCombiner,String> {
    @Override
    public Supplier<StringCombiner> supplier() {
        return () -> new StringCombiner("{",",","}");
    }

    @Override
    public BiConsumer<StringCombiner, String> accumulator() {
        return StringCombiner::add;
    }

    @Override
    public BinaryOperator<StringCombiner> combiner() {
        return StringCombiner::merge;
    }

    @Override
    public Function<StringCombiner, String> finisher() {
        return StringCombiner::toString;
    }

    /**
     * Characteristics是一个包含三个项目的枚举。
         UNORDERED——归约结果不受流中项目的遍历和累积顺序的影响。
         CONCURRENT——accumulator函数可以从多个线程同时调用，且该收集器可以并行归
         约流。如果收集器没有标为UNORDERED， 那它仅在用于无序数据源时才可以并行归约。
         IDENTITY_FINISH——这表明完成器方法返回的函数是一个恒等函数，可以跳过。
     */
    @Override
    public Set<Characteristics> characteristics() {
        return EnumSet.of(Characteristics.CONCURRENT);
    }
}
