package com.example.scotti.procrastinanao;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.scotti.procrastinanao.pObjetivo.Objetivo;
import com.example.scotti.procrastinanao.pObjetivo.ObjetivoAdapter;
import com.example.scotti.procrastinanao.pObjetivo.ObjetivoDialog;
import com.example.scotti.procrastinanao.pObjetivo.ObjetivoFragment;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class InicioFragment extends Fragment {

    private static final String TAG = "InicioFragmentTag";
    ListView listViewObjetivo;
    ArrayList<Objetivo> listObjetivo = new ArrayList<>();

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_inicio, container, false);

        FloatingActionButton buttonAdd = (FloatingActionButton) view.findViewById(R.id.buttonAdd);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjetivoDialog dialog = new ObjetivoDialog();
                dialog.show(getFragmentManager(), null);
            }
        });

        listViewObjetivo = (ListView) view.findViewById(R.id._dynamicObjetivos);

        db.collection("objetivos").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {

                if(e != null){
                    Log.d(TAG, "OIA O ERRO AQUI: " + e.getMessage());
                }

                listObjetivo.clear();

                for (DocumentSnapshot snapshot : documentSnapshots) {
                    Objetivo objetivo =  new Objetivo();
                    objetivo.setNome(snapshot.getString("nome"));
                    objetivo.setDescricao(snapshot.getString("descricao"));
                    objetivo.setEtiqueta(snapshot.getString("etiqueta"));
                    objetivo.setId(snapshot.getId());
                    objetivo.setDia(snapshot.getString("dia"));

                    listObjetivo.add(objetivo);
                }

                if(getActivity() != null) {
                    ObjetivoAdapter adapter = new ObjetivoAdapter(getActivity(), 0, listObjetivo);
                    adapter.notifyDataSetChanged();
                    listViewObjetivo.setAdapter(adapter);
                }

            }
        });

        listViewObjetivo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Objetivo objetivo = (Objetivo) listViewObjetivo.getItemAtPosition(position);

                ObjetivoFragment objetivoFragment = new ObjetivoFragment();

                Bundle bundle = new Bundle();
                bundle.putString("nome", objetivo.getNome());
                bundle.putString("dia", "Criado em " + objetivo.getDia());
                bundle.putString("etiqueta", objetivo.getEtiqueta());
                bundle.putString("descricao", objetivo.getDescricao());
                bundle.putString("id", objetivo.getId());

                objetivoFragment.setArguments(bundle);

                getFragmentManager().beginTransaction().replace(R.id.fragment_container, objetivoFragment).commit();

            }
        });

        return view;
    }


}
