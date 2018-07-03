package com.example.jho63.hongstudy01;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class EditActivity extends AppCompatActivity {

    EditText name, phone, eMail;
    ImageButton editBtn;
    ImageButton profileImg;
    int index;
    String menu;
    Person p;
    DBManager dbManager;

    private final int READ_STORAGE_REQ_CODE = 111;
    private final int REQ_CODE_SELECT_IMAGE = 222;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        name = findViewById(R.id.edit_name);
        phone = findViewById(R.id.edit_phone);
        eMail = findViewById(R.id.edit_email);
        editBtn = findViewById(R.id.edit_editBtn);
        profileImg = findViewById(R.id.setProfileBtn);

        menu = getIntent().getStringExtra("menu");

        dbManager = new DBManager(this);


        if (menu.equals("edit")) {
            index = getIntent().getIntExtra("cid", -1);
            p = dbManager.getData(index);
            name.setText(p.getName());
            phone.setText(p.getPhoneNum());
            eMail.setText(p.geteMail());
            if (p.getProfileImg() != null)
                Glide.with(this).load(p.getProfileImg()).into(profileImg);
        }else if(menu.equals("add")) {
            p = new Person("","","");
            if (p.getProfileImg() != null)
                Glide.with(this).load(p.getProfileImg()).into(profileImg);
        }

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name_data = name.getText().toString();
                String phone_data = phone.getText().toString();
                String email_data = eMail.getText().toString();
                p = new Person(name_data, phone_data, email_data);

                if (menu.equals("add")) {
                    if (name.getText().toString().isEmpty() || phone.getText().toString().isEmpty())
                        Toast.makeText(EditActivity.this, "빈 텍스트 입력 불가", Toast.LENGTH_SHORT).show();
                    else if(!eMail.getText().toString().contains("@") && !eMail.getText().toString().contains("."))
                        Toast.makeText(EditActivity.this, "이메일 형식으로 입력해주세요", Toast.LENGTH_SHORT).show();
                    else {
                        dbManager.insertData(p);
                        Intent a = new Intent(EditActivity.this, MainActivity.class);
                        setResult(Activity.RESULT_OK, a);
                        startActivity(a);
                    }
                }
                if (menu.equals("edit")) {
                    if (name.getText().toString().isEmpty() || phone.getText().toString().isEmpty())
                        Toast.makeText(EditActivity.this, "빈 텍스트 입력 불가", Toast.LENGTH_SHORT).show();
                    else if(!eMail.getText().toString().contains("@") && !eMail.getText().toString().contains("."))
                        Toast.makeText(EditActivity.this, "이메일 형식으로 입력해주세요", Toast.LENGTH_SHORT).show();
                    else {
                        dbManager.updateData(p);
                        finish();
                    }
                }
            }
        });

        profileImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                        && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(EditActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, READ_STORAGE_REQ_CODE);
                } else {
                    sendImageReq();
                }
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == READ_STORAGE_REQ_CODE) {
            if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setCancelable(false);
                dialog.setMessage("앨범에 ACCESS 할 수 없습니다.");

                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            } else {
                sendImageReq();
            }
        }
    }

    private void sendImageReq() {
        Intent imageIntent = new Intent(Intent.ACTION_PICK);

        //imageIntent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        imageIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(imageIntent, REQ_CODE_SELECT_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQ_CODE_SELECT_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                index = getIntent().getIntExtra("position", 0);//key값, 없을 때 반환할 값

                Glide.with(this).load(PathManager.getPath(this, data.getData())).into(profileImg);
                p.setProfileImg(PathManager.getPath(this, data.getData()));
            }
        }
    }
}
