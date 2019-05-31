package com.zeyad.soleeklabtask.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.zeyad.soleeklabtask.R;
import com.zeyad.soleeklabtask.utils.Validation;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class LoginActivity extends AppCompatActivity {

    /**
     * Class tag
     */
    private static final String TAG = LoginActivity.class.getSimpleName();

    /**
     * Static binding
     */
    @BindView(R.id.activity_login_et_email)
    TextInputEditText etEmail;
    @BindView(R.id.activity_login_et_password)
    TextInputEditText etPassword;

    private boolean isEmailValid, isPasswordValid;
    private String userEmail, userPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        Objects.requireNonNull(getSupportActionBar()).hide();
        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }

    @OnTextChanged({R.id.activity_login_et_email, R.id.activity_login_et_password})
    public void checkEmailAndPasswordValidity() {
        userEmail = etEmail.getText().toString().trim();
        userPassword = etPassword.getText().toString();

        isEmailValid = Validation.isEmailValid(userEmail);
        isPasswordValid = Validation.isPasswordValid(userPassword);

        if (!isEmailValid)
            etEmail.setError(getString(R.string.warning_wrong_email_format));
        if (!isPasswordValid)
            etPassword.setError(getString(R.string.warning_wrong_password_format));
    }

    @OnClick(R.id.activity_login_btn_login)
    public void loginToAccount() {
        FirebaseAuth authentication = FirebaseAuth.getInstance();
        if (isEmailValid && isPasswordValid) {
            authentication.signInWithEmailAndPassword(userEmail, userPassword)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "signInWithEmail:success");
                            LoginActivity.this.startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        } else {
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Snackbar.make(LoginActivity.this.findViewById(R.id.activity_login_btn_login), "Authentication failed.",
                                    Snackbar.LENGTH_LONG).show();
                        }
                    });
        } else
            Snackbar.make(findViewById(R.id.activity_login_btn_login), getString(R.string.warning_enter_correct_credentials),
                    Snackbar.LENGTH_LONG).show();
    }

    @OnClick(R.id.activity_login_tv_register)
    public void goToRegistrationActivity() {
        startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
    }
}
