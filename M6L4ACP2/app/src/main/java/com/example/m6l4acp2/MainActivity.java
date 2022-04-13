package com.example.m6l4acp2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import static android.os.Environment.getExternalStoragePublicDirectory;

public class MainActivity extends AppCompatActivity {

    // Default file name
    private String FILE_NAME = "demo.txt";

    // UI components used
    Button read,write;
    EditText mEditText,mEditFilename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Referred UI components
        read = findViewById(R.id.button_load);
        write = findViewById(R.id.button_save);
        mEditText = findViewById(R.id.edit_text);
        mEditFilename = findViewById(R.id.edit_filename);

        // Declared button on click
        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FILE_NAME = mEditFilename.getText().toString() +".txt";
                readfiletoExternalprivate();
            }
        });

        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FILE_NAME = mEditFilename.getText().toString() +".txt";
                createfiletoExternalprivate();
            }
        });
    }

    private void readfiletoExternalprivate()
    {
        //Read new file and set text into TextView
        File file = new File(getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),FILE_NAME);
        String msg = load(file);
        mEditText.setText(msg);
    }
    private void createfiletoExternalprivate()
    {
        //Make a new file with file location and file name
        File file = new File(getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),FILE_NAME);
        save(file);
    }


    public void save(File file) {
        //Get string from EditText to put into file
        String text = mEditText.getText().toString();

        FileOutputStream fileOutputStream = null;
        try {
            //file output stream given our file
            fileOutputStream = new FileOutputStream(file);

            //write text to file
            fileOutputStream.write(text.getBytes());

            mEditText.getText().clear();

            //Make toast to show where the file is saved
            Toast.makeText(this, "Saved file: " + file.getName() + " Path: " + file.getPath(),
                    Toast.LENGTH_LONG).show();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    // close stream
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private String load(File file) {
        //take file as input and put into file input stream
        FileInputStream fileInputStream = null;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            //file stream to get file content
            fileInputStream = new FileInputStream(file);

            int text;

            // append text to string while there is a line
            while ((text = fileInputStream.read()) != -1) {
                stringBuilder.append((char) text);
            }

            // set set to Edit text
            mEditText.setText(stringBuilder.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    // close the stream
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return stringBuilder.toString();
    }
}