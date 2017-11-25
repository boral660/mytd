/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.enemies;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Cell;
import java.util.ArrayList;

/**
 *
 * @author PK
 */
public class EnemyFactory {
    
    public static Enemy getEnemy(String Type,Cell pos, ArrayList<Cell> road){
        // Ближний бой
       if(Type.equals("SkeletonWarrior")) {
           return new Melee(pos,road,5,5,1f,10,new Texture("ArcherTower.png"));
       }
       else if(Type.equals("Viking")) {
           return new Melee(pos,road,10,7,0.5f,15,new Texture("ArcherTower.png"));
       }
        else if(Type.equals("Ork")) {
           return new Melee(pos,road,7,10,0.7f,15,new Texture("ArcherTower.png"));
       }
        else if(Type.equals("Rat")) {
           return new Melee(pos,road,2,3,2f,10,new Texture("ArcherTower.png"));
       }
        else if(Type.equals("Slime")) {
           return new Melee(pos,road,2,20,0.3f,30,new Texture("ArcherTower.png"));
       }
        //Дальний бой
       else if(Type.equals("SkeletonMage")) {
           return new Range(pos,road,5,5,1f,15,new Texture("ArcherTower.png"),1);
       }
       else if(Type.equals("LizardArcher")) {
           return new Range(pos,road,10,7,1.2f,20,new Texture("ArcherTower.png"),2);
       }
       else if(Type.equals("Hunter")) {
           return new Range(pos,road,7,5,2f,30,new Texture("ArcherTower.png"),3);
       }
       else if(Type.equals("Bomer")) {
           return new Range(pos,road,15,2,0.8f,20,new Texture("ArcherTower.png"),1);
       }
       else if(Type.equals("Hunter")) {
           return new Range(pos,road,25,10,0.5f,35,new Texture("ArcherTower.png"),3);
       }
       return null;
    
    }
}
