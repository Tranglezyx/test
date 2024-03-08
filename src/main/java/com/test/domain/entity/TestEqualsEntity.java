package com.test.domain.entity;

import java.util.Objects;

/**
 * @author trangle
 */
public class TestEqualsEntity {

    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestEqualsEntity entity = (TestEqualsEntity) o;
        return Objects.equals(name, entity.name);
    }

    public int hashCode(){
        return 1;
    }

    public TestEqualsEntity() {
        super();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    @Override
    public String toString() {
        return "TestEqualsEntity{" +
                "name='" + name + '\'' +
                '}';
    }

    public TestEqualsEntity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
