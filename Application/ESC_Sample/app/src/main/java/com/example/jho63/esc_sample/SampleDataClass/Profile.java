package com.example.jho63.esc_sample.SampleDataClass;

import com.example.jho63.esc_sample.R;

public class Profile {
    private String Name, PhoneNum, E_Mail, ProfileImgRes;

    public static class Builder {
        private final String Name;
        private final String PhoneNum;
        private final String E_Mail;

        private String ProfileImgRes = null;

        public Builder(String Name, String PhoneNum, String E_Mail) {
            this.Name = Name;
            this.PhoneNum = PhoneNum;
            this.E_Mail = E_Mail;
        }

        public Builder ProfileImgRes(String ProfileImgRes) {
            this.ProfileImgRes = ProfileImgRes;
            return this;
        }

        public Profile build() {
            return new Profile(this);

        }
    }

    private Profile(Builder builder) {
        Name = builder.Name;
        PhoneNum = builder.PhoneNum;
        E_Mail = builder.E_Mail;
        ProfileImgRes = builder.ProfileImgRes;
    }
}
