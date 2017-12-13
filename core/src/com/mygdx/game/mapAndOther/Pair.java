/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.mapAndOther;

/**
 * Класс пар
 *
 * @author PK
 */
public class Pair<A, B> {

    private A first;
    private B second;

    /**
     * Конструктор - создание пары
     *
     * @param first первый элемент пары
     * @param second второй элемент пары
     */
    public Pair(A first, B second) {
        super();
        this.first = first;
        this.second = second;
    }

    /**
     * Сеттер для первого элемента
     *
     * @param first - первый элемент
     */
    public void setFirst(A first) {
        this.first = first;
    }

    /**
     * Сеттер для второго элемента
     *
     * @param second - второй элемент
     */
    public void setSecond(B second) {
        this.second = second;
    }

    /**
     * Сравнение пар
     *
     * @param other - другая пара
     */
    public boolean equals(Object other) {
        if (other instanceof Pair) {
            Pair otherPair = (Pair) other;
            return ((this.first == otherPair.first
                    || (this.first != null && otherPair.first != null
                    && this.first.equals(otherPair.first)))
                    && (this.second == otherPair.second
                    || (this.second != null && otherPair.second != null
                    && this.second.equals(otherPair.second))));
        }

        return false;
    }

    /**
     * Геттер для первого элемента
     */
    public A getFirst() {
        return first;
    }

    /**
     * Геттер для второго элемента
     */
    public B getSecond() {
        return second;
    }

}
