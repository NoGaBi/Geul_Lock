package com.example.jho63.esc_sample;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jho63.esc_sample.SampleDataClass.DummyData;
import com.example.jho63.esc_sample.SampleDataClass.EditActivity;

public class ContactActivity extends AppCompatActivity {

    TextView phoneNum, Name, email, pageView;
    ImageView profileImg;
    ImageButton BackBtn, NextBtn;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.profile_menu, menu);
        return true;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        phoneNum = (TextView) findViewById(R.id.add_phoneNum);
        Name = (TextView) findViewById(R.id.Contact_Name);
        email = (TextView) findViewById(R.id.Contact_email);
        pageView = (TextView) findViewById(R.id.PageView);
        profileImg = (ImageView) findViewById(R.id.Contact_Img);

        phoneNum.setText(DummyData.dummyList.get(DummyData.getPage()).getPhoneNum());
        Name.setText(DummyData.dummyList.get(DummyData.getPage()).getName());
        email.setText(DummyData.dummyList.get(DummyData.getPage()).getE_Mail());
        pageView.setText((DummyData.getPage() + 1) + "/" + DummyData.dummyList.size());
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        //actionBar 객체를 가져올 수 있다.
        ActionBar actionBar = getSupportActionBar();

        //메뉴바에 '<' 버튼이 생긴다.(두개는 항상 같이다닌다)
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        //메뉴바에 타이틀 지정
        actionBar.setTitle("Profile");

        phoneNum = (TextView) findViewById(R.id.add_phoneNum);
        Name = (TextView) findViewById(R.id.Contact_Name);
        email = (TextView) findViewById(R.id.Contact_email);
        pageView = (TextView) findViewById(R.id.PageView);
        profileImg = (ImageView) findViewById(R.id.Contact_Img);
        NextBtn = (ImageButton) findViewById(R.id.NextButton);
        BackBtn = (ImageButton) findViewById(R.id.BackButton);

        if (DummyData.getPage() == 0) {
            BackBtn.setVisibility(View.GONE);
        } else if (DummyData.getPage() == DummyData.dummyList.size() - 1) {
            NextBtn.setVisibility(View.GONE);
        }
        phoneNum.setText(DummyData.dummyList.get(DummyData.getPage()).getPhoneNum());
        Name.setText(DummyData.dummyList.get(DummyData.getPage()).getName());
        email.setText(DummyData.dummyList.get(DummyData.getPage()).getE_Mail());
        pageView.setText((DummyData.getPage() + 1) + "/" + DummyData.dummyList.size());

        BackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DummyData.PageDown();
                Intent intent = new Intent(ContactActivity.this, ContactActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.back_slideinanim, R.anim.back_slideoutanim);
                finish();
            }
        });

        NextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DummyData.PageUp();
                Intent intent = new Intent(ContactActivity.this, ContactActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.next_slideinanim, R.anim.next_slideoutanim);
                finish();
            }
        });


        //TODO: 이미지 로드 기능 추가
    }

    //액션바 아이템 선택시 각각 수행할 작업에 대한 함수.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //뒤로가기 버튼 선택시 현재 액티비티를 닫아줌으로써 스택에서 바로 앞 액티비티를 불러옴.
            case android.R.id.home:
                finish();
                return true;

            case R.id.CallProfile:
                // 사용자의 OS 버전이 마시멜로우 이상인지 체크한다.
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    /**
                     * 사용자 단말기의 권한 중 "전화걸기" 권한이 허용되어 있는지 확인한다.
                     * Android는 C언어 기반으로 만들어졌기 때문에 Boolean 타입보다 Int 타입을 사용한다. */
                    int permissionResult = checkSelfPermission(Manifest.permission.CALL_PHONE);
                    /**
                     * 패키지는 안드로이드 어플리케이션의 아이디이다.
                     * 현재 어플리케이션이 CALL_PHONE에 대해 거부되어있는지 확인한다. */
                    if (permissionResult == PackageManager.PERMISSION_DENIED) {
                        /**
                         * shouldShowRequestPermissionRationale()는
                         * 사용자가 CALL_PHONE 권한을 거부한 적이 있는지 확인한다.
                         * 거부한적이 있으면 True를 리턴하고 * 거부한적이 없으면 False를 리턴한다.
                         * 이는 한번 이상 거부하면 왜 필요한지 알리는 메시지를 띄우기 위한 알고리즘이나,
                         * 현재는 필요없어 참고만 하라고 주석처리해두었다. */
//                        if (shouldShowRequestPermissionRationale(Manifest.permission.CALL_PHONE)) {
//                            // (이미 한번 이상 권한요청을 거절했을 경우에 해당)
//                            AlertDialog.Builder dialog = new AlertDialog.Builder(DialActivity.this);
//                            dialog.setTitle("권한이 필요합니다.")
//                                    .setMessage("이 기능을 사용하기 위해서는 단말기의 \"전화걸기\" 권한이 필요합니다. 계속 하시겠습니까?")
//                                    .setPositiveButton("네", new DialogInterface.OnClickListener() {
//                                        @Override
//                                        public void onClick(DialogInterface dialog, int which) {
//                                            /**
//                                             * 새로운 인스턴스(onClickListener)를 생성했기 때문에
//                                             * 버전체크를 다시 해준다. */
//                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                                                // CALL_PHONE 권한을 Android OS에 요청한다.
//                                                requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 1000);
//                                            }
//                                        }
//                                    })
//                                    .setNegativeButton("아니요", new DialogInterface.OnClickListener() {
//                                        @Override
//                                        public void onClick(DialogInterface dialog, int which) {
//                                            Toast.makeText(DialActivity.this, "기능을 취소했습니다", Toast.LENGTH_SHORT).show();
//                                        }
//                                    })
//                                    .create()
//                                    .show();
//                        }
//                        else { // (최초로 권한요청하는 경우에 해당) CALL_PHONE 권한을 Android OS에 요청한다.
//                            requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 1000);
//                        }
                        requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 1000);


                    } // CALL_PHONE의 권한이 있을 때
                    else { // 즉시 실행
                        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNum.getText()));
                        startActivity(intent);
                    }
                } // 마시멜로우 미만의 버전일 때
                else {
                    // 즉시 실행
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNum.getText()));
                    startActivity(intent);
                }
                return true;

            case R.id.EditProfile:
                Intent intentEdit = new Intent(ContactActivity.this, EditActivity.class);
                startActivity(intentEdit);

            case R.id.MessageProfile:
                //TODO: Message 권한 부여 기능 추가

                return true;


            case R.id.DeleteProfile:
                DummyData.dummyList.remove(DummyData.getPage());
                if (DummyData.getPage() >= DummyData.dummyList.size() && DummyData.getPage() > 0) {
                    DummyData.PageDown();
                    Intent intentDel = new Intent(ContactActivity.this, ContactActivity.class);
                    startActivity(intentDel);
                    finish();
                } else if (DummyData.getPage() < DummyData.dummyList.size() && DummyData.getPage() >= 0) {
                    Intent intentDel = new Intent(ContactActivity.this, ContactActivity.class);
                    startActivity(intentDel);
                    finish();
                } else {
                    Toast.makeText(ContactActivity.this, "There is no Profile Left!", Toast.LENGTH_SHORT).show();
                    finish();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * requestPermission()함수 수행 이후 할 동작 지정.
     * requestPermission에서 ALLOW를 선택할 경우 onRequestPermissionsResult 함수를 호출한다.
     * intent에 들어온 빨간 줄은 무시해도 된다.
     * ACTION_CALL의 경우 퍼미션 체크를 해주라고 뜨는건데
     * 아래 함수에서 동작은 퍼미션 체크 이후 수행하는 것이기에 상관 없음.
     * 따라서 SuppressLint로 warning은 간단하게 무시해준다.
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            //전달된 requestCode가 1000일 경우(임의로 지정 가능)
            case 1000: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // 동의 및 로직 처리
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNum.getText()));
                    startActivity(intent);
                } else {
                    // 동의 안함
                    Toast.makeText(ContactActivity.this, "Need permission allowed to Call!", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }
}
