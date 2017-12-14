package com.geulock.geul_lock.data;

import java.util.ArrayList;

/**
 * Created by sihwan on 2017. 12. 13..
 */

public class SearchHistoriesPrev {
    private static ArrayList<String> messageList;

    public static ArrayList<String> getList() {
        if(messageList == null) {
            messageList = new ArrayList<>();
            messageList.add("#Dummy #Hello #IAmInsane #Wowowowowow #whatTheHell");
            messageList.add("#Hell yeah #Dushbags #IMyPeople");
            messageList.add("No meanings of tags or any thing");
            messageList.add("Very long text this would be. Very long text this would be. Very long text this would be. Very long text this would be. Very long text this would be.");
            messageList.add("Now What");
            messageList.add("진심 아무말 대잔치가 이런건가");
            messageList.add("#지리구요");
            messageList.add("#사자 두마리가 서로 아웅다웅 못해 미쳐 날뛰어 뒤지는 각이구요");
            messageList.add("#둘이먹다죽어도모를그맛은레알지리산꼭대기약수터에서퍼마신약수물은저리가라할정도의빼박캔트황천하는맛이구요");
            messageList.add("#와정말 야호우 #와우 #대기번호10번");
        }

        return messageList;
    }
}
