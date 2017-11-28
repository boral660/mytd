/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.bullets.Bullet;
import com.mygdx.game.enemies.Enemy;
import com.mygdx.game.enemies.Wave;
import com.mygdx.game.navigation.Direction;
import java.util.ArrayList;
import java.util.Iterator;

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
    private Stage stage;
private ScrollPane scrollPane;
       private List list ;

    
 private Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
    /**
     * Снаряды
     */
    private ArrayList<Bullet> _bullets = new ArrayList();

    public LevelScreen(TDGame aThis, Map aMap) {
        super();
        restart(aThis, aMap);

        stepHeight = (Gdx.graphics.getHeight() - map.height() * Cell.Size) / 2;
        if (stepHeight < 0) {
            stepHeight = 0;
        }
        stepWidth = (Gdx.graphics.getWidth() - map.width() * Cell.Size) / 2;
        if (stepWidth < 0) {
            stepWidth = 0;
        }

    }

    public void restart(TDGame aThis, Map aMap) {
        game = aThis;
        map = aMap;
        stage = new Stage();
        currentMoney = map.moneyOnStart();
        batch = new SpriteBatch();
        gameIm = new Texture("grass.jpg");
        roadIm = new Texture(Gdx.files.internal("road.png"));
        mainCon = new Texture(Gdx.files.internal("mainConstuct.png"));
        currentWave = map.waves().get(1);

    }

    @Override
    public void show() {

    }

    public void renderBullits() {

        for (Bullet bullet : _bullets) {

            bullet.move();
            if (bullet.texture() == null) {
                int i = 0;
            }
            batch.draw(
                    bullet.texture(),
                    stepWidth + bullet.x(),
                    stepHeight + bullet.y(),
                    Cell.Size / 4,
                    Cell.Size / 4);
        }
        Iterator<Bullet> iter = _bullets.iterator();
        while (iter.hasNext()) {

            Bullet bullet = iter.next();

            if (bullet.moveOff()) {
                map.main().DecriseIntegrity(bullet.damage());
                iter.remove();

            }
        }

    }

    public void renderEnemy() {
        for (Enemy enemy : currentWave.enemies()) {
            if (enemy.canAttack(map.main().position())) {
                Bullet temp = enemy.attack(map.main().position(), stepWidth + map.main().position().x() * Cell.Size + 32, stepHeight + map.main().position().y() * Cell.Size - 32);
                if (temp != null) {
                    _bullets.add(temp);
                }
            } else {
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
        batch.draw(gameIm, stepWidth, stepHeight, map.width() * Cell.Size, map.height() * Cell.Size);
        if (map.main().integrity() > 0) {

            MouseProcessor inputProcessor = new MouseProcessor();
            Gdx.input.setInputProcessor(inputProcessor);

            for (Cell cell : map.roadCell()) {
                batch.draw(roadIm, stepWidth + cell.x() * Cell.Size, stepHeight + cell.y() * Cell.Size, Cell.Size, Cell.Size);
            }
            batch.draw(mainCon, stepWidth + map.main().position().x() * Cell.Size - 16, stepHeight + map.main().position().y() * Cell.Size - 16, Cell.Size * 1.5f, Cell.Size * 1.5f);

            // Отрисовка башен
            renderTower();
            renderSquare();
            if (currentWave != null) {
                renderEnemy();
            }
            if (_bullets.size() != 0) {
                renderBullits();
            }

            //Прочность главного строения
            renderNode(map.main().integrity() + "/" + map.main().maxIntegrity(), stepWidth + map.main().position().x() * Cell.Size, stepHeight + map.main().position().y() * Cell.Size + Cell.Size / 4, 15);
            //Количество золота
            renderNode("Gold: " + currentMoney, Gdx.graphics.getWidth() * 13 / 16, Gdx.graphics.getHeight() - Cell.Size, 20);

        } else {
            createButtons();
        }
        batch.end();
        stage.act();
        stage.draw();

    }
    public void createList(float x, float y)
    {
      list = new List(skin);
	   
     	list.setItems(listEntries);
		list.getSelection().setMultiple(false);
		list.getSelection().setRequired(false);
		 list.getSelection().setToggle(true);
                 scrollPane = new ScrollPane(list, skin);
		scrollPane.setFlickScroll(false);
                    
                 scrollPane.setX(x);
                 scrollPane.setY(y);
        stage.addActor(scrollPane);
                 
    }
    Object[] listEntries = {"This is a list entry1", "And another one1", "The meaning of life1", "Is hard to come by1",
		"This is a list entry2", "And another one2", "The meaning of life2", "Is hard to come by2", "This is a list entry3",
		"And another one3", "The meaning of life3", "Is hard to come by3", "This is a list entry4", "And another one4",
		"The meaning of life4", "Is hard to come by4", "This is a list entry5", "And another one5", "The meaning of life5",
		"Is hard to come by5"};
    
    // Отрисовка надписей
    public void renderNode(String str, float x, float y, int size) {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("myfont.ttf"));
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size = size;
        BitmapFont node = generator.generateFont(parameter);
        node.draw(batch, str, x, y);
        generator.dispose();

    }

    public void createButtons() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Skin buttonsSkin = game.createBasicSkin();

        TextButton exit = new TextButton("Change Map", buttonsSkin); // Use the initialized skin
        exit.setBounds(0, 0, Gdx.graphics.getWidth() / 8, Gdx.graphics.getHeight() / 16);
        exit.setPosition(Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 16, stepHeight + Gdx.graphics.getHeight() / 2);
        exit.setTouchable(Touchable.enabled);
        stage.addActor(exit);

        exit.addListener(new ClickListener() {
            @Override
            public boolean touchDown(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(game.mapsScreen);
                return true;
            }
        });

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
                 if (button == Buttons.LEFT) {
                     if(  scrollPane!=null) scrollPane.remove();
                 createList(x,  Gdx.graphics.getHeight()-y);
                return true;     
                }
                 if (button == Buttons.RIGHT) {
                     scrollPane.remove();
                return true;     
                }
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
         
            return true;
        }

        @Override
        public boolean mouseMoved(int i, int i1) {
            currentCell = findCell(i, Gdx.graphics.getHeight() - i1);
            return true;
        }
    }
     public class MenuItem extends Label
{
    private boolean selected = false;
    private LabelStyle menuOption;
    public MenuItem(CharSequence text, LabelStyle menuOption)
    {
        super(text, menuOption);
        this.menuOption = menuOption;
        menuOption.fontColor = Color.WHITE;
    }
    public void select()
    {
        if(selected == true)
        {
            selected = false;
            menuOption.fontColor = Color.WHITE;
        }
        else
        {
            selected = true;
            menuOption.fontColor = Color.CYAN;
        }
    }
}
}


