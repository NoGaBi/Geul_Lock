package com.example.jho63.esc_sample.SampleDataClass;

import com.example.jho63.esc_sample.R;

public class Profile {
    private String Name, PhoneNum, E_Mail, ProfileImgRes;

    public Profile(String Name, String PhoneNum, String E_Mail) {
        this.Name = Name;
        this.PhoneNum = PhoneNum;
        this.E_Mail = E_Mail;
    }

    public String getName() {
        return Name;
    }

    public String getPhoneNum() {
        return PhoneNum;
    }

    public String getE_Mail() {
        return E_Mail;
    }

    public String getProfileImgRes() {
        return ProfileImgRes;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public void setPhoneNum(String phoneNum) {
        this.PhoneNum = phoneNum;
    }

    public void setE_Mail(String E_Mail) {
        this.E_Mail = E_Mail;
    }

    public void setProfileImgRes(String ProfileImgRes) {
        this.ProfileImgRes = ProfileImgRes;
    }
}
