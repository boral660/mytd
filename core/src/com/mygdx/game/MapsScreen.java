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
class MapsScreen implements Screen {

    TDGame game;
    OrthographicCamera camera;
    private SpriteBatch batch;
    private Texture gameIm;
    Skin buttonsSkin = new Skin();
    Stage stage = new Stage();
    Map currentMap=null;

    public MapsScreen(TDGame aThis) {
        super();
        game = aThis;
        batch = new SpriteBatch();
        gameIm = new Texture("Intro.jpg");

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float f) {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);// Make the stage consume event
        createBasicSkin();
        if(currentMap==null)
            currentMap=game.mapsList.get(0);
        
        TextButton MapName = new TextButton(currentMap.name(), buttonsSkin); // Use the initialized skin
        MapName.setPosition(Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 8, Gdx.graphics.getHeight() / 2);
        MapName.setTouchable(Touchable.enabled);
        stage.addActor(MapName);

        MapName.addListener(new ClickListener() {
            @Override
            public boolean touchDown(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(game.mapsScreen);
                return true;
            }
        });

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
        Back.setBounds(0, 0, Gdx.graphics.getWidth() /8, Gdx.graphics.getHeight() / 16);
        Back.setPosition(Gdx.graphics.getWidth() / 32,Gdx.graphics.getHeight()*4/5);
        Back.setTouchable(Touchable.enabled);
        stage.addActor(Back);
        
        Back.addListener(new ClickListener() {
            @Override
            public boolean touchDown(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(game.introScreen);
                return true;
            }
        });
        
        PrevMap.addListener(new ClickListener() {
            @Override
            public boolean touchDown(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y, int pointer, int button) {
                if(changeMapsOnList(currentMap.name(),1)==2)
                {
                    currentMap=game.mapsList.get(game.mapsList.size()-1);
                }
                return true;
            }
        });

        NextMap.addListener(new ClickListener() {
            @Override
            public boolean touchDown(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y, int pointer, int button) {
                if(changeMapsOnList(currentMap.name(),0)==2)
                {
                    currentMap=game.mapsList.get(0);
                }
                return true;
            }
        });
        Gdx.input.setInputProcessor(stage);// Make the stage consume event

        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(gameIm, 0, 0);
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

    private void createBasicSkin() {
        //Create a font
        BitmapFont font = new BitmapFont();
        buttonsSkin.add("default", font);

        //Create a texture
        Pixmap pixmap = new Pixmap((int) Gdx.graphics.getWidth() / 4, (int) Gdx.graphics.getHeight() / 12, Pixmap.Format.RGB888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        buttonsSkin.add("background", new Texture(pixmap));

        //Create a button style
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = buttonsSkin.newDrawable("background", Color.LIGHT_GRAY);
        textButtonStyle.down = buttonsSkin.newDrawable("background", Color.DARK_GRAY);
        textButtonStyle.checked = buttonsSkin.newDrawable("background", Color.DARK_GRAY);
        textButtonStyle.over = buttonsSkin.newDrawable("background", Color.LIGHT_GRAY);
        textButtonStyle.font = buttonsSkin.getFont("default");
        buttonsSkin.add("default", textButtonStyle);

    }

    // 0 - previous, 1 - next
    int changeMapsOnList(String name, int direction) {
        int index = -1;
        int find = -1;
        for (Map map : game.mapsList) {
            index++;
            String mapsName = map.name();
            if (name.equals(mapsName)) {
                find = index;
            }
        }
        if (find != -1) {
            index = -1;
            for (Map map : game.mapsList) {
                index++;
                if (direction == 0) {
                    if (find == index - 1) {
                        currentMap = map;
                        return 0;
                    }
                } else {
                    if (find == index + 1) {
                        currentMap = map;
                        return 1;
                    }
                }

            }
            return 2;
        }
        return 3;
    }
}
