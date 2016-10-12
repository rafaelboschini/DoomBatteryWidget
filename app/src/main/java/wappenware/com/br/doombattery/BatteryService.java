package wappenware.com.br.doombattery;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.BatteryManager;
import android.os.IBinder;
import android.widget.RemoteViews;

/**
 * Created by boschini on 11/10/16.
 */

public class BatteryService extends Service {

    private int batterylevel = 0;
    private String batteryStatus ="";
    private boolean isCharging = false;

    private BroadcastReceiver myReceiver = new BroadcastReceiver()
    {
        @Override
        public void onReceive(Context context, Intent intent)
        {
            String action = intent.getAction();
            if (action.equals(Intent.ACTION_BATTERY_CHANGED))
            {
                batterylevel = intent.getIntExtra("level", 0);
                isCharging = false;

                int status = intent.getIntExtra("status", BatteryManager.BATTERY_STATUS_UNKNOWN);
                String strStatus;
                if (status == BatteryManager.BATTERY_STATUS_CHARGING){
                    batteryStatus = "Carregando";
                    isCharging = true;

                } else if (status == BatteryManager.BATTERY_STATUS_DISCHARGING){
                    batteryStatus = "Descarregando";
                } else if (status == BatteryManager.BATTERY_STATUS_NOT_CHARGING){
                    batteryStatus = "Não carregando";
                } else if (status == BatteryManager.BATTERY_STATUS_FULL){
                    batteryStatus = "Full";
                } else {
                    batteryStatus = "";
                }

                updateAppWidget(context);
            }
        }

        public void updateAppWidget(Context context){
            Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.doom11);

            RemoteViews updateViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
            //updateViews.setTextViewText(R.id.textView1,
            //  "Bat. Status:\n" +
            //  "Level: " + batterylevel + "%\n" +
            //  "Status: " + batteryStatus);

            //updateViews.setTextViewText(R.id.textView1,"Level:" + batterylevel + "%\n");

            updateViews.setImageViewResource(R.id.imageView, R.mipmap.doom11);
            if((batterylevel > 0) && (batterylevel < 10)){
                bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.doom10);

            }

            if((batterylevel >= 10) && (batterylevel < 20)){
                bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.doom9);

            }

            if((batterylevel >= 20) && (batterylevel < 30)){
                bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.doom12);

            }

            if((batterylevel >= 30) && (batterylevel < 40)){
                bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.doom7);

            }

            if((batterylevel > 40) && (batterylevel < 50)){
                bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.doom6);

            }

            if((batterylevel >= 50) && (batterylevel < 60)){
                bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.doom5);

            }

            if((batterylevel >= 60) && (batterylevel < 70)){
                bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.doom4);
            }

            if((batterylevel >= 70) && (batterylevel < 80)){
                bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.doom3);
            }

            if((batterylevel >= 80) && (batterylevel < 90)){
                bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.doom2);
            }

            if((batterylevel >= 90) && (batterylevel < 101)){
                bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.doom1);
            }

            if(isCharging){
                bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.doom11);
            }

            updateViews.setImageViewBitmap(R.id.imageView,bitmap);

            ComponentName myComponentName = new ComponentName(context, DoomWidgetProvider.class);
            AppWidgetManager manager = AppWidgetManager.getInstance(context);
            manager.updateAppWidget(myComponentName, updateViews);
        }
    };

    @Override
    public IBinder onBind(Intent arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(myReceiver, intentFilter);
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        unregisterReceiver(myReceiver);
    }

}