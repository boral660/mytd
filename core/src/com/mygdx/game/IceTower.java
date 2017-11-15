/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

/**
 * Башня льда
 * @author PK
 */
public class IceTower extends Tower {
    
    // Определяем урон башни
    @Override
    int damage() {
        return 30;
    }

    // Определяем цену башни
    @Override
    int _price() {
        return 50;
    }
    
    // Определяем радиус башни
    @Override
    int rangeAttack() {
        return 2;
    }
    
    // Определяем скорость атаки башни
    @Override
    double attackSpeed() {
        return 0.5;
    }
}
