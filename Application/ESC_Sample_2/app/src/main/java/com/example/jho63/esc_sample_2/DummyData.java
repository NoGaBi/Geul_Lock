package com.example.jho63.esc_sample_2;

import java.util.ArrayList;

public class DummyData {
    public static ArrayList<Person> dummyList;
    private static int page = 0;

    static {
        dummyList = new ArrayList<Person>();
        dummyList.add(new Person("정은호","010-9263-6394","jho6394@gmail.com"));
        dummyList.add(new Person("고세짱","010-0000-0000","hyu.eos@gmail.com"));
    }

    public static int getPage() {
        return page;
    }

    public static int PageUp(){
        if(page+1 < dummyList.size()){
            page++;
            return 1;//pageUp 성공
        } else {
            return 0;//pageUp 실패
        }
    }

    public static int PageDown(){
        if(page > 0) {
            page--;
            return 1;//pageDown 성공
        } else {
            return 0;//pageDown 실패
        }
    }

}
