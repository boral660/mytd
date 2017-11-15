/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

/**
 * Башня лучников
 * @author PK
 */
public class ArcherTower extends Tower {

     /**
     * Конструктор - башни
     *
     * @param position позиция башни
     */
    ArcherTower(Cell position)
   {
       _position=position;
   }
    // Определяем урон башни
    @Override
    int damage() {
        return 5;
    }

    // Определяем цену башни
    @Override
    int _price() {
        return 35;
    }
    
    // Определяем радиус башни
    @Override
    int rangeAttack() {
        return 3;
    }
    
    // Определяем скорость атаки башни
    @Override
    double attackSpeed() {
        return 0.25;
    }
 

}
