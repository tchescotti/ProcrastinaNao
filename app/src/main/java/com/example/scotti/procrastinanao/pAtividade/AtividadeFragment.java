package com.example.scotti.procrastinanao.pAtividade;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.scotti.procrastinanao.MainActivity;
import com.example.scotti.procrastinanao.R;
import com.example.scotti.procrastinanao.pObjetivo.ObjetivoFragment;

import java.util.ArrayList;

public class AtividadeFragment extends Fragment {

    ListView listViewAtividade;
    ArrayList<Atividade> listAtividade = new ArrayList<>();
    Button btnPermissao;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_atividade, null);

        listViewAtividade = (ListView) view.findViewById(R.id._dynamicAtividade);

        btnPermissao = (Button) view.findViewById(R.id.buttonPermissao);

        //PERMISSAO
        if(((MainActivity) getActivity()).checkForPermission(getContext()) == false){
            btnPermissao.setVisibility(View.VISIBLE);
        }else{
            btnPermissao.setVisibility(View.INVISIBLE);
        }

        btnPermissao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS));

                ((MainActivity) getActivity()).navigation.setSelectedItemId(R.id.navigation_inicio);
            }
        });

        //VERIFICA LISTA
        if(((MainActivity) getActivity()).listUsageStats.isEmpty()) {
            ((MainActivity) getActivity()).listUsageStats = ((MainActivity) getActivity()).UsageStats();
        }

        listAtividade = ((MainActivity) getActivity()).listUsageStats;

        if(getActivity() != null) {
            AtividadeAdapter adapter = new AtividadeAdapter(getActivity(), 0, listAtividade);
            adapter.notifyDataSetChanged();
            listViewAtividade.setAdapter(adapter);
        }

        return view;
    }


}
