/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

/**
 * Башня
 * @author PK
 */
public abstract class Tower extends DefenseConstruction {
   /**
    * Дальность атаки
    */
   int _rangeAttack;
    /**
    * Дальность атаки
    */
   int rangeAttack()
   {
       return _rangeAttack;
   }
    /**
    * Скорость атаки
    */
   double _attackSpeed;
     /**
    * Скорость атаки
    */
   double attackSpeed()
   {
       return _attackSpeed;
   }
}
