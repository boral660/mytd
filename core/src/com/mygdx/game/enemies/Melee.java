/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.enemies;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.mapAndOther.Cell;
import com.mygdx.game.mapAndOther.Map;
import java.util.ArrayList;

/**
 * Враги ближнего боя
 * @author PK
 */
public class Melee extends Enemy{
       
   public Melee(Cell pos, ArrayList<Cell> road,int hp,int dmg,float speed, int moneyForKill,float atkSpeed, TextureRegion  pict,TextureRegion  pictForBullet, Map map){
       super(pos,road,hp,dmg,speed,moneyForKill,atkSpeed,pict,pictForBullet, map);
         _rangeAttack=1;
    }
    
}
