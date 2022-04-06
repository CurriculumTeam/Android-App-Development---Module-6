package com.example.m6l3acp1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    //Adding all UI components
    String FILE_NAME;

    //edit text to take input
    EditText mEditText,editFirid,loadfirid;
    TextView txtFirLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // referred edit text
        mEditText = findViewById(R.id.editInfo);
        editFirid = findViewById(R.id.editFIRID);
        loadfirid = findViewById(R.id.editLoadfirid);
        txtFirLoad = findViewById(R.id.firload);
    }

    public void save(View v) {
        // get FIR report
        String text = mEditText.getText().toString();
        FileOutputStream fileOutputStream = null;

        //Get FIR file name
        FILE_NAME = editFirid.getText().toString();

        try {

            //Give file name and file type
            fileOutputStream = openFileOutput(FILE_NAME, MODE_PRIVATE);
            fileOutputStream.write(text.getBytes());

            mEditText.getText().clear();

            //Toast about file saved
            Toast.makeText(this, "Saved to " + getFilesDir() + "/" + FILE_NAME,
                    Toast.LENGTH_LONG).show();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    // close the stream
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void load(View v) {
        FileInputStream fileInputStream = null;
        try {

            //get file name to load
            FILE_NAME = loadfirid.getText().toString();
            fileInputStream = openFileInput(FILE_NAME);

            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();
            String text;

            // while there is a new line in the file append it to string
            while ((text = bufferedReader.readLine()) != null) {
                stringBuilder.append(text).append("\n");
            }

            // set fetched text tp Text view
            txtFirLoad.setText(stringBuilder.toString());

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
    }
}