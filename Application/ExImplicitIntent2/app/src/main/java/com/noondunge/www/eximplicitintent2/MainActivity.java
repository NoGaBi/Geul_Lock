package com.noondunge.www.eximplicitintent2;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button callBtn, searchBtn;
    EditText callText, searchText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        callBtn = (Button)findViewById(R.id.call_button);
        callText = (EditText) findViewById(R.id.call_text);
        searchBtn = (Button) findViewById(R.id.search_button);
        searchText = (EditText)findViewById(R.id.search_text);

        callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String callInput = callText.getText().toString();
                Snackbar.make(v, R.string.call_snack, Snackbar.LENGTH_SHORT)
                        .setAction(R.string.ok_button, new View.OnClickListener() {
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
                                        if (permissionResult == PackageManager.PERMISSION_GRANTED) {
                                            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + callInput));
                                            startActivity(intent);
                                        }

                                    } // CALL_PHONE의 권한이 있을 때
                                    else { // 즉시 실행
                                        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + callInput));
                                        startActivity(intent);
                                    }
                                } // 마시멜로우 미만의 버전일 때
                                else {
                                    // 즉시 실행
                                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + callInput));
                                    startActivity(intent);
                                }
                            }
                        }).show();
            }
        });

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String searchInput = searchText.getText().toString();
                Snackbar.make(v, R.string.search_snack, Snackbar.LENGTH_SHORT)
                        .setAction(R.string.ok_button, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(Intent.ACTION_VIEW,
                                        Uri.parse("https://www.google.co.kr/search?q="+searchInput+"&oq="+searchInput+"&ie=UTF-8"));
                                startActivity(intent);
                            }
                        }).show();
            }
        });
    }
}
