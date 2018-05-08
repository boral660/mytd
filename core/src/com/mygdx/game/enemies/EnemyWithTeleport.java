/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.enemies;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.mapAndOther.Cell;
import com.mygdx.game.mapAndOther.Direction;
import com.mygdx.game.mapAndOther.Map;
import com.mygdx.game.mapAndOther.Pair;
import java.util.ArrayList;
import java.util.Random;

/**
 * Враги ближнего боя c телепортом
 *
 * @author PK
 */
public class EnemyWithTeleport extends Melee {

    public EnemyWithTeleport(Cell pos, ArrayList<Cell> road, int hp, int dmg, float speed, int moneyForKill, float atkSpeed, TextureRegion pict, TextureRegion pictForBullet, Map map) {
        super(pos, road, hp, dmg, speed, moneyForKill, atkSpeed, pict, pictForBullet, map);
    }

     /**
     * @param shift сдвиг от центра
     *
     */
    int _shiftOnCenter=0;
    /**
     * Двигаться по направлению
     *
     * @param shift сдвиг
     *
     */
    public void move(float shift) {
        Random rand = new Random();
        int num = rand.nextInt(100);
        if (num > 2) {
            if (_path.size() > 0) {
                _direction = _path.get(0).getFirst();

                float deltaC = _path.get(0).getSecond() - (_speed - shift / 2);

                float countStep;
                if (deltaC < 0) {
                    countStep = _path.get(0).getSecond();
                    _path.remove(0);
                } else {
                    _path.get(0).setSecond(deltaC);
                    countStep = _speed - shift / 2;
                }

                if (deltaC == 0) {
                     _shiftOnCenter=0;
                    countStep = 0;
                    _path.remove(0);

                }

                if (_direction.equals(Direction.north())) {
                    _y += countStep;
                    if (countStep == 0 || deltaC < 0) {
                        _position = new Cell(_position.x(), _position.y() + 1);
                    }
                } else if (_direction.equals(Direction.south())) {
                    _y -= countStep;
                    if (countStep == 0 || deltaC < 0) {
                        _position = new Cell(_position.x(), _position.y() - 1);
                    }
                } else if (_direction.equals(Direction.west())) {
                    _x += countStep;
                    if (countStep == 0 || deltaC < 0) {
                        _position = new Cell(_position.x() + 1, _position.y());
                    }
                } else if (_direction.equals(Direction.east())) {
                    _x -= countStep;
                    if (countStep == 0 || deltaC < 0) {
                        _position = new Cell(_position.x() - 1, _position.y());
                    }
                }
                if (deltaC < 0) {
                    move(deltaC * -1);
                }
            }
            else
            {
              rand = new Random();
               num = 0;
                _position = new Cell (_roadCell.get(_roadCell.size()-1).x(),_roadCell.get(_roadCell.size()-1).y()-1);
                _x = _position.x()*Cell.Size-rand.nextInt(8);
                _y = _position.y()*Cell.Size-rand.nextInt(8);
            }
        } else {
            teleport();
        }

    }

    /**
     * Телепортироваться
     *
     */
    public void calcShiftOnPath(Direction dir) {
//        int index=-1;
//        for (Pair pair : _path )
//        {   index++;
//            if(pair.getSecond().equals(dir))
//            {
//                break;
//            }
//        }
//        if (_path.size() > 1 && index!=_path.size()) {
//            if (dir.equals(Direction.north())) {
//                if (_path.get(index).getFirst().equals(Direction.north())) {
//                    _path.get(index).setSecond(_path.get(index).getSecond() - Cell.Size);
//                } else if (_path.get(index).getFirst().equals(Direction.south())) {
//                    _path.get(index).setSecond(_path.get(index).getSecond() + Cell.Size);
//                }
//            } else if (dir.equals(Direction.south())) {
//                if (_path.get(index).getFirst().equals(Direction.north())) {
//                    _path.get(index).setSecond(_path.get(index).getSecond() + Cell.Size);
//                } else if (_path.get(index).getFirst().equals(Direction.south())) {
//                    _path.get(index).setSecond(_path.get(index).getSecond() - Cell.Size);
//                }
//            } else if (dir.equals(Direction.west())) {
//                if (_path.get(index).getFirst().equals(Direction.west())) {
//                    _path.get(index).setSecond(_path.get(index).getSecond() - Cell.Size);
//                } else if (_path.get(index).getFirst().equals(Direction.east())) {
//                    _path.get(index).setSecond(_path.get(index).getSecond() + Cell.Size);
//                }
//            } else if (dir.equals(Direction.east())) {
//                if (_path.get(index).getFirst().equals(Direction.west())) {
//                    _path.get(index).setSecond(_path.get(index).getSecond() + Cell.Size);
//                } else if (_path.get(index).getFirst().equals(Direction.east())) {
//                    _path.get(index).setSecond(_path.get(index).getSecond() - Cell.Size);
//                }
//            }
//        }
    }

    /**
     * Телепортироваться
     *
     */
    public void teleport() {
        Random rand = new Random();
        int num = 0;
        if (_direction.equals(Direction.north()) || _direction.equals(Direction.south())) {
            rand = new Random();
            num = rand.nextInt(2);
            if (num == 1) {
                if (checkTeleport(Direction.west()) && _shiftOnCenter!=1) {
                      _shiftOnCenter++;
                    calcShiftOnPath(Direction.west());
                    _position = new Cell(_position.x() + 1, _position.y());
                    _x = _x + Cell.Size;
                }
            } else if (checkTeleport(Direction.east())&& _shiftOnCenter!=-1) {
                 _shiftOnCenter--;
                calcShiftOnPath(Direction.east());
                _position = new Cell(_position.x() - 1, _position.y());
                _x = _x - Cell.Size;
            }
        } else {
            rand = new Random();
            num = rand.nextInt(2);
            if (num == 1) {
                if (checkTeleport(Direction.north())&& _shiftOnCenter!=1) {
                     _shiftOnCenter++;
                    calcShiftOnPath(Direction.north());
                    _position = new Cell(_position.x(), _position.y() + 1);
                    _y = _y + Cell.Size;
                }
            } else if (checkTeleport(Direction.south())&& _shiftOnCenter!=-1) {
                calcShiftOnPath(Direction.south());
                 _shiftOnCenter--;
                _position = new Cell(_position.x(), _position.y() - 1);
                _y = _y - Cell.Size;
            }
        }
    }

    /**
     * Проверить возможно ли телепортироваться по направлению
     *
     *
     */
    public boolean checkTeleport(Direction dir) {
        if (dir.equals(Direction.north())) {
            return _map.CheckRoad(new Cell(_position.x(), _position.y() + 1));
        } else if (dir.equals(Direction.south())) {
            return _map.CheckRoad(new Cell(_position.x(), _position.y() - 1));
        } else if (dir.equals(Direction.west())) {
            return _map.CheckRoad(new Cell(_position.x() + 1, _position.y()));
        }
        return _map.CheckRoad(new Cell(_position.x() - 1, _position.y()));
    }

}
