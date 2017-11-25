/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.enemies;

import com.mygdx.game.Cell;
import java.util.ArrayList;

/**
 * Враги
 * @author PK
 */
public abstract class Enemy {
     /**
    * Здоровье 
    */
  private int _HealPoints;
   /**
    * Здоровье 
    */
  int HealPoints()
  {
      return _HealPoints;
  }
    /**
    * Позиция
    */
  private Cell _Position;
    /**
    * Позиция
    */
  Cell Position()
  {
       return _Position;
  }
  
    /**
    * Скорость
    */

  private float _Speed;
  
   /**
    * Скорость
    */
  float Speed()
  {
       return _Speed;
  }
   /**
    * Траектория до главного строения
    */
   private ArrayList<Cell> _roadCell = new ArrayList();
    /**
    * Траектория до главного строения
    */
    public ArrayList<Cell> roadCell()
    {
       return _roadCell;
    }

}
