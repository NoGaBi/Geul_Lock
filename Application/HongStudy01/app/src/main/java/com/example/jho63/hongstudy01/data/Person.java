package com.example.jho63.hongstudy01.data;

import java.util.ArrayList;

public class Person {

    String name, phoneNum, eMail;

    public Person(String name, String phoneNum, String eMail) {
        this.name = name;
        this.phoneNum = phoneNum;
        this.eMail = eMail;
    }

    public String getName(){return name;}
    public String getPhoneNum(){return phoneNum;}

    public String geteMail() {
        return eMail;
    }
    public static ArrayList<Person> dummyList = new ArrayList<Person>(){
        {
            add(new Person("기범", "010-3376-4850", "steveall1@naver.com"));
            add(new Person("아이린", "010-1234-1234", "redvelvet@naver.com"));
            add(new Person("세정", "010-1234-2345", "gugudan@daum.net"));
            add(new Person("인직", "010-0000-0000", "injiks@gmail.com"));
        }
    };
}
