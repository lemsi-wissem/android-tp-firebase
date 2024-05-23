package com.example.tp5firebase;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyAdapter extends ArrayAdapter {

    Activity context;
    ArrayList<String> resources;

    public MyAdapter(Activity context, ArrayList<String> resources) {
        super(context, R.layout.list_line, resources);
        this.context = context;
        this.resources = resources;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View element = inflater.inflate(R.layout.list_line, null);

        ImageView img = (ImageView) element.findViewById(R.id.image);
        TextView note = (TextView) element.findViewById(R.id.note);

        note.setText(resources.get(position));
        float valeur = Float.valueOf(resources.get(position));

        if(valeur >= 10)
            img.setImageResource(R.drawable.icon_mood);
        else
            img.setImageResource(R.drawable.icon_mood_cry);

        return element;

    }
}
