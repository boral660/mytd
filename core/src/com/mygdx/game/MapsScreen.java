/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 *
 * @author PK
 */
class MapsScreen implements Screen{
 
TDGame  game;
OrthographicCamera camera;
private SpriteBatch batch;
private Texture gameIm;
Skin buttonsSkin = new Skin();
Stage stage = new Stage();
 Map currentMap;

    public MapsScreen(TDGame aThis) {
       super();
        game=aThis;
       batch = new SpriteBatch();
       gameIm = new Texture("grass.jpg");

    }
    

    @Override
    public void show() {
        
    }

    @Override
    public void render(float f) {
         stage = new Stage();
         Gdx.input.setInputProcessor(stage);// Make the stage consume event

        createBasicSkin();
        
        TextButton MapName = new TextButton("New game", buttonsSkin); // Use the initialized skin
        MapName.setPosition(Gdx.graphics.getWidth()/2 - Gdx.graphics.getWidth()/8 , Gdx.graphics.getHeight()/2);
        MapName.setTouchable(Touchable.enabled);
        stage.addActor(MapName);
        
          MapName.addListener( new ClickListener() {
             @Override
        public boolean touchDown(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y, int pointer, int button) {
              game.setScreen( game.mapsScreen );
               return true;
         }
        } );
        
        TextButton PrevMap = new TextButton("Exit", buttonsSkin); // Use the initialized skin
        PrevMap.setPosition(Gdx.graphics.getWidth()/2 - Gdx.graphics.getWidth()/8 , Gdx.graphics.getHeight()/2-Gdx.graphics.getHeight()/8);
        PrevMap.setTouchable(Touchable.enabled);
         stage.addActor(PrevMap);
         
             TextButton NextMap = new TextButton("Exit", buttonsSkin); // Use the initialized skin
        NextMap.setPosition(Gdx.graphics.getWidth()/2 - Gdx.graphics.getWidth()/8 , Gdx.graphics.getHeight()/2-Gdx.graphics.getHeight()/8);
        NextMap.setTouchable(Touchable.enabled);
         stage.addActor(NextMap);
        
        PrevMap.addListener( new ClickListener() {
             @Override
        public boolean touchDown(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y, int pointer, int button) {
              game.setScreen( game.mapsScreen );
               return true;
         }
        } );
        
         NextMap.addListener( new ClickListener() {
             @Override
        public boolean touchDown(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y, int pointer, int button) {
              game.setScreen( game.mapsScreen );
               return true;
         }
        } );
              Gdx.input.setInputProcessor(stage);// Make the stage consume event
       
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
    private void createBasicSkin(){
  //Create a font
  BitmapFont font = new BitmapFont();
  buttonsSkin.add("default", font);
 
  //Create a texture
  Pixmap pixmap = new Pixmap((int)Gdx.graphics.getWidth()/4,(int)Gdx.graphics.getHeight()/12, Pixmap.Format.RGB888);
  pixmap.setColor(Color.WHITE);
  pixmap.fill();
  buttonsSkin.add("background",new Texture(pixmap));
 
  //Create a button style
  TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
  textButtonStyle.up = buttonsSkin.newDrawable("background", Color.GRAY);
  textButtonStyle.down = buttonsSkin.newDrawable("background", Color.DARK_GRAY);
  textButtonStyle.checked = buttonsSkin.newDrawable("background", Color.DARK_GRAY);
  textButtonStyle.over = buttonsSkin.newDrawable("background", Color.LIGHT_GRAY);
  textButtonStyle.font = buttonsSkin.getFont("default");
  buttonsSkin.add("default", textButtonStyle);
 
    }    
}


