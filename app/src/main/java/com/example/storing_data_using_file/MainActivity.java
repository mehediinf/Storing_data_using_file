package com.example.storing_data_using_file;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText_Id);
        saveButton = findViewById(R.id.saveButton_Id);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String text = editText.getText().toString();

                if (text!=null){
                    writeToFile(text);

                }
                else {
                    Toast.makeText(getApplicationContext(),"Please enter some date",Toast.LENGTH_SHORT).show();
                }


            }
        });

        readFromFile();

    }

    public void writeToFile(String text){
        try {
            FileOutputStream fileOutputStream = openFileOutput("diary.txt", Context.MODE_PRIVATE);
            fileOutputStream.write(text.getBytes());
            fileOutputStream.close();
            Toast.makeText(getApplicationContext(), "data is saved", Toast.LENGTH_SHORT).show();

        }
        catch (FileNotFoundException e) {
        e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void readFromFile()
    {

        try {
            FileInputStream fileInputStream = openFileInput("diary.txt");

            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            StringBuffer stringBuffer = new StringBuffer();

            while ((line = bufferedReader.readLine())!=null){

                stringBuffer.append(line+"\n");
            }
            editText.setText(stringBuffer.toString());


        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }


    }

}