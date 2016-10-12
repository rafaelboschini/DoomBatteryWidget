package wappenware.com.br.doombattery;

import android.appwidget.AppWidgetProvider;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;

public class DoomWidgetProvider extends AppWidgetProvider {

    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
        context.startService(new Intent(context, BatteryService.class));
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }

}