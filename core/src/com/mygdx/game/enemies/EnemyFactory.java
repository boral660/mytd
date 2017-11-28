/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.game.Cell;
import java.util.ArrayList;

/**
 *
 * @author PK
 */
public class EnemyFactory {
    private static TextureAtlas  EnemyAtlas= new TextureAtlas(Gdx.files.internal("Texturs.atlas"));
    public static Enemy getEnemy(String Type,Cell pos, ArrayList<Cell> road){
        // Ближний бой
       if(Type.equals("SkeletonWarrior")) {
           return new Melee(pos,road,5,5,1.2f,10,EnemyAtlas.findRegion("SkeletonWarrior"),EnemyAtlas.findRegion("Hit") );
       }
       else if(Type.equals("Viking")) {
           return new Melee(pos,road,10,30,0.8f,15,EnemyAtlas.findRegion("Viking"),EnemyAtlas.findRegion("Hit"));
       }
        else if(Type.equals("Ork")) {
           return new Melee(pos,road,7,10,1.4f,15,EnemyAtlas.findRegion("Ork"),EnemyAtlas.findRegion("Hit"));
       }
        else if(Type.equals("Rat")) {
           return new Melee(pos,road,2,5,1.7f,10,EnemyAtlas.findRegion("Rat"),EnemyAtlas.findRegion("Hit"));
       }
        else if(Type.equals("Slime")) {
           return new Melee(pos,road,2,50,0.9f,30,EnemyAtlas.findRegion("Slime"),EnemyAtlas.findRegion("Hit"));
       }
        //Дальний бой
       else if(Type.equals("SkeletonMage")) {
           return new Range(pos,road,5,5,1.2f,15,EnemyAtlas.findRegion("SkeletonMage"),1,EnemyAtlas.findRegion("MagicBall"));
       }
       else if(Type.equals("LizardArcher")) {
           return new Range(pos,road,10,7,1.3f,20,EnemyAtlas.findRegion("LizardArcher"),2,EnemyAtlas.findRegion("MetallicBall"));
       }
       else if(Type.equals("Hunter")) {
           return new Range(pos,road,7,5,1.5f,30,EnemyAtlas.findRegion("Hunter"),3,EnemyAtlas.findRegion("MetallicBall"));
       }
       else if(Type.equals("Bomber")) {
           return new Range(pos,road,15,2,1.0f,20,EnemyAtlas.findRegion("Bomber"),1,EnemyAtlas.findRegion("MetallicBall"));
       }
       else if(Type.equals("Balista")) {
           return new Range(pos,road,25,50,1.1f,35,EnemyAtlas.findRegion("Balista"),4,EnemyAtlas.findRegion("MetallicBall"));
       }
       return null;
    
    }
}
