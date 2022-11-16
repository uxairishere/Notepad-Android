package com.example.storagemanipulation;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {
EditText textFileName;
EditText textModifyName;
Button buttonSave;
Button buttonLoad;
TextView textViewInsta;

    public void makeDirectory(){
        String path = getExternalFilesDir(null) + "/UxairIsHere";
        File directory = new File(path);
        if(!directory.exists()){
            directory.mkdir();
        }
    }

    public void makeFile(){
        String fileName = textFileName.getText().toString();
        String path = getExternalFilesDir(null).getAbsolutePath() + "/UxairIsHere/" + fileName + ".txt";
        File file = new File(path);
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //Saving Content
        String text = textModifyName.getText().toString();
        //FileOutputStream fileOutputStream = null;
        try {
            //fileOutputStream = openFileOutput(path,MODE_PRIVATE);
            FileOutputStream fileOutputStream = new FileOutputStream(path);
            fileOutputStream.write(text.getBytes());
            textModifyName.getText().clear();
            fileOutputStream.close();
            Toast.makeText(this, "File successfully Saved", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMethod(View view){
        String fileName = textFileName.getText().toString();
        String path = getExternalFilesDir(null).getAbsolutePath() + "/UxairIsHere/" + fileName + ".txt";
        //FileInputStream fileInputStream = null;
        try {
            //fileInputStream = openFileInput(path);
            FileInputStream fileInputStream = new FileInputStream(path);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();
            String text;
            while((text = bufferedReader.readLine()) != null){
                stringBuilder.append(text).append("\n");
            }
            textModifyName.setText(stringBuilder.toString());
            fileInputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

public void createFile(View view){
        makeFile();
}


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textFileName = findViewById(R.id.textFileName);
        textModifyName = findViewById(R.id.textModifyFile);
        buttonLoad = findViewById(R.id.buttonLoad);
        buttonSave = findViewById(R.id.buttonSave);
        textViewInsta = findViewById(R.id.textViewInsta);

        makeDirectory();

        textViewInsta.setClickable(true);
        textViewInsta.setMovementMethod(LinkMovementMethod.getInstance());
        String text = "<a href='https://www.instagram.com/uxair_abbass'> I N S T A G R A M </a>";
        textViewInsta.setText(Html.fromHtml(text));

    }
}