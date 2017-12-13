package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.game.screen.IntroScreen;
import com.mygdx.game.screen.MapsScreen;

/**
 * Игра TD
 * @author Alex
 */
public class TDGame extends Game {

    /**
    * Начальный экран
    */
    public IntroScreen introScreen;
    
     /**
    * Экран для выбора карты
    */
    public MapsScreen mapsScreen;


    @Override
    public void create() {

        introScreen = new IntroScreen(this);
        mapsScreen = new MapsScreen(this);

        this.setScreen(introScreen);

    }

    @Override
    public void render() {
        super.render();

    }

    @Override
    public void dispose() {

    }

    @Override
    public void resize(int width, int height) {
    }

    public void pause() {
    }

    public void resume() {
    }

}
