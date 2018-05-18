package com.example.jjong.lab5_2;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textview;
    Button button;
    EditText edittext;

    int count,sum=1;
    String str="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //match
        edittext=findViewById(R.id.editText);
        textview=findViewById(R.id.textView2);
        button=findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count=Integer.parseInt(edittext.getText().toString());  //get to number in editText
                new CalFacto().execute();   //start
                str="";     //init
            }
        });

    }

    private class CalFacto extends AsyncTask<Void,Integer,Void>{
        @Override
        protected void onPreExecute() {
        }

        //Work on Thread
        @Override
        protected Void doInBackground(Void... voids) {
            for(int i=count; i>0; i--){
                try {
                    Thread.sleep(500);  //500ms
                    sum*=i;         //factorial
                    publishProgress(i);     //send to onProgressUpdate
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            str+=" "+Integer.toString(values[0].intValue());    //set String
            textview.setText(str);  //set String to edit Text
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            str+="\n= "+sum;
            textview.setText(str);  //show Factorial result
        }
    }
}
