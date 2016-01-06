package com.maybrightventures.lender;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.maybrightventures.lender.dao.interfaces.ServerResponseListener;
import com.maybrightventures.lender.threads.AuthenticationTask;
import com.maybrightventures.lender.threads.SignUpTask;
import com.maybrightventures.lender.util.AppPreferences;

/**
 * Created by arindamnath on 26/12/15.
 */
public class SplashActivity extends Activity implements TextWatcher, ServerResponseListener {

    private View promoView, signInView, signUpView, forgotPasswordView, signInFooterView, signUpFooterView;
    private Button splashSignIn, splashSignUp, signInButton, signUpButton,
            recoverPasswordButton;
    private EditText signInEmail, signInPassword;
    private EditText forgotPasswordEmail;
    private EditText signUpName, signUpEmail, signUpMobile, signUpPassword;
    private TextView forgotPassworButton, forgotPasswordBackButton;

    private AppPreferences appPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        appPreferences = new AppPreferences(this);

        forgotPasswordView = findViewById(R.id.forgot_password_holder);
        promoView = findViewById(R.id.splash_promo);
        signInView = findViewById(R.id.sign_in_holder);
        signUpView = findViewById(R.id.sign_up_holder);
        signInFooterView = findViewById(R.id.sign_in_footer_holder);
        signUpFooterView = findViewById(R.id.sign_up_footer_holder);

        splashSignIn = (Button) promoView.findViewById(R.id.splash_sign_in_btn);
        splashSignUp = (Button) promoView.findViewById(R.id.splash_sign_up_btn);
        signInButton = (Button) signInView.findViewById(R.id.sign_in_button);
        signUpButton = (Button) signUpView.findViewById(R.id.sign_up_button);
        forgotPassworButton = (TextView) signInView.findViewById(R.id.forgot_password_button);
        recoverPasswordButton = (Button) forgotPasswordView.findViewById(R.id.recover_password_button);
        forgotPasswordBackButton = (TextView) forgotPasswordView.findViewById(R.id.forgot_password_back_button);

        signInEmail = (EditText) signInView.findViewById(R.id.sign_in_email);
        signInPassword = (EditText) signInView.findViewById(R.id.sign_in_password);
        forgotPasswordEmail = (EditText) forgotPasswordView.findViewById(R.id.forgot_password_email);
        signUpName = (EditText) signUpView.findViewById(R.id.sign_up_name);
        signUpEmail = (EditText) signUpView.findViewById(R.id.sign_up_email);
        signUpMobile = (EditText) signUpView.findViewById(R.id.sign_up_phone);
        signUpPassword = (EditText) signUpView.findViewById(R.id.sign_up_password);

        signInEmail.addTextChangedListener(this);
        signInPassword.addTextChangedListener(this);
        forgotPasswordEmail.addTextChangedListener(this);
        signUpName.addTextChangedListener(this);
        signUpEmail.addTextChangedListener(this);
        signUpMobile.addTextChangedListener(this);
        signUpPassword.addTextChangedListener(this);

        splashSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                promoView.setVisibility(View.GONE);
                signInView.setVisibility(View.VISIBLE);
                signUpFooterView.setVisibility(View.VISIBLE);
                signInFooterView.setVisibility(View.GONE);
                signUpView.setVisibility(View.GONE);
                forgotPasswordView.setVisibility(View.GONE);
            }
        });

        splashSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                promoView.setVisibility(View.GONE);
                signUpView.setVisibility(View.VISIBLE);
                signInFooterView.setVisibility(View.VISIBLE);
                signInView.setVisibility(View.GONE);
                signUpFooterView.setVisibility(View.GONE);
            }
        });

        signInFooterView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInView.setVisibility(View.VISIBLE);
                signUpFooterView.setVisibility(View.VISIBLE);
                signInFooterView.setVisibility(View.GONE);
                signUpView.setVisibility(View.GONE);
                forgotPasswordView.setVisibility(View.GONE);
            }
        });

        signUpFooterView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUpView.setVisibility(View.VISIBLE);
                signInFooterView.setVisibility(View.VISIBLE);
                signInView.setVisibility(View.GONE);
                signUpFooterView.setVisibility(View.GONE);
            }
        });

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AuthenticationTask(1, v.getContext(), SplashActivity.this)
                        .execute(new String[] {signInEmail.getText().toString(),
                                signInPassword.getText().toString()});
            }
        });

        forgotPassworButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forgotPasswordView.setVisibility(View.VISIBLE);
                signInView.setVisibility(View.GONE);
                signUpFooterView.setVisibility(View.GONE);
            }
        });

        recoverPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInView.setVisibility(View.VISIBLE);
                signUpFooterView.setVisibility(View.VISIBLE);
                forgotPasswordView.setVisibility(View.GONE);
            }
        });

        forgotPasswordBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInView.setVisibility(View.VISIBLE);
                signUpFooterView.setVisibility(View.VISIBLE);
                forgotPasswordView.setVisibility(View.GONE);
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SignUpTask(2, v.getContext(), SplashActivity.this)
                        .execute(new String[]{signUpName.getText().toString(),
                                signUpEmail.getText().toString(),
                                signUpMobile.getText().toString(),
                                signUpPassword.getText().toString()});
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        new Handler().postDelayed(new Runnable() {
            public void run() {
                if(appPreferences.isUserLoggedIn()) {
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    finish();
                } else {
                    promoView.setVisibility(View.VISIBLE);
                }
            }
        }, 2500);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if(signInEmail.getText().toString().length() > 0 &&
                signInPassword.getText().toString().length() > 0) {
            signInButton.setEnabled(true);
        } else {
            signInButton.setEnabled(false);
        }

        if(forgotPasswordEmail.getText().toString().length() > 0) {
            forgotPassworButton.setEnabled(true);
        } else {
            forgotPassworButton.setEnabled(false);
        }

        if(signUpName.getText().toString().length() > 0 &&
                signUpEmail.getText().toString().length() > 0 &&
                signUpMobile.getText().toString().length() > 0 &&
                signUpPassword.getText().toString().length() > 0) {
            signUpButton.setEnabled(true);
        } else {
            signUpButton.setEnabled(false);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onSuccess(int threadId) {
        switch (threadId) {
            case 1:
                appPreferences.setLoggedIn();
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
                break;
            case 2:
                startActivity(new Intent(SplashActivity.this, MobileOTPActivity.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
                break;
        }
    }

    @Override
    public void onFaliure() {

    }
}
