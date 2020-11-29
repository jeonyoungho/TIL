package org.techtown.callstatus;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.util.Log;

public class IncomingCallReceiver extends BroadcastReceiver {
    private static String TAG = "IncomingReceiver";

    String mobile;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive() called.");
        if(intent != null) {
            String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
            if(state != null) {
                if(state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
                    Log.d(TAG, "Incoming call ringing.");

                    CallStatusService.ongoing = true;

                    // get phone number
                    mobile = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
                    Log.d(TAG, "state : " + state + ", mobile : " + mobile);

                    // 서비스로 보내서 통화상태 저장 요청
                    Intent callStatusIntent = new Intent(context, CallStatusService.class);
                    callStatusIntent.putExtra("command", "status");
                    callStatusIntent.putExtra("direction", "incoming");
                    callStatusIntent.putExtra("state", 1);
                    callStatusIntent.putExtra("mobile", mobile);
                    context.startService(callStatusIntent);

                } else if(state.equals(TelephonyManager.EXTRA_STATE_IDLE)) { // 전화 끊어졌을 때
                    if (!CallStatusService.ongoing) {
                        return;
                    }

                    CallStatusService.ongoing = false;

                    Log.d(TAG, "Incoming call quit.");
                    Log.d(TAG, "state : " + state + ", mobile : " + mobile);

                    // 서비스로 보내서 통화상태 저장 요청
                    Intent callStatusIntent = new Intent(context, CallStatusService.class);
                    callStatusIntent.putExtra("command", "status");
                    callStatusIntent.putExtra("direction", "incoming");
                    callStatusIntent.putExtra("state", 2);
                    callStatusIntent.putExtra("mobile", mobile);
                    context.startService(callStatusIntent);
                }
            }
        }
    }
}
