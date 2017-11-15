/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

/**
 * Защитные сооружение
 * @author PK
 */
public abstract class DefenseConstruction {
    
    
     /**
    * Позиция
    */
   protected Cell _position;
    /**
    * Позиция
    */
   Cell position()
   {
       return _position;
   }
   /**
    * Урон
    */
   protected int _damage;
    /**
    * Урон
    */
     int damage()
   {
       return _damage;
   }
      /**
    * Цена
    */
   protected int _price;
   
     /**
    * Цена
    */
     int _price()
   {
       return _price;
   }
    
}
