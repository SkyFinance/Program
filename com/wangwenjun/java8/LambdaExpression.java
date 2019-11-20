package com.wangwenjun.java8;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by wangwenjun on 2016/10/12.
 */
public class LambdaExpression {

    public static void main(String[] args) {


        Comparator<Apple> byColor = new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getColor().compareTo(o2.getColor());
            }
        };

        List<Apple> list = Collections.emptyList();

        list.sort(byColor);

        Comparator<Apple> byColor2 = (o1, o2) -> o1.getColor().compareTo(o2.getColor());

        Function<String, Integer> flambda = s -> s.length();

        Predicate<Apple> p = (Apple a) -> a.getColor().equals("green");

        Predicate<Integer> cc = (Integer a) -> a>8;

        Runnable r = ()->{};

        /*lambda级联和柯西化，书写技巧：多个函数嵌套，形成高阶函数，书写lambda表达式时无需为
        下一个函数式接口无需为上一个函数的返回值留占位参数， 嵌套几个函数就有几个级联箭头，入参一直级联一直往下写，最后再处理入参算法。*/
        BiFunction<Integer, Integer, Function<Integer, Predicate<Integer>>> test1 = (a, b) -> c -> e -> (a + b + c) > e;
        boolean result1 = test1.apply(1, 2).apply(3).test(9);
        System.out.println(result1); //false

        BiFunction<Integer, Integer, BiFunction<Integer, Integer, Predicate<Integer>>> test2 = (a, b) -> (c, d) -> e -> a+b+d+c > d;
        boolean result2 = test2.apply(1, 2).apply(3, 4).test(9);
        System.out.println(result2); //true

        Function<Apple,Boolean> f = (a)->a.getColor().equals("green");

    }
}
