/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.enemies;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.navigation.Cell;
import com.mygdx.game.bullets.Bullet;
import com.mygdx.game.navigation.Direction;
import com.mygdx.game.navigation.Pair;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Враги
 *
 * @author PK
 */
public abstract class Enemy {

    public Enemy(Cell pos, ArrayList<Cell> road,int hp,int dmg,float speed, int moneyForKill,float atkSpeed, TextureRegion  pict,TextureRegion  pictForBullet ){
       _healPoints=hp;
       _damage=dmg;
       _speed=speed;
       _moneyForKill=moneyForKill;  
       _position=pos;
        _roadCell=road;
        _texture=pict;
        _textureForBullet=pictForBullet;
        _direction=Direction.south();
        _attackSpeed=atkSpeed;
        Random rand = new Random();
        
        _x=pos.x()*Cell.Size+ rand.nextInt(32);
        _y= pos.y()*Cell.Size+ rand.nextInt(32);
        _path=createRoad();
    }
    
    /**
     * Скорость атаки
     */
    protected float  _attackSpeed;
      /**
     * Скорость атаки
     */
    public float  attackSpeed() {
        return _attackSpeed;
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
    /**
     * Текстура для отрисовки
     */
    public void  reduseHP(int value) {
        if(_healPoints-value<=0)
           _healPoints=0;
       else
           _healPoints=_healPoints-value;
    }

      /**
     * Время последней атаки
     */
    protected long  _lastAttackTime;
      /**
     * Время последней атаки
     */
    public long lastAttackTime() {
        return _lastAttackTime;
    }
    
      /**
     * Текстура для снаряда
     */
    protected TextureRegion  _textureForBullet;
      /**
     * Текстура для отрисовки
     */
    public TextureRegion  textureForBullet() {
        return _textureForBullet;
    }
     /**
     * Координаты врага
     */
    protected float _x,_y;
    public float x(){
            return _x;
    }
     public float y(){
            return _y;
    }
       /**
     * Проверить может ли враг атаковать цель
     * @param target клетка, которую должен атакавать
     * @return false - если не может, true если может
     * 
    */  
    public boolean canAttack(Cell target)
    {
       return (_position.x()<=target.x()+1+1*_rangeAttack && _position.y()==target.y())      // Цель слева от врага
              ||(_position.x()>=target.x()-1-1*_rangeAttack && _position.y()==target.y())      // Цель справа от врага
              ||(_position.y()>=target.y()+1+1*_rangeAttack && _position.x()==target.x())     // Цель снизу от врага
              ||(_position.y()>=target.y()-1-1*_rangeAttack && _position.x()==target.x());     // Цель сверху от врага
    }
     /**
     *  Атаковать цель
     * @param target клетка, которую должен атакавать
     * 
    */  
    public Bullet attack(Cell target,float x, float y)
    {
        
        _direction= Direction.defineDirect(_position,target);
        if(_lastAttackTime==0 || (TimeUtils.millis() - _lastAttackTime > _attackSpeed*1000))
        {
           
           _lastAttackTime=TimeUtils.millis();
           
         if(_textureForBullet!=null)
         {      TextureRegion temp=new TextureRegion();
             temp.setRegion(_textureForBullet);
             
              if(_rangeAttack==0)  return new Bullet(x, y, x, y,_damage, temp); // Создать пулю у цели
                  
            return new Bullet(_x, _y, x, y,_damage, temp);
         }
         return null;
        }
        else
            return null;
            
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
            if(countStep==0) _position=new Cell(_position.x(),_position.y()+1);
        }
        else if(_direction.equals(Direction.south())){
            _y-=countStep;
             if(countStep==0) _position=new Cell(_position.x(),_position.y()-1);
        }
        else if(_direction.equals(Direction.west())){
            _x+=countStep;
             if(countStep==0) _position=new Cell(_position.x()+1,_position.y());
        }
        else if(_direction.equals(Direction.south())){
            _x-=countStep;
            if(countStep==0) _position=new Cell(_position.x()-1,_position.y());
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
