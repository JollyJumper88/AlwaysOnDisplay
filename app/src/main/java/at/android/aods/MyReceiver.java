package at.android.aods;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    private boolean screenOff;

    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("receiver", "onreceive");
        if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
            screenOff = true;
            Log.i("receiver", "Screen went OFF");
            //Toast.makeText(context, "screen OFF",Toast.LENGTH_LONG).show();
        } else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
            screenOff = false;
            Log.i("receiver", "Screen went ON");
            //Toast.makeText(context, "screen ON", Toast.LENGTH_LONG).show();
        }


        // Send Current screen ON/OFF value to service
        Intent i = new Intent(context, MyService.class);
        i.putExtra("screen_state", screenOff);
        context.startService(i);
    }
}
