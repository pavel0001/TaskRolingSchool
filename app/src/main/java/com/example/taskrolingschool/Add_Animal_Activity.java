package com.example.taskrolingschool;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static java.lang.String.valueOf;

public class Add_Animal_Activity extends AppCompatActivity implements View.OnClickListener {
    EditText edtTV_Name, edtTV_Age, edtTV_Breed;
    Button btnAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__animal_);

        edtTV_Age = (EditText) findViewById(R.id.edtTV_Age);
        edtTV_Breed = (EditText) findViewById(R.id.edtTV_Breed);
        edtTV_Name = (EditText) findViewById(R.id.edtTV_Name);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
        case R.id.btnAdd:
            if(!edtTV_Name.getText().toString().equals("")&&!edtTV_Breed.getText().toString().equals("")&&!edtTV_Age.getText().toString().equals("")){
                if(isNumeric(edtTV_Age.getText().toString())) {
                    String name, breed;
                    Integer age;
                    name = edtTV_Name.getText().toString();
                    age = Integer.parseInt(edtTV_Age.getText().toString());
                    breed = edtTV_Breed.getText().toString();
                    Intent intent = new Intent();
                    intent.putExtra("name", name);
                    intent.putExtra("age", age);
                    intent.putExtra("breed", breed);
                    setResult(RESULT_OK, intent);
                    finish();
                }
                else Toast.makeText(this, "Укажите возраст в цифрах", Toast.LENGTH_SHORT).show();
            }
            else Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show();
            break;
            case R.id.edtTV_Name:
                edtTV_Name.setText("");
                break;
            case R.id.edtTV_Age:
                edtTV_Age.setText("");
                break;
            case R.id.edtTV_Breed:
                edtTV_Breed.setText("");
                break;
            default:
                break;

        }
    }
    private static boolean isNumeric(String s) throws NumberFormatException {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}