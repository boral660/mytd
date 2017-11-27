/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.defenseConstucts;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Cell;
import com.mygdx.game.navigation.Direction;
import java.util.ArrayList;
import java.util.Random;

/**
 * Защитные конструкции
 * @author PK
 */
public abstract class DefenseConstruction {
    
     public DefenseConstruction(Cell pos,int dmg,int price, TextureRegion  pict){

       _damage=dmg;
       _position=pos;
       _price=_price;
    }
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
