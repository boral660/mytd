/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

/**
 * Ячейка
 * @author PK
 */
public class Cell {
    
   public static int Size=64;
     /**
     * Конструктор - создание новой клетки
     *
     * @param height высота
     * @param width ширина
     */
    Cell( int width,int height)
   {
      _height = height;
      _width = width;
   }
    
    /**
    * Высота 
    */
   private int _height;
   
    /**
     * Ширина
     */
   private int _width;
   
   /**
    * Высота 
    */
   public int height()
   {
       return _height;
   }
   
   /**
     * Ширина
     */
   public int width()
   {
       return _width;
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
               return (_width==((Cell)obj).width() && _height==((Cell)obj).height()); 
       } 
    return false;

    }
    
 
}
