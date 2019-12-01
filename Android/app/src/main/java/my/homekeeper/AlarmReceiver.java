package my.homekeeper;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AlarmReceiver extends BroadcastReceiver {
    String TAG = "TAG+AlarmReceiver";
    public static final String ACTION_RESTART_SERVICE = "Restart";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "AlarmReceiver");
        if(intent.getAction().equals(ACTION_RESTART_SERVICE)) {
            Intent in = new Intent(context, AlarmService.class);
            context.startService(in);
        }
    }
}
