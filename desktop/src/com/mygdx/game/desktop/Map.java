/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.desktop;

import java.util.ArrayList;

/**
 * Игровая карта
 * @author PK
 */
public class Map {
    /**
    * Название карты
    */
    private String _name;
    
    /**
    * Ширина карты
    */
    private int _width;
    
     /**
    * Высота карты 
    */
    private int _height;
    
    /**
    * Деньги на старте 
    */
    private int _moneyOnStart;
    
    /**
    * Клетки с дорогой
    */
    private ArrayList<Cell> _roadCell = new ArrayList();

}
