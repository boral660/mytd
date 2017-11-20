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
    Map(String name, int moneyOnStart, int width,int height,MainConstruction main)
   {
       _main=main;
       _name = name;
       _moneyOnStart = moneyOnStart;
      _height = height;
      _width = width;
   }
    /**
    * Главная конструкиция
    */
    private MainConstruction _main;
    
     /**
    * Главная конструкиция
    */
    MainConstruction main()
    {
        return _main;
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
    * Клетки башнями
    */
    public  ArrayList<DefenseConstruction> _defenseConst=new ArrayList();
    /**
    * Клетки с дорогой
    */
   public ArrayList<Cell> _roadCell = new ArrayList();
 
    /**
     * Проверить, занята ли ячейка
     * @param position позиция ячейки на карте
     * @return true - ячейка занята объектом, false -ячейка свободна
     * 
    */     
  public boolean CheckCell(Cell position)
   {
       if(_main.position().equals(position))
           return true;
       
       for (DefenseConstruction dc:_defenseConst)
        {
            if(dc.position().equals(position))
                return true;
        }
            return false;
   }
  
   /**
     * Проверить, является ли эта ячейка дорогой
     * @param position позиция ячейки на карте
     * @return true - ячейка является дорогой, false -ячейка местоности
     * 
    */     
  public boolean CheckRoad(Cell position)
   {
       for (Cell cell:_roadCell)
        {
            if(cell.equals(position))
                return true;
        }
       return false;
    }

}
