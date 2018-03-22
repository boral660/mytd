/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.extentions;

import com.mygdx.game.TDGame;

public interface Module {

  public static final int EXIT_SUCCESS = 0;
  public static final int EXIT_FAILURE = 1;

  public void load(TDGame game);
  public int run(TDGame game);
  public void unload(TDGame game);
}
