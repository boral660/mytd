/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.navigation;

import com.mygdx.game.Cell;

/**
    /*
 * Direction - абстракция направления в системе координат "север-юг-восток-запад"; 
 * позволяет сравнивать направления и порождать новые направления относительно 
 * текущего
 */
public class Direction {
    
    // определяем направление как угол в градусах от 0 до 360
    private int _angle = 90;

    private Direction(int angle) {
        // Приводим заданный угол к допустимому диапазону 
        angle = angle%360;
        if(angle < 0)    angle += 360;
        
        this._angle = angle;
    }
    
    // ------------------ Возможные направления ---------------------
    
    public static Direction north()
    { return new Direction(90); }
    
    public static Direction south()
    { return new Direction(270); }

    public static Direction east()
    { return new Direction(0); }

    public static Direction west()
    { return new Direction(180); }
    
      // ------------------ Сравнить направления ---------------------
    
    @Override
    public boolean equals(Object other) {

        if(other instanceof Direction) {
            // Типы совместимы, можно провести преобразование
            Direction otherDirect = (Direction)other;
            // Возвращаем результат сравнения углов
            return  _angle == otherDirect._angle;
        }
        
        return false;
    }
    
          /**
     * Определить направление движения к ячейке
     * @param first начальная позиция
     * @param nextCell позиция ячейки на карте
     * @return Direction - Направление
     * 
    */    
   static public Direction defineDirect(Cell first,Cell nextCell)
    {
        if(first.width()<nextCell.width())
        {
            return Direction.west();
                }
        else if(first.width()>nextCell.width())
        {
              return Direction.east();
        }
        else if(first.height()<nextCell.height())
        {
          return Direction.north();
        }
        else 
        {
          return Direction.south();
        }    
    }
    
    
}
