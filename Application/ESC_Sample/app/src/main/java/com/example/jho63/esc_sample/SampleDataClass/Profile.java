package com.example.jho63.esc_sample.SampleDataClass;

public class Profile {
    private String Name, PhoneNum, E_Mail;

    public static class Builder {
        private final String Name;
        private final String PhoneNum;
        private final String E_Mail;

        public Builder(String Name, String PhoneNum, String E_Mail) {
            this.Name = Name;
            this.PhoneNum = PhoneNum;
            this.E_Mail = E_Mail;
        }

        public Profile build() {
            return new Profile(this);
        }
    }

    private Profile(Builder builder) {
        Name = builder.Name;
        PhoneNum = builder.PhoneNum;
        E_Mail = builder.E_Mail;
    }
}
