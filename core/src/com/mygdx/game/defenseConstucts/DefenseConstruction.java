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
import com.mygdx.game.mapAndOther.Cell;

/**
 * Защитные конструкции
 *
 * @author PK
 */
public abstract class DefenseConstruction {

    /**
     * Конструктор - создание новой защитной конструкции
     *
     * @param pos позиция
     * @param dmg урон
     * @param price цена ловушки
     * @param atkSpeed скорость атаки
     * @param pict изоображение
     * @param Name название башни
     * @param pictForBullet изоображение снаряда
     *
     */
    public DefenseConstruction(Cell pos, int dmg, int price, float atkSpeed, TextureRegion pict, TextureRegion pictForBullet, String Name) {

        _damage = dmg;
        _position = pos;
        _price = price;
        _attackSpeed = atkSpeed;
        _texture = pict;
        _textureForBullet = pictForBullet;
        _destroy = false;
        _name = Name;
    }
    /**
     * Название башни
     */
    protected String _name;

    /**
     * Название башни
     */
    @Override
    public String toString() {
        return new String(_name + " - " + _price);
    }
    /**
     * Радиус атаки
     */
    protected int _rangeAttack;

    /**
     * Сооружение разрушено
     */
    protected boolean _destroy;

    /**
     * Сооружение разрушено
     */
    public boolean IsDestroy() {
        return _destroy;
    }

    /**
     * Радиус атаки
     */
    public int rangeAttack() {
        return _rangeAttack;
    }
    /**
     * Время последней атаки
     */
    protected long _lastAttackTime;

    /**
     * Время последней атаки
     */
    public long lastAttackTime() {
        return _lastAttackTime;
    }

    /**
     * Проверить может ли строение атаковать цель
     *
     * @param target враг, которого нужно атаковать
     * @return false - если не может, true если может
     *
     */
    public boolean canAttack(Enemy target) {
        float x1 = _position.x();
        float x2 = target.position().x();
        float y1 = _position.y();
        float y2 = target.position().y();

        boolean right = x1 >= x2 && x1 <= x2 + _rangeAttack;
        boolean top = y1 >= y2 && y1 <= y2 + _rangeAttack;
        boolean bot = y1 <= y2 && y1 >= y2 - _rangeAttack;
        boolean left = x1 <= x2 && x1 >= x2 - _rangeAttack;

        return ((right && (bot || top)) || (left && (bot || top)));

    }

    /**
     * Атаковать цель
     *
     * @param target враг, которого необходимо атаковать
     * @return снаряд летящий в цель
     */
    public Bullet attack(Enemy target) {
        Bullet bull = null;
        if (_lastAttackTime == 0 || (TimeUtils.millis() - _lastAttackTime > _attackSpeed * 1000)) {

            _lastAttackTime = TimeUtils.millis();

            if (_textureForBullet != null) {
                TextureRegion temp = new TextureRegion();
                temp.setRegion(_textureForBullet);

                if (_rangeAttack == 0) {
                    target.reduseHP(_damage); // Создать пулю у цели
                } else {
                    bull = new Bullet(_position.x() * Cell.Size + Cell.Size / 2, _position.y() * Cell.Size + Cell.Size / 2, target, _damage, temp);
                }
            }
        }
        return bull;

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
     * Функция проверяющая может ли стоять сооружение на дороге
     */
    static boolean canStayOnRoad() {
        return false;
    }

    /**
     * Скорость атаки
     */
    protected float _attackSpeed;

    /**
     * Скорость атаки
     */
    public float attackSpeed() {
        return _attackSpeed;
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
     * Цена
     */
    protected int _price;

    /**
     * Цена
     */
    public int price() {
        return _price;
    }

    /**
     * Текстура для снаряда
     */
    protected TextureRegion _textureForBullet;

    /**
     * Текстура для отрисовки
     */
    public TextureRegion textureForBullet() {
        return _textureForBullet;
    }
    /**
     * Текстура для отрисовки
     */
    protected TextureRegion _texture;

    /**
     * Текстура для отрисовки
     */
    public TextureRegion texture() {
        return _texture;
    }

}
