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
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.mygdx.game.enemies.Enemy;
import com.mygdx.game.enemies.Wave;
import com.mygdx.game.navigation.Direction;

/**
 *
 * @author PK
 */
class LevelScreen implements Screen {

    TDGame game;
    Map map;
    OrthographicCamera camera;
    private SpriteBatch batch;
    private Texture gameIm;
    private Texture mainCon;
    private Texture roadIm;
    private Texture squareIm;
    private Texture defense;

    private Cell currentCell = new Cell(0, 0);
    private Wave currentWave;

    private int stepHeight;
    private int stepWidth;
    private int currentMoney;

    public LevelScreen(TDGame aThis, Map aMap) {
        super();
        game = aThis;
        map = aMap;

        currentMoney = map.moneyOnStart();
        batch = new SpriteBatch();
        gameIm = new Texture("grass.jpg");
        roadIm = new Texture(Gdx.files.internal("road.png"));
        mainCon = new Texture(Gdx.files.internal("mainConstuct.png"));

        stepHeight = (Gdx.graphics.getHeight() - map.height() * Cell.Size) / 2;
        if (stepHeight < 0) {
            stepHeight = 0;
        }
        stepWidth = (Gdx.graphics.getWidth() - map.width() * Cell.Size) / 2;
        if (stepWidth < 0) {
            stepWidth = 0;
        }

        currentWave = map.waves().get(1);

    }

    @Override
    public void show() {

    }

    public void renderEnemy() {
        for (Enemy enemy : currentWave.enemies()) {
            if(enemy.canAttack(map.main().position())){
                   enemy.attack(map.main().position());
            }
            else
            {
                 enemy.move();
            }
            if (enemy.direction().equals(Direction.north())) {
                batch.draw(enemy.texture(), stepWidth + enemy.x(), stepHeight + enemy.y(), Cell.Size / 4, Cell.Size / 4, Cell.Size / 2, Cell.Size / 2, 1f, 1f, 180f);
            } else if (enemy.direction().equals(Direction.east())) {
                batch.draw(enemy.texture(), stepWidth + enemy.x(), stepHeight + enemy.y(), Cell.Size / 4, Cell.Size / 4, Cell.Size / 2, Cell.Size / 2, 1f, 1f, 1f, true);
            } else if (enemy.direction().equals(Direction.west())) {
                batch.draw(enemy.texture(), stepWidth + enemy.x(), stepHeight + enemy.y(), Cell.Size / 4, Cell.Size / 4, Cell.Size / 2, Cell.Size / 2, 1f, 1f, 1f, false);

            } else {
                batch.draw(enemy.texture(), stepWidth + enemy.x(), stepHeight + enemy.y(), Cell.Size / 4, Cell.Size / 4);
            }
     
      }
        

    }

    @Override
    public void render(float f) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();

        MouseProcessor inputProcessor = new MouseProcessor();
        Gdx.input.setInputProcessor(inputProcessor);

        batch.draw(gameIm, stepWidth, stepHeight, map.width() * Cell.Size, map.height() * Cell.Size);
        for (Cell cell : map.roadCell()) {
            batch.draw(roadIm, stepWidth + cell.x() * Cell.Size, stepHeight + cell.y() * Cell.Size, Cell.Size, Cell.Size);
        }
        batch.draw(mainCon, stepWidth + map.main().position().x() * Cell.Size-16, stepHeight + map.main().position().y() * Cell.Size-16 , Cell.Size*1.5f, Cell.Size *1.5f);

        // Отрисовка башен
        renderTower();
        renderSquare();
        if (currentWave != null) {
            renderEnemy();
        }
        //Прочность главного строения
        renderNode(map.main().integrity() + "/" + map.main().maxIntegrity(), stepWidth + map.main().position().x() * Cell.Size, stepHeight + map.main().position().y() * Cell.Size + Cell.Size / 4, 12);
        //Количество золота
        renderNode("Gold: " + currentMoney, Gdx.graphics.getWidth() * 13 / 16, Gdx.graphics.getHeight() - Cell.Size, 20);
        batch.end();
    }

    // Отрисовка надписей
    public void renderNode(String str, float x, float y, int size) {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("myfont.ttf"));
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size = size;
        BitmapFont node = generator.generateFont(parameter);
        node.draw(batch, str, x, y);
        generator.dispose();

    }

    // Сделать так, что бы сначала отрисовывались нижние
    public void renderTower() {
        for (DefenseConstruction constuct : map.defenseConst()) {
            if (constuct instanceof ArcherTower) {
                defense = new Texture(Gdx.files.internal("ArcherTower.png"));
            } else if (constuct instanceof IceTower) {
                defense = new Texture(Gdx.files.internal("IceTower.png"));
            } else if (constuct instanceof LightTower) {
                defense = new Texture(Gdx.files.internal("LightTower.png"));
            }
            batch.draw(defense, stepWidth + constuct.position().x() * Cell.Size, stepHeight + constuct.position().y() * Cell.Size, Cell.Size, Cell.Size);
        }
    }
    // Выделить квадраты

    public void renderSquare() {
        if (map.CheckCell(currentCell)) {
            squareIm = new Texture(Gdx.files.internal("Red.png"));
        } else {
            squareIm = new Texture(Gdx.files.internal("Yellow.png"));
        }
        batch.draw(squareIm, stepWidth + currentCell.x() * Cell.Size, stepHeight + currentCell.y() * Cell.Size, Cell.Size, Cell.Size);

    }
    // Найти позицию

    public Cell findCell(int x, int y) {
        return new Cell((x - stepWidth) / Cell.Size, (y - stepHeight) / Cell.Size);
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
        squareIm.dispose();

    }

    public class MouseProcessor implements InputProcessor {

        @Override
        public boolean keyDown(int keycode) {
            return false;
        }

        @Override
        public boolean keyUp(int keycode) {
            return false;
        }

        @Override
        public boolean keyTyped(char character) {
            return false;
        }

        @Override
        public boolean touchDown(int x, int y, int pointer, int button) {
            return false;
        }

        @Override
        public boolean touchUp(int x, int y, int pointer, int button) {
            return false;
        }

        @Override
        public boolean touchDragged(int x, int y, int pointer) {
            return false;
        }

        @Override
        public boolean scrolled(int amount) {
            return false;
        }

        @Override
        public boolean mouseMoved(int i, int i1) {

            currentCell = findCell(i, Gdx.graphics.getHeight() - i1);
            return true;
        }
    }

}
