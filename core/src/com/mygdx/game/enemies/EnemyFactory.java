/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import java.util.ArrayList;
import com.mygdx.game.mapAndOther.Cell;
import com.mygdx.game.mapAndOther.Map;

/**
 * Фабрика врагов
 *
 * @author PK
 */
public class EnemyFactory {

    /**
     * Текстуры врагов
     */
    private static TextureAtlas enemyAtlas = new TextureAtlas(Gdx.files.internal("Texturs.atlas"));

    /**
     * Получить врага
     *
     * @param Type - тип врага
     * @param pos - начальная позиция
     * @param road - путь
     * @return -враг
     */
    public static Enemy getEnemy(String Type, Cell pos, ArrayList<Cell> road, Map map) {
        // Ближний бой
        if (Type.equals("SkeletonWarrior")) {
            return new Melee(pos, road, 15, 5, 2.2f, 4, 1.5f, enemyAtlas.findRegion("SkeletonWarrior"), enemyAtlas.findRegion("Nothing"), map);
        } else if (Type.equals("Viking")) {
            return new Melee(pos, road, 25, 30, 1.8f, 6, 1.3f, enemyAtlas.findRegion("Viking"), enemyAtlas.findRegion("Nothing"), map);
        } else if (Type.equals("Ork")) {
            return new Melee(pos, road, 30, 10, 2.4f, 6, 1.7f, enemyAtlas.findRegion("Ork"), enemyAtlas.findRegion("Nothing"), map);
        } else if (Type.equals("Rat")) {
            return new Melee(pos, road, 10, 2, 2.7f, 4, 0.5f, enemyAtlas.findRegion("Rat"), enemyAtlas.findRegion("Nothing"), map);
        } else if (Type.equals("Slime")) {
            return new Melee(pos, road, 50, 50, 1.9f, 10, 3f, enemyAtlas.findRegion("Slime"), enemyAtlas.findRegion("Nothing"), map);
        } // Враг с телепортом
         else if (Type.equals("Teleporter")) {
            return new EnemyWithTeleport(pos, road, 50, 50, 2.5f, 10, 2f, enemyAtlas.findRegion("SlimeMage"), enemyAtlas.findRegion("Nothing"), map);
        } //Дальний бой
        else if (Type.equals("SkeletonMage")) {
            return new Range(pos, road, 10, 5, 2.2f, 6, 1.5f, enemyAtlas.findRegion("SkeletonMage"), 2, enemyAtlas.findRegion("MagicBall"), map);
        } else if (Type.equals("LizardArcher")) {
            return new Range(pos, road, 15, 7, 2.3f, 8, 1f, enemyAtlas.findRegion("LizardArcher"), 3, enemyAtlas.findRegion("MetallicBall"), map);
        } else if (Type.equals("Hunter")) {
            return new Range(pos, road, 21, 5, 2.5f, 10, 1.2f, enemyAtlas.findRegion("Hunter"), 3, enemyAtlas.findRegion("MetallicBall"), map);
        } else if (Type.equals("Bomber")) {
            return new Range(pos, road, 20, 2, 2.0f, 8, 1.7f, enemyAtlas.findRegion("Bomber"), 2, enemyAtlas.findRegion("MetallicBall"), map);
        } else if (Type.equals("Balista")) {
            return new Range(pos, road, 50, 50, 2.1f, 15, 4f, enemyAtlas.findRegion("Balista"), 4, enemyAtlas.findRegion("MetallicBall"), map);
        }
        return null;

    }
    /**
     * Список врагов
     */
    public static String[] EnemyList = {"SkeletonWarrior", "Viking", "Ork", "Rat", "Slime", "SkeletonMage", "LizardArcher", "Hunter", "Bomber", "Balista"};
}
