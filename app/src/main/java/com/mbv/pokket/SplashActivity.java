package com.mbv.pokket;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.mbv.pokket.dao.interfaces.ServerResponseListener;
import com.mbv.pokket.threads.AuthenticationTask;
import com.mbv.pokket.threads.SignUpTask;
import com.mbv.pokket.util.AppPreferences;

/**
 * Created by arindamnath on 26/12/15.
 */
public class SplashActivity extends Activity implements TextWatcher, ServerResponseListener, View.OnClickListener {

    private View promoView, signInView, signUpView, forgotPasswordView, signInFooterView, signUpFooterView;
    private Button splashSignIn, splashSignUp, signInButton, signUpButton,
            recoverPasswordButton, signUpFBButton;
    private EditText signInEmail, signInPassword;
    private EditText forgotPasswordEmail;
    private EditText signUpName, signUpEmail, signUpMobile, signUpPassword;
    private TextView forgotPassworButton, forgotPasswordBackButton, splashAppName;
    private LinearLayout container;
    private VideoView videoView;

    private AppPreferences appPreferences;

    /*private CallbackManager callbackManager;
    private LoginManager loginManager;
    private Profile profile;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.activity_open_translate, R.anim.activity_close_translate);
        //FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_splash);

        appPreferences = new AppPreferences(this);
        //callbackManager = CallbackManager.Factory.create();
        //loginManager = LoginManager.getInstance();

        forgotPasswordView = findViewById(R.id.forgot_password_holder);
        promoView = findViewById(R.id.splash_promo);
        signInView = findViewById(R.id.sign_in_holder);
        signUpView = findViewById(R.id.sign_up_holder);
        signInFooterView = findViewById(R.id.sign_in_footer_holder);
        signUpFooterView = findViewById(R.id.sign_up_footer_holder);
        videoView = (VideoView) findViewById(R.id.splash_video_container);
        container = (LinearLayout) findViewById(R.id.splash_content_holder);

        splashSignIn = (Button) promoView.findViewById(R.id.splash_sign_in_btn);
        splashSignUp = (Button) promoView.findViewById(R.id.splash_sign_up_btn);
        signInButton = (Button) signInView.findViewById(R.id.sign_in_button);
        signUpButton = (Button) signUpView.findViewById(R.id.sign_up_button);
        signUpFBButton = (Button) signUpView.findViewById(R.id.sign_up_facebook_login_button);

        splashAppName = (TextView) findViewById(R.id.splash_app_name);
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

        splashSignIn.setOnClickListener(this);
        splashSignUp.setOnClickListener(this);
        signInFooterView.setOnClickListener(this);
        signUpFooterView.setOnClickListener(this);
        forgotPassworButton.setOnClickListener(this);
        recoverPasswordButton.setOnClickListener(this);
        forgotPasswordBackButton.setOnClickListener(this);

        signInEmail.addTextChangedListener(this);
        signInPassword.addTextChangedListener(this);
        forgotPasswordEmail.addTextChangedListener(this);
        signUpName.addTextChangedListener(this);
        signUpEmail.addTextChangedListener(this);
        signUpMobile.addTextChangedListener(this);
        signUpPassword.addTextChangedListener(this);

        splashAppName.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Pacifico.ttf"));

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AuthenticationTask(1, v.getContext(), SplashActivity.this)
                        .execute(new String[]{signInEmail.getText().toString(),
                                signInPassword.getText().toString()});
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

        signUpFBButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //loginManager.logInWithReadPermissions(SplashActivity.this, Arrays.asList("public_profile", "user_friends", "email"));
            }
        });

        /*loginManager.registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        profile = Profile.getCurrentProfile();
                        signUpName.setText(profile.getFirstName() + " " + profile.getMiddleName() + " " + profile.getLastName());
                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                    }
                });*/

        new Handler().postDelayed(new Runnable() {
            public void run() {
                if (appPreferences.isUserLoggedIn()) {
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();
                } else {
                    promoView.setVisibility(View.VISIBLE);
                    container.setBackgroundColor(Color.parseColor("#80000000"));
                    videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.splash));
                    videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            mp.setLooping(true);
                        }
                    });
                    videoView.start();
                }
            }
        }, 2500);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(videoView.isPlaying()) {
            videoView.stopPlayback();
        }
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
                finish();
                break;
        }
    }

    @Override
    public void onFaliure() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.splash_sign_in_btn:
                promoView.setVisibility(View.GONE);
                signInView.setVisibility(View.VISIBLE);
                signUpFooterView.setVisibility(View.VISIBLE);
                signInFooterView.setVisibility(View.GONE);
                signUpView.setVisibility(View.GONE);
                forgotPasswordView.setVisibility(View.GONE);
                break;
            case R.id.splash_sign_up_btn:
                promoView.setVisibility(View.GONE);
                signUpView.setVisibility(View.VISIBLE);
                signInFooterView.setVisibility(View.VISIBLE);
                signInView.setVisibility(View.GONE);
                signUpFooterView.setVisibility(View.GONE);
                break;
            case R.id.sign_in_footer_holder:
                signInView.setVisibility(View.VISIBLE);
                signUpFooterView.setVisibility(View.VISIBLE);
                signInFooterView.setVisibility(View.GONE);
                signUpView.setVisibility(View.GONE);
                forgotPasswordView.setVisibility(View.GONE);
                break;
            case R.id.sign_up_footer_holder:
                signUpView.setVisibility(View.VISIBLE);
                signInFooterView.setVisibility(View.VISIBLE);
                signInView.setVisibility(View.GONE);
                signUpFooterView.setVisibility(View.GONE);
                break;
            case R.id.forgot_password_button:
                forgotPasswordView.setVisibility(View.VISIBLE);
                signInView.setVisibility(View.GONE);
                signUpFooterView.setVisibility(View.GONE);
                break;
            case R.id.recover_password_button:
                signInView.setVisibility(View.VISIBLE);
                signUpFooterView.setVisibility(View.VISIBLE);
                forgotPasswordView.setVisibility(View.GONE);
                break;
            case R.id.forgot_password_back_button:
                signInView.setVisibility(View.VISIBLE);
                signUpFooterView.setVisibility(View.VISIBLE);
                forgotPasswordView.setVisibility(View.GONE);
                break;
        }
    }
}
