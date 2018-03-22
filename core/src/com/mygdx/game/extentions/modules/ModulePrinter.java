/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.extentions.modules;

import com.mygdx.game.TDGame;
import com.mygdx.game.extentions.Module;
import com.mygdx.game.screen.LevelScreen;

public class ModulePrinter implements Module {

  @Override
  public void load(TDGame game) {
    System.out.println("Module " + this.getClass() + " loading ...");
  }

  @Override
  public int run(TDGame game) {
    System.out.println("Module " + this.getClass() + " running ...");
    game.setScreen(new LevelScreen(game, game.mapsScreen.getMap(),true));
    return Module.EXIT_SUCCESS;
  }

  @Override
  public void unload(TDGame game) {
    System.out.println("Module " + this.getClass() + " inloading ...");
  }
}
