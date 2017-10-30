/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import java.util.Iterator;


/**
 * Начальный экран
 * @author PK
 */
class IntroScreen implements Screen{
TDGame  game;
OrthographicCamera camera;
 private SpriteBatch batch;
  private Texture intrIm;
  Skin skin = new Skin();
     Stage stage = new Stage();
     
    public IntroScreen(TDGame aThis) {
         super();
        game=aThis;
        batch = new SpriteBatch();
        intrIm = new Texture("Intro.jpg");
    }


 
    @Override
    public void render(float delta) {
         stage = new Stage();
         Gdx.input.setInputProcessor(stage);// Make the stage consume event

        createBasicSkin();
        TextButton newGameButton = new TextButton("New game", skin); // Use the initialized skin
        newGameButton.setPosition(Gdx.graphics.getWidth()/2 - Gdx.graphics.getWidth()/8 , Gdx.graphics.getHeight()/2);
        newGameButton.setTouchable(Touchable.enabled);
        stage.addActor(newGameButton);
        
        TextButton ExitButton = new TextButton("Exit", skin); // Use the initialized skin
        ExitButton.setPosition(Gdx.graphics.getWidth()/2 - Gdx.graphics.getWidth()/8 , Gdx.graphics.getHeight()/2-Gdx.graphics.getHeight()/8);
        ExitButton.setTouchable(Touchable.enabled);
         stage.addActor(ExitButton);
         
     
    
        Gdx.input.setInputProcessor(stage);// Make the stage consume event
        
        ExitButton.addListener( new ClickListener() {
             @Override
        public boolean touchDown(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y, int pointer, int button) {
              game.setScreen( game.gameScreen );
            return true;
         }
    } );
        
       
           Gdx.gl.glClearColor(0, 0, 0.2f, 1);
                Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
       
        batch.begin();
        batch.draw(intrIm, 0, 0);
        batch.end();
         stage.act();
        stage.draw();

    }




    @Override
    public void hide() { }
 
    @Override
    public void pause() { }
 
    @Override
    public void resume() { }
 
    @Override
    public void show() {   
        Gdx.input.setInputProcessor(stage);
    }
 
    @Override
    public void resize(int width, int height) { }
 
    @Override
    public void dispose() {
        intrIm.dispose();
        batch.dispose();
    }
    
private void createBasicSkin(){
  //Create a font
  BitmapFont font = new BitmapFont();
  skin.add("default", font);
 
  //Create a texture
  Pixmap pixmap = new Pixmap((int)Gdx.graphics.getWidth()/4,(int)Gdx.graphics.getHeight()/12, Pixmap.Format.RGB888);
  pixmap.setColor(Color.WHITE);
  pixmap.fill();
  skin.add("background",new Texture(pixmap));
 
  //Create a button style
  TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
  textButtonStyle.up = skin.newDrawable("background", Color.GRAY);
  textButtonStyle.down = skin.newDrawable("background", Color.DARK_GRAY);
  textButtonStyle.checked = skin.newDrawable("background", Color.DARK_GRAY);
  textButtonStyle.over = skin.newDrawable("background", Color.LIGHT_GRAY);
  textButtonStyle.font = skin.getFont("default");
  skin.add("default", textButtonStyle);
 
}
        public class MyInputProcessor implements InputProcessor {
    @Override
    public boolean keyDown (int keycode) {
        return false;
    }

    @Override
    public boolean keyUp (int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped (char character) {
        return false;
    }

    @Override
    public boolean touchDown (int x, int y, int pointer, int button) {
         game.setScreen(game.gameScreen);
         return true;
    }

    @Override
    public boolean touchUp (int x, int y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged (int x, int y, int pointer) {
        return false;
    }

    @Override
    public boolean scrolled (int amount) {
        return false;
    }

        @Override
        public boolean mouseMoved(int i, int i1) {
            return false;
        
    }
}
       
}
