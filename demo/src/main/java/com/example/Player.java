package com.example;
import java.io.*;
import java.util.*;

public class Player {
    private String csvFilePath = "./player.csv";	//데이터베이스 위치

    private String id, playerSum;           // id:로그인 할때 썻던 id, playerSum:player의 점수 합계, playerTier:player의 티어(푼 개수마다 바뀜)
    private String playerTier = "unranked";                       
    private String[] question = new String[12];        // qestion:문제 푼 여부를 저장한 변수 문제 번호 0~12, 0:문제를 안품 1:문제를 품

    //id를 매개변수로 받아 player 인스턴스 변수들에 player데이터를 복사한다.
    public Player(String id) {                                       
        this.id = id;
		try (FileWriter writer = new FileWriter(csvFilePath, true);
			Scanner scanner = new Scanner(new File(csvFilePath))) {
			// csv 파일에서 ID를 찾음
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] fields = line.split(",");
				if (fields[0].equals(id)) {
                    int i = 0;
                    for(i = 0; i < 12; i++){
                        question[i] = fields[i+1];
                    }
                    playerSum = fields[13];
                    playerTier = fields[14];
					break;
				}
            }
		}
        catch (IOException ex) {
			ex.printStackTrace();
		}
	}
    //인스턴수 변수에 저장된 값들을 데이터베이스에 저장한다.
    public void playerSave(String id) {                                        
		try (FileWriter writer = new FileWriter(csvFilePath, true);
			Scanner scanner = new Scanner(new File(csvFilePath))) {
			// csv 파일에서 ID를 찾음
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] fields = line.split(",");
				if (fields[0].equals(id)) {
                    removeData(id);
                    writer.write(id);
                    int i = 0;
                    for(i = 0; i < 12; i++){
                        writer.write("," + question[i]);
                    }
                    writer.write("," + playerSum);
                    writer.write("," + playerTier + "\n");
					break;
				}
			}
		}
        catch (IOException ex) {
			ex.printStackTrace();
		}
	}

    //id를 제외한 값만 남겨두는 메서드
    private void removeData(String id) throws IOException {
        File file = new File(csvFilePath);

        BufferedReader reader = new BufferedReader(new FileReader(file));
        StringBuilder stringBuilder = new StringBuilder();

        String line;
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");        //data에 id를 제외한 값들을 집어 넣는다.
            if (!data[0].equals(id)) {
                stringBuilder.append(line).append("\n");
            }
        }
        reader.close();

        // 입력 파일에 새로운 데이터를 덮어씌움
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(stringBuilder.toString());
        writer.close();
    }

    public String getUsername() {                       
        return this.id;
    }

    public String getQuestion(int num){                 //문제를 풀었는지 확인해주는 함수. num에 문제 번호 0~11값 return값 0:안풀었음 1:풀었음
        return question[num];
    }

    public void setQuestion(int num, String value){     //문제를 푼 다음 값을 변경해주는 함수 num에 문제 번호 0~11값 value에 0:안풀었음 1:풀었음
        question[num-1] = value;
        int sum = 0;
        int i = 0;
        for(i = 0; i < 11; i++){
            sum += Integer.parseInt(getQuestion(i));
        }
        playerSum = Integer.toString(sum);      //Integer.toString : int를 string으로 변환
        playerTier = calcTier();
        playerSave(id);
    }


    //푼 문제의 합계를 반환
    public int getPlayerSum(){  
        if (playerSum != null) {
            return Integer.parseInt(playerSum);
        } else {
            return 0; // 또는 적절한 기본값으로 설정
        }
    }

    //티어 계산
    public String calcTier(){
        int score = getPlayerSum();
        if(score >= 12){
            return "graduate";
        }
        else if(score >= 8){
            return "gold";
        }
        else if(score >= 4){
            return "silver";
        }
        else {
            return "bronze";
        }
    }
}
