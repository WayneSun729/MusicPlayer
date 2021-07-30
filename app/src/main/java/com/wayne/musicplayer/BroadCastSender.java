package com.wayne.musicplayer;

import android.content.Intent;
import android.util.Log;

/**
 * @author Wayne
 */
public class BroadCastSender {

    private static BroadCastSender broadCastSender;;
    private int id;
    String action;

    private BroadCastSender(String action, int id) {
        this.id = id;
        this.action = action;
    }

    private void setId(int id) {
        this.id = id;
    }

    private void setAction(String action) {
        this.action = action;
    }

    public static BroadCastSender create(String action, int id) {
        if (broadCastSender == null){
            broadCastSender = new BroadCastSender(action, id);
        }else {
            broadCastSender.setAction(action);
            broadCastSender.setId(id);
        }

        return broadCastSender;
    }

    public void sendBroadCast() {
        Intent intent = new Intent(action);
        intent.setPackage(MyApplication.context.getPackageName());
        intent.putExtra("id",id);
        MyApplication.context.sendBroadcast(intent);
    }

}
