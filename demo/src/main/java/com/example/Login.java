package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class Login extends JPanel{

    private Image loginBackground; // 로그인 배경화면
    private JTextField usernameField; // 유저네임 입력란
    private ImageIcon loginButtonImage; // 로그인버튼 이미지
    private JButton loginButton; // 로그인버튼
    private String csvFilePath;	//데이터베이스 위치(성광)
	private String id;	//유저네임
	
	//로그인 창
    public Login(IntroTab introTab) {
        setLayout(null);
        loginButtonImage = new ImageIcon(Main.class.getResource("/images/login.png"));
		loginBackground = new ImageIcon(Main.class.getResource("/images/loginBackground.png")).getImage();
		loginButton = new JButton(loginButtonImage); // 로그인버튼
		csvFilePath = "./player.csv"; 

        usernameField = new JTextField(10);

		//로그인 버튼
        loginButton.setVisible(true);
		loginButton.setBounds(500, 630, 200, 75);
		loginButton.setBorderPainted(false);
		loginButton.setContentAreaFilled(false);
		loginButton.setFocusPainted(false);
		loginButton.addMouseListener(new MouseAdaptor() {
			@Override
			public void mouseEntered(MouseEvent e) {
				loginButton.setIcon(loginButtonImage);
				loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				loginButton.setIcon(loginButtonImage);
				loginButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				id = usernameField.getText(); //textfield에서 id 받아오기
				saveIDToFile(id); //데이터베이스에 id 저장
				introTab.setID(id); //introTab에 id 넘기기
				introTab.enterMain(); //mainTab으로 넘어가기
				usernameField.setText(""); //textfield 초기화
			}
			
		});
		add(loginButton);

		//username입력 필드
		usernameField.setBounds(398, 505, 400, 60);
        usernameField.setFont(new Font("맑은 고딕", Font.BOLD, 30)); //답 입력란 폰트 설정
		usernameField.setVisible(true);
        add(usernameField);
    }

	//데이터베이스로 로그인 데이터 넘기는 함수 (성광)
    private void saveIDToFile(String id) {
		String header = "ID,Q1,Q2,Q3,Q4,Q5,Q6,Q7,Q8,Q9,Q10,Q11,Q12,PLAYERSUM,Tier";
		String initialValues = id + ",0,0,0,0,0,0,0,0,0,0,0,0,0,unranked";
		try (FileWriter writer = new FileWriter(csvFilePath, true);
			Scanner scanner = new Scanner(new File(csvFilePath))) {
			boolean found = false;
			
			// csv 파일이 없으면 새로 생성하고 헤더 추가
			if (!scanner.hasNextLine()) {
				writer.write(header + "\n");
			}
			
			// csv 파일에서 ID를 찾음
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] fields = line.split(",");
				if (fields[0].equals(id)) {
					found = true;
					break;
				}
			}
			
			// csv 파일에 ID 추가
			if (!found) {
				writer.write(initialValues + "\n");
			}
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

    //JPanel에 이미지 넣기
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
		screenDraw(g);
    }
	public void screenDraw(Graphics g) {
		g.drawImage(loginBackground, 0, 0, null); //퀴즈 배경 이미지 그리기
	}

    //JPanel 닫기
    public void close(){
        this.setVisible(false);
    }
}
