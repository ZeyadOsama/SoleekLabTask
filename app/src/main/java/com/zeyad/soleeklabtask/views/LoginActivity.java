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

import static com.zeyad.soleeklabtask.utils.LogMessages.AUTHENTICATION;
import static com.zeyad.soleeklabtask.utils.LogMessages.CREDENTIAL_SIGN_IN;
import static com.zeyad.soleeklabtask.utils.LogMessages.FAILED;
import static com.zeyad.soleeklabtask.utils.LogMessages.SUCCEEDED;

public class LoginActivity extends AppCompatActivity {

    /**
     * Class tag
     */
    private static final String TAG = LoginActivity.class.getSimpleName();

    /**
     * View binding
     */
    @BindView(R.id.activity_login_et_email)
    TextInputEditText etEmail;
    @BindView(R.id.activity_login_et_password)
    TextInputEditText etPassword;

    private boolean isEmailValid, isPasswordValid;
    private String email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        Objects.requireNonNull(getSupportActionBar()).hide();
        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }

    @OnTextChanged(R.id.activity_login_et_email)
    public void validateEmail() {
        email = etEmail.getText().toString().trim();
        isEmailValid = Validation.isEmailValid(email);
        if (!isEmailValid)
            etEmail.setError(getString(R.string.warning_wrong_email_format));
    }

    @OnTextChanged(R.id.activity_login_et_password)
    public void validatePassword() {
        password = etPassword.getText().toString();
        isPasswordValid = Validation.isPasswordValid(password);
        if (!isPasswordValid)
            etPassword.setError(getString(R.string.warning_wrong_password_format));
    }

    /**
     * validates input first then creates a Firebase user
     */
    @OnClick(R.id.activity_login_btn_login)
    public void login() {
        FirebaseAuth authentication = FirebaseAuth.getInstance();
        if (isEmailValid && isPasswordValid) {
            authentication.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Log.d(TAG, CREDENTIAL_SIGN_IN + SUCCEEDED);
                            LoginActivity.this.startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        } else {
                            Log.w(TAG, CREDENTIAL_SIGN_IN + FAILED, task.getException());
                            Snackbar.make(LoginActivity.this.findViewById(R.id.activity_login_btn_login),
                                    AUTHENTICATION + FAILED, Snackbar.LENGTH_LONG).show();
                        }
                    });
        } else
            Snackbar.make(findViewById(R.id.activity_login_btn_login), getString(R.string.warning_enter_correct_credentials),
                    Snackbar.LENGTH_LONG).show();
    }

    /**
     * starts {@link RegistrationActivity}
     */
    @OnClick(R.id.activity_login_tv_register)
    public void startsRegistrationActivity() {
        startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
    }
}
