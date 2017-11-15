/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

/**
 * Башня молний
 * @author PK
 */
public class LightTower extends Tower {
    
       /**
     * Конструктор - башни
     *
     * @param position позиция башни
     */
    LightTower(Cell position)
   {
       _position=position;
   }
    // Определяем урон башни
    @Override
    int damage() {
        return 100;
    }

    // Определяем цену башни
    @Override
    int _price() {
        return 100;
    }
    
    // Определяем радиус башни
    @Override
    int rangeAttack() {
        return 5;
    }
    
    // Определяем скорость атаки башни
    @Override
    double attackSpeed() {
        return 1;
    }
    
}
