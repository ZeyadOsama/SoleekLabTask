package com.zeyad.soleeklabtask.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.zeyad.soleeklabtask.R;
import com.zeyad.soleeklabtask.utils.Validation;

import java.util.Arrays;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

import static com.zeyad.soleeklabtask.utils.LogMessages.AUTHENTICATION;
import static com.zeyad.soleeklabtask.utils.LogMessages.CANCELED;
import static com.zeyad.soleeklabtask.utils.LogMessages.CREDENTIAL_SIGN_IN;
import static com.zeyad.soleeklabtask.utils.LogMessages.FAILED;
import static com.zeyad.soleeklabtask.utils.LogMessages.LOGIN;
import static com.zeyad.soleeklabtask.utils.LogMessages.SUCCEEDED;

public class RegistrationActivity extends AppCompatActivity {

    /**
     * Class tag
     */
    public static final String TAG = RegistrationActivity.class.getSimpleName();

    /**
     * View binding
     */
    @BindView(R.id.activity_registration_et_email)
    TextInputEditText etEmail;
    @BindView(R.id.activity_registration_et_password)
    TextInputEditText etPassword;
    @BindView(R.id.activity_registration_et_confirm_password)
    TextInputEditText etPasswordConfirmation;
    @BindView(R.id.activity_registration_btn_create)
    Button btnCreateAccount;
    @BindView(R.id.activity_registration_tv_login)
    TextView tvLogin;
    @BindView(R.id.activity_registration_btn_sign_in_facebook)
    Button btnFacebook;
    @BindView(R.id.activity_registration_btn_sign_in_google)
    Button btnGoogle;

    public static final int RC_SIGN_IN = 1;

    private String email, password;
    private boolean isEmailValid, isPasswordValid, isPasswordMatch;

    private FirebaseAuth authentication;
    private CallbackManager callbackManager;
    private GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ButterKnife.bind(this);

        Objects.requireNonNull(getSupportActionBar()).hide();
        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        authentication = FirebaseAuth.getInstance();
        initGoogleAuthentication();
        initFacebookAuthentication();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = authentication.getCurrentUser();
        if (currentUser != null)
            startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                authenticate(account);
            } catch (ApiException e) {
                Log.w(TAG, e.getCause());
            }
        }
    }

    @OnTextChanged(R.id.activity_registration_et_email)
    public void validateEmail() {
        email = etEmail.getText().toString().trim();
        isEmailValid = Validation.isEmailValid(email);
        if (!isEmailValid)
            etEmail.setError(getString(R.string.warning_wrong_email_format));
    }

    @OnTextChanged(R.id.activity_registration_et_password)
    public void validatePassword() {
        password = etPassword.getText().toString();
        isPasswordValid = Validation.isPasswordValid(password);
        if (!isPasswordValid)
            etPassword.setError(getString(R.string.warning_wrong_password_format));
    }

    @OnTextChanged(R.id.activity_registration_et_confirm_password)
    public void validatePasswordConfirmation() {
        String passwordConfirmation = etPasswordConfirmation.getText().toString();
        isPasswordMatch = false;
        if (!password.equals(passwordConfirmation))
            etPasswordConfirmation.setError(getString(R.string.warning_password_does_not_match));
        else isPasswordMatch = true;
    }

    @OnClick(R.id.activity_registration_btn_create)
    public void createAccount() {
        if (isEmailValid && isPasswordValid && isPasswordMatch) {
            authentication.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "createUserWithEmail:success");
                            startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
                        } else {
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Snackbar.make(findViewById(R.id.activity_registration_btn_create), "Authentication failed.",
                                    Snackbar.LENGTH_LONG).show();
                        }
                    });
        } else
            Snackbar.make(findViewById(R.id.activity_registration_btn_create), "Please enter correct values!",
                    Snackbar.LENGTH_SHORT).show();

    }

    @OnClick(R.id.activity_registration_btn_sign_in_google)
    public void createAccountUsingGoogle() {
        startActivityForResult(mGoogleSignInClient.getSignInIntent(), RC_SIGN_IN);
    }

    private void authenticate(GoogleSignInAccount account) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        authentication.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Log.d(TAG, CREDENTIAL_SIGN_IN + SUCCEEDED);
                        startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
                    } else {
                        Log.w(TAG, CREDENTIAL_SIGN_IN + FAILED, task.getException());
                        Snackbar.make(findViewById(R.id.activity_registration_btn_sign_in_google),
                                AUTHENTICATION + FAILED, Snackbar.LENGTH_SHORT).show();
                    }
                });
    }

    private void initGoogleAuthentication() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.google_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    private void initFacebookAuthentication() {
        btnFacebook.setOnClickListener(view ->
                LoginManager.getInstance().
                        logInWithReadPermissions(RegistrationActivity.this, Arrays.asList("public_profile", "user_friends")));
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        accessTokenHandler(loginResult.getAccessToken());
                    }

                    @Override
                    public void onCancel() {
                        Snackbar.make(findViewById(R.id.activity_registration_btn_sign_in_facebook),
                                LOGIN + CANCELED, Snackbar.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        Snackbar.make(findViewById(R.id.activity_registration_btn_sign_in_facebook),
                                exception.getMessage(), Snackbar.LENGTH_LONG).show();
                    }
                });
    }

    private void accessTokenHandler(AccessToken token) {
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        authentication.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Log.d(TAG, CREDENTIAL_SIGN_IN + SUCCEEDED);
                        RegistrationActivity.this.startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
                    } else {
                        Log.w(TAG, CREDENTIAL_SIGN_IN + FAILED, task.getException());
                        Snackbar.make(findViewById(R.id.activity_registration_btn_sign_in_facebook),
                                AUTHENTICATION + FAILED, Snackbar.LENGTH_LONG).show();
                    }
                });
    }

    /**
     * starts {@link LoginActivity}
     */
    @OnClick(R.id.activity_registration_tv_login)
    public void startLoginActivity() {
        startActivity(new Intent(this, LoginActivity.class));
    }
}
