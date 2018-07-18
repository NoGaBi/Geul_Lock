package com.example.jho63.esc_sample;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DialActivity extends AppCompatActivity {

    TextView enterdNum;
    Button btn[];
    Button btn_Del, btn_Call, btn_Add, btn_Contact, btn_Sharp, btn_Plus, btn_Star;

    /**
     * 배열로 버튼을 이어주기 위해 필요한, string 으로 입력된 리소스아이디명을 id값으로 바꿔주는 함수.
     * 궁금하시면 보시고, 안보셔도 무난합니다.
     */
    public int getResourceID(final String resName, final String resType, final Context ctx) {
        final int ResourceID =
                ctx.getResources().getIdentifier(resName, resType, ctx.getApplicationInfo().packageName);
        if (ResourceID == 0) {
            throw new IllegalArgumentException("No resource string found with name " + resName);
        } else {
            return ResourceID;
        }
    }

    /**
     * 01098908717 과 같이 입력되는 형태를 010-9890-8717 로 입력되게 해주는 함수.
     * +, #, * 이 들어가는 경우 - 없애주는 기능 또한 함.
     * 간단하면서도 간단하지 않은 방법으로 구현했으니 한번 봐보시면 좋습니다.
     * 참고) setText(문자열 값), getText(문자열 값) 함수는 TextView 객체 내부에 정의되어있는 텍스트 설정, 텍스트 가져오기 함수입니다.
     */
    public String changeToDialFormat(String rawStr) {

        while (rawStr.contains("-")) {
            rawStr = rawStr.substring(0, rawStr.indexOf("-")) + rawStr.substring(rawStr.indexOf("-") + 1);
        }

        if (!(rawStr.contains("*") || rawStr.contains("#") || rawStr.contains("+") || rawStr.length() > 11)) {
            if (rawStr.length() >= 4 && rawStr.charAt(3) != '-') {
                rawStr = rawStr.substring(0, 3) + "-" + rawStr.substring(3);
            }
            if (rawStr.length() >= 9 && rawStr.charAt(8) != '-') {
                rawStr = rawStr.substring(0, 8) + "-" + rawStr.substring(8);
            }
        }

        return rawStr;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dial);

        btn = new Button[10];
        for (int i = 0; i < 10; i++) {
            btn[i] = (Button) findViewById(getResourceID("btn" + i, "id", this));
        }
        btn_Del = (Button) findViewById(R.id.btnDel);
        btn_Call = (Button) findViewById(R.id.btnCall);
        btn_Add = (Button) findViewById(R.id.btnAdd);
        btn_Contact = (Button) findViewById(R.id.btnContact);
        btn_Sharp = (Button) findViewById(R.id.btnSharp);
        btn_Plus = (Button) findViewById(R.id.btnPlus);
        btn_Star = (Button) findViewById(R.id.btnStar);

        enterdNum = (TextView) findViewById(R.id.EnteredNum);
        enterdNum.setText("");

        for (int i = 0; i < 10; i++) {
            final int finalI = i;
            btn[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    enterdNum.setText(changeToDialFormat(enterdNum.getText().toString() + finalI));
                }
            });
        }

        btn_Plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterdNum.setText(changeToDialFormat(enterdNum.getText().toString() + "+"));
            }
        });

        btn_Sharp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterdNum.setText(changeToDialFormat(enterdNum.getText().toString() + "#"));
            }
        });

        btn_Star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterdNum.setText(changeToDialFormat(enterdNum.getText().toString() + "*"));
            }
        });

        btn_Del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String prevText = enterdNum.getText().toString();
                if (prevText.length() > 0) {
                    prevText = prevText.substring(0, prevText.length() - 1);

                    if (prevText.length() >= 1) {
                        if (prevText.substring(prevText.length() - 1).equals("-")) {
                            prevText = prevText.substring(0, prevText.length() - 1);
                        }
                    }
                } else {
                    /**더이상 지울 게 없을 경우 알림을 띄웁니다.*/
                    Toast.makeText(DialActivity.this, "There is no Element to Delete!", Toast.LENGTH_SHORT).show();
                }
                enterdNum.setText(changeToDialFormat(prevText));
            }
        });

        btn_Call.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View v) {
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
                        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + enterdNum.getText()));
                        startActivity(intent);
                    }
                } // 마시멜로우 미만의 버전일 때
                else {
                    // 즉시 실행
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + enterdNum.getText()));
                    startActivity(intent);
                }
            }
        });

        btn_Contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btn_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    /**
     * requestPermission()함수 수행 이후 할 동작 지정.
     * requestPermission에서 ALLOW를 선택할 경우 onRequestPermissionsResult 함수를 호출한다.
     * intent에 들어온 빨간 줄은 무시해도 된다.
     * ACTION_CALL의 경우 퍼미션 체크를 해주라고 뜨는건데
     * 아래 함수에서 동작은 퍼미션 체크 이후 수행하는 것이기에 상관 없음.
     * 따라서 SuppressLint로 warning은 간단하게 무시해준다.
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1000: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // 동의 및 로직 처리
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + enterdNum.getText()));
                    startActivity(intent);
                } else {
                    // 동의 안함
                    Toast.makeText(DialActivity.this, "Need permission allowed to Call!", Toast.LENGTH_SHORT).show();
                }
                return;
            }
            // 예외 케이스
        }
    }

}
