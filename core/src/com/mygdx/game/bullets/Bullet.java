/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.bullets;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Снаряд
 * @author PK
 */
public class Bullet {
    public Bullet(float x, float y, float destx, float desty,int dmg, TextureRegion  pict)
    {
        _x=x;
        _y=y;
        _destx=destx;
        _desty=desty;
        _damage=dmg;
        _texture=new TextureRegion();
        _texture.setRegion(pict);
        
    }
    /**
      * Проверка, полёт закончен
     */
    public boolean moveOff(){
            return _x==_destx &&_y==_desty;
    }
     /**
      * Пролететь к цели
     */
    public void move(){
       float speed =3;
       
       if(_x<_destx){
           if(_x+speed<_destx)
               _x+=speed;
           else
               _x=_destx;
       }
       else if(_x>_destx)
       {
           if(_destx+speed<_x)
               _x-=speed;
           else
               _x=_destx;
       }
      if(_y<_desty){
           if(_y+speed<_desty)
               _y+=speed;
           else
               _y=_desty;
       }
      else if(_y>_desty)
       {
           if(_desty+speed<_y)
               _y-=speed;
           else
               _y=_desty;
       }

               
    }
     /**
        * Урон от снаряда
     */
    private int _damage;
    public int damage(){
            return _damage;
    }
     /**
 * Изображение снаряда
 */
   private TextureRegion  _texture;
    
    public TextureRegion texture(){
        if(_texture==null){
            int i=2;
        }
        
       return _texture;
    }
  /**
 * Координаты снаряда
 */
 private float _x, _y;

   public float x(){
            return _x;
    }
     public float y(){
            return _y;
    }
 /**
 * Координаты цели
 */
 private float _destx, _desty;
 
       public float destx(){
            return _destx;
    }
     public float desty(){
            return _desty;
    }
}
