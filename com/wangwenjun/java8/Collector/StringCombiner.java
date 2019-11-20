package com.wangwenjun.java8.Collector;

public class StringCombiner {
    private String prefix;
    private String delim;
    private String suffix;
    private StringBuilder builder;

    public StringCombiner(String prefix, String delim, String suffix) {
        this.prefix = prefix;
        this.delim = delim;
        this.suffix = suffix;
        this.builder = new StringBuilder();
    }

    public boolean areAtStart() {
        return builder.length() == 0;
    }

    public StringCombiner add(String element) {
        if (areAtStart()) {
            builder.append(prefix);
        }
        else {
            builder.append(delim);
        }

        builder.append(element);

        return this;
    }

    public StringCombiner merge(StringCombiner other) {
        this.builder.append(other.builder);
        return this;
    }

    /*Collector中的finisher方法将调用这个toString() 方法，finisher即为收集完成之后要操作*/
    @Override
    public String toString() {
        return builder.toString() + suffix;
    }
}

