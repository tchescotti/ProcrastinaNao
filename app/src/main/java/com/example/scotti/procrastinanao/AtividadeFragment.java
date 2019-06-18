package com.example.scotti.procrastinanao;

import android.app.usage.UsageStats;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class AtividadeFragment extends Fragment {

    private static final String TAG = "AtividadeFragmentTag";
    ListView listViewAtividade;
    ArrayList<Atividade> listAtividade = new ArrayList<>();

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_atividade, null);

        listViewAtividade = (ListView) view.findViewById(R.id._dynamicAtividade);

        listAtividade = ((MainActivity) getActivity()).UsageStats();

        if(getActivity() != null) {
            AtividadeAdapter adapter = new AtividadeAdapter(getActivity(), 0, listAtividade);
            adapter.notifyDataSetChanged();
            listViewAtividade.setAdapter(adapter);
        }

        return view;
    }
}
