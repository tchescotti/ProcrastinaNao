package com.example.scotti.procrastinanao.pObjetivo;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.scotti.procrastinanao.R;

import java.util.ArrayList;

public class ObjetivoAdapter extends ArrayAdapter<Objetivo> {

    Activity activity;
    ArrayList<Objetivo> objetivo;
    LayoutInflater inflater = null;


    public ObjetivoAdapter (Activity activity, int textViewResourceId, ArrayList<Objetivo> objetivo) {
        super(activity, textViewResourceId, objetivo);

        try {
            this.activity = activity;
            this.objetivo = objetivo;

            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        } catch (Exception e) {

        }
    }

    public int getCount() {
        return objetivo.size();
    }

    public Objetivo getItem(Objetivo position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        Objetivo objetivo = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_objetivo, parent, false);
        }

        TextView nome = (TextView) convertView.findViewById(R.id.textViewNomeObjetivo);

        nome.setText(objetivo.getNome());

        return convertView;
    }
}