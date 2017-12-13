/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.mapAndOther;
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
  public MainConstruction(Cell position, int integrity)
   {
      
      _position = position;
      _integrity = integrity;
      _maxIntegrity=integrity;
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
