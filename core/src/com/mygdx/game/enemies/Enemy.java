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
 * Враги
 *
 * @author PK
 */
public abstract class Enemy {

    public Enemy(Cell pos, ArrayList<Cell> road,int hp,int dmg,float speed, int moneyForKill, Texture pict){
       _healPoints=hp;
       _damage=dmg;
       _speed=speed;
       _moneyForKill=moneyForKill;  
       _position=pos;
        _roadCell=road;
        _texture=pict;
    }
      /**
     * Текстура для отрисовки
     */
    protected Texture _texture;
      /**
     * Текстура для отрисовки
     */
    public Texture texture() {
        return _texture;
    }
    
    
    /**
     * Деньги за убийство
     */
    protected int _moneyForKill;
    /**
     * Деньги за убийство
     */
    public int moneyForKill() {
        return _moneyForKill;
    }
    
      /**
     * Урон
     */
    protected int _damage;
    /**
     * Урон
     */
    public int damage() {
        return _damage;
    }
    
    
    /**
     * Здоровье
     */
    protected int _healPoints;
    /**
     * Здоровье
     */
    public int healPoints() {
        return _healPoints;
    }
    
    
    /**
     * Радиус атаки
     */
    protected int _rangeAttack;

    /**
     * Радиус атаки
     */
    public int rangeAttack() {
        return _rangeAttack;
    }

    /**
     * Позиция
     */
    protected Cell _position;
    /**
     * Позиция
     */
    public Cell position() {
        return _position;
    }
    

    /**
     * Скорость
     */
    protected float _speed;
    /**
     * Скорость
     */
    public float speed() {
        return _speed;
    }
    
    
    /**
     * Траектория до главного строения
     */
    protected ArrayList<Cell> _roadCell = new ArrayList();

    /**
     * Траектория до главного строения
     */
    public ArrayList<Cell> roadCell() {
        return _roadCell;
    }

}
