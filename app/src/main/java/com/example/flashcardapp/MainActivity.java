package com.example.flashcardapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    // username is admin & the password is admin

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button btnToSubmit = findViewById(R.id.btnToSubmit);
        final EditText usernameInputText = findViewById(R.id.usernameInputText);
        final EditText passwordInputText = findViewById(R.id.passwordInputText);

        // Check if username and password match the hardcoded username and password given above

        btnToSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (usernameInputText.getText().toString().equals("admin") && passwordInputText.getText().toString().equals("admin")) {
                    Intent flashcardScreen = new Intent(MainActivity.this, FlashCardScreen.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("name", usernameInputText.getText().toString());
                    flashcardScreen.putExtras(bundle);
                    startActivity(flashcardScreen);
                } else {
                    passwordInputText.setError("Username or Password incorrect");
                }
            }
        });
    }
}
