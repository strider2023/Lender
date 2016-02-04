package com.maybrightventures.lender;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by arindamnath on 04/01/16.
 */
public class MobileOTPActivity extends AppCompatActivity implements TextWatcher {

    private Button verfiyOTP;
    private TextView regenerateOTP;
    private EditText otpCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.activity_open_translate, R.anim.activity_close_translate);
        setContentView(R.layout.activity_mobile_otp);

        verfiyOTP = (Button) findViewById(R.id.otp_verfiy_button);
        regenerateOTP = (TextView) findViewById(R.id.otp_regenerate_button);
        otpCode = (EditText) findViewById(R.id.otp_code_edittext);

        otpCode.addTextChangedListener(this);

        verfiyOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MobileOTPActivity.this, SignUpActivity.class));
                finish();
            }
        });

        regenerateOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if(otpCode.getText().toString().trim().length() > 0) {
            verfiyOTP.setEnabled(true);
        } else {
            verfiyOTP.setEnabled(false);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
