/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import java.util.ArrayList;

/**
 * Игровая карта
 * @author PK
 */
public class Map {
    
      /**
     * Конструктор - создание новой карты
     * @param name название карты
     * @param moneyOnStart деньги на старте
     * @param height высота
     * @param width ширина
     * 
     */
    Map(String name, int moneyOnStart, int height, int width)
   {
       _name = name;
       _moneyOnStart = moneyOnStart;
      _height = height;
      _width = width;
   }
    
    
    /**
    * Название карты
    */
    private String _name;
    
    String name()
    {
        return _name;
    }
    /**
    * Ширина карты
    */
    private int _width;
    
     int width()
    {
        return _width;
    }
     /**
    * Высота карты 
    */
    private int _height;
    
      int height()
    {
        return _height;
    }
      
    /**
    * Деньги на старте 
    */
    private int _moneyOnStart;
    
      int moneyOnStart()
    {
        return _moneyOnStart;
    }
      
    /**
    * Клетки с дорогой
    */
   public ArrayList<Cell> _roadCell = new ArrayList();

}
