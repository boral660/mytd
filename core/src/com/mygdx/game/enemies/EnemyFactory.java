/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.game.navigation.Cell;
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
           return new Melee(pos,road,5,5,2.2f,10,1.5f,EnemyAtlas.findRegion("SkeletonWarrior"),EnemyAtlas.findRegion("Nothing") );
       }
       else if(Type.equals("Viking")) {
           return new Melee(pos,road,10,30,1.8f,15,1.3f,EnemyAtlas.findRegion("Viking"),EnemyAtlas.findRegion("Nothing"));
       }
        else if(Type.equals("Ork")) {
           return new Melee(pos,road,7,10,2.4f,15,1.7f,EnemyAtlas.findRegion("Ork"),EnemyAtlas.findRegion("Nothing"));
       }
        else if(Type.equals("Rat")) {
           return new Melee(pos,road,2,5,2.7f,10,0.5f,EnemyAtlas.findRegion("Rat"),EnemyAtlas.findRegion("Nothing"));
       }
        else if(Type.equals("Slime")) {
           return new Melee(pos,road,2,50,1.9f,30,3f,EnemyAtlas.findRegion("Slime"),EnemyAtlas.findRegion("Nothing"));
       }
        //Дальний бой
       else if(Type.equals("SkeletonMage")) {
           return new Range(pos,road,5,5,2.2f,15,1.5f,EnemyAtlas.findRegion("SkeletonMage"),1,EnemyAtlas.findRegion("MagicBall"));
       }
       else if(Type.equals("LizardArcher")) {
           return new Range(pos,road,10,7,2.3f,20,1f,EnemyAtlas.findRegion("LizardArcher"),2,EnemyAtlas.findRegion("MetallicBall"));
       }
       else if(Type.equals("Hunter")) {
           return new Range(pos,road,7,5,2.5f,30,1.2f, EnemyAtlas.findRegion("Hunter"),3,EnemyAtlas.findRegion("MetallicBall"));
       }
       else if(Type.equals("Bomber")) {
           return new Range(pos,road,15,2,2.0f,20,1.7f,EnemyAtlas.findRegion("Bomber"),1,EnemyAtlas.findRegion("MetallicBall"));
       }
       else if(Type.equals("Balista")) {
           return new Range(pos,road,25,50,2.1f,35,3f,EnemyAtlas.findRegion("Balista"),4,EnemyAtlas.findRegion("MetallicBall"));
       }
       return null;
    
    }
}
