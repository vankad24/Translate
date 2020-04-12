package com.example.yatranslate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    static MainActivity activity;
    Spinner spinner;
    String translatedText;
    Button button;
    YaTranslateTask task;
    EditText et;
    int k;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = findViewById(R.id.start);
        button = findViewById(R.id.translate);
        et = findViewById(R.id.text);
        activity =this;
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] choose = getResources().getStringArray(R.array.languages);
                Request req = new Request("Перевести!", "ru-" + language(choose[position]),2);
                task = new YaTranslateTask(MainActivity.activity);
                task.execute(req);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }

        });
    }
    public void setTranslatedText(String translatedText) {
        this.translatedText = translatedText;
    }

    public void displayResult() {
        TextView tv = findViewById(R.id.result);
        tv.setText(translatedText);
    }

    public void finishTranslate(int finishCode){
        switch (finishCode){
            case 0:
                break;
            case 1:
                displayResult();
                break;
            case 2:
                button.setText(translatedText);
                break;
            case 3:
                String[] lang= {"ru","fr","he", "zh", "de", "en","ru"};
                Request req;
                if (k<lang.length-1){
                    req = new Request(translatedText, lang[k] + "-" + lang[k + 1], 3);
                    task = new YaTranslateTask(this);
                    task.execute(req);
                    k++;
                }else {
                    k = 0;
                    displayResult();
                }
                break;
        }
    }

    public void onClick(View v) {
        String txt = et.getText().toString();
        Spinner spinner2 = findViewById(R.id.end);
        Request req = new Request(txt, language(spinner.getSelectedItem().toString())+"-"+language(spinner2.getSelectedItem().toString()),1);
        task = new YaTranslateTask(this);
        task.execute(req);
    }

    public void onClick2(View v){
        Request req;
        if (k==0) {
            req = new Request(et.getText().toString(), "ru-ru", 3);
            task = new YaTranslateTask(this);
            task.execute(req);
        }
    }

    String  language(String s){
        return s.substring(0,2);
    }
}
