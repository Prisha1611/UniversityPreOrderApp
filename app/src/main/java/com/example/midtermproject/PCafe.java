package com.example.midtermproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;

public class PCafe extends AppCompatActivity {
    private Button btn4;
    private CheckBox c1,c2,c3,c4,c5;
    private EditText i1,i2,i3,i4,i5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pcafe);
        btn4 = (Button) findViewById(R.id.button4);
        c1 = (CheckBox) findViewById(R.id.checkBox);
        c2 = (CheckBox) findViewById(R.id.checkBox2);
        c3 = (CheckBox) findViewById(R.id.checkBox3);
        c4 = (CheckBox) findViewById(R.id.checkBox4);
        c5 = (CheckBox) findViewById(R.id.checkBox5);

        i1 = (EditText) findViewById(R.id.i1);
        i2 = (EditText) findViewById(R.id.i2);
        i3 = (EditText) findViewById(R.id.i3);
        i4 = (EditText) findViewById(R.id.i4);
        i5 = (EditText) findViewById(R.id.i5);


        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LinkedList<String> str = new LinkedList<String>();
                LinkedList<String> qn = new LinkedList<String>();
                LinkedList<String> pr = new LinkedList<String>();
                int v1 = 0, v2 =0  , v3 =0 , v4 = 0 , v5 =0 ;
                if(!i1.getText().toString().equals("")) {
                    v1 = Integer.parseInt(i1.getText().toString());
                }
                if(!i2.getText().toString().equals("")) {
                    v2 = Integer.parseInt(i2.getText().toString());
                }
                if(!i3.getText().toString().equals("")) {
                    v3 = Integer.parseInt(i3.getText().toString());
                }
                if(!i4.getText().toString().equals("")) {
                    v4 = Integer.parseInt(i4.getText().toString());
                }
                if(!i5.getText().toString().equals("")) {
                    v5 = Integer.parseInt(i5.getText().toString());
                }
                int sum = 0;
                if(c1.isChecked()){
                    sum+=(90*v1);
                    pr.add((90*v1)+"");
                    qn.add(v1+"");
                    str.add("Chocolate Donut");
                }
                if(c2.isChecked()){
                    sum+=(150*v2);
                    pr.add((150*v2)+"");
                    qn.add(v2+"");
                    str.add("Cappuccino");
                }
                if(c3.isChecked()){
                    sum+=(200*v3);
                    pr.add((200*v3)+"");
                    qn.add(v3+"");
                    str.add("Ham,Spinach and Cheese sandwich ");
                }
                if(c4.isChecked()){
                    sum+=(300*v4);
                    pr.add((300*v4)+"");
                    qn.add(v4+"");
                    str.add("Beef Burger");
                }
                if(c5.isChecked()){
                    sum+=(230*v5);
                    pr.add((230*v5)+"");
                    qn.add(v5+"");
                    str.add("Stir fried egg rice");
                }
                String h = sum+"";
                Toast.makeText(PCafe.this, h, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(PCafe.this,OrderPage.class);
                intent.putExtra("tot",(sum+""));
                intent.putExtra("qn",qn);
                intent.putExtra("str",str);
                intent.putExtra("pr",pr);
                startActivity(intent);
            }
        });


    }
}