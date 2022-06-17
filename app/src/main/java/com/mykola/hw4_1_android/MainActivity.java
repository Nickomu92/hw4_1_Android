/**
 * Створіть у модулі проекту по два текстові файли у каталогах /res/raw та /assets. Напишіть додаток,
 * який прочитає вміст цих файлів та виведе цей вміст у віджети android.widget.TextView.
 */

package com.mykola.hw4_1_android;

import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private TextView tvFirst;
    private TextView tvSecond;
    private TextView tvThird;
    private TextView tvFourth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        initViews();
        setTextViews();
    }

    public void initViews() {
        tvFirst = findViewById(R.id.tvFirst);
        tvSecond = findViewById(R.id.tvSecond);
        tvThird = findViewById(R.id.tvThird);
        tvFourth = findViewById(R.id.tvFourth);
    }


    private String getText(String fileName, boolean isAssetFolder) {
        String text = "";
        InputStream inputStream;

        try {
            if (isAssetFolder) {
                inputStream = this.getAssets().open(fileName);
            } else {
                inputStream = this.getResources().openRawResource(Integer.parseInt(fileName));
            }

            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            text = new String(buffer);
        } catch (IOException ex) {
            text = ex.getMessage();
        }
        return text;
    }

    private void setTextViews() {
        tvFirst.setText(getText("text_file1.txt", true));
        tvSecond.setText(getText("text_file2.txt", true));
        tvThird.setText(getText(Integer.toString(R.raw.text_file3), false));
        tvFourth.setText(getText(Integer.toString(R.raw.text_file4), false));
    }
}