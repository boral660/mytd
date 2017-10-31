/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 *
 * @author PK
 */
class MapsScreen implements Screen{
 
TDGame  game;
OrthographicCamera camera;
private SpriteBatch batch;
private Texture gameIm;
Skin skin = new Skin();
    public MapsScreen(TDGame aThis) {
       super();
        game=aThis;
       batch = new SpriteBatch();
       gameIm = new Texture("grass.jpg");
       game=aThis;
    }
    

    @Override
    public void show() {
        
    }

    @Override
    public void render(float f) {
          Gdx.gl.glClearColor(0, 0, 0.2f, 1);
                Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
       
        batch.begin();
        batch.draw(gameIm, 0, 0);
        batch.end();
    }

    @Override
    public void resize(int i, int i1) {
        
    }

    @Override
    public void pause() {
       
    }

    @Override
    public void resume() {
        
    }

    @Override
    public void hide() {
       
    }

    @Override
    public void dispose() {
          gameIm.dispose();
        batch.dispose();
    }
    
}
