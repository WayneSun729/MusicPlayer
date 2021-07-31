package com.wayne.musicplayer.util;

import android.content.Intent;

import com.wayne.musicplayer.MyApplication;

/**
 * @author Wayne
 */
public class BroadcastSenderUtils {

    private static BroadcastSenderUtils BroadcastSenderUtils;
    private int id;
    String action;

    private BroadcastSenderUtils(String action, int id) {
        this.id = id;
        this.action = action;
    }

    private void setId(int id) {
        this.id = id;
    }

    private void setAction(String action) {
        this.action = action;
    }

    public static BroadcastSenderUtils create(String action, int id) {
        if (BroadcastSenderUtils == null){
            BroadcastSenderUtils = new BroadcastSenderUtils(action, id);
        }else {
            BroadcastSenderUtils.setAction(action);
            BroadcastSenderUtils.setId(id);
        }

        return BroadcastSenderUtils;
    }

    public void sendBroadCast() {
        Intent intent = new Intent(action);
        intent.setPackage(MyApplication.getContext().getPackageName());
        intent.putExtra("id",id);
        MyApplication.getContext().sendBroadcast(intent);
    }

}
