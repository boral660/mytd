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
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import javafx.scene.paint.Color;


/**
 *
 * @author PK
 */
class LevelScreen implements Screen{
 
TDGame  game;
Map map;
OrthographicCamera camera;
private SpriteBatch batch;
private Texture gameIm;
private Texture mainCon;
private Texture roadIm;
int stepHeight;
int stepWidth;
 int currentMoney;

    public LevelScreen(TDGame aThis, Map aMap) {
       super();
        game=aThis;
        map=aMap;
        currentMoney=map.moneyOnStart();
        batch = new SpriteBatch();
        gameIm = new Texture("grass.jpg");
        stepHeight=(Gdx.graphics.getHeight()-map.height()*Cell.Size)/2;
        if(stepHeight<0)
            stepHeight=0;
        stepWidth=(Gdx.graphics.getWidth()-map.width()*Cell.Size)/2;
          if(stepWidth<0)
            stepWidth=0;
    }
    

    @Override
    public void show() {
        
    }

    @Override
    public void render(float f) {
         Stage stage = new Stage();
         Gdx.input.setInputProcessor(stage);// Make the stage consume event
         
          roadIm = new Texture(Gdx.files.internal("road.png"));
          mainCon=new Texture(Gdx.files.internal("mainConstuct.png"));
          Gdx.gl.glClearColor(0, 0, 0.2f, 1);
                Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
       batch.begin();
       
       batch.draw(gameIm, stepWidth, stepHeight, map.width()*Cell.Size, map.height()*Cell.Size);
        for (Cell cell:map._roadCell)
        {
            batch.draw(roadIm,  stepWidth+cell.width()*Cell.Size,stepHeight+cell.height()*Cell.Size, Cell.Size,Cell.Size);      
        }
        batch.draw(mainCon, stepWidth + map.main().position().width()*Cell.Size, stepHeight+map.main().position().height()*Cell.Size, Cell.Size, Cell.Size+15);

       
         FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("myfont.ttf"));
         FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size = 20;
        BitmapFont fontToGold = generator.generateFont(parameter); 
        generator.dispose(); 
        
        fontToGold.draw(batch,"Gold: "+ map.moneyOnStart(),Gdx.graphics.getWidth()*65/80 ,Gdx.graphics.getHeight()-Cell.Size);
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
        roadIm.dispose();
    }
    
}
