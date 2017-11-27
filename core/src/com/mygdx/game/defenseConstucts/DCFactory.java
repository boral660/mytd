/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.defenseConstucts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.game.Cell;
import com.mygdx.game.enemies.Enemy;
import com.mygdx.game.enemies.Melee;
import com.mygdx.game.enemies.Range;
import java.util.ArrayList;

/**
 *
 * @author PK
 */
public class DCFactory {
    private static TextureAtlas  EnemyAtlas= new TextureAtlas(Gdx.files.internal("Enemies.atlas"));
    public static Enemy getEnemy(String Type,Cell pos, ArrayList<Cell> road){
        // Ближний бой
       if(Type.equals("ArcherTower")) {
           return new Melee(pos,road,5,5,1f,10,EnemyAtlas.findRegion("SkeletonWarrior") );
       }
       else if(Type.equals("LightTower")) {
           return new Melee(pos,road,10,7,0.5f,15,EnemyAtlas.findRegion("Viking"));
       }
       else if(Type.equals("IceTower")) {
           return new Melee(pos,road,10,7,0.5f,15,EnemyAtlas.findRegion("Viking"));
       }
     
       return null;
    
    }
}
