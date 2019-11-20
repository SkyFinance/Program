package com.wangwenjun.java8.Collector;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CollectorMain {
    public static void main(String[] args) {
            List<String> source = new ArrayList<>();

            source.add("math");
            source.add("English");
            source.add("music");
            source.add("physic");

        String collect = source.stream().collect(new StringCollector());
        System.out.println(collect);//{math,English,music,physic}

        StringCombiner reduce = source.stream().reduce(new StringCombiner("[", ",", "]"), StringCombiner::add, StringCombiner::merge);
        System.out.println(reduce.toString());//[math,English,music,physic]

        StringCombiner collect1 = source.stream().collect(Collectors.reducing(new StringCombiner("(", ",", ")"), name -> new StringCombiner("(", ",", ")").add(name), (sc1, sc2) -> sc2.merge(sc1)));
        System.out.println(collect1.toString());//(physic(music(English(math)     sc2与sc1换了个位置

        int a = 2;
        if (3<4 && (false || ++a>2 )) {
            System.out.println("已经执行");
        }
        System.out.println(a);
    }
}
