package com.example.m6l4a1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.os.Environment.getExternalStorageDirectory;

public class MainActivity extends AppCompatActivity {

    // default file name
    private static final String FILE_NAME = "demo.txt";

    //Button to save file on click
    Button write;

    //Edit text to take user input of file content
    EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //referred UI components
        write = findViewById(R.id.button_save);
        mEditText = findViewById(R.id.edit_text);

        // on click of write button create file in external storage.
        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createfiletoExternalprivate();
                mEditText.setText("");
            }
        });

    }

    private void createfiletoExternalprivate()
    {
        // save file with file name
        File file = new File(getExternalStorageDirectory(),FILE_NAME);
        save(file);
    }

    public void save(File file) {
        // get text to save in file
        String text = mEditText.getText().toString();
        FileOutputStream fileOutputStream = null;
        try
        {
            fileOutputStream = new FileOutputStream(file);
            //write text to file
            fileOutputStream.write(text.getBytes());
            mEditText.getText().clear();

            //Toast about path of file
            Toast.makeText(this, "Saved file: " + file.getName() + " Path: " + file.getPath(),
                    Toast.LENGTH_LONG).show();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally {
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
}