package com.moyang.room.provider;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.moyang.room.R;

public class ContentProviderLearnActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider_learn);

        Button buttonCall = findViewById(R.id.btn_call);
        buttonCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 1. 检查用户权限
                if (ContextCompat.checkSelfPermission(ContentProviderLearnActivity.this, Manifest.permission.CALL_PHONE)
                        == PackageManager.PERMISSION_GRANTED) {
                    callPhone(Intent.ACTION_CALL);
                } else {
                    // 2. 请求权限
                    ActivityCompat.requestPermissions(ContentProviderLearnActivity.this,  new String[]{Manifest.permission.CALL_PHONE}, 0);
                }

            }
        });

        Button buttonDial = findViewById(R.id.btn_dial);
        buttonDial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPhone(Intent.ACTION_DIAL);
            }
        });
    }

    private void callPhone(String actionCall) {
        Intent intent = new Intent(actionCall);
        intent.setData(Uri.parse("tel:10086"));
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        // 3. 授权回调
        if (requestCode == 0 ) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                callPhone(Intent.ACTION_CALL);
            } else {
                Toast.makeText(this, "拒绝授权, 禁止打电话!", Toast.LENGTH_SHORT).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}