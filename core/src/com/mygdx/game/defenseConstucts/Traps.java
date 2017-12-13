/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.defenseConstucts;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.bullets.Bullet;
import com.mygdx.game.enemies.Enemy;
import com.mygdx.game.mapAndOther.Cell;

/**
 * Ловушки
 *
 * @author PK
 */
public class Traps extends DefenseConstruction {

    /**
     * Конструктор - создание новой ловушки
     *
     * @param pos позиция
     * @param dmg урон
     * @param price цена ловушки
     * @param atkSpeed скорость атаки
     * @param pict изоображение
     * @param Name название
     * @param pictForBullet изоображение снаряда
     *
     */

    public Traps(Cell pos, int dmg, int price, float atkSpeed, TextureRegion pict, TextureRegion pictForBullet, String Name) {
        super(pos, dmg, price, atkSpeed, pict, pictForBullet, Name);
        _rangeAttack = 0;
    }

    /**
     * Функция проверяющая может ли стоять сооружение на дороге
     */
    static boolean canStayOnRoad() {
        return true;
    }

    /**
     * Атаковать цель
     *
     * @param target враг, которого необходимо атаковать
     *
     */
    public Bullet attack(Enemy target) {
        _destroy = true;
        target.reduseHP(_damage);
        return null;
    }

    /**
     * Проверить может ли строение атаковать цель
     *
     * @param target враг, которого нужно атаковать
     * @return false - если не может, true если может
     *
     */
    public boolean canAttack(Enemy target) {
        return _position.equals(target.position());

    }
}
