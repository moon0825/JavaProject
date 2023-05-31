package com.example;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Note extends Thread{
    private Image noteBasicImage = new ImageIcon(Main.class.getResource("/minigame/note_basic.png")).getImage();
	private int x, y = 760 - (1000 / Main.SLEEP_TIME * Main.NOTE_SPEED) * Main.REACH_TIME;
	private String noteType;
	private boolean proceeded = true;
	
	public String getnoteType() {
		return noteType;
	}
	
	public boolean isProceeded() {
		return proceeded;
	}
	
	public void close() {
		proceeded = false;
	}
	
	public Note(String noteType) {

		if(noteType.equals("A")){
			x = 135;
		}
		else if(noteType.equals("S")){
			x = 263;
		}
		else if (noteType.equals("D")) {
			x = 391;
		}
		else if(noteType.equals("Space")){
			x = 519;
		}
		else if(noteType.equals("J")){
			x = 647;
		}
		else if(noteType.equals("K")){
			x = 775;
		}
		else if(noteType.equals("L")){
			x = 903;
		}
		this.noteType = noteType;
	}
	
	public void screenDraw(Graphics2D g) {
		g.drawImage(noteBasicImage, x, y, null); 
	}
	@Override
	public void run() {
		try {
			while (true) {
				drop();
				if(proceeded) {
					Thread.sleep(Main.SLEEP_TIME);
				}
				else {
					interrupt();
					break;
				}
			}
		} catch (Exception e){
			System.err.println(e.getMessage());
		}
	}
	
	public void drop() {
		y += Main.NOTE_SPEED;
		if(y >= 800) {
			System.out.println("Miss");
			close();
		}
	}
	
	public String judge() {
		if (y >= 800) {
			System.out.println("Miss");
			close();
			return "Miss";
		}
		else if (y >= 785) {
			System.out.println("Late");
			close();
			return "Late";
		}

		else if (y >= 767) {
			System.out.println("Perfect");
			close();
			return "Perfect";
		}
		else if (y >= 760) { // "Good" 판정 범위 조정
			System.out.println("Good");
			close();
			return "Good";
		}
		else if (y >= 740) { // "Early" 판정 범위 조정
			System.out.println("Early");
			close();
			return "Early";
		}
		return "None";
	}
	
	public int getY() {
		return y;
	}
}
