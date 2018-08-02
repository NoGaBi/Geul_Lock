package com.example.jho63.esc_sample_2;

public class Person {
    private String Name, PhoneNum, E_mail, ProfileImgRes;

    public Person(String Name, String PhoneNum, String E_mail) {
        this.E_mail = E_mail;
        this.Name = Name;
        this.PhoneNum = PhoneNum;
    }

    public String getName() {
        return Name;
    }

    public String getPhoneNum() {
        return PhoneNum;
    }

    public String getE_mail() {
        return E_mail;
    }

    public String getProfileImgRes() {
        return ProfileImgRes;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setPhoneNum(String phoneNum) {
        PhoneNum = phoneNum;
    }

    public void setE_mail(String e_mail) {
        E_mail = e_mail;
    }

    public void setProfileImgRes(String profileImgRes) {
        ProfileImgRes = profileImgRes;
    }
}