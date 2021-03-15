package com.test.design.patterns.filter.chain;

/**
 * @author trangle
 */
public interface FilterChain extends Filter{

    void setNextFilter(Filter filter);

    Filter getNextFilter();

    Boolean doFilter(Object o,FilterChain filterChain);
}
