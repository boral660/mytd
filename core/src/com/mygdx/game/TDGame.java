package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.List;
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
          MainConstruction main=new MainConstruction(new Cell(1,1),250);
            // Создать первую карту
          Map TestMap=new Map("Test1", 100,16,8,main);
          TestMap._roadCell.add(0,new Cell(0,0));
          TestMap._roadCell.add(1,new Cell(15,0));
 
 
          mapsList.add(TestMap);
           TestMap=new Map("Test2", 50,16,16,main);
          mapsList.add(TestMap);
           TestMap=new Map("Test3", 600,16,16,main);
          mapsList.add(TestMap);
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



}
