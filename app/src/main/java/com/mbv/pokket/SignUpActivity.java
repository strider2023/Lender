package com.mbv.pokket;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.mbv.pokket.util.AppPreferences;

import java.util.Calendar;

public class SignUpActivity extends AppCompatActivity {

    private static TextView dobButton;
    private Button profileCompleteBtn;
    private SelectDateFragment selectDateFragment;
    private EditText name;
    private AppPreferences appPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.activity_open_translate, R.anim.activity_close_translate);
        setContentView(R.layout.activity_sign_up);

        selectDateFragment = new SelectDateFragment();
        appPreferences = new AppPreferences(this);

        name = (EditText) findViewById(R.id.sign_up_confirm_name);
        profileCompleteBtn = (Button) findViewById(R.id.sign_up_complete_btn);
        dobButton = (TextView) findViewById(R.id.sign_up_dob_btn);

        name.setText(((appPreferences.getUserFirstName() != null) ? appPreferences.getUserFirstName() + " " : "") +
                ((appPreferences.getUserMiddleName() != null) ? appPreferences.getUserMiddleName() + " " : "") +
                ((appPreferences.getUserLastName() != null) ? appPreferences.getUserLastName() : ""));

        dobButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectDateFragment.show(getSupportFragmentManager(), "dialog");
            }
        });

        profileCompleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appPreferences.setLoggedIn();
                startActivity(new Intent(SignUpActivity.this, AppIntroActivity.class));
                finish();
            }
        });
    }

    public static class SelectDateFragment  extends DialogFragment implements
            DatePickerDialog.OnDateSetListener {

        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar calendar = Calendar.getInstance();
            int yy = calendar.get(Calendar.YEAR);
            int mm = calendar.get(Calendar.MONTH);
            int dd = calendar.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(), this, yy, mm, dd);
        }

        @Override
        public void onDateSet(DatePicker view, int yy, int mm, int dd) {
            dobButton.setText(dd + " - " + (mm+1) + " - " + yy);
        }
    }
}
