/*
 * Copyright (C) 2013 asksven
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.asksven.betterbatterystats.widgetproviders;

import android.annotation.TargetApi;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.widget.RemoteViews;

import java.util.Locale;

import com.asksven.betterbatterystats.R;

@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
public class AppWidgetApi16 extends AppWidgetProvider
{
	// based on http://stackoverflow.com/a/18552461/115145

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds)
	{
		super.onUpdate(context, appWidgetManager, appWidgetIds);

		for (int appWidgetId : appWidgetIds)
		{
			Bundle options = appWidgetManager.getAppWidgetOptions(appWidgetId);

			onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId, options);
		}
	}

	@Override
	public void onAppWidgetOptionsChanged(Context ctxt, AppWidgetManager mgr, int appWidgetId, Bundle newOptions)
	{
		RemoteViews updateViews = new RemoteViews(ctxt.getPackageName(), R.layout.widget);
		String msg = String.format(Locale.getDefault(), "[%d-%d] x [%d-%d]",
				newOptions.getInt(AppWidgetManager.OPTION_APPWIDGET_MIN_WIDTH),
				newOptions.getInt(AppWidgetManager.OPTION_APPWIDGET_MAX_WIDTH),
				newOptions.getInt(AppWidgetManager.OPTION_APPWIDGET_MIN_HEIGHT),
				newOptions.getInt(AppWidgetManager.OPTION_APPWIDGET_MAX_HEIGHT));

		updateViews.setTextViewText(R.id.size, msg);

		mgr.updateAppWidget(appWidgetId, updateViews);
	}
}