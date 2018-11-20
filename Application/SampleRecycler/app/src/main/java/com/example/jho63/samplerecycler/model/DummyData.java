package com.example.jho63.samplerecycler.model;

import java.util.ArrayList;

/**
 * Created by Jyoung on 2018. 8. 30..
 */

public class DummyData {
    public static ArrayList<ListItem> items = new ArrayList<ListItem>() {
        {
            add(new ListHeader());
            add(new ListToast("토스트", "안드 토스트는 못생겼다"));
            add(new ListSnackbar("스낵바", "사실 스낵바도 못생겼음"));
            add(new ListDialog("다이얼로그", "다이얼로그가 그나마 낫지"));
            add(new ListToast("은호야", "안녕?"));
            add(new ListSnackbar("야야야 이거 눌러봐", "메롱"));
            add(new ListSnackbar("어그로", "죄송합니다.."));
            add(new ListToast("후배님들", "안녕하세요"));
            add(new ListDialog("처음 뵙겠습니다",
                    "저 지금 은호가 시켜서 이거 하고 있거든요.\n" +
                            "흑흑 은호 무서웡,,따흑\n" +
                            "그래도 이왕 만드는 거니 잘 쓰이면 좋겠어요!!!\n" +
                            "EOS 화이팅~~"));
        }
    };
}
