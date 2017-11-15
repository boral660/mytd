/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

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
    MainConstruction(Cell position, int integrity)
   {
      
      _position = position;
      _integrity = integrity;
   }
     /**
    * Позиция здания
    */
   private Cell _position;
    
     /**
    * Прочность
    */
     private int _integrity;
    
      /**
    * Позиция здания
    */
   Cell position()
   {
       return _position;
   }
   
    /**
    * Прочность
    */
   int integrity()
   {
       return _integrity;
   }
   
   /**
     * Уменьшить прочность здания
     *
     * @param count количество прочности, на которое уменьшаем
     */
   void DecriseIntegrity(int count)
   {

       if(_integrity-count<=0)
           _integrity=0;
       else
           _integrity=_integrity-count;
   }
    
}
