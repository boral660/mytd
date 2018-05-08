/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.screen;

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
import com.mygdx.game.TDGame;
import com.mygdx.game.extentions.ModuleEngine;
import com.mygdx.game.mapAndOther.Map;
import java.util.ArrayList;
import com.mygdx.game.mapAndOther.SkinForButton;
import java.io.File;
import javax.swing.JFileChooser;

/**
 * Экран выбора карты
 *
 * @author PK
 */
public class MapsScreen implements Screen {

    /**
     * Ссылка на игру
     */
    private TDGame game;
    public TDGame getGame ()
            {
               return game;
            }
    /**
     * Ссылка на сущность для отрисовки
     */
    private SpriteBatch batch;
    /**
     * Текстура фона
     */
    private Texture gameIm;
    
   public String filePath="";
    /**
     * Скин кнопок
     */
    private Skin buttonsSkin = new Skin();
    /**
     * Сцена
     */
    private Stage stage = new Stage();

    /**
     * Текущая карта
     */
    private Map currentMap = null;
    public Map getMap ()
            {
               return currentMap;
      }
    /**
     * Список карт
     */
    private ArrayList<Map> mapsList = new ArrayList<Map>();

    public MapsScreen(TDGame aThis) {
        super();
        game = aThis;
        batch = new SpriteBatch();
        gameIm = new Texture("Intro.jpg");
        restart();
        currentMap = mapsList.get(0);

    }

    /**
     * Функция для генерации карт
     */
    public void restart() {
        mapsList.clear();
        mapsList.add(Map.GenerateMap1());
        mapsList.add(Map.GenerateMap2());
        mapsList.add(Map.GenerateMap3());
        mapsList.add(Map.GenerateMap4());
        currentMap = mapsList.get(0);
        createButtons();
    }

    /**
     * Функция создающая кнопки выбора карты
     */
    private void createMapNameButt() {
        TextButton MapName = new TextButton(currentMap.name(), buttonsSkin); // Use the initialized skin
        MapName.setPosition(Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 8, Gdx.graphics.getHeight() / 2);
        MapName.setTouchable(Touchable.enabled);
        stage.addActor(MapName);

        MapName.addListener(new ClickListener() {
            @Override
            public boolean touchDown(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new LevelScreen(game, currentMap, false));
                return true;
            }
        });

    }

    /**
     * Функция создающая кнопки на экране
     */
    private void createButtons() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);// Make the stage consume event
        buttonsSkin = SkinForButton.createBasicSkin();
        createMapNameButt();

        TextButton PrevMap = new TextButton("<-", buttonsSkin); // Use the initialized skin
        PrevMap.setBounds(0, 0, Gdx.graphics.getWidth() / 16, Gdx.graphics.getHeight() / 16);
        PrevMap.setPosition(Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 2 - Gdx.graphics.getHeight() / 8);
        PrevMap.setTouchable(Touchable.enabled);
        stage.addActor(PrevMap);

        TextButton NextMap = new TextButton("->", buttonsSkin); // Use the initialized skin
        NextMap.setBounds(0, 0, Gdx.graphics.getWidth() / 16, Gdx.graphics.getHeight() / 16);
        NextMap.setPosition(Gdx.graphics.getWidth() / 2 + Gdx.graphics.getWidth() / 4 - Gdx.graphics.getWidth() / 16, Gdx.graphics.getHeight() / 2 - Gdx.graphics.getHeight() / 8);
        NextMap.setTouchable(Touchable.enabled);
        stage.addActor(NextMap);

        TextButton Back = new TextButton("Back", buttonsSkin); // Use the initialized skin
        Back.setBounds(0, 0, Gdx.graphics.getWidth() / 8, Gdx.graphics.getHeight() / 16);
        Back.setPosition(Gdx.graphics.getWidth() / 32, Gdx.graphics.getHeight() * 4 / 5);
        Back.setTouchable(Touchable.enabled);
        stage.addActor(Back);
        
        TextButton StartBot = new TextButton("StartBot", buttonsSkin); // Use the initialized skin
        StartBot.setBounds(0, 0, Gdx.graphics.getWidth() / 8, Gdx.graphics.getHeight() / 16);
        StartBot.setPosition(Gdx.graphics.getWidth() / 2  + Gdx.graphics.getWidth() / 4 + Gdx.graphics.getWidth() / 16 + Gdx.graphics.getWidth() / 32, Gdx.graphics.getHeight() * 4 / 5);
        StartBot.setTouchable(Touchable.enabled);
        stage.addActor(StartBot);

        Back.addListener(new ClickListener() {
            @Override
            public boolean touchDown(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(game.introScreen);
                return true;
            }
        });
        
        StartBot.addListener(new ClickListener() {
            @Override
           public boolean touchDown(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y, int pointer, int button) {

               JFileChooser fileopen = new JFileChooser();    
                fileopen.setCurrentDirectory(new File("..\\build\\classes\\main\\com\\mygdx\\game\\extentions\\modules"));
               int ret = fileopen.showDialog(null, "Открыть файл");
               filePath="";
               if(ret == JFileChooser.APPROVE_OPTION)
                {
                    filePath =  fileopen.getSelectedFile().getPath();
                      System.out.println(filePath);
                }
               
               Gdx.app.postRunnable(new Runnable(){
                   @Override
                   public void run() {
                       if(filePath != ""){
                            String[] arr = new String[1];
                           arr[0] = filePath;
                            LevelScreen ls = new LevelScreen(game, game.mapsScreen.getMap(), true);
                            ModuleEngine.main(arr,ls);
                            game.setScreen(ls);
                       }    
                   }
                   
               });
                return true;
            }
        });


        PrevMap.addListener(new ClickListener() {
            @Override
            public boolean touchDown(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y, int pointer, int button) {
                if (changeMapsOnList(currentMap.name(), 1) == 2) {
                    currentMap = mapsList.get(mapsList.size() - 1);
                }
                createMapNameButt();
                return true;
            }
        });

        NextMap.addListener(new ClickListener() {
            @Override
            public boolean touchDown(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y, int pointer, int button) {
                if (changeMapsOnList(currentMap.name(), 0) == 2) {
                    currentMap = mapsList.get(0);

                }
                createMapNameButt();
                return true;
            }
        });

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);

    }

    @Override
    public void render(float f) {

        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(gameIm, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();
        stage.act();
        stage.draw();
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

    /**
     * Функция изменяющая текущие карты
     *
     * @param name название карты
     * @param direction направление переключения
     */
    int changeMapsOnList(String name, int direction) {
        int index = -1;
        int find = -1;
        for (Map map : mapsList) {
            index++;
            String mapsName = map.name();
            if (name.equals(mapsName)) {
                find = index;
            }
        }
        if (find != -1) {
            index = -1;
            for (Map map : mapsList) {
                index++;
                if (direction == 0) {
                    if (find == index - 1) {
                        currentMap = map;
                        return 0;
                    }
                } else if (find == index + 1) {
                    currentMap = map;
                    return 1;
                }

            }
            return 2;
        }
        return 3;
    }
}
