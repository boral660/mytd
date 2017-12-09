package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
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
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import java.util.ArrayList;
import java.util.Iterator;


/**
 *  Игра TD
 * @author Alex
 */
public class TDGame extends Game {
   

    public IntroScreen introScreen;
    public MapsScreen mapsScreen;


    ArrayList<Map> mapsList=new ArrayList<Map>();
    
        OrthographicCamera camera;
        SpriteBatch batch;// это специальный класс, который используется для рисования 2D изображений, 
                          //таких как текстуры, которые мы загрузили
     

        long lastDropTime;
        
	
	@Override
	public void create () {
            
            // Создать первую карту
          mapsList.add(Map.GenerateMap1());
          
                 introScreen = new IntroScreen(this);
                 mapsScreen = new MapsScreen(this);
               
            this.setScreen(introScreen);
                
	}

	@Override
	public void render () {
            super.render();
               
	}
	
	@Override
	public void dispose () {
            
	
	}
        @Override
          public void resize (int width, int height) { }

        public void pause () { }

        public void resume () { }

   public  Skin createBasicSkin(){
  //Create a font
  Skin buttonsSkin=new Skin();
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
  textButtonStyle.checked = buttonsSkin.newDrawable("background", Color.GRAY);
  textButtonStyle.over = buttonsSkin.newDrawable("background", Color.LIGHT_GRAY);
  textButtonStyle.font = buttonsSkin.getFont("default");
  buttonsSkin.add("default", textButtonStyle);
 return buttonsSkin;
    } 

}
