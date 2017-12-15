/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.mapAndOther;

import com.badlogic.gdx.utils.TimeUtils;

/**
 *  Главное здание
 * @author PK
 * 
 */
public class MainConstruction {
     
    /**
     * Конструктор - создание главного здания
     *
     * @param position позиция главного здания
     * @param integrity прочность главного здания
     */
  public MainConstruction(Cell position, int integrity,Effects effect)
   {
      
      _position = position;
      _integrity = integrity;
      _maxIntegrity=integrity;
      _effect=effect;
      _lastEffectTime=0;
   }
   /**
    * Перечисление возможных эффектов
    */
    public enum Effects { Damage, Heal, Money }
     /**
    * Эффекты
    */
   private Effects _effect;
     /**
    * Эффекты
    */
   public Effects effect(){
        return _effect;
    }
    
     /**
    * Позиция здания
    */
   private Cell _position;

      /**
    * Позиция здания
    */
  public Cell position()
   {
       return _position;
   }
      /**
    * Максимальная прочность
    */
     private int _maxIntegrity;
    
    /**
    * Максимальная прочность
    */
  public int maxIntegrity()
   {
       return _maxIntegrity;
   }
    /**
     * Время последней атаки
     */
    protected long _lastEffectTime;

    /**
     * Время последней атаки
     */
    public long lastEffectTime() {
        return _lastEffectTime;
    }
      /**
     * Время последней атаки
     */
    public void setLastEffectTime(long value) {
        _lastEffectTime=value;
    }
     
     /**
    * Текущая прочность
    */
     private int _integrity;
    
    /**
    * Текущая прочность
    */
  public int integrity()
   {
       return _integrity;
   }
   /**
    * Текущая прочность
    */
  public void addIntegrity(int value)
     
   {
       if(_integrity+value<=_maxIntegrity)
             _integrity+=value;
       else
           _integrity=_maxIntegrity;
   }
   
   /**
     * Уменьшить прочность здания
     *
     * @param value количество прочности, на которое уменьшаем
     */
  public void DecriseIntegrity(int value)
   {

       if(_integrity-value<=0)
           _integrity=0;
       else
           _integrity=_integrity-value;
   }
    
}
