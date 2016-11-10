package com.creater.redplugin;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.app.KeyguardManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Toast;

import java.util.List;

public class RedService extends AccessibilityService {
    private  static final String WE_CHAT_PACKAGENAME="com.tencent.mm";
    private  static  final String TEST_DEFAULT="com.creater.accessabilitytest";

    private boolean isLockScreen;
    public RedService() {
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        Log.e("xv","handle the event");
        String packName=event.getPackageName().toString();
        int eventType=event.getEventType();
        KeyguardManager km=(KeyguardManager)this.getSystemService(Context.KEYGUARD_SERVICE);
        isLockScreen=km.isKeyguardLocked();
        if(isLockScreen){
            Log.e("xv","Lockscreen is tures");
        }else {
            Log.e("xv","Lockscreen is false");
        }
        if (packName.equals(WE_CHAT_PACKAGENAME)) {
            startWECHAT(event);
            if (eventType == AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED) {//通知栏发生变化
                startWECHAT(event);
            }
        }else if (packName.equals(TEST_DEFAULT)){
            Log.e("xv","handle the test packeage");
            if (eventType == AccessibilityEvent.TYPE_VIEW_CLICKED) {//通知栏发生变化
                Toast.makeText(this,"click other app",Toast.LENGTH_SHORT).show();
                //startTest(event);
            }
        }


    }

    private  void startTest(AccessibilityEvent event){
        AccessibilityNodeInfo rootNode=getRootInActiveWindow();
        //if (rootNode.findAccessibilityNodeInfosByViewId(""))
    }

    private void startWECHAT(AccessibilityEvent event){
        List<CharSequence> texts = event.getText();
        if (!texts.isEmpty()) {
            for (CharSequence text : texts) {
                String content = text.toString();
                Log.i("demo", "text:" + content);
                if (content.contains("[微信红包]")) {

                    //模拟打开通知栏消息
                    if (event.getParcelableData() != null
                            &&
                            event.getParcelableData() instanceof Notification) {
                        Notification notification = (Notification) event.getParcelableData();
                        PendingIntent pendingIntent = notification.contentIntent;
                        try {
                            pendingIntent.send();
                        } catch (PendingIntent.CanceledException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

        @Override
    public void onInterrupt() {

    }

    @Override
    protected void onServiceConnected() {
        Toast.makeText(this,"service connected",Toast.LENGTH_SHORT).show();
        Log.e("xv","service connected");
        AccessibilityServiceInfo info = getServiceInfo();
        //这里可以设置多个包名，监听多个应用
        info.packageNames = new String[]{"com.creater.accessabilitytest","com.tencent.mm"};
        setServiceInfo(info);
        super.onServiceConnected();
    }
}
