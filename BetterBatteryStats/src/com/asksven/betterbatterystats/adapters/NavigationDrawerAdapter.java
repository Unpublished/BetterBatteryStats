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
package com.asksven.betterbatterystats.adapters;

import com.asksven.betterbatterystats.R;
import com.asksven.betterbatterystats.data.NavigationDrawerItem;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author sven
 * 
 */

public class NavigationDrawerAdapter extends ArrayAdapter<NavigationDrawerItem>
{

	private final static String TAG = "NavigationDrawerAdapter";
	/*
	 * public NsMenuAdapter(Context context, int resource, int
	 * textViewResourceId, String[] objects) { super(context,
	 * R.layout.ns_menu_row, textViewResourceId, objects); }
	 */

	public NavigationDrawerAdapter(Context context)
	{
		super(context, 0);
	}

	public void addHeader(int title)
	{
		add(new NavigationDrawerItem(title, -1, true));
	}

	public void addItem(int title, int icon)
	{
		add(new NavigationDrawerItem(title, icon, false));
	}

	public void addItem(NavigationDrawerItem itemModel)
	{
		add(itemModel);
	}

	@Override
	public int getViewTypeCount()
	{
		return 2;
	}

	@Override
	public int getItemViewType(int position)
	{
		return getItem(position).isHeader ? 0 : 1;
	}

	@Override
	public boolean isEnabled(int position)
	{
		return !getItem(position).isHeader;
	}

	public static class ViewHolder
	{
		public final TextView textHolder;
		public final ImageView imageHolder;

		public ViewHolder(TextView text1, ImageView image1)
		{
			this.textHolder = text1;
			this.imageHolder = image1;
		}
	}

	public View getView(int position, View convertView, ViewGroup parent)
	{

		NavigationDrawerItem item = getItem(position);
		Log.d(TAG, "Adding " + item.toString());
		ViewHolder holder = null;
		View view = convertView;

		if (view == null)
		{
			int layout = R.layout.drawer_list_item;
			if (item.isHeader)
				layout = R.layout.drawer_list_header;

			view = LayoutInflater.from(getContext()).inflate(layout, null);

			TextView text1 = (TextView) view.findViewById(R.id.title);
			ImageView image1 = (ImageView) view.findViewById(R.id.icon);
			view.setTag(new ViewHolder(text1, image1));
		}

		if (holder == null && view != null)
		{
			Object tag = view.getTag();
			if (tag instanceof ViewHolder)
			{
				holder = (ViewHolder) tag;
			}
		}

		if (item != null && holder != null)
		{
			if (holder.textHolder != null)
				holder.textHolder.setText(item.title);

			if (holder.imageHolder != null)
			{
				if (item.iconRes > 0)
				{
					holder.imageHolder.setVisibility(View.VISIBLE);
					holder.imageHolder.setImageResource(item.iconRes);
				} else
				{
					holder.imageHolder.setVisibility(View.GONE);
				}
			}
		}

		return view;
	}

}