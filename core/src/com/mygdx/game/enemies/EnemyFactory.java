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
           return new Melee(pos,road,15,5,2.2f,4,1.5f,EnemyAtlas.findRegion("SkeletonWarrior"),EnemyAtlas.findRegion("Nothing") );
       }
       else if(Type.equals("Viking")) {
           return new Melee(pos,road,25,30,1.8f,6,1.3f,EnemyAtlas.findRegion("Viking"),EnemyAtlas.findRegion("Nothing"));
       }
        else if(Type.equals("Ork")) {
           return new Melee(pos,road,30,10,2.4f,6,1.7f,EnemyAtlas.findRegion("Ork"),EnemyAtlas.findRegion("Nothing"));
       }
        else if(Type.equals("Rat")) {
           return new Melee(pos,road,10,2,2.7f,4,0.5f,EnemyAtlas.findRegion("Rat"),EnemyAtlas.findRegion("Nothing"));
       }
        else if(Type.equals("Slime")) {
           return new Melee(pos,road,50,50,1.9f,10,3f,EnemyAtlas.findRegion("Slime"),EnemyAtlas.findRegion("Nothing"));
       }
        //Дальний бой
       else if(Type.equals("SkeletonMage")) {
           return new Range(pos,road,10,5,2.2f,6,1.5f,EnemyAtlas.findRegion("SkeletonMage"),2,EnemyAtlas.findRegion("MagicBall"));
       }
       else if(Type.equals("LizardArcher")) {
           return new Range(pos,road,15,7,2.3f,8,1f,EnemyAtlas.findRegion("LizardArcher"),3,EnemyAtlas.findRegion("MetallicBall"));
       }
       else if(Type.equals("Hunter")) {
           return new Range(pos,road,21,5,2.5f,10,1.2f, EnemyAtlas.findRegion("Hunter"),3,EnemyAtlas.findRegion("MetallicBall"));
       }
       else if(Type.equals("Bomber")) {
           return new Range(pos,road,20,2,2.0f,8,1.7f,EnemyAtlas.findRegion("Bomber"),2,EnemyAtlas.findRegion("MetallicBall"));
       }
       else if(Type.equals("Balista")) {
           return new Range(pos,road,50,50,2.1f,15,4f,EnemyAtlas.findRegion("Balista"),4,EnemyAtlas.findRegion("MetallicBall"));
       }
       return null;
    
    }
    public static  String[] EnemyList={"SkeletonWarrior","Viking","Ork","Rat","Slime","SkeletonMage","LizardArcher","Hunter","Bomber","Balista"};
}
