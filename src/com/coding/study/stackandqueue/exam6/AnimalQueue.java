package com.coding.study.stackandqueue.exam6;

import java.util.LinkedList;

public class AnimalQueue {
    LinkedList<Animal> dogs = new LinkedList();
    LinkedList<Animal> cats = new LinkedList();
    private int order = 0;  // timestamp의 역할

    public void enqueue(Animal a) {
        /*
        개와 고양이의 수용 순서를 비교하기 위해 timestamp 를 사용해서 이들의 순서를 정한다.
         */
        a.setOrder(order);
        order++;

        if (a instanceof Dog) {
            dogs.addLast(a);
        } else if (a instanceof Cat) {
            cats.addLast(a);
        }
    }

    public Animal dequeueAny() {
        /*
        개와 고양이 큐의 맨 앞을 살펴본 뒤 그중 더 오래전에 들어온 동물을 큐에서 뺀다
         */
        if (dogs.size() == 0) {
            return dequeueCats();
        } else if (cats.size() == 0) {
            return dequeueDogs();
        }
        Dog dog = (Dog) dogs.peek();
        Cat cat = (Cat) cats.peek();
        if (dog.isOlderThan(cat)) {
            return dequeueDogs();
        } else {
            return dequeueCats();
        }
    }

    public Dog dequeueDogs() {
        return (Dog) dogs.poll();
    }

    public Cat dequeueCats() {
        return (Cat) cats.poll();
    }
}
