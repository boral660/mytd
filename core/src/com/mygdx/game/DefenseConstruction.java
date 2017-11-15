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
   private Cell _position;
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
   private int _damage;
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
   private int _price;
   
     /**
    * Цена
    */
     int _price()
   {
       return _price;
   }
    
}
