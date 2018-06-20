package com.example.maxim.test;

import java.io.Serializable;

public class Tests implements Serializable {
    private String test;
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;

    public Tests(){

    }
    public Tests(String test, String answer1, String answer2, String answer3, String answer4){
        this.test = test;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
    }
    public String getTest(){return test;}
    public void setTest(String test){this.test=test;}

    public String getAnswer1(){return answer1;}
    public void setAnswer1(String answer1){this.answer1=answer1;}
    public String getAnswer2(){return answer2;}
    public void setAnswer2(String answer2){this.answer2=answer2;}
    public String getAnswer3(){return answer3;}
    public void setAnswer3(String answer3){this.answer3=answer3;}
    public String getAnswer4(){return answer4;}
    public void setAnswer4(String answer4){this.answer4=answer4;}
}
