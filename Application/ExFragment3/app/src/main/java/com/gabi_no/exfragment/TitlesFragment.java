package com.gabi_no.exfragment;

import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

//글목록을 보여주는 fragment
//글 선택시 기기의 방향에 따라 글내용을 보여주는 방법이 달라짐
public class TitlesFragment extends ListFragment {
    boolean mDualPane;
    int mCurCheckPosition = 0;

    @Override
    //Activity의 onCreate() 메소드 완료시 호출됨
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Shakespeare 클래스에 있는 글제목으로 리스트를 보여줌
        setListAdapter(new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_activated_1,
                Shakespeare.TITLES));


        View detailsFrame = getActivity().findViewById(R.id.details);

        mDualPane = detailsFrame != null  //기기 방향이 Landscape라서 DetailsFragment를 위한 레이아웃 구성요소가 포함되어 있다면 DetailsFragment가 생성된다.
                && detailsFrame.getVisibility() == View.VISIBLE; //DetailsFragment가 Activity에 추가되었다면 화면에 보여짐


        if (savedInstanceState != null) { //TitlesFragment가 처음 생성된게 아니라면
            //글목록에서 체크했던 항목 위치를 복구
            mCurCheckPosition = savedInstanceState.getInt("curChoice", 0);
        }

        if (mDualPane) {
            //dual-panel 모드라면
            //리스트에서 아이템 하나씩 선택가능하도록 하고
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            //리스트에서 선택된 아이템을 하이라이트 및 DetailsFragment에 글내용을 표시하여 같이 보여줌.
            showDetails(mCurCheckPosition);
        } else {//uni-pane 모드이라면
            //리스트에서 아이템 하나씩 선택가능하도록 하고
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            //리스트에서 선택된 아이템을 하이라이트
            getListView().setItemChecked(mCurCheckPosition, true);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Toast.makeText(getActivity(), "onSaveInstanceState",
                Toast.LENGTH_LONG).show();

        outState.putInt("curChoice", mCurCheckPosition);
    }

    //사용자가 리스트에서 글 선택시, showDetails 호출하여 기기방향에 따라 글내용을 위한 DetailsFragment 보여주는 방법 결정
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        showDetails(position);
    }


    //기기의 방향에 따라 글내용을 위한 DetailsFragment 보여주는 방법 결정
    void showDetails(int index) {
        mCurCheckPosition = index;

        if (mDualPane) {
            //기기의 방향이 Landscape라면 dual-pane가능
            //글목록 Fragment와 글내용 Fragment를 하나의 Activity에서 보여줌

            //리스트에서 선택한 아이템 하이라이트
            getListView().setItemChecked(index, true);

            DetailsFragment details = (DetailsFragment) getFragmentManager()
                    .findFragmentById(R.id.details);
            //DetailsFragment를 처음 생성하는 경우이거나 글목록에서 기존과 다른 글이 선택되었다면
            if (details == null || details.getShownIndex() != index) {

                //새로운 DetailsFragment 인스턴스를 생성
                details = DetailsFragment.newInstance(index);

                //transaction을 실행하여 기존 fragment를 교체함
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.details, details);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
            }

        } else {
            //기기의 방향이 portrait인경우 single-pane이다.
            //다른 Activity를 시작하여 글목록에서 선택된 글의 내용을 위한 DetailsFragment를 보여준다.

            //새로운 intent를 생성
            Intent intent = new Intent();

            //Activty context와 시작할 Activity의 클래스를 지정한다.
            intent.setClass(getActivity(), DetailsActivity.class);

            //글목록에서 현재 선택된 위치를 전달한다.
            intent.putExtra("index", index);

            //DetailsActivity 시작
            startActivity(intent);
        }
    }
}