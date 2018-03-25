/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.extentions.modules;

import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.TDGame;
import com.mygdx.game.defenseConstucts.DCFactory;
import com.mygdx.game.extentions.Module;
import com.mygdx.game.mapAndOther.Cell;
import com.mygdx.game.screen.IntroScreen;
import com.mygdx.game.screen.LevelScreen;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ModulePrinter implements Module {

    private long _lastActionTime = 0;
    private LevelScreen _ls = null;
    private Cell _targetPos = null;

    @Override
    public void load(TDGame game) {
        System.out.println("Module " + this.getClass() + " loading ...");
        _ls = new LevelScreen(game, game.mapsScreen.getMap(), true);
        game.setScreen(_ls);
    }

    @Override
    public int run(TDGame game) {
        if (_lastActionTime == 0 || (TimeUtils.millis() - _lastActionTime > 1 * 300)) {
            _lastActionTime = TimeUtils.millis();
            System.out.println("Module " + this.getClass() + "Bot action");
            //Определить состояние игры
            if (_ls.map.main().integrity() > 0 && !_ls.Win) {
                if (_targetPos == null) {
                    // Определить позицию для башни.
                    _targetPos = definePosition(game);
                }
                if (_ls.currentCell.equals(_targetPos)) {
                    Random rand = new Random();
                    int num = rand.nextInt(3);

                    // Передвинуть ячейку
                    _ls.currentCell = _targetPos;
                    // Поставить башню
                    if (num == 0 && !_ls.map.CheckRoad(_targetPos)) {
                        _ls.buyTower(DCFactory.getTower("IceTower", _ls.currentCell));
                    } else if (num == 1 && !_ls.map.CheckRoad(_targetPos)) {
                        _ls.buyTower(DCFactory.getTower("ArcherTower", _ls.currentCell));
                    } else if (num == 2 && !_ls.map.CheckRoad(_targetPos)) {
                        _ls.buyTower(DCFactory.getTower("LightTower", _ls.currentCell));
                    }
                    _targetPos = null;
                } else {
                    if (_ls.currentCell.x() > _targetPos.x()) {
                        _ls.currentCell = new Cell(_ls.currentCell.x() - 1, _ls.currentCell.y());
                    } else if (_ls.currentCell.x() < _targetPos.x()) {
                        _ls.currentCell = new Cell(_ls.currentCell.x() + 1, _ls.currentCell.y());
                    } else if (_ls.currentCell.y() > _targetPos.y()) {
                        _ls.currentCell = new Cell(_ls.currentCell.x(), _ls.currentCell.y() - 1);
                    } else if (_ls.currentCell.y() < _targetPos.y()) {
                        _ls.currentCell = new Cell(_ls.currentCell.x(), _ls.currentCell.y() + 1);
                    }

                }

                if (_ls.currentMoney < 25) {
                    //Запустить волну, если не запущена
                    _ls.currentWave = _ls.map.waves().get(_ls.numberWave);
                }
            }

        }

        return Module.EXIT_SUCCESS;
    }

    public void moveCurrentCell(TDGame game) {

    }

    public Cell definePosition(TDGame game) {
        Random rand = new Random();
        int num = rand.nextInt(_ls.map.roadCell().size());
        Cell roadCell = _ls.map.roadCell().get(num);

        if (!_ls.map.CheckCell(new Cell(roadCell.x() + 1, roadCell.y()))
                && !_ls.map.CheckRoad(new Cell(roadCell.x() + 1, roadCell.y()))
                && roadCell.x() + 1 < _ls.map.width()) {
            return new Cell(roadCell.x() + 1, roadCell.y());
        } else if (!_ls.map.CheckCell(new Cell(roadCell.x(), roadCell.y() + 1))
                && !_ls.map.CheckRoad(new Cell(roadCell.x(), roadCell.y() + 1))
                && roadCell.y() + 1 < _ls.map.height()) {
            return new Cell(roadCell.x(), roadCell.y() + 1);
        } else if (!_ls.map.CheckCell(new Cell(roadCell.x() - 1, roadCell.y()))
                && !_ls.map.CheckRoad(new Cell(roadCell.x() - 1, roadCell.y()))
                && roadCell.x() - 1 > -1) {
            return new Cell(roadCell.x() - 1, roadCell.y());
        } else if (!_ls.map.CheckCell(new Cell(roadCell.x(), roadCell.y() - 1))
                && !_ls.map.CheckRoad(new Cell(roadCell.x(), roadCell.y() - 1))
                && roadCell.y() - 1 > -1) {
            return new Cell(roadCell.x(), roadCell.y() - 1);
        }

        return roadCell;
    }

    @Override
    public void unload(TDGame game) {
        System.out.println("Module " + this.getClass() + " inloading ...");
    }

}
