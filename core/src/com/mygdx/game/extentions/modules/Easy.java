/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.extentions.modules;

import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.TDGame;
import com.mygdx.game.defenseConstucts.DCFactory;
import com.mygdx.game.defenseConstucts.DefenseConstruction;
import com.mygdx.game.defenseConstucts.Tower;
import com.mygdx.game.extentions.Module;
import com.mygdx.game.mapAndOther.Cell;
import com.mygdx.game.screen.LevelScreen;
import java.util.Random;

public class Easy implements Module {

    public long _lastActionTime = 0;
    public LevelScreen _ls = null;
    public Cell _targetPos = null;

    @Override
    public void load( LevelScreen ls) {
        System.out.println("Module " + this.getClass() + " loading ...");
        _ls = ls;
    }

    @Override
    public int run(TDGame game) {
        if (_lastActionTime == 0 || (TimeUtils.millis() - _lastActionTime > 1 * 300)) {
            _lastActionTime = TimeUtils.millis();
            System.out.println("Module " + this.getClass() + "Bot action");
            //Определить состояние игры
            if (_ls.mainHP() > 0 && !_ls.Win) {
                if (_targetPos == null) {
                    // Определить позицию для башни.
                    _targetPos = definePosition();
                }
                if (_ls.currentCell.equals(_targetPos)) {
                   buildRandomTower();
                } else {
                    _ls.currentCell = moveToTarget(_ls.currentCell,_targetPos);

                }

                if (_ls.currentMoney < 25) {
                    //Запустить волну, если не запущена
                    _ls. startWave();
                }
            }

        }

        return Module.EXIT_SUCCESS;
    }
    public DefenseConstruction buildTowerOnNum(int num, Cell cell)
    {
         if (num == 0) {
            return DCFactory.getTower("IceTower",cell );
        } else if (num == 1) {
           return DCFactory.getTower("ArcherTower", cell);
        } else if (num == 2) {
           return DCFactory.getTower("LightTower", cell);
        }else{
            return null;
        }

    }
    
    public void buildRandomTower() {
        Random rand = new Random();
        int num = rand.nextInt(3);

        // Передвинуть ячейку
        _ls.currentCell = _targetPos;
        if(!_ls.isRoad(_targetPos)){
        // Поставить башню
         _ls.buyTower(buildTowerOnNum(num,_ls.currentCell));
        
        }
        _targetPos = null;
    }
    
    public Cell moveToTarget(Cell curPos, Cell targetPos) {
//        if (curPos.x() > targetPos.x()) {
//           return new Cell(curPos.x() - 1, curPos.y());
//        } else if (curPos.x() < targetPos.x()) {
//            return new Cell(curPos.x() + 1, curPos.y());
//        } else if (curPos.y() > targetPos.y()) {
//            return new Cell(curPos.x(), curPos.y() - 1);
//        } else if (curPos.y() < targetPos.y()) {
//            return new Cell(curPos.x(), curPos.y() + 1);
//        } 
            return null;

    }

    public Cell definePosition() {
        Random rand = new Random();
        int num = rand.nextInt(_ls.getRoad().size());
        Cell roadCell = _ls.getRoad().get(num);

        if (!_ls.isNotEmpty(new Cell(roadCell.x() + 1, roadCell.y()))
                && !_ls.isRoad(new Cell(roadCell.x() + 1, roadCell.y()))
                && roadCell.x() + 1 < _ls.mapWidth()) {
            return new Cell(roadCell.x() + 1, roadCell.y());
        } else if (!_ls.isNotEmpty(new Cell(roadCell.x(), roadCell.y() + 1))
                && !_ls.isRoad(new Cell(roadCell.x(), roadCell.y() + 1))
                && roadCell.y() + 1 < _ls.mapHeight()) {
            return new Cell(roadCell.x(), roadCell.y() + 1);
        } else if (!_ls.isNotEmpty(new Cell(roadCell.x() - 1, roadCell.y()))
                && !_ls.isRoad(new Cell(roadCell.x() - 1, roadCell.y()))
                && roadCell.x() - 1 > -1) {
            return new Cell(roadCell.x() - 1, roadCell.y());
        } else if (!_ls.isNotEmpty(new Cell(roadCell.x(), roadCell.y() - 1))
                && !_ls.isRoad(new Cell(roadCell.x(), roadCell.y() - 1))
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
