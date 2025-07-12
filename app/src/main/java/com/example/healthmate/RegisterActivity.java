package com.example.healthmate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RegisterActivity extends AppCompatActivity {

EditText userName,email,passWord,confirmPass;
Button button;
TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        userName = findViewById(R.id.regUsername);
        email = findViewById(R.id.regemail);
        passWord = findViewById(R.id.regPassword);
        confirmPass = findViewById(R.id.regconfirmPass);
        button = findViewById(R.id.regButton);
        textView = findViewById(R.id.loginText);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = userName.getText().toString();
                String Email = email.getText().toString();
                String password = passWord.getText().toString();
                String ConfirmPass = confirmPass.getText().toString();

                Database db = new Database(getApplicationContext(),"healthmate", null,1);

                if(username.length()==0 || Email.length()==0 || password.length()==0 || ConfirmPass.length()==0){
                    Toast.makeText(getApplicationContext(), "Please fill all the details", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(password.compareTo(ConfirmPass)==0){
                        if(isValid(password)){
                            db.register(username,Email,password);

                            Toast.makeText(getApplicationContext(),"Record Inserted Successfully!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                        }
                        else{
                                Toast.makeText(getApplicationContext(),"The password must contain at least 8 characters, including a mix of letters, numbers, and special characters.", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(),"Password and Confirm Password did not match", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public static boolean isValid(String passwordhere) {
        int f1 = 0,f2 = 0,f3 = 0;
        if(passwordhere.length()<=8){
            return false;
        }
        else {
            for(int p = 0; p < passwordhere.length();p++){
                if (Character.isLetter(passwordhere.charAt(p))){
                    f1 = 1;
                }
            }
            for(int r = 0; r < passwordhere.length(); r++ ){
                if (Character.isDigit(passwordhere.charAt(r))){
                    f2=1;
                }
            }
            for(int s = 0;s < passwordhere.length();s++){
                char c = passwordhere.charAt(s);
                if(c>=33&&c<=46||c==64){
                    f3=1;
                }
            }
            if(f1==1 && f2==1 && f3==1)
                return true;
            return false;
        }
    }
}