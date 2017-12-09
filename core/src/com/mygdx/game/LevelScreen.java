/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.InputMultiplexer;
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
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.bullets.Bullet;
import com.mygdx.game.defenseConstucts.DCFactory;
import com.mygdx.game.enemies.Enemy;
import com.mygdx.game.enemies.Wave;
import com.mygdx.game.navigation.Direction;
import java.util.ArrayList;
import java.util.Iterator;
import com.mygdx.game.navigation.Cell;
import com.mygdx.game.defenseConstucts.DefenseConstruction;


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
    private Texture Panel;
    private Cell currentCell = new Cell(0, 0);
    private Wave currentWave;
    private int numberWave;
    private int currentMoney;
    private Stage stage;
    private ScrollPane scrollPane;
    private boolean Win = false;
    private List list;
    private FreeTypeFontGenerator generator;
    private FreeTypeFontParameter parameter;
    BitmapFont node15=null;
    BitmapFont node20=null;
    TextButton start;
       InputMultiplexer multiplexer = new InputMultiplexer();
        MouseProcessor inputProcessor = new MouseProcessor();
       
    private Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
    /**
     * Снаряды
     */
    private ArrayList<Bullet> _bullets = new ArrayList();

    public LevelScreen(TDGame aThis, Map aMap) {
        super();
        restart(aThis, aMap);

    }

    public void restart(TDGame aThis, Map aMap) {
        game = aThis;
        map = aMap;
        stage = new Stage();
        currentMoney = map.moneyOnStart();
        batch = new SpriteBatch();
        gameIm = new Texture(Gdx.files.internal("grass.jpg"));
        roadIm = new Texture(Gdx.files.internal("road.png"));
        mainCon = new Texture(Gdx.files.internal("mainConstuct.png"));
        Panel = new Texture(Gdx.files.internal("Panel.png"));
        currentWave = null;
        numberWave = 0;


        Gdx.input.setInputProcessor(multiplexer);
        multiplexer.addProcessor(inputProcessor);
        multiplexer.addProcessor(stage);
        createStartButton();
      
        
        generator = new FreeTypeFontGenerator(Gdx.files.internal("myfont.ttf"));
        parameter = new FreeTypeFontParameter();
            parameter.size = 15;
            node15 = generator.generateFont(parameter);
               parameter.size = 20;
            node20 = generator.generateFont(parameter);
        
    }

    // Отрисовка надписей
    public void renderNode(String str, float x, float y,int size) {
        if(size==15)
             node15.draw(batch, str, x, y);
        if(size==20)
        node20.draw(batch, str, x, y);

    }

   

    public void renderBullits() {

        for (Bullet bullet : _bullets) {
            bullet.move();
            batch.draw(bullet.texture(), bullet.x(), bullet.y(), Cell.Size / 4, Cell.Size / 4);
        }
        Iterator<Bullet> iter = _bullets.iterator();

        while (iter.hasNext()) {

            Bullet bullet = iter.next();

            if (bullet.moveOff()) {
                if (bullet.toEnemy()) {
                    bullet.target().reduseHP(bullet.damage());
                } else {
                    map.main().DecriseIntegrity(bullet.damage());
                }
                iter.remove();
            }
        }

    }

    public void renderEnemy() {
        for (Enemy enemy : currentWave.enemies()) {
            if (enemy.canAttack(map.main().position())) {
                Bullet temp = enemy.attack(map.main().position(), map.main().position().x() * Cell.Size + 32, map.main().position().y() * Cell.Size - 32);
                if (temp != null) {
                    _bullets.add(temp);
                }
            } else {
                enemy.move();
            }
            if (enemy.direction().equals(Direction.north())) {
                batch.draw(enemy.texture(), enemy.x(), enemy.y(), Cell.Size / 4, Cell.Size / 4, Cell.Size / 2, Cell.Size / 2, 1f, 1f, 180f);
            } else if (enemy.direction().equals(Direction.east())) {
                batch.draw(enemy.texture(), enemy.x(), enemy.y(), Cell.Size / 4, Cell.Size / 4, Cell.Size / 2, Cell.Size / 2, 1f, 1f, 1f, true);
            } else if (enemy.direction().equals(Direction.west())) {
                batch.draw(enemy.texture(), enemy.x(), enemy.y(), Cell.Size / 4, Cell.Size / 4, Cell.Size / 2, Cell.Size / 2, 1f, 1f, 1f, false);

            } else {
                batch.draw(enemy.texture(), enemy.x(), enemy.y(), Cell.Size / 4, Cell.Size / 4);
            }

        }
        Iterator<Enemy> iter = currentWave.enemies().iterator();
        while (iter.hasNext()) {

            Enemy enemy = iter.next();

            if (enemy.healPoints() == 0) {
                currentMoney += enemy.moneyForKill();
                iter.remove();
            }
        }

    }

    @Override
    public void render(float f) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(gameIm, 0, 0, map.width() * Cell.Size, map.height() * Cell.Size);
        batch.draw(Panel, 0, 512);

        if (map.main().integrity() <= 0) {
            renderNode("You lose the game!:c" , Gdx.graphics.getWidth() * 7 / 16, Gdx.graphics.getHeight()-20, 20);
            currentWave = null;
        }

        if (currentWave != null && currentWave.enemies().isEmpty()) {
            endWave();
        }

        for (Cell cell : map.roadCell()) {
            batch.draw(roadIm, cell.x() * Cell.Size, cell.y() * Cell.Size, Cell.Size, Cell.Size);
        }
        batch.draw(mainCon, map.main().position().x() * Cell.Size - 4, map.main().position().y() * Cell.Size - 4, Cell.Size * 1.125f, Cell.Size * 1.125f);

        // Отрисовка башен
        renderTower();
        renderSquare();
        if (currentWave != null) {
            renderEnemy();
            //Количество врагов
             renderNode("Enemies: " + currentWave.enemies().size(), Gdx.graphics.getWidth() * 7 / 16, Gdx.graphics.getHeight()-20, 20);

        }

        if (_bullets.size() != 0) {
            renderBullits();
        }

        //Прочность главного строения
        renderNode(map.main().integrity() + "/" + map.main().maxIntegrity(), map.main().position().x() * Cell.Size, map.main().position().y() * Cell.Size + Cell.Size / 4, 15);
        //Количество золота
        renderNode("Gold: " + currentMoney, Gdx.graphics.getWidth() * 13 / 16, Gdx.graphics.getHeight()-20, 20);
        //Количество волн
         renderNode("Wave " + (numberWave+1) + "/" + map.waves().size(), Gdx.graphics.getWidth() * 1 / 16, Gdx.graphics.getHeight()-20, 20);

        if (Win && map.main().integrity() > 0)
        renderNode("You win the game!" , Gdx.graphics.getWidth() * 7 / 16, Gdx.graphics.getHeight()-20, 20);
       
        
       
        batch.end();
        stage.act();
        stage.draw();
        System.gc();

    }
 @Override
    public void show() {   
       
    }
   
    public void createList(float x, float y) {
        if(Gdx.graphics.getHeight()-y<150)
            y-=150;
        if(Gdx.graphics.getWidth()-x<200)
            x-=200;
        
        list = new List(skin);
        if(map.CheckCell(currentCell))
        { 
             list.setItems(ListActions);
        }
            else
        {
        if(map.CheckRoad(currentCell))
        {
            list.setItems(ListTraps);
        }
        else
        {
            list.setItems(ListTowers);
        }
        }

        list.getSelection().setMultiple(false);
        list.getSelection().setRequired(false);
        list.getSelection().setToggle(true);
        scrollPane = new ScrollPane(list, skin);
        scrollPane.setFlickScroll(false);
        scrollPane.setHeight(150);
        scrollPane.setWidth(200);
        scrollPane.setX(x);
        scrollPane.setY(y);
        stage.addActor(scrollPane);
    scrollPane.addListener(new InputListener() {
        public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
               if (button == Buttons.RIGHT) {
                    if (scrollPane != null) {
                    scrollPane.remove();
                            Gdx.input.setInputProcessor(multiplexer);
                    return true;
                    }
                }
                return false;
        }
        });
    

    scrollPane.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent ce, Actor actor) {
             Object obj=list.getSelected();
             if(obj==null)
                 obj=list.getItems().first();
              if(obj.toString().compareTo("Destroy tower")==0)
            {
                       destroyTower();
            }
             else if(obj.toString().compareTo("IceTower")==0 )
             {
                 buyTower(DCFactory.getTower("IceTower",currentCell ));
             }
                else if(obj.toString().compareTo("ArcherTower")==0 )
             {
                 buyTower(DCFactory.getTower("ArcherTower",currentCell ));
             }
                else if(obj.toString().compareTo("LightTower")==0 )
             {
                 buyTower(DCFactory.getTower("LightTower",currentCell ));
             }
                else if(obj.toString().compareTo("Wire")==0 )
             {
                 buyTower(DCFactory.getTower("Wire",currentCell ));
             }
                else if(obj.toString().compareTo("ElectricBomb")==0 )
             {
                 buyTower(DCFactory.getTower("ElectricBomb",currentCell ));
             }
                 else if(obj.toString().compareTo("Spike")==0 )
             {
                 buyTower(DCFactory.getTower("Spike",currentCell ));
             }
             
                
            }
    });

    }
    Object[] ListTowers = {DCFactory.getTower("ArcherTower",currentCell ), DCFactory.getTower("IceTower",currentCell ), DCFactory.getTower("LightTower",currentCell )};
    Object[] ListTraps = {DCFactory.getTower("Wire",currentCell ),DCFactory.getTower("ElectricBomb",currentCell ),DCFactory.getTower("Spike",currentCell ),};
    Object[] ListActions = {"Destroy construction" };
     //купить башню
    public void buyTower(DefenseConstruction dc) {
        if(canBuyTower(dc)){
            map.defenseConst().add(dc);
            currentMoney-=dc.price();
           scrollPane.remove();
           Gdx.input.setInputProcessor(multiplexer);
        }
    }
      //продать башню
    public void destroyTower() {
         Iterator<DefenseConstruction> iter =  map.defenseConst().iterator();
        while (iter.hasNext()) {

            DefenseConstruction dc = iter.next();

              if (dc.position().equals(currentCell)) {
                iter.remove();
            }
        }
           scrollPane.remove();
           Gdx.input.setInputProcessor(multiplexer);
    }
     //проверить, возможно ли купить сооружение
    public boolean canBuyTower(DefenseConstruction dc) {
        return (currentMoney-dc.price())>0;
        
    }

    // Конец волны
    public void endWave() {
        currentWave = null;
        if (numberWave + 1 != map.waves().size()) {
            numberWave++;


        } else {
            Win = true;
        }
        
    }


    // Создание кнопки для переключения волны 
    public void createStartButton() {
        Skin buttonsSkin = game.createBasicSkin();
        start = new TextButton("Start wave", buttonsSkin); // Use the initialized skin
        start.setBounds(0, 0, 150, 30);
        start.setPosition(Gdx.graphics.getWidth() * 3 / 16, Gdx.graphics.getHeight() - 40);
        start.setTouchable(Touchable.enabled);
      
        stage.addActor(start);

        start.addListener(new ClickListener() {
            @Override
            public boolean touchDown(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y, int pointer, int button) {
                currentWave = map.waves().get(numberWave);
                
                return true;
            }
        });
    }

    public void createButtons() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Skin buttonsSkin = game.createBasicSkin();

        TextButton exit = new TextButton("Change Map", buttonsSkin); // Use the initialized skin
        exit.setBounds(0, 0, Gdx.graphics.getWidth() / 8, Gdx.graphics.getHeight() / 16);
        exit.setPosition(Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 16, Gdx.graphics.getHeight() / 2);
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
        for (DefenseConstruction dc : map.defenseConst()) {
            if (currentWave != null) {
                for (Enemy enemy : currentWave.enemies()) {
                    if (dc.canAttack(enemy)) {
                        Bullet temp = dc.attack(enemy);
                        if (temp != null) {
                            _bullets.add(temp);
                        }
                    }
                }
            }
            batch.draw(dc.texture(), dc.position().x() * Cell.Size, dc.position().y() * Cell.Size, Cell.Size, Cell.Size);
        }
         Iterator<DefenseConstruction> iter =  map.defenseConst().iterator();
        while (iter.hasNext()) {

            DefenseConstruction td = iter.next();

            if (td.IsDestroy()) {
                iter.remove();
            }
        }

    }
    

    // Выделить квадраты
    public void renderSquare() {
        if (Gdx.graphics.getHeight() - currentCell.y() * Cell.Size > 50) {
            if (map.CheckCell(currentCell)) {
                squareIm = new Texture(Gdx.files.internal("Red.png"));
            } else {
                squareIm = new Texture(Gdx.files.internal("Yellow.png"));
            }
            batch.draw(squareIm, currentCell.x() * Cell.Size, currentCell.y() * Cell.Size, Cell.Size, Cell.Size);
        }
    }
    // Найти позицию

    public Cell findCell(int x, int y) {
        return new Cell(x / Cell.Size, y / Cell.Size);
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
            if (y > 50) {
                if (button == Buttons.LEFT) {
                    if (scrollPane != null) {
                        scrollPane.remove();
                    }
                    if(!map.main().position().equals(currentCell))
                    {
                        createList(x, Gdx.graphics.getHeight() - y);
                          Gdx.input.setInputProcessor(stage);
                    }
                    return true;
                }
                if (button == Buttons.RIGHT) {
                    if (scrollPane != null) {
                    scrollPane.remove();
                            Gdx.input.setInputProcessor(multiplexer);
                    return true;
                    }
                }
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

    public class MenuItem extends Label {

        private boolean selected = false;
        private LabelStyle menuOption;

        public MenuItem(CharSequence text, LabelStyle menuOption) {
            super(text, menuOption);
            this.menuOption = menuOption;
            menuOption.fontColor = Color.WHITE;
        }

        public void select() {
            if (selected == true) {
                selected = false;
                menuOption.fontColor = Color.WHITE;
            } else {
                selected = true;
                menuOption.fontColor = Color.CYAN;
            }
        }
    }
}
