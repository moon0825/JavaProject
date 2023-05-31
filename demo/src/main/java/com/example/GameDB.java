package com.example;


public class GameDB {
    private String[] quizAnswer = {"","김오곤","2","애국가","26","CAFE","11","내시경","탈모","C","학교","박열","2-4-5-3-1"};         //문제 정답을 배열안에 넣어주세요

    public String getQuizAnswer(int quizIndex){
        return quizAnswer[quizIndex];
    }
}
