package com.example.registro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    EditText username, password, repassword, usgender,usmail;
    Button singup, singin;
    DBHelper DB;

    protected  void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText) findViewById(R.id.username);
        usgender = (EditText) findViewById(R.id.usergender);
        usmail = (EditText) findViewById(R.id.useremail);
        password = (EditText) findViewById(R.id.password);
        repassword = (EditText) findViewById(R.id.repassword);


        singin = (Button) findViewById(R.id.btnsingin);
        singup = (Button) findViewById(R.id.btnsingup);

        DB = new DBHelper(this);

        singup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user = username.getText().toString();
                String gen = usgender.getText().toString();
                String mail = usmail.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();


                if(user.equals("")||gen.equals("")||mail.equals("")||pass.equals("")||repass.equals("")){

                    Toast.makeText(MainActivity.this, "Por favor, introdusca todos los campos", Toast.LENGTH_SHORT).show();

                }else{

                    if(pass.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9]).{8,}$")){
                        if(pass.length()>=8){
                            if(pass.equals(repass)){

                                Boolean checkuser = DB.checkusername(user);

                                if (checkuser==false){

                                    if(mail.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")){
                                        Boolean insert = DB.inserData(user, pass, gen, mail);
                                        if (insert==true){

                                            Toast.makeText(MainActivity.this, "Registro correcto", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                                            startActivity(intent);

                                        }else {
                                            Toast.makeText(MainActivity.this, "Registro fallido", Toast.LENGTH_SHORT).show();
                                        }
                                    }else{
                                        Toast.makeText(MainActivity.this, "Formato de correo no valido", Toast.LENGTH_SHORT).show();
                                    }


                                }else{

                                    Toast.makeText(MainActivity.this, "El usuario ya existe", Toast.LENGTH_SHORT).show();

                                }
                            }
                            else {

                                Toast.makeText(MainActivity.this, "La contraseña no coincide", Toast.LENGTH_SHORT).show();

                            }
                        }else{
                            Toast.makeText(MainActivity.this, "La contraseña debe tener mas de 8 caracteres", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(MainActivity.this, "La contraseña debe tener al menos una mayúscula, una minúscula y un número", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        singin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);

            }
        });

    }

}