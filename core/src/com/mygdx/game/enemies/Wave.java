/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.enemies;

import com.mygdx.game.Cell;
import java.util.ArrayList;

/**
 * Волны
 * @author PK
 */
public class Wave {
    public Wave (ArrayList<Enemy> enemies,ArrayList<Cell> roadcell ){
        _enemies=enemies;
        _roadCell=roadcell;
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
    protected ArrayList<Cell> _roadCell;

    /**
     * Траектория до главного строения
     */
    public ArrayList<Cell> roadCell() {
        return _roadCell;
    }

}
