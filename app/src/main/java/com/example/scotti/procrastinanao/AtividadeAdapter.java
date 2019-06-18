package com.example.scotti.procrastinanao;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class AtividadeAdapter extends ArrayAdapter<Atividade> {

    Activity activity;
    ArrayList<Atividade> atividade;
    LayoutInflater inflater = null;

    public AtividadeAdapter (Activity activity, int textViewResourceId, ArrayList<Atividade> atividade){
        super(activity, textViewResourceId, atividade);

        try {
            this.activity = activity;
            this.atividade = atividade;

            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        } catch (Exception e) {

        }
    }

    public int getCount() {
        return atividade.size();
    }

    public Atividade getItem(Atividade position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        Atividade atividade = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_atividade, parent, false);
        }

        TextView nome = (TextView) convertView.findViewById(R.id.textViewNomeAtividade);
        TextView tempo = (TextView) convertView.findViewById(R.id.textViewTempo);
        ImageView icon = (ImageView) convertView.findViewById(R.id.imageViewIcon);

        nome.setText(atividade.getNome());
        tempo.setText(atividade.getTempo());
        icon.setImageDrawable(atividade.getIcon());

        return convertView;
    }
}
