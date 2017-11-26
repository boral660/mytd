/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.game.enemies.Enemy;
import com.mygdx.game.enemies.EnemyFactory;
import com.mygdx.game.navigation.Direction;
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
private Texture squareIm;
  Stage stage = new Stage();

Cell currentCell=new Cell(0,0);
private Texture  EnemyAtlas;
 

Enemy enemy;
Enemy enemy2;
 Enemy enemy3;
     
int stepHeight;
int stepWidth;
int currentMoney;
private Texture defense;

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
     
       enemy=  EnemyFactory.getEnemy("SkeletonWarrior", map.roadCell().get(0), map.roadCell());
       enemy2=  EnemyFactory.getEnemy("SkeletonMage", map.roadCell().get(0), map.roadCell());
       enemy3=  EnemyFactory.getEnemy("LizardArcher", map.roadCell().get(0), map.roadCell());
    }

    @Override
    public void show() {
        
    }
    public void renderEnemy()
    {
        enemy.move();
        float OriginX = Cell.Size/4;
        float OriginY = Cell.Size/4;
        if(enemy.direction().equals(Direction.north())){
               batch.draw(enemy.texture(),stepWidth + enemy.x(), stepHeight+enemy.y(), OriginX, OriginY, Cell.Size/2, Cell.Size/2, 1f, 1f, 180f);
        }
        else if(enemy.direction().equals(Direction.east())){
            batch.draw(enemy.texture(),stepWidth + enemy.x(), stepHeight+enemy.y(), OriginX, OriginY, Cell.Size/2, Cell.Size/2, 1f, 1f, 1f,true);
        }
        else if(enemy.direction().equals(Direction.west())){
            batch.draw(enemy.texture(),stepWidth + enemy.x(), stepHeight+enemy.y(), OriginX, OriginY, Cell.Size/2, Cell.Size/2, 1f, 1f, 1f,false);
           
                }
        else{
        
       batch.draw(enemy.texture(), stepWidth + enemy.x(), stepHeight+enemy.y(), Cell.Size/2, Cell.Size/2);

    }
    }
        

    @Override
    public void render(float f) {
          roadIm = new Texture(Gdx.files.internal("road.png"));
          mainCon=new Texture(Gdx.files.internal("mainConstuct.png"));
          Gdx.gl.glClearColor(0, 0, 0, 1);
                Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
       batch.begin();
       
           MouseProcessor inputProcessor = new MouseProcessor();
        Gdx.input.setInputProcessor(inputProcessor);

       batch.draw(gameIm, stepWidth, stepHeight, map.width()*Cell.Size, map.height()*Cell.Size);
        for (Cell cell:map.roadCell())
        {
            batch.draw(roadIm,  stepWidth+cell.width()*Cell.Size,stepHeight+cell.height()*Cell.Size, Cell.Size,Cell.Size);      
        }
        batch.draw(mainCon, stepWidth + map.main().position().width()*Cell.Size, stepHeight+map.main().position().height()*Cell.Size, Cell.Size, Cell.Size);
 
        //Количество золота
         FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("myfont.ttf"));
         FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size = 20;
        BitmapFont fontToGold = generator.generateFont(parameter); 
        generator.dispose(); 
        fontToGold.draw(batch,"Gold: "+ currentMoney,Gdx.graphics.getWidth()*65/80 ,Gdx.graphics.getHeight()-Cell.Size);

                // Отрисовка башен
        renderTower();
        renderSquare();
        renderEnemy();
        batch.end();
    }

    // Сделать так, что бы сначала отрисовывались нижние
    public void renderTower() {
         for (DefenseConstruction constuct:map.defenseConst())
        {
            if(constuct instanceof ArcherTower)
            {
                 defense=new Texture(Gdx.files.internal("ArcherTower.png"));
            }
            else if(constuct instanceof IceTower)
            {
                 defense=new Texture(Gdx.files.internal("IceTower.png"));
            } else if(constuct instanceof LightTower)
            {
                 defense=new Texture(Gdx.files.internal("LightTower.png"));
            }
            batch.draw(defense,stepWidth + constuct.position().width()*Cell.Size, stepHeight+constuct.position().height()*Cell.Size,Cell.Size,Cell.Size );       
        }     
    }
        // Выделить квадраты
        public void renderSquare() {
            if(map.CheckCell(currentCell))
                    squareIm=new Texture(Gdx.files.internal("Red.png"));
            else
                   squareIm=new Texture(Gdx.files.internal("Yellow.png"));  
                batch.draw(squareIm,stepWidth + currentCell.width()*Cell.Size, stepHeight+currentCell.height()*Cell.Size,Cell.Size,Cell.Size );    
      
        }
        // Найти позицию
        public Cell findCell(int x, int y)
        {
            return new Cell((x-stepWidth)/Cell.Size,(y-stepHeight)/Cell.Size);
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
             defense.dispose();
    }
    
    public class MouseProcessor implements InputProcessor {
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
        return false;
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
         
            currentCell=findCell(i,Gdx.graphics.getHeight()-i1);
            return true;
        }
    }
    
}
