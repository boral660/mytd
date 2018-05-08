/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.mapAndOther;

import com.mygdx.game.enemies.Enemy;
import com.mygdx.game.enemies.EnemyFactory;
import com.mygdx.game.enemies.Wave;
import java.util.ArrayList;
import com.mygdx.game.defenseConstucts.DefenseConstruction;
import java.util.Random;

/**
 * Игровая карта
 *
 * @author PK
 */
public class Map {

    /**
     * Конструктор - создание новой карты
     *
     * @param name название карты
     * @param moneyOnStart деньги на старте
     *
     */
    Map(String name, int moneyOnStart, MainConstruction main) {
        _main = main;
        _name = name;
        _moneyOnStart = moneyOnStart;
        _height = 8;
        _width = 16;
    }
    /**
     * Главная конструкиция
     */
    private MainConstruction _main;

    /**
     * Главная конструкиция
     */
    public MainConstruction main() {
        return _main;
    }

    /**
     * Название карты
     */
    private String _name;

    public String name() {
        return _name;
    }
    /**
     * Ширина карты
     */
    private int _width;

    public int width() {
        return _width;
    }
    /**
     * Высота карты
     */
    private int _height;

    public int height() {
        return _height;
    }

    /**
     * Деньги на старте
     */
    private int _moneyOnStart;

    public int moneyOnStart() {
        return _moneyOnStart;
    }
    /**
     * Клетки башнями
     */
    private ArrayList<DefenseConstruction> _defenseConst = new ArrayList();

    public ArrayList<DefenseConstruction> defenseConst() {
        return _defenseConst;
    }

    /**
     * Клетки с дорогой
     */
    private ArrayList<Cell> _roadCell = new ArrayList();

    public ArrayList<Cell> roadCell() {
        return _roadCell;
    }

    /**
     * Волны с врагами
     */
    private ArrayList<Wave> _waves = new ArrayList();

    public ArrayList<Wave> waves() {
        return _waves;
    }

    /**
     * Проверить, занята ли ячейка
     *
     * @param position позиция ячейки на карте
     * @return true - ячейка занята объектом, false -ячейка свободна
     *
     */
    public boolean CheckCell(Cell position) {
        if (_main.position().equals(position)) {
            return true;
        }

        for (DefenseConstruction dc : _defenseConst) {
            if (dc.position().equals(position)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Проверить, является ли эта ячейка дорогой
     *
     * @param position позиция ячейки на карте
     * @return true - ячейка является дорогой, false -ячейка местоности
     *
     */
    public boolean CheckRoad(Cell position) {
        for (Cell cell : _roadCell) {
            if (cell.equals(position)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Заполнение карты по первому шаблону
     *
     */
    static public Map GenerateMap1() {
        MainConstruction main = new MainConstruction(new Cell(8, 5), 200,MainConstruction.Effects.Damage);
        // Создать первую карту
        Map map = new Map("Long-long way", 100, main);
        for (int i = 0; i < 16; i++) {
            map.roadCell().add(new Cell(i, 0));
        }
        for (int i = 1; i < 7; i++) {
            map.roadCell().add(new Cell(15, i));
        }
        for (int i = 15; i > 0; i--) {
            map.roadCell().add(new Cell(i, 7));
        }
        for (int i = 7; i > 1; i--) {
            map.roadCell().add(new Cell(0, i));
        }
        for (int i = 1; i < 9; i++) {
            map.roadCell().add(new Cell(i, 2));
        }
        map.roadCell().add(new Cell(8, 3));
        map.roadCell().add(new Cell(8, 4));

        // Создаём первую волну
        ArrayList<Enemy> enemies = new ArrayList<Enemy>();
        enemies.add(EnemyFactory.getEnemy("SkeletonWarrior", map.roadCell().get(0), map.roadCell(), map));
        enemies.add(EnemyFactory.getEnemy("Slime", map.roadCell().get(0), map.roadCell(), map));
        enemies.add(EnemyFactory.getEnemy("Rat", map.roadCell().get(0), map.roadCell(), map));
        enemies.add(EnemyFactory.getEnemy("Balista", map.roadCell().get(0), map.roadCell(), map));
        enemies.add(EnemyFactory.getEnemy("Ork", map.roadCell().get(0), map.roadCell(), map));
        map.waves().add(new Wave(enemies));
        // Cоздаём вторую волну
        enemies = new ArrayList<Enemy>();
        enemies.add(EnemyFactory.getEnemy("Balista", map.roadCell().get(0), map.roadCell(), map));
        enemies.add(EnemyFactory.getEnemy("Ork", map.roadCell().get(0), map.roadCell(), map));
        enemies.add(EnemyFactory.getEnemy("Viking", map.roadCell().get(0), map.roadCell(), map));
        enemies.add(EnemyFactory.getEnemy("Bomber", map.roadCell().get(0), map.roadCell(), map));
        enemies.add(EnemyFactory.getEnemy("Rat", map.roadCell().get(0), map.roadCell(), map));
        map.waves().add(new Wave(enemies));
        // Cоздаём третью волну
        enemies = new ArrayList<Enemy>();
        enemies.add(EnemyFactory.getEnemy("Rat", map.roadCell().get(0), map.roadCell(), map));
        enemies.add(EnemyFactory.getEnemy("Rat", map.roadCell().get(0), map.roadCell(), map));
        enemies.add(EnemyFactory.getEnemy("Rat", map.roadCell().get(0), map.roadCell(), map));
        enemies.add(EnemyFactory.getEnemy("Rat", map.roadCell().get(0), map.roadCell(), map));
        enemies.add(EnemyFactory.getEnemy("Rat", map.roadCell().get(0), map.roadCell(), map));
        enemies.add(EnemyFactory.getEnemy("Balista", map.roadCell().get(0), map.roadCell(), map));
        enemies.add(EnemyFactory.getEnemy("Ork", map.roadCell().get(0), map.roadCell(), map));
        enemies.add(EnemyFactory.getEnemy("Balista", map.roadCell().get(0), map.roadCell(), map));
        enemies.add(EnemyFactory.getEnemy("Ork", map.roadCell().get(0), map.roadCell(), map));
        enemies.add(EnemyFactory.getEnemy("Balista", map.roadCell().get(0), map.roadCell(), map));
        enemies.add(EnemyFactory.getEnemy("Ork", map.roadCell().get(0), map.roadCell(), map));
        enemies.add(EnemyFactory.getEnemy("Balista", map.roadCell().get(0), map.roadCell(), map));
        enemies.add(EnemyFactory.getEnemy("Ork", map.roadCell().get(0), map.roadCell(), map));
        enemies.add(EnemyFactory.getEnemy("Balista", map.roadCell().get(0), map.roadCell(), map));
        enemies.add(EnemyFactory.getEnemy("Ork", map.roadCell().get(0), map.roadCell(), map));

        map.waves().add(new Wave(enemies));

        return map;
    }

    /**
     * Заполнение карты по второму шаблону
     */
    static public Map GenerateMap2() {
        MainConstruction main = new MainConstruction(new Cell(7, 4), 350, MainConstruction.Effects.Heal);
        // Создать первую карту
        Map map = new Map("Two fronts", 115, main);
        for (int i = 0; i < 16; i++) {
            if (i != 7) {
                map.roadCell().add(new Cell(i, 4));
            }
        }
        // Создаём волну первого фронта
        ArrayList<Cell> Left_road = new ArrayList();
        for (int i = 0; i < 7; i++) {
            Left_road.add(new Cell(i, 4));
        }
        ArrayList<Enemy> enemies = new ArrayList<Enemy>();
        enemies.add(EnemyFactory.getEnemy("SkeletonWarrior", Left_road.get(0), Left_road, map));
        enemies.add(EnemyFactory.getEnemy("Slime", Left_road.get(0), Left_road, map));
        enemies.add(EnemyFactory.getEnemy("Rat", Left_road.get(0), Left_road, map));
        map.waves().add(new Wave(enemies));
        // Создаём волну второго фронта
        ArrayList<Cell> Right_road = new ArrayList();
        for (int i = 15; i > 8; i--) {
            Right_road.add(new Cell(i, 4));
        }
        enemies = new ArrayList<Enemy>();
        enemies.add(EnemyFactory.getEnemy("Viking", Right_road.get(0), Right_road, map));
        enemies.add(EnemyFactory.getEnemy("Ork", Right_road.get(0), Right_road, map));
        enemies.add(EnemyFactory.getEnemy("Balista", Right_road.get(0), Right_road, map));
        map.waves().add(new Wave(enemies));

        // Cоздаём третью волну
        enemies = new ArrayList<Enemy>();
        enemies.add(EnemyFactory.getEnemy("Viking", Right_road.get(0), Right_road, map));
        enemies.add(EnemyFactory.getEnemy("Ork", Right_road.get(0), Right_road, map));
        enemies.add(EnemyFactory.getEnemy("Balista", Right_road.get(0), Right_road, map));
        enemies.add(EnemyFactory.getEnemy("SkeletonWarrior", Left_road.get(0), Left_road, map));
        enemies.add(EnemyFactory.getEnemy("Slime", Left_road.get(0), Left_road, map));
        enemies.add(EnemyFactory.getEnemy("Rat", Left_road.get(0), Left_road, map));
        map.waves().add(new Wave(enemies));
        return map;
    }

    /**
     * Заполнение карты по третьему шаблону
     */
    static public Map GenerateMap3() {
        MainConstruction main = new MainConstruction(new Cell(8, 5), 350,MainConstruction.Effects.Money);
        Map map = new Map("Random Waves", 60, main);
        map.roadCell().add(new Cell(0, 0));
        map.roadCell().add(new Cell(1, 0));
        map.roadCell().add(new Cell(2, 0));
        map.roadCell().add(new Cell(2, 1));
        map.roadCell().add(new Cell(2, 2));
        map.roadCell().add(new Cell(3, 2));
        map.roadCell().add(new Cell(4, 2));
        map.roadCell().add(new Cell(5, 2));
        map.roadCell().add(new Cell(6, 2));
        map.roadCell().add(new Cell(7, 2));
        map.roadCell().add(new Cell(8, 2));
        map.roadCell().add(new Cell(8, 3));
        map.roadCell().add(new Cell(8, 4));
        Random rand = new Random();
        int num = rand.nextInt(15) + 1;

        for (int i = 0; i < num; i++) {
            map.waves().add(Wave.createRandomWave(i + 2, map.roadCell(),map));
        }

        return map;
    }
    
     /**
     * Заполнение карты по третьему шаблону
     */
    static public Map GenerateMap4() {
       MainConstruction main = new MainConstruction(new Cell(0, 6), 1000,MainConstruction.Effects.Money);
         // Создать первую карту
        Map map = new Map("HouseOnRoad", 115, main);
        for (int i = 0; i < 16; i++) {
                map.roadCell().add(new Cell(i, 0));
                map.roadCell().add(new Cell(i, 1));
                map.roadCell().add(new Cell(i, 2));
        }
          for (int i = 3; i < 8; i++) {
                map.roadCell().add(new Cell(15, i));
                 map.roadCell().add(new Cell(14, i));
                  map.roadCell().add(new Cell(13, i));
        }

       
        for (int i = 14; i > 0; i--) {
                map.roadCell().add(new Cell(i, 7));
                map.roadCell().add(new Cell(i, 6));
                map.roadCell().add(new Cell(i, 5));
        }
         map.roadCell().add(new Cell(0, 7));
          map.roadCell().add(new Cell(0, 5));
            
    
        ArrayList<Cell> roadForEnemy = new ArrayList<Cell>();
        
             for (int i = 0; i < 15; i++) {
                roadForEnemy.add(new Cell(i, 1));
        }
          for (int i = 3; i < 7; i++) {
                roadForEnemy.add(new Cell(14, i));
        }
       
        for (int i = 14; i > 0; i--) {
               roadForEnemy.add(new Cell(i, 7));
        }
     
        Random rand = new Random();
        int num = rand.nextInt(15) + 1;

        // Cоздаём третью волну
        for (int i = 0; i < num; i++) {
            map.waves().add(Wave.createRandomWave(i + 2, roadForEnemy,map));
            map.waves().get( i).enemies().add(EnemyFactory.getEnemy("Teleporter", roadForEnemy.get(0), roadForEnemy, map));
            map.waves().get( i).enemies().add(EnemyFactory.getEnemy("Teleporter", roadForEnemy.get(0), roadForEnemy, map));
            map.waves().get( i).enemies().add(EnemyFactory.getEnemy("Teleporter", roadForEnemy.get(0), roadForEnemy, map));
            map.waves().get( i).enemies().add(EnemyFactory.getEnemy("Teleporter", roadForEnemy.get(0), roadForEnemy, map));
        }
        return map;
    }

}
