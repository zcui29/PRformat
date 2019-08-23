package com.example.qaformat;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText id,version,leapSubDesc;
    EditText des;
    EditText result;
    Button btn,finish,clear,addDesc;
    String s3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        id  = findViewById(R.id.leapID);
        des = findViewById(R.id.leapDesc);
        result = findViewById(R.id.result);
        btn = findViewById(R.id.btn);
        finish = findViewById(R.id.finish);
        clear = findViewById(R.id.clear);
        leapSubDesc = findViewById(R.id.leapSubDesc);
        addDesc = findViewById(R.id.addDesc);
        version = findViewById(R.id.version);
        result.setText("");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = "[LEAP - " + id.getText() + "]" + "("  + "https://bankunited.atlassian.net/browse/leap-" + id.getText() + "): ";
                if(!result.getText().toString().contains("#### PARENT STORY/DEFECT")){
                    s1 = "\n#### PARENT STORY/DEFECT\n" + s1;
                }else {
                    s1 = "[LEAP - " + id.getText() + "]" + "("  + "https://bankunited.atlassian.net/browse/leap-" + id.getText() + "): ";
                }
                String s2 = "*" + des.getText() + "*\n";
                if(id.getText().toString().isEmpty()&&des.getText().toString().isEmpty()){
                    s1 = "";
                    s2 = "";
                }
                s3 = result.getText() + "";
                id.setText("");
                des.setText("");
                leapSubDesc.setText("");
                result.setText(s3 + s1+ s2);
            }
        });
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result.setText("**_BUILD: "+version.getText()+"_**\n" + s3 +
                        "\n#### QA Checklist\n" +
                        "- [x] Development is completed on this task to the best of my knowledge\n" +
                        "- [x] These changes pass my own testing ");


                ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("",result.getText());
                clipboard.setPrimaryClip(clip);
                Toast.makeText(MainActivity.this, "Yeyyy, your result already in your clip board, go ahead make your PR.", Toast.LENGTH_SHORT).show();
                version.setText("");
            }
        });

        addDesc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s3 = result.getText().toString();
                String s4 = "\n- "+leapSubDesc.getText() + "";
                if(leapSubDesc.getText().toString().isEmpty()){
                    s4 = "";
                }

                if(!s3.contains("#### What did we do?")){
                    result.setText(s3 + "#### What did we do?\n" + s4);
                }else {
                    result.setText(s3 + s4);
                }
                leapSubDesc.setText("");
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result.setText("");
                version.setText("");
                leapSubDesc.setText("");
            }
        });

    }
}
