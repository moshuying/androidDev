package com.example.moshuying;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class JsonEdit extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.json_edit);
        final TextView editText = (TextView) findViewById(R.id.editTextTextMultiLine);
        editText.setText("{\"name\":\"moshuying\",\"url\":\"moshuying.top\",\"email\":\"1460083332@qq\",\"github\":\"moshuying.top\"}");

        findViewById(R.id.sendJson).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(JsonEdit.this,Auto.class);
                intent.putExtra("data",editText.getText().toString());
                startActivity(intent);
            }
        });

        findViewById(R.id.json_edit_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
