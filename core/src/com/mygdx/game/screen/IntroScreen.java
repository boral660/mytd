/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.screen;

import com.mygdx.game.TDGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.mapAndOther.SkinForButton;

/**
 * Начальный экран
 *
 * @author PK
 */
public class IntroScreen implements Screen {

    /**
     * Ссылка на игру
     */
    private TDGame game;
    /**
     * Начальный экран
     */
    private SpriteBatch batch;
    /**
     * Текстура фона
     */
    private Texture intrIm;
    /**
     * Скин для кнопок
     */
    private Skin buttonsSkin = new Skin();
    /**
     * Сцена
     */
    private Stage stage;

    public IntroScreen(TDGame aThis) {
        super();
        game = aThis;
        batch = new SpriteBatch();
        intrIm = new Texture("Intro.jpg");
        createButtons();
    }

    /**
     * Создание кнопок и слушателей
     */
    private void createButtons() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        buttonsSkin = SkinForButton.createBasicSkin();
        TextButton newGameButton = new TextButton("New game", buttonsSkin);
        newGameButton.setPosition(Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 8, Gdx.graphics.getHeight() / 2);
        newGameButton.setTouchable(Touchable.enabled);
        stage.addActor(newGameButton);

        TextButton ExitButton = new TextButton("Exit", buttonsSkin);
        ExitButton.setPosition(Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 8, Gdx.graphics.getHeight() / 2 - Gdx.graphics.getHeight() / 8);
        ExitButton.setTouchable(Touchable.enabled);
        stage.addActor(ExitButton);

        newGameButton.addListener(new ClickListener() {
            @Override
            public boolean touchDown(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y, int pointer, int button) {
                game.mapsScreen.restart();
                game.setScreen(game.mapsScreen);
                return true;
            }
        });

        ExitButton.addListener(new ClickListener() {
            @Override
            public boolean touchDown(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y, int pointer, int button) {
                System.runFinalizersOnExit(true);
                System.exit(0);
                return true;
            }
        });
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(intrIm, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();
        stage.act();
        stage.draw();

    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void dispose() {
        intrIm.dispose();
        batch.dispose();
    }

}
