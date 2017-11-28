/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.mygdx.game.enemies.Enemy;
import com.mygdx.game.enemies.EnemyFactory;
import com.mygdx.game.enemies.Wave;
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
    private  ArrayList<DefenseConstruction> _defenseConst=new ArrayList();
    public ArrayList<DefenseConstruction> defenseConst()
    {
       return _defenseConst;
    }
    
    /**
    * Клетки с дорогой
    */
   private ArrayList<Cell> _roadCell = new ArrayList();
   public ArrayList<Cell> roadCell()
    {
       return _roadCell;
    }
   
     /**
    * Волны с врагами 
    */
    private ArrayList<Wave> _waves = new ArrayList();
    
      public ArrayList<Wave> waves()
    {
        return _waves;
    }
      
    
    
      
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
  /**
     * Заполнение карты по первому шаблону
     * 
    */     
   static public Map GenerateMap1()
    {
         MainConstruction main=new MainConstruction(new Cell(8,5),150);
            // Создать первую карту
          Map map=new Map("Long-long way", 100,16,8,main);
          map.roadCell().add(new Cell(0,0));
          map.roadCell().add(new Cell(1,0));
          map.roadCell().add(new Cell(2,0));
          map.roadCell().add(new Cell(2,1));
          map.roadCell().add(new Cell(2,2));
          map.roadCell().add(new Cell(3,2));
          map.roadCell().add(new Cell(4,2));
          map.roadCell().add(new Cell(5,2));
          map.roadCell().add(new Cell(6,2));
          map.roadCell().add(new Cell(7,2));
          map.roadCell().add(new Cell(8,2));
          map.roadCell().add(new Cell(8,3));
          map.roadCell().add(new Cell(8,4));
          // Создаём первую волну
         ArrayList<Enemy> enemies=new ArrayList<Enemy>();
         enemies.add( EnemyFactory.getEnemy("SkeletonWarrior", map.roadCell().get(0), map.roadCell()));
         enemies.add( EnemyFactory.getEnemy("Slime", map.roadCell().get(0), map.roadCell()));
         enemies.add( EnemyFactory.getEnemy("Rat", map.roadCell().get(0), map.roadCell()));
         map.waves().add(new Wave(enemies,map.roadCell()));
         // Cоздаём вторую волну
         enemies=new ArrayList<Enemy>();
         enemies.add( EnemyFactory.getEnemy("Balista", map.roadCell().get(0), map.roadCell()));
         enemies.add( EnemyFactory.getEnemy("Ork", map.roadCell().get(0), map.roadCell()));
         enemies.add( EnemyFactory.getEnemy("Viking", map.roadCell().get(0), map.roadCell()));
         enemies.add( EnemyFactory.getEnemy("Bomber", map.roadCell().get(0), map.roadCell()));
        map.waves().add(new Wave(enemies,map.roadCell()));
        
        return map;
    }
   
   

}
