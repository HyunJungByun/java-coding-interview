package com.coding.study.stackandqueue.exam6;

abstract class Animal {
    protected String name;
    private int order;

    public Animal(String n) {
        name = n;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int ord) {
        order = ord;
    }

    // 오래된 동물을 반환하기 위해서 순서 비교하기
    public boolean isOlderThan(Animal a) {
        return this.order < a.getOrder();
    }
}
