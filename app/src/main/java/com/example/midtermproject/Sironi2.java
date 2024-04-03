package com.example.midtermproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;

import java.util.LinkedList;

public class Sironi2 extends AppCompatActivity {
    private Button btn4;
    private CheckBox c1,c2,c3,c4,c5;
    private EditText i1,i2,i3,i4,i5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sironi2);

        ImageView logoImageView = findViewById(R.id.logoImageView);
        ImageView imageView10 = findViewById(R.id.imageView10);
        ImageView imageView12 = findViewById(R.id.imageView12);
        ImageView imageView13 = findViewById(R.id.imageView13);
        ImageView imageView14 = findViewById(R.id.imageView14);
        ImageView imageView15 = findViewById(R.id.imageView15);

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
        Glide.with(this)
                .load(R.drawable.sironi2) // Replace with your image URL or resource ID
                .into(logoImageView);

        Glide.with(this)
                .load(R.drawable.pasta)
                .into(imageView10);

        Glide.with(this)
                .load(R.drawable.coffee)
                .into(imageView12);

        Glide.with(this)
                .load(R.drawable.masala_chips) // Replace with your image URL or resource ID
                .into(imageView13);

        Glide.with(this)
                .load(R.drawable.cake)
                .into(imageView14);

        Glide.with(this)
                .load(R.drawable.pizza)
                .into(imageView15);

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
                    sum+=(300*v1);
                    pr.add((300*v1)+"");
                    qn.add(v1+"");
                    str.add("Cheese and Tangy Tomato Pasta");
                }
                if(c2.isChecked()){
                    sum+=(150*v2);
                    pr.add((150*v2)+"");
                    qn.add(v2+"");
                    str.add("House Coffee");
                }
                if(c3.isChecked()){
                    sum+=(250*v3);
                    pr.add((250*v3)+"");
                    qn.add(v3+"");
                    str.add("Masala Chips");
                }
                if(c4.isChecked()){
                    sum+=(350*v4);
                    pr.add((350*v4)+"");
                    qn.add(v4+"");
                    str.add("Black forest cake");
                }
                if(c5.isChecked()){
                    sum+=(300*v5);
                    pr.add((300*v5)+"");
                    qn.add(v5+"");
                    str.add("Beef and cheese pizza");
                }
                String h = sum+"";
                Toast.makeText(Sironi2.this, h, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(Sironi2.this,OrderPage.class);
                intent.putExtra("tot",(sum+""));
                intent.putExtra("qn",qn);
                intent.putExtra("str",str);
                intent.putExtra("pr",pr);
                startActivity(intent);
            }
        });


    }
}