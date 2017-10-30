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
    
     /**
     * Конструктор - создание новой клетки
     *
     * @param height высота
     * @param width ширина
     */
    Cell(int height, int width)
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
   int height()
   {
       return _height;
   }
   
   /**
     * Ширина
     */
   int width()
   {
       return _width;
   }
   
 
}
