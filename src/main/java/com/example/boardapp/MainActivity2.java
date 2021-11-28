package com.example.boardapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        final TextView txtFunction = (TextView) findViewById(R.id.txtFunction);
        final CustomView customView = (CustomView) findViewById(R.id.plotview);
        Button buttonPlot = (Button) findViewById(R.id.buttonPlot);

        buttonPlot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (customView != null) {
                    String strFunction = txtFunction.getText().toString();
                    customView.setStrFunction(strFunction);
                    customView.invalidate();
                }

            }
        });

        buttonPlot.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (customView != null) {
                    String strFunction = "";
                    customView.setStrFunction(strFunction);
                    customView.invalidate();
                }


                return true;
            }
        });

//        buttonPlot.setOnClickListener(v -> {
//            if (customView != null) {
//                String strFunction = txtFunction.getText().toString();
//                customView.setStrFunction(strFunction);
//                customView.invalidate();
//            }
//        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu2,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case R.id.help1:
                Intent intent = new Intent(MainActivity2.this,MainActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}