package com.example;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Game extends JPanel {
    private Image currentImage; //현재 퀴즈 이미지
    private Image quizImage; //퀴즈 
    private Image quizWrongImage; // 퀴즈 오답
    private Image quizCorrectImage; // 퀴즈 정답
    private JTextField answerField; //답 입력란
    private ImageIcon submitImage; //제출 버튼 이미지
    private ImageIcon returnImage; //돌아가기 버튼 이미지
    private ImageIcon backMapImage; //지도로 돌아가기 버튼 이미지
    private JButton submitButton; //제출 버튼
    private JButton returnButton; //돌아가기 버튼
    private JButton backMapButton; //지도로 돌아가기 버튼
    private String quizNum; //퀴즈 번호(문자열)
    private GameDB gameDB = new GameDB(); //DB 생성
    private String id; //유저네임

    public Game(int quizIndex, IntroTab introTab) {
        setLayout(null); //레이아웃 양식 없음
        this.id = introTab.player.getUsername(); //유저네임 받아오기
        quizNum = Integer.toString(quizIndex); //퀴즈 번호(문자열)로 변환
        submitImage = new ImageIcon(Main.class.getResource("/images/submit.png")); //제출 버튼 이미지
        returnImage = new ImageIcon(Main.class.getResource("/images/returnButton.png")); //돌아가기 버튼 이미지
        backMapImage = new ImageIcon(Main.class.getResource("/images/backMap.png")); //돌아가기 버튼 이미지
        quizImage = new ImageIcon(Main.class.getResource("/images/quiz/quiz"+quizNum+".png")).getImage(); //인덱스 별 퀴즈 이미지 생성 demo\src\main\resources\images\quiz
        quizWrongImage = new ImageIcon(Main.class.getResource("/images/quiz/currectOrWrong/quiz"+quizNum+"_wrong.png")).getImage(); //인덱스 별 퀴즈 오답 이미지 생성 demo\src\main\resources\images
        quizCorrectImage = new ImageIcon(Main.class.getResource("/images/quiz/currectOrWrong/quiz"+quizNum+"_correct.png")).getImage(); //인덱스 별 퀴즈 정답 이미지 생성 demo\src\main\resources\images\quiz\currectOrWrong
        submitButton = new JButton(submitImage); //제출 버튼 생성
        returnButton = new JButton(returnImage); //돌아가기 버튼 생성
        backMapButton = new JButton(backMapImage); //지도로 돌아가기 버튼 생성
        answerField = new JTextField(10); //답 입력란 생성
        currentImage = quizImage; //현재 퀴즈 이미지 = 퀴즈 이미지


        //정답 텍스트 필드
        answerField.setBounds(357, 745, 345, 84); //답 입력란 위치, 크기 설정
        answerField.setFont(new Font("맑은 고딕", Font.BOLD, 30)); //답 입력란 폰트 설정
        answerField.setVisible(true); //답 입력란 보이기
        add(answerField); //JPanel에 답 입력란 추가

        //정답 제출 버튼
        submitButton.setVisible(true); //제출 버튼 보이기
		submitButton.setBounds(764, 751, 227, 78); //제출 버튼 위치, 크기 설정
		submitButton.setBorderPainted(false); //제출 버튼 테두리 없음
		submitButton.setContentAreaFilled(false); //제출 버튼 내용 영역 채우기 없음
		submitButton.setFocusPainted(false); //제출 버튼 포커스 없음
		submitButton.addMouseListener(new MouseAdaptor() {
			@Override
			public void mouseEntered(MouseEvent e) {     //마우스가 제출 버튼 위에 있을 때
				submitButton.setIcon(submitImage); 
				submitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {     //마우스가 제출 버튼 위에 없을 때
				submitButton.setIcon(submitImage);
				submitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {    //마우스로 제출 버튼을 눌렀을 때
                checkAnswer(quizIndex); //답 확인
			}
		});
		add(submitButton); //JPanel에 제출 버튼 추가

        //재시도 버튼
        returnButton.setVisible(false); //돌아가기 버튼 보이기
		returnButton.setBounds(487, 745, 227, 78); //돌아가기 버튼 위치, 크기 설정
		returnButton.setBorderPainted(false); //돌아가기 버튼 테두리 없음
		returnButton.setContentAreaFilled(false); //돌아가기 버튼 내용 영역 채우기 없음
		returnButton.setFocusPainted(false); //돌아가기 버튼 포커스 없음
		returnButton.addMouseListener(new MouseAdaptor() {
			@Override
			public void mouseEntered(MouseEvent e) {     //마우스가 돌아가기 버튼 위에 있을 때
				returnButton.setIcon(returnImage); 
				returnButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {     //마우스가 돌아가기 버튼 위에 없을 때
				returnButton.setIcon(returnImage);
				returnButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {    //마우스로 returnButton을 눌렀을 때
                currentImage = quizImage; //퀴즈 이미지로 변경
                returnButton.setVisible(false); //돌아가기 버튼 숨기기
                answerField.setVisible(true); //답 입력란 보이기
                submitButton.setVisible(true); //제출 버튼 보이기
                repaint();
			}
		});
		add(returnButton); //JPanel에 돌아가기 버튼 추가

        //지도로 돌아가는 버튼
        backMapButton.setVisible(false); //돌아가기 버튼 보이기
		backMapButton.setBounds(487, 745, 227, 78); //돌아가기 버튼 위치, 크기 설정
		backMapButton.setBorderPainted(false); //돌아가기 버튼 테두리 없음
		backMapButton.setContentAreaFilled(false); //돌아가기 버튼 내용 영역 채우기 없음
		backMapButton.setFocusPainted(false); //돌아가기 버튼 포커스 없음
		backMapButton.addMouseListener(new MouseAdaptor() {
			@Override
			public void mouseEntered(MouseEvent e) {     //마우스가 돌아가기 버튼 위에 있을 때
				backMapButton.setIcon(backMapImage); 
				backMapButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {     //마우스가 돌아가기 버튼 위에 없을 때
				backMapButton.setIcon(backMapImage);
				backMapButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {    //마우스로 backMapButton을 눌렀을 때
                introTab.enterMain(); //메인화면으로 돌아가기
			}
		});
		add(backMapButton); //JPanel에 돌아가기 버튼 추가
    }


    //답 확인
    public void checkAnswer(int quizIndex) {
        String userAnswer = answerField.getText(); // 사용자가 입력한 답
        String correctAnswer = gameDB.getQuizAnswer(quizIndex); // 정답을 DB에서 가져옴
    
        if (userAnswer.equals(correctAnswer)) { // 답이 맞으면
            Player player = new Player(id); //저장할 플레이어 객체 생성
            player.setQuestion(quizIndex, "1"); //해당 문제 품
            currentImage = quizCorrectImage; //정답 이미지로 변경
            returnButton.setVisible(false);
            submitButton.setVisible(false);
            answerField.setVisible(false);
            backMapButton.setVisible(true);
            repaint();
            
        }
        else{                                   // 답이 틀리면
            currentImage  = quizWrongImage; //오답 이미지로 변경
            returnButton.setVisible(true);
            submitButton.setVisible(false);
            answerField.setVisible(false);
            repaint();
        }
    }

	// 게임화면 그리는 함수
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(currentImage, 0, 0, null); // 퀴즈 배경 이미지 그리기
    }

    //JPanel 닫기
    public void close(){
        this.setVisible(false);
    }
}
