/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.enemies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Cell;
import com.mygdx.game.navigation.Direction;
import com.mygdx.game.navigation.Pair;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Vector;

/**
 * Враги
 *
 * @author PK
 */
public abstract class Enemy {

    public Enemy(Cell pos, ArrayList<Cell> road,int hp,int dmg,float speed, int moneyForKill, TextureRegion  pict){
       _healPoints=hp;
       _damage=dmg;
       _speed=speed;
       _moneyForKill=moneyForKill;  
       _position=pos;
        _roadCell=road;
        _texture=pict;
        _direction=Direction.south();
        
        Random rand = new Random();
        int r = rand.nextInt(10) * 20;
        _x=rand.nextInt(32);
        _y=rand.nextInt(32);
        _path=createRoad();
    }
      /**
     * Текстура для отрисовки
     */
    protected TextureRegion  _texture;
      /**
     * Текстура для отрисовки
     */
    public TextureRegion  texture() {
        return _texture;
    }
    protected float _x,_y;
    public float x(){
            return _x;
    }
     public float y(){
            return _y;
    }
     
      /**
     * Двигаться по направлению
     * @param direct направление
     * @param countStep расстояние
     * 
    */  
    public void move()
    {
        if(_path.size()>0){
       _direction=_path.get(0).getFirst();
     
       float countStep=_path.get(0).getSecond() -_speed;
       
      _path.get(0).setSecond(countStep);
       if(countStep<=0 || countStep ==0){
           countStep=0;
           _path.remove(0);
       }
       else
           countStep=_speed;
       
        if(_direction.equals(Direction.north())){
            _y+=countStep;
        }
        else if(_direction.equals(Direction.south())){
            _y-=countStep;
        }
        else if(_direction.equals(Direction.west())){
            _x+=countStep;
        }
        else if(_direction.equals(Direction.south())){
            _x-=countStep;
        }
        }
    }
      
    
   
    private List<Pair<Direction, Float>> createRoad()
    {
       List<Pair<Direction, Float>> road=new ArrayList<Pair<Direction, Float>>();
       Cell temp=_position;
       for(Cell cell : _roadCell) {
           Pair<Direction, Float> pair = new Pair<Direction, Float>(Direction.defineDirect(temp, cell),(float)Cell.Size);
                 road.add(pair);
           temp=cell;
        }
       road.remove(0);
        return road;  
    }
    private List<Pair<Direction, Float>> _path;
    
    
    
    private Direction _direction;

   public Direction direction()
    {
       return  _direction  ;
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
