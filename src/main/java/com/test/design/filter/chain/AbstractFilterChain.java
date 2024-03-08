package com.test.design.filter.chain;

import java.util.LinkedList;
import java.util.List;

/**
 * @author trangle
 */
public abstract class AbstractFilterChain implements FilterChain {

    private List filterList;
    private Filter nextFilter;

    public AbstractFilterChain(List filterList) {
        this.filterList = new LinkedList();
    }

    @Override
    public void setNextFilter(Filter filter) {
        this.nextFilter = filter;
    }

    @Override
    public Filter getNextFilter() {
        return nextFilter;
    }

    @Override
    public Boolean doFilter(Object o, FilterChain filterChain) {
        return filterChain.doFilter(o, filterChain);
    }
}
