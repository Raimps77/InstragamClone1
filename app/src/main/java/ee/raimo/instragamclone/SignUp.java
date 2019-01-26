package ee.raimo.instragamclone;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import static ee.raimo.instragamclone.R.id;
import static ee.raimo.instragamclone.R.id.btnLogIn;
import static ee.raimo.instragamclone.R.layout;

public class SignUp extends AppCompatActivity implements View.OnClickListener {


    private EditText edtEmail, edtUsername, edtPassword;
    private Button btnSignUp, btnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_sign_up);

        setTitle("Registreeri");

        edtEmail = findViewById(id.edtEnterEmail);
        edtUsername = findViewById(id.edtUsername);
        edtPassword = findViewById(id.edtEnterPassword);

        edtPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent event) {

                if (keyCode == KeyEvent.KEYCODE_ENTER &&
                        event.getAction() == KeyEvent.ACTION_DOWN) {

                onClick(btnSignUp);

                }
                return false;
            }
        });

        btnSignUp = findViewById(id.btnSignUp);
        btnLogin = findViewById(btnLogIn);

        btnSignUp.setOnClickListener(this);
        btnLogin.setOnClickListener(this);

        if (ParseUser.getCurrentUser() != null) {
        // ParseUser.getCurrentUser().logOut();
            transitionToSocialMediaActivity();
    }

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case id.btnSignUp:


                if (edtEmail.getText().toString().equals("") ||
                        edtUsername.getText().toString().equals("") ||
                        edtPassword.getText().toString().equals("")) {

                    FancyToast.makeText(SignUp.this,
                            "Email, kasutajanimi, parool on vajalik!",
                            Toast.LENGTH_LONG, FancyToast.INFO,
                            true).show();

                } else {

                final ParseUser appUser = new ParseUser();
                appUser.setEmail(edtEmail.getText().toString());
                appUser.setUsername(edtUsername.getText().toString());
                appUser.setPassword(edtPassword.getText().toString());

                final ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.setMessage("Olen regamas " + edtUsername.getText().toString());
                progressDialog.show();

                appUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            FancyToast.makeText(SignUp.this,
                            appUser.getUsername() + " on reganud ",
                            Toast.LENGTH_LONG, FancyToast.SUCCESS,
                            true).show();

                            transitionToSocialMediaActivity();


                        } else {
                            FancyToast.makeText(SignUp.this,
                                    appUser.getUsername() + " siin on viga ",
                                    Toast.LENGTH_LONG, FancyToast.ERROR,
                                    true).show();


                        }

                        progressDialog.dismiss();

                    }
                });
        }


                break;
            case R.id.btnLogIn:

                Intent intent = new Intent(SignUp.this, LoginActivity.class);
                startActivity(intent);

                break;
        }
        }

        public void rootLayoutTapped(View view) {


        try {

            InputMethodManager inputMethodManager =
                    (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

        } catch (Exception e) {

            e.printStackTrace();
        }

        }


        private void transitionToSocialMediaActivity () {

            Intent intent  = new Intent (SignUp.this, SocialMediaActivity.class);
            startActivity(intent);
        }
};
