package com.example;

import java.io.BufferedInputStream;
import java.io.InputStream;

import javazoom.jl.player.Player;

public class Music extends Thread {
    private Player player;
    private boolean isLoop;
	private String name;

    public Music(String name, boolean isLoop) {
        try {
			this.name = name;
            this.isLoop = isLoop;
            InputStream inputStream = getClass().getResourceAsStream(name);
            BufferedInputStream bis = new BufferedInputStream(inputStream);
            player = new Player(bis);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public int getTime() {
        if (player == null)
            return 0;
        return player.getPosition();
    }

    public void close() {
        isLoop = false;
        player.close();
        this.interrupt();
    }

    @Override
    public void run() {
        try {
            do {
                player.play();
                player.close();
                InputStream inputStream = getClass().getResourceAsStream(name);
                BufferedInputStream bis = new BufferedInputStream(inputStream);
                player = new Player(bis);
            } while (isLoop);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
