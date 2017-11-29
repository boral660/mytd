/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.defenseConstucts;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.navigation.Cell;

/**
 *
 * @author PK
 */
public class Traps extends DefenseConstruction {
    
     public Traps(Cell pos,int dmg,int price,float atkSpeed, TextureRegion  pict, TextureRegion  pictForBullet){
       super(pos,dmg,price,atkSpeed,pict,pictForBullet);
        _rangeAttack=0;
     }
       /**
    * Функция проверяющая может ли стоять сооружение на дороге
    */
    static boolean canStayOnRoad()
    {
       return true;
    }
}
