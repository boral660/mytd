/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.defenseConstucts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.game.navigation.Cell;

/**
 *
 * @author PK 
 */
public class DCFactory {
 private static TextureAtlas  Atlas= new TextureAtlas(Gdx.files.internal("Texturs.atlas"));
    public static Tower getTower(String Type,Cell pos){
        // Башни
       if(Type.equals("ArcherTower")) {
           return new Tower(pos,5,35,1, Atlas.findRegion("ArcherTower"),Atlas.findRegion("MetallicBall"), 3);
       }
        else if(Type.equals("IceTower")) {
           return new Tower(pos,15,50,2f, Atlas.findRegion("IceTower"),Atlas.findRegion("MagicBall"), 4);
       }
       else if(Type.equals("LightTower")) {
           return new Tower(pos,1,35,0.3f, Atlas.findRegion("LightTower"),Atlas.findRegion("Hit"), 2);
       }
        return null;
    }
  
}
