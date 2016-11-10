package com.creater.redplugin;

import android.app.ActivityManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button mStartButton;
    private Button mCheckBUtton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mStartButton=(Button)this.findViewById(R.id.start_button);
        mStartButton.setOnClickListener(this);
        mCheckBUtton=(Button)this.findViewById(R.id.check_button);
        mCheckBUtton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.start_button){
            startRedService();
        }else if(v.getId()==R.id.check_button){
            isServiceWork(this,"com.creater.redplugin.RedService");
        }

    }


    private void startRedService(){
        Intent intent=new Intent(this,RedService.class);
        startService(intent);
    }



    /**
     * 判断某个服务是否正在运行的方法
     *
     * @param mContext
     * @param serviceName
     *            是包名+服务的类名（例如：net.loonggg.testbackstage.TestService）
     * @return true代表正在运行，false代表服务没有正在运行
     */
    public boolean isServiceWork(Context mContext, String serviceName) {
        boolean isWork = false;
        ActivityManager myAM = (ActivityManager) mContext
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> myList = myAM.getRunningServices(40);
        if (myList.size() <= 0) {
            return false;
        }
        for (int i = 0; i < myList.size(); i++) {
            String mName = myList.get(i).service.getClassName().toString();
            if (mName.equals(serviceName)) {
                isWork = true;
                break;
            }
        }
        Log.e("xv","service is working:"+isWork);
        return isWork;
    }
}
