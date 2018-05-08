/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.enemies;

import com.mygdx.game.mapAndOther.Cell;
import com.mygdx.game.mapAndOther.Map;
import java.util.ArrayList;
import java.util.Random;

/**
 * Волны
 * @author PK
 */
public class Wave {
    public Wave (ArrayList<Enemy> enemies){
        _enemies=enemies;
    }
    
     /**
     * Траектория до главного строения
     */
    protected ArrayList<Enemy> _enemies;

    /**
     * Траектория до главного строения
     */
    public ArrayList<Enemy> enemies() {
        return _enemies;
    }
    
/**
     * Траектория до главного строения
     */
    public static Wave createRandomWave(int countEnemy, ArrayList<Cell> road,Map map)
    {
        ArrayList<Enemy> enemies=new ArrayList<Enemy>();
        for(int i=countEnemy; i>0; i--)
        {              
            Random rand = new Random();        
            int num =rand.nextInt( EnemyFactory.EnemyList.length);
            enemies.add(EnemyFactory.getEnemy(EnemyFactory.EnemyList[num], road.get(0), road,map));
            
        }
        return new Wave(enemies);
    }

}
