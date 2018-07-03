package com.example.jho63.hongstudy01;

import java.util.ArrayList;

public class Person {
    String name, phoneNum, eMail, profileImg=null;
    private int cid;

    public Person(String name, String phoneNum, String eMail){
        this.name = name;
        this.phoneNum = phoneNum;
        this.eMail = eMail;
    }

    public void setCid(int cid){this.cid = cid;}
    public int getCid(){return cid;}
    public String getName() {return name;}
    public String getPhoneNum() {return phoneNum;}
    public String geteMail() {return eMail;}
    public String getProfileImg(){return profileImg;}
    public void setProfileImg(String profileImg){this.profileImg = profileImg;}

    public static ArrayList<Person> dummyList = new ArrayList<Person>() {
        {
            add(new Person("기범", "010-3376-4850", "steveall1@naver.com"));
            add(new Person("아이린", "010-1234-1234", "redvelvet@naver.com"));
            add(new Person("세정", "010-1234-5678", "gugudan@daum.com"));
            add(new Person("인직", "010-0000-0000", "injiks@gmail.com"));
        }
    };

}
