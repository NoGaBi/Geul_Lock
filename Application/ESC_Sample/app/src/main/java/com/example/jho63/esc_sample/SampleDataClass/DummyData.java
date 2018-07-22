package com.example.jho63.esc_sample.SampleDataClass;

import android.content.Context;
import android.widget.Toast;

import com.example.jho63.esc_sample.ContactActivity;

import java.util.ArrayList;

public class DummyData {
    public static ArrayList<Profile> dummyList;
    private static int page = 0;

    static {
        dummyList = new ArrayList<Profile>();
        dummyList.add(new Profile("정은호","010-9263-6394","jho6394@gmail.com"));
        dummyList.add(new Profile("고세진", "010-2910-9704", "blablabla@gmail.com"));
    }

    public static int getPage() {
        return page;
    }
    public static int PageUp(){
        if(page < dummyList.size()) {
            page++;
            return 0;
        }else{
            return 1;
        }
    }

    public static int PageDown(){
        if(page > 0) {
            page--;
            return 0;
        }else {
            return 1;
        }
    }

}
