/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.defenseConstucts;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.bullets.Bullet;
import com.mygdx.game.enemies.Enemy;
import com.mygdx.game.navigation.Cell;
import com.mygdx.game.navigation.Direction;

/**
 * Защитные конструкции
 * @author PK
 */
public abstract class DefenseConstruction {
    
     public DefenseConstruction(Cell pos,int dmg,int price,float atkSpeed,TextureRegion  pict, TextureRegion  pictForBullet){

       _damage=dmg;
       _position=pos;
       _price=price;
       _attackSpeed=atkSpeed;
        _texture=pict;
        _textureForBullet=pictForBullet;

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
     * Проверить может ли строение атаковать цель
     * @param target враг, которого нужно атаковать
     * @return false - если не может, true если может
     * 
    */  
    public boolean canAttack(Enemy target)
    {
        //double temp =Math.sqrt( Math.pow((target.x()-(_position.x()*Cell.Size +Cell.Size/2)),2)+ Math.pow(target.y()-(_position.y()*Cell.Size+Cell.Size/2),2));
       // return _rangeAttack*Cell.Size > temp;
        float x1=_position.x();
        float x2=target.position().x();
        float y1=_position.y();
        float y2=target.position().y();
        
        boolean right=x1>=x2 && x1<=x2+_rangeAttack;
        boolean top=y1>=y2 && y1<=y2+_rangeAttack;
        boolean bot=y1<=y2 && y1>=y2-_rangeAttack;
         boolean left=x1<=x2 && x1>=x2-_rangeAttack;
         
         return ((right && (bot||top)) || (left &&(bot||top)));
                
    }
     /**
     *  Атаковать цель
     * @param target враг, которого необходимо атаковать
     * 
    */  
    public Bullet attack(Enemy target)
    {
        float x=target.position().x();
        float y=target.position().y();
        
       if(_lastAttackTime==0 || (TimeUtils.millis() - _lastAttackTime > _attackSpeed*1000))
        {
           
           _lastAttackTime=TimeUtils.millis();
           
         if(_textureForBullet!=null)
         {   
             TextureRegion temp=new TextureRegion();
             temp.setRegion(_textureForBullet);
             
              if(_rangeAttack==0)  return new Bullet(x, y, x, y,_damage, temp); // Создать пулю у цели
                  
            return new Bullet(_position.x()*Cell.Size,_position.y()*Cell.Size, target,_damage, temp);
         }
         return null;
        }
        else
            return null;
            
    }
    
     /**
    * Позиция
    */
   protected Cell _position;
    /**
    * Позиция
    */
   public Cell position()
   {
       return _position;
   }
    /**
    * Функция проверяющая может ли стоять сооружение на дороге
    */
    static boolean canStayOnRoad()
    {
       return false;
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
    * Урон
    */
   protected int _damage;
    /**
    * Урон
    */
     int damage()
   {
       return _damage;
   }
      /**
    * Цена
    */
   protected int _price;
   
     /**
    * Цена
    */
     int _price()
   {
       return _price;
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
     * Текстура для отрисовки
     */
    protected TextureRegion  _texture;
      /**
     * Текстура для отрисовки
     */
    public TextureRegion  texture() {
        return _texture;
    }
    
}
