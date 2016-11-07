package com.creater.redplugin;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button mStartButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mStartButton=(Button)this.findViewById(R.id.start_button);
        mStartButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.start_button){
            startRedService();
        }
    }


    private void startRedService(){
        Log.e("xv","in the service");
        Intent intent=new Intent(this,RedService.class);
        startService(intent);
    }
}
