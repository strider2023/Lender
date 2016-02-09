package com.mbv.pokket.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mbv.pokket.R;
import com.mbv.pokket.dao.TimelineDAO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arindamnath on 07/02/16.
 */
public class TimelineListAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<TimelineDAO> timelineDAOs = new ArrayList<>();

    public TimelineListAdapter(Context contenxt) {
        this.context = contenxt;
        layoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(List<TimelineDAO> data) {
        timelineDAOs = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return timelineDAOs.size();
    }

    @Override
    public TimelineDAO getItem(int position) {
        return timelineDAOs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.adapter_timeline, parent, false);
            holder.content = (TextView) convertView.findViewById(R.id.adapter_timeline_content);
            holder.date = (TextView) convertView.findViewById(R.id.adapter_timeline_date);
            holder.typeImage = (ImageView) convertView.findViewById(R.id.adapter_timeline_type_image);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.content.setText(timelineDAOs.get(position).getContent());
        holder.date.setText(timelineDAOs.get(position).getDate());
        holder.typeImage.setImageResource(timelineDAOs.get(position).getTypeImage());
        holder.typeImage.setBackgroundResource(timelineDAOs.get(position).getTypeImageBG());
        return convertView;
    }

    static class ViewHolder {
        TextView content, date;
        ImageView typeImage;
    }
}
