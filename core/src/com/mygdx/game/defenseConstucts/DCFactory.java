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
    public static DefenseConstruction getTower(String Type,Cell pos){
        // Башни
       if(Type.equals("ArcherTower")) {
           return new Tower(pos,10,35,1, Atlas.findRegion("ArcherTower"),Atlas.findRegion("MetallicBall"), 3,"ArcherTower");
       }
        else if(Type.equals("IceTower")) {
           return new Tower(pos,20,50,2f, Atlas.findRegion("IceTower"),Atlas.findRegion("MagicBall"), 4,"IceTower");
       }
       else if(Type.equals("LightTower")) {
           return new Tower(pos,2,25,0.3f, Atlas.findRegion("LightTower"),Atlas.findRegion("Hit"), 2,"LightTower");
       }
       else if(Type.equals("Wire")) {
           return new Traps(pos,10,10,0, Atlas.findRegion("Wire"),Atlas.findRegion("Hit"),"Wire");
       }
       else if(Type.equals("ElectricBomb")) {
           return new Traps(pos,25,15,0, Atlas.findRegion("ElectricBomb"),Atlas.findRegion("Hit"),"ElectricBomb");
       }
         else if(Type.equals("Spike")) {
           return new Traps(pos,40,20,0, Atlas.findRegion("Spike"),Atlas.findRegion("Hit"),"Spike");
       }
        
       return null;
    
    }
    public static     String[] ListTowers = {"ArcherTower - 35", "IceTower - 50" ,"LightTower - 25"};
   public static  Object[] ListTraps = {DCFactory.getTower("Wire",new Cell(0,0)),DCFactory.getTower("ElectricBomb",new Cell(0,0) ),DCFactory.getTower("Spike",new Cell(0,0) ),};
  
}
