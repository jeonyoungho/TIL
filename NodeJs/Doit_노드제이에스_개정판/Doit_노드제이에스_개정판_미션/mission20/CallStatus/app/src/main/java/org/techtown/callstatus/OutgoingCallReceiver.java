package org.techtown.callstatus;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

public class OutgoingCallReceiver extends BroadcastReceiver {
    private static TelephonyManager manager;
    Context mContext;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("OutgoingReceiver", "onReceive() called.");

        mContext = context;

        if (manager == null) {
            manager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
            manager.listen(new PhoneStateListener() {
                @Override
                public void onCallStateChanged(int state, String mobile) {
                    super.onCallStateChanged(state, mobile);
                    Log.d("OutgoingReceiver", "state : " + state + ", mobile : " + mobile);

                    if (state == 2) {
                        CallStatusService.ongoing = true;

                        // 서비스로 보내서 통화상태 저장 요청
                        Intent callStatusIntent = new Intent(mContext, CallStatusService.class);
                        callStatusIntent.putExtra("command", "status");
                        callStatusIntent.putExtra("direction", "outgoing");
                        callStatusIntent.putExtra("state", 1);
                        callStatusIntent.putExtra("mobile", mobile);
                        mContext.startService(callStatusIntent);
                    } else if (state == 2) {
                        if (!CallStatusService.ongoing) {
                            return;
                        }

                        CallStatusService.ongoing = false;

                        // 서비스로 보내서 통화상태 저장 요청
                        Intent callStatusIntent = new Intent(mContext, CallStatusService.class);
                        callStatusIntent.putExtra("command", "status");
                        callStatusIntent.putExtra("direction", "outgoing");
                        callStatusIntent.putExtra("state", 2);
                        callStatusIntent.putExtra("mobile", mobile);
                        mContext.startService(callStatusIntent);
                    }

                }
            }, PhoneStateListener.LISTEN_CALL_STATE);
        }

    }

}
