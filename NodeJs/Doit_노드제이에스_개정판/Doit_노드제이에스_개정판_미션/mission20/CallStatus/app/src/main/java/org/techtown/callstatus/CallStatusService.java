package org.techtown.callstatus;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import wei.mark.standout.StandOutWindow;

public class CallStatusService extends Service {
    private static final String TAG = "StatusService";

    public static boolean ongoing = false;

    RequestQueue queue;
    String mMobile;

    public CallStatusService() {

    }

    @Override
    public void onCreate() {
        super.onCreate();

        if (queue == null) {
            queue = Volley.newRequestQueue(this);
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(intent != null) {
            String command = intent.getStringExtra("command");
            if (command != null) {
                if (command.equals("status")) {
                    String direction = intent.getStringExtra("direction");
                    int state = intent.getIntExtra("state", -1);
                    String mobile = intent.getStringExtra("mobile");

                    if (state == 1) {
                        mMobile = mobile;
                        showFloating();
                    } else if (state == 2) {
                        closeFloating();
                    }

                    if (state == 1 || state == 2) {
                        sendRequest(direction, state, mMobile);
                    }
                }
            }
        }

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /**
     * 웹서버에 요청
     */
    public void sendRequest(final String direction, final int state, final String mobile) {
        Log.d(TAG, "sendRequest() called : " + direction + ", " + state + ", " + mobile);

        String urlStr = "";
        if (state == 1) {
            urlStr = "http://192.168.0.33:3000/process/addstatus";
        } else if (state == 2) {
            urlStr = "http://192.168.0.33:3000/process/updatestatus";
        }

        StringRequest request = new StringRequest(
            Request.Method.POST,
            urlStr,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d(TAG, "onResponse() called : " + response);

                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d(TAG, "onErrorResponse() called.");
                }
            }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<String, String>();
                params.put("direction", direction);
                params.put("state", String.valueOf(state));
                params.put("mobile", mobile);

                return params;
            }
        };

        request.setShouldCache(false);
        queue.add(request);
        Log.d(TAG, "request sent to " + urlStr);
    }

    private void showFloating() {
        StandOutWindow.closeAll(this, SimpleWindow.class);
        StandOutWindow.show(this, SimpleWindow.class, StandOutWindow.DEFAULT_ID);
    }

    private void closeFloating() {
        StandOutWindow.closeAll(this, SimpleWindow.class);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
