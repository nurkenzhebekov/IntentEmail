package com.example.intentemail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText email;
    private EditText theme;
    private EditText text;
    private Button sendBT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.emailEDTV);
        theme = findViewById(R.id.themeEDTV);
        text = findViewById(R.id.textEDTV);

        sendBT = findViewById(R.id.sendBT);

        sendBT.setOnClickListener(v -> {
            if (!email.getText().toString().isEmpty() && !theme.getText().toString().isEmpty()
                    && !text.getText().toString().isEmpty()) {

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{email.getText().toString()});
                intent.putExtra(Intent.EXTRA_SUBJECT, theme.getText().toString());
                intent.putExtra(Intent.EXTRA_TEXT, text.getText().toString());
                intent.setType("message/rfc822");
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this,
                            "There is no application that support this action",
                            Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(MainActivity.this, "Please fill all the fields",
                        Toast.LENGTH_SHORT).show();
            }
        });

    }
}