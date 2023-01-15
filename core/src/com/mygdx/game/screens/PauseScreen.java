package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.mygdx.game.TankStars;
import com.mygdx.game.entities.Tank;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class PauseScreen implements Screen {
    private final TankStars game;
    protected final Stage stage;
    protected Skin mySkin;
    private int row_height = Gdx.graphics.getWidth() / 12;
    private int col_width = Gdx.graphics.getWidth() / 12;
    private Button saveButton, resumeButton, mainMenuButton;
    private Texture pmenu;
    private OrthographicCamera camera;
    private Tank t1, t2;

    public PauseScreen(final TankStars game, final Tank t1, final Tank t2) {
        this.game = game;
        this.t1 = t1;
        this.t2 = t2;
        mySkin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
        pmenu = new Texture("backgrounds/pmenu.png");
        camera = new OrthographicCamera();
        camera.setToOrtho(false, TankStars.WIDTH, TankStars.HEIGHT);
        // create stage and set it as input processor
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        // making buttons
        saveButton = new TextButton("SAVE",mySkin,"small");
        saveButton.setSize(col_width*2,row_height);
        saveButton.setPosition(Gdx.graphics.getWidth()/2 - saveButton.getWidth()/2 ,Gdx.graphics.getHeight()/2 - saveButton.getHeight()/2 + 170);
        saveButton.addListener(new ChangeListener() { // stuff to change when button is pressed
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                // write important info to file
//                FileWriter fw;
//                try {
//                    fw = new FileWriter("saved.txt",true);
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//                PrintWriter out = new PrintWriter(fw);
                PrintWriter out;
                try {
                    out = new PrintWriter("saved.txt");
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
//                out.println(t1.getHealth() +  " "+ t1.getX() + " "+ t1.getY() + " " + t2.getHealth() + " " + t2.getX() + " " + t2.getY());
                out.println("hi");
                // Close the file.
                out.close();
//                game.setScreen(new MainMenuScreen(game));
//                dispose();
            }
        });
        stage.addActor(saveButton);
        resumeButton = new TextButton("RESUME",mySkin,"small");
        resumeButton.setSize(col_width*2,row_height);
        resumeButton.setPosition(Gdx.graphics.getWidth()/2 - resumeButton.getWidth()/2 ,Gdx.graphics.getHeight()/2 - resumeButton.getHeight()/2 +45);
        resumeButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new GameScreen(game));
                dispose();
            }
        });
        stage.addActor(resumeButton);
        mainMenuButton = new TextButton("MAIN MENU",mySkin,"small");
        mainMenuButton.setSize(col_width*2,row_height);
        mainMenuButton.setPosition(Gdx.graphics.getWidth()/2 - mainMenuButton.getWidth()/2 ,Gdx.graphics.getHeight()/2 - mainMenuButton.getHeight()/2 -80);
        mainMenuButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new MainMenuScreen(game));
                dispose();
            }
        });
        stage.addActor(mainMenuButton);

    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.batch.draw(pmenu,Gdx.graphics.getWidth()/2 - pmenu.getWidth()/2,250);
        game.batch.end();
        stage.act();
        stage.draw();

    }

    @Override
    public void resize(int width, int height) {

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
        stage.dispose();
        mySkin.dispose();
        pmenu.dispose();
    }
}
