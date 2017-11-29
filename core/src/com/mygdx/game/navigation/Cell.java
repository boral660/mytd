/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.navigation;

/**
 * Ячейка
 * @author PK
 */
public class Cell {
    
   public static int Size=64;
     /**
     * Конструктор - создание новой клетки
     *
     * @param x высота
     * @param y ширина
     */
  public  Cell( int x,int y)
   {
      _y = y;
      _x = x;
   }
    
    /**
    * Высота 
    */
   private int _y;
   
    /**
     * Ширина
     */
   private int _x;
   
   /**
    * Высота 
    */
   public int y()
   {
       return _y;
   }
   
   /**
     * Позиция по ширине
     */
   public int x()
   {
       return _x;
   }
   
      /**
     * Проверить, является ли эта эквиалентной
     * @param other - другая ячейка 
     * @return true - ячейка является эквиалентной, false -ячейка не является эквиалентной
     * 
    */ 
   @Override
    public boolean equals(Object obj) { 
        if (obj instanceof Cell) { 
               return (_x==((Cell)obj).x() && _y==((Cell)obj).y()); 
       } 
    return false;

    }
    
 
}
