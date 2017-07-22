package at.android.aods;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends Activity implements View.OnClickListener {

    private int NOTIFICATION_ID;
    private Button start, send, cancel;
    private NotificationManager mNotificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        start = (Button)findViewById(R.id.start);
        start.setOnClickListener(this);
        send = (Button)findViewById(R.id.send);
        send.setOnClickListener(this);
        cancel = (Button)findViewById(R.id.cancel);
        cancel.setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void sendNotification(String type, String title, String message) {

        NOTIFICATION_ID = 8;

        int icon;

        icon = R.drawable.abc_ic_menu_selectall_mtrl_alpha;

        // R.drawable.ic_menu_start_conversation

        mNotificationManager = (NotificationManager) this
                .getSystemService(Context.NOTIFICATION_SERVICE);

        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                this.getIntent(), PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
                this)
                .setSmallIcon(icon)
                .setContentTitle(title)
                .setStyle(
                        new NotificationCompat.BigTextStyle().bigText(message))
                        // .setStyle(
                        // new NotificationCompat.BigPictureStyle()
                        // .bigPicture(drawableToBitmap(getResources()
                        // .getDrawable(R.drawable.ic_menu_gallery))))
                .setContentText(message);

        mBuilder.setContentIntent(contentIntent);
        mBuilder.setAutoCancel(true);

        mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start:
                startService(new Intent(this, MyService.class));
                break;
            case R.id.send:
                sendNotification("type", "title", "message");
                break;
            case R.id.cancel:
                cancelNotification();
                break;
            default:
                break;
        }
    }

    private void cancelNotification() {
        if (mNotificationManager != null)  {
            mNotificationManager.cancel(8);
        } else {
            Log.e("Main", "noti manager not found");
        }
    }
}
