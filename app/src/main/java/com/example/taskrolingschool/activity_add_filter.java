package com.example.taskrolingschool;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class activity_add_filter extends AppCompatActivity implements View.OnClickListener {
    Button btnFilterName, btnFilterAge, btnFilterBreed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_filter);
        btnFilterAge = (Button) findViewById(R.id.btnFilterAge);
        btnFilterName = (Button) findViewById(R.id.btnFilterName);
        btnFilterBreed = (Button) findViewById(R.id.btnFilterBreed);

        btnFilterAge.setOnClickListener(this);
        btnFilterName.setOnClickListener(this);
        btnFilterBreed.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btnFilterName:
                returnAnswer(0);
                break;
            case R.id.btnFilterAge:
                returnAnswer(1);
                break;
            case R.id.btnFilterBreed:
                returnAnswer(2);
                break;
            default:
                break;
        }
    }
    public void returnAnswer(int filter){
        if(filter>=0&&filter<=2) {
            Intent intent = new Intent();
            intent.putExtra("filter", filter);
            setResult(RESULT_OK, intent);
            finish();

        }
    }
}