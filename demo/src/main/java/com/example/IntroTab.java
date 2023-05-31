//제출 버튼 크기 수정, 제출 버튼 드래그 삭제, 게임클래스 this 수정

package com.example;

import javax.swing.*;			//csv읽어오는 모듈 (성광)
import java.awt.*;
import java.awt.event.*;

public class IntroTab extends JFrame
{
	private Game game;
	static MiniGame miniGame;
	private Login login;
	Player player;
	private String id;
	JLabel tierLabel;

    private Image screenImage;
	private Graphics screenGraphic; 
	private Image introBackground = new ImageIcon(getClass().getResource("/images/introBackground.png")).getImage();
	private Image gameBackground = new ImageIcon("/minigame/introBackground.png").getImage();
	private Image map4 = new ImageIcon(Main.class.getResource("/images/map.png")).getImage();
	private Image titleImage = new ImageIcon(Main.class.getResource("/images/title.png")).getImage();

	private ImageIcon startButtonBasicImage = new ImageIcon(Main.class.getResource("/images/start.png")); // 시작버튼 기본이미지
	private ImageIcon startButtonEnteredImage = new ImageIcon(Main.class.getResource("/images/start_entered.png")); // 시작버튼 클릭이미지
	private ImageIcon endButtonBasicImage = new ImageIcon(Main.class.getResource("/images/end.png")); // 종료버튼 기본이미지
	private ImageIcon endButtonEnteredImage = new ImageIcon(Main.class.getResource("/images/end_entered.png")); // 종료버튼 클릭이미지
	private ImageIcon backImage = new ImageIcon(Main.class.getResource("/images/back.png")); // 종료버튼 기본이미지
	private ImageIcon questButtonImage = new ImageIcon(Main.class.getResource("/images/question1.png")); // 준서 문제버튼1
	private ImageIcon questButtonenteredImage = new ImageIcon(Main.class.getResource("/images/question1_entered.png")); //병훈 초록 클릭 이미지1
	private ImageIcon questButton1Image = new ImageIcon(Main.class.getResource("/images/question2.png"));// 준서 문제버튼2
	private ImageIcon questButton1enteredImage = new ImageIcon(Main.class.getResource("/images/question2_entered.png")); //병훈 초록 클릭 이미지1
	private ImageIcon questButton2Image = new ImageIcon(Main.class.getResource("/images/question3.png"));// 준서 문제버튼3
	private ImageIcon questButton2enteredImage = new ImageIcon(Main.class.getResource("/images/question3_entered.png")); //병훈 초록 클릭 이미지1
	private ImageIcon bhoonButton1Image = new ImageIcon(Main.class.getResource("/images/Button1.png")); //병훈 초록 이미지1
	private ImageIcon bhhonButton1ClickedImage = new ImageIcon(Main.class.getResource("/images/Button1_clicked.png")); //병훈 초록 클릭 이미지1
	private ImageIcon bhoonButton2Image = new ImageIcon(Main.class.getResource("/images/Button2.png")); //병훈 초록 이미지2
	private ImageIcon bhhonButton2ClickedImage = new ImageIcon(Main.class.getResource("/images/Button2_clicked.png")); //병훈 초록 클릭 이미지2
	private ImageIcon bhoonButton3Image = new ImageIcon(Main.class.getResource("/images/Button3.png")); //병훈 초록 이미지3
	private ImageIcon bhhonButton3ClickedImage = new ImageIcon(Main.class.getResource("/images/Button3_clicked.png")); //병훈 초록 클릭 이미지3
	private ImageIcon JaehyunButtonLanguageBasicImage = new ImageIcon(Main.class.getResource("/images/languege.png")); // 인문사회 버튼
	private ImageIcon JaehyunButtonLanguageEnteredImage = new ImageIcon(Main.class.getResource("/images/languege_entered.png")); // 인문사회 클릭시 버튼
	private ImageIcon JaehyunButtonMoneyBasicImage = new ImageIcon(Main.class.getResource("/images/money.png")); // 상과대 버튼
	private ImageIcon JaehyunButtonMoneyEnteredImage = new ImageIcon(Main.class.getResource("/images/money_entered.png")); // 상과대 클릭시 버튼
	private ImageIcon JaehyunButtonSpannerBasicImage = new ImageIcon(Main.class.getResource("/images/spanner.png")); // 공대 버튼
	private ImageIcon JaehyunButtonSpannerEnteredImage = new ImageIcon(Main.class.getResource("/images/spanner_entered.png")); // 공대 클릭시 버튼
	private ImageIcon medical1BasicImage = new ImageIcon(Main.class.getResource("/images/medical1.png")); // 메디컬 버튼 기본이미지1
	private ImageIcon medical1EnteredImage = new ImageIcon(Main.class.getResource("/images/medical1_entered.png")); // 공대 클릭시 버튼
	private ImageIcon medical2BasicImage = new ImageIcon(Main.class.getResource("/images/medical2.png")); // 메디컬 버튼 기본이미지2
	private ImageIcon medical2EnteredImage = new ImageIcon(Main.class.getResource("/images/medical2_entered.png")); // 공대 클릭시 버튼
	private ImageIcon medical3BasicImage = new ImageIcon(Main.class.getResource("/images/medical3.png")); // 메디컬 버튼 기본이미지3
	private ImageIcon medical3EnteredImage = new ImageIcon(Main.class.getResource("/images/medical3_entered.png")); // 공대 클릭시 버튼
	private ImageIcon tierImage;




	private ImageIcon secretImage = new ImageIcon(Main.class.getResource("/images/Secret.png")); // 리듬게임 기본이미지 demo\src\main\resources\images\Secret.png

	private boolean isMainScreen = false;
	private boolean isGameScreen = false;
    private boolean isLoginScreen = false;
	private boolean isMiniGame = false;
	private JButton startButton = new JButton(startButtonBasicImage); // 시작버튼
	private JButton endButton = new JButton(endButtonBasicImage); // 종료버튼
	private JButton backButton = new JButton(backImage); // 종료버튼
	private JButton bhoonButton1 = new JButton(bhoonButton1Image); //병훈 초록 버튼 1
	private JButton bhoonButton2 = new JButton(bhoonButton2Image); //병훈 초록 버튼 2
	private JButton bhoonButton3 = new JButton(bhoonButton3Image); //병훈 초록 버튼 3
    private JButton questButton = new JButton(questButtonImage); // 준서 퀘스트 버튼1
	private JButton questButton1 = new JButton(questButton1Image);// 준서 퀘스트 버튼2
	private JButton questButton2 = new JButton(questButton2Image);// 준서 퀘스트 버튼3
	private JButton JaehyunButtonLanguage = new JButton(JaehyunButtonLanguageBasicImage); // 인문사회 버튼
	private JButton JaehyunButtonMoney = new JButton(JaehyunButtonMoneyBasicImage); // 상과대 버튼
	private JButton JaehyunButtonSpanner = new JButton(JaehyunButtonSpannerBasicImage); // 공대 버튼
	private JButton medicalButton_1 = new JButton(medical1BasicImage); // 메디컬 버튼1
	private JButton medicalButton_2 = new JButton(medical2BasicImage); // 메디컬 버튼2
	private JButton medicalButton_3 = new JButton(medical3BasicImage); // 메디컬 버튼3
	private JButton secretButton = new JButton(secretImage); // 메디컬 버튼3

	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("/images/menuBar.png")));
	private int mouseX, mouseY;
	private Music introMusic = new Music("/music/introMusic.mp3", true);

	//초기화 함수
    public IntroTab() {
		addKeyListener(new KeyListener());
		setUndecorated(true);
		setTitle("전북대 추리퀴즈");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false);
		// setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setBackground(new Color(0, 0, 0, 0));
		setLayout(null);
		introMusic.start();

		//시작버튼 구현 코드
		startButton.setBounds(449, 549, 290, 123);
		startButton.setBorderPainted(false);
		startButton.setContentAreaFilled(false);
		startButton.setFocusPainted(false);
		startButton.addMouseListener(new MouseAdaptor() {
			@Override
			public void mouseEntered(MouseEvent e) {
				startButton.setIcon(startButtonEnteredImage);
				startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				startButton.setIcon(startButtonBasicImage);
				startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				enterlogin();
			}
		});
		add(startButton);

		//종료버튼 구현 코드
		endButton.setBounds(427, 729, 345, 84);
		endButton.setBorderPainted(false);
		endButton.setContentAreaFilled(false);
		endButton.setFocusPainted(false);
		endButton.addMouseListener(new MouseAdaptor() {
			@Override
			public void mouseEntered(MouseEvent e) {
				endButton.setIcon(endButtonEnteredImage);
				endButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				endButton.setIcon(endButtonBasicImage);
				endButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
				try {
					Thread.sleep(1000);
				} catch(InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0);
			}
		});
		add(endButton);


		//뒤로가기 버튼 구현함수
		backButton.setVisible(false);
		backButton.setBounds(0, 0, 46, 47);
		backButton.setBorderPainted(false);
		backButton.setContentAreaFilled(false);
		backButton.setFocusPainted(false);
		backButton.addMouseListener(new MouseAdaptor() {
			@Override
			public void mouseEntered(MouseEvent e) {
				backButton.setIcon(backImage);
				backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				backButton.setIcon(backImage);
				backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			public void mousePressed(MouseEvent e) {
				if (isGameScreen){
					enterMain();
				} else if (isLoginScreen) {
					login.close();
					backMain();
				}
				else if(isMainScreen){
					enterlogin();
				}
				else if(isMiniGame){
					miniGame.close();
					enterMain();
				}
			}
			});
		add(backButton);
//피아노 퀴즈 버튼//피아노 퀴즈 버튼
		secretButton.setVisible(false);
		secretButton.setBounds(550, 730, 40, 40);
		secretButton.setBounds(550, 730, 40, 40);
		secretButton.setBorderPainted(false);
		secretButton.setContentAreaFilled(false);
		secretButton.setFocusPainted(false);
		secretButton.addMouseListener(new MouseAdaptor() {
			@Override
			public void mouseEntered(MouseEvent e) {
				secretButton.setIcon(secretImage);
				secretButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				secretButton.setIcon(secretImage);
				secretButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
				enterMiniGame();
			}
			});
		add(secretButton);

		//메뉴바 생성
		menuBar.setBounds(0, 0, 1200, 26);
		menuBar.addMouseListener(new MouseAdaptor() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		menuBar.addMouseMotionListener(new MouseMotionAdaptor() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y= e.getYOnScreen();
				setLocation(x -mouseX, y - mouseY);
			}
		});
		add(menuBar);

		setQuizButton(medicalButton_1, medical1BasicImage, medical1EnteredImage, 984, 210, 70, 46, 7);		// 의대 1
		setQuizButton(medicalButton_2, medical2BasicImage, medical2EnteredImage, 900, 160, 70, 46, 8);		//의대 2
		setQuizButton(medicalButton_3, medical3BasicImage, medical3EnteredImage, 1018, 160, 70, 46, 9);		//의대 3
		setQuizButton(questButton, questButtonImage, questButtonenteredImage, 550, 490, 70, 40, 4);		//준서 버튼 1
		setQuizButton(questButton1, questButton1Image, questButton1enteredImage, 423, 710, 70, 40, 5);   //준서 버튼 2
		setQuizButton(questButton2, questButton2Image, questButton2enteredImage, 533, 655, 70, 40, 6);   //준서 버튼 3
		setQuizButton(bhoonButton1, bhoonButton1Image, bhhonButton1ClickedImage, 200, 330, 70, 40, 1);		//산디과과 1
		setQuizButton(bhoonButton2, bhoonButton2Image, bhhonButton2ClickedImage, 360, 665, 70, 40, 2);		//조소학과 2
		setQuizButton(bhoonButton3, bhoonButton3Image, bhhonButton3ClickedImage, 280, 320, 70, 40, 3);		//음학과 3
		//시작버튼 구현 코드 파랑색 1 (건축공학)
		setQuizButton(JaehyunButtonLanguage, JaehyunButtonLanguageBasicImage, JaehyunButtonLanguageEnteredImage, 650, 435, 70, 44, 10);
		//시작버튼 구현 코드 파랑색 2 (컴퓨터공학)
		setQuizButton(JaehyunButtonMoney, JaehyunButtonMoneyBasicImage, JaehyunButtonMoneyEnteredImage, 763, 400, 70, 40, 11);
		//시작버튼 구현 코드 파랑색 3 (재료공학)
		setQuizButton(JaehyunButtonSpanner, JaehyunButtonSpannerBasicImage, JaehyunButtonSpannerEnteredImage, 650, 360, 70, 44, 12);
	}

	// 게임화면 그리는 함수
    public void paint(Graphics g) {
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		screenGraphic = screenImage.getGraphics();
		screenDraw((Graphics2D)screenGraphic);
		g.drawImage(screenImage, 0, 0, null);
	}
	//마찬가지
    public void screenDraw(Graphics2D g) {
		if(isMainScreen) {
			g.drawImage(map4, 0, 0, null);
		}
		else if(isMiniGame) {
			g.drawImage(gameBackground, 0, 0, null);
			miniGame.screenDraw(g);
		}
		else {
			g.drawImage(introBackground, 0, 0, null);
			g.drawImage(titleImage, 159, 103, null);
		}
		paintComponents(g);
		this.repaint();
	}

	public void enterMain() {
		isMainScreen = true;
		isGameScreen = false;
		isLoginScreen = false;
		isMiniGame = false;
		startButton.setVisible(false);
		endButton.setVisible(false);
		backButton.setVisible(true);
		introMusic.close();
		setVisibleQuizButton(true); //퀴즈 버튼 보이게
		if (login != null) {
			login.close();
		}
	
		if (game != null) {
			game.close();
		}

		setPlayer(this.id); //플레이어 설정
		tierImage = new ImageIcon(Main.class.getResource("/images/tier_" + this.player.calcTier() + ".png")); //티어 이미지 불러오기
		tierLabel = new JLabel(tierImage); //티어 이미지 출력할 JLabel 생성
		tierLabel.setBounds(-50, -30, tierImage.getIconWidth(), tierImage.getIconHeight()); //티어이미지 위치
		add(tierLabel); //티어 이미지 출력
		
		repaint();    // 추가: 컴포넌트를 다시 그리도록 지시
	}

	public void backMain() {
		isMainScreen = false;
        isGameScreen = false;
		isLoginScreen = false;
		isMiniGame = false;
		backButton.setVisible(false);
		startButton.setVisible(true);
		endButton.setVisible(true);
		introMusic = new Music("/music/introMusic.mp3", true);
		introMusic.start();
		setVisibleQuizButton(false); //퀴즈 버튼 안보이게
		if(tierLabel != null){ //티어 이미지 안보이게
			tierLabel.setVisible(false);
		}
	}

	public void enterGame(int quizIndex) {
		isGameScreen = true;
		isLoginScreen = false;
		isMainScreen = false;
		isMiniGame = false;
		startButton.setVisible(false);
		endButton.setVisible(false);
		backButton.setVisible(true);
		introMusic.close();
		setVisibleQuizButton(false); //퀴즈 버튼 안보이게
		tierLabel.setVisible(false); //티어 이미지 안보이게
		game = new Game(quizIndex, this);
		System.out.printf("quizIndex = %d\n",quizIndex);
		game.setBounds(0, 0, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT); //game의 panel 크기 설정
		this.add(game); //game을 panel에 추가
		game.setVisible(true); //game을 보이게 함
	}

	public void enterMiniGame() {
		isLoginScreen = false;
		isGameScreen = false;
		isMainScreen = false;
		isMiniGame = true;
		startButton.setVisible(false);
		endButton.setVisible(false);
		backButton.setVisible(true);
		tierLabel.setVisible(false);
		introMusic.close();
		miniGame = new MiniGame();
		miniGame.start();
		requestFocusInWindow();
        setVisibleQuizButton(false);	//퀴즈 버튼 안보이게
	}


    public void enterlogin() {
		isLoginScreen = true;
		isGameScreen = false;
		isMainScreen = false;
		isMiniGame = false;
		startButton.setVisible(false);
		endButton.setVisible(false);
		backButton.setVisible(true);
		introMusic.close();
        setVisibleQuizButton(false);	//퀴즈 버튼 안보이게
		if(tierLabel != null){ //티어 이미지 안보이게
			tierLabel.setVisible(false);
		}
		login = new Login(this);
		login.setBounds(0, 0, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		this.add(login);
		login.setVisible(true);
		
	}

	//맵에 있는 퀴즈 버튼 생성
	//매개변수 : JButton, 기본이미지, 마우스올렸을 때 이미지, x좌표, y좌표, 너비, 높이, 퀴즈 번호
	public void setQuizButton(JButton jbutton, ImageIcon basicImage, ImageIcon enteredImage, int x, int y, int width, int height, int quizIndex){
		jbutton.setVisible(false);
		jbutton.setBounds(x, y, width, height);
		jbutton.setBorderPainted(false);
		jbutton.setContentAreaFilled(false);
		jbutton.setFocusPainted(false);
		jbutton.addMouseListener(new MouseAdaptor() {
			@Override
			public void mouseEntered(MouseEvent e) {
				jbutton.setIcon(enteredImage);
				jbutton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				jbutton.setIcon(basicImage);
				jbutton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				enterGame(quizIndex);
		}
		});
		add(jbutton);
	}

	//퀴즈 버튼 보이게 하는 함수
	public void setVisibleQuizButton(boolean isVisible){
		questButton.setVisible(isVisible);
		questButton1.setVisible(isVisible);
		questButton2.setVisible(isVisible);
		bhoonButton1.setVisible(isVisible);
		bhoonButton2.setVisible(isVisible);
		bhoonButton3.setVisible(isVisible);
		JaehyunButtonLanguage.setVisible(isVisible);
		JaehyunButtonMoney.setVisible(isVisible);
		JaehyunButtonSpanner.setVisible(isVisible);
		medicalButton_1.setVisible(isVisible);
		medicalButton_2.setVisible(isVisible);
		medicalButton_3.setVisible(isVisible);
		secretButton.setVisible(isVisible);
	}

	//플레이어 생성
	public void setPlayer(String id){
		this.player = new Player(id);
	}

	//플레이어 id 설정
	public void setID(String id){
		this.id = id;
	}

	public void setQuizSolved(int quizIndex){
		this.player.setQuestion(quizIndex, "1");
	}
}