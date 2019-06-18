package com.example.scotti.procrastinanao.pObjetivo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.scotti.procrastinanao.InicioFragment;
import com.example.scotti.procrastinanao.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

public class ObjetivoFragment extends Fragment {

    private static final String TAG = "ObjetivoFragment";

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_info_objetivo, container, false);

        TextView titulo = view.findViewById(R.id.textViewObjetivo);
        TextView dia = view.findViewById(R.id.TextViewDataCriacao);
        TextView etiqueta = view.findViewById(R.id.textViewEtiqueta);
        TextView descricao = view.findViewById(R.id.textViewDescricao);

        if(getArguments() != null){
            titulo.setText(getArguments().getString("nome"));
            dia.setText(getArguments().getString("dia"));
            etiqueta.setText(getArguments().getString("etiqueta"));
            descricao.setText(getArguments().getString("descricao"));
        }



        Button btnRemover = (Button) view.findViewById(R.id.buttonRemover);

        btnRemover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new InicioFragment()).commit();

                db.collection("objetivos").document(getArguments().getString("id"))
                        .delete()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d(TAG, "DocumentSnapshot successfully deleted!");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Error deleting document", e);
                            }
                        });

            }
        });


        Button btnEditSobre = (Button) view.findViewById(R.id.buttonEditSobre);

        btnEditSobre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjetivoSobreDialog dialogSobre = new ObjetivoSobreDialog();

                Bundle bundle = new Bundle();
                bundle.putString("nome", getArguments().getString("nome"));
                bundle.putString("dia", getArguments().getString("dia"));
                bundle.putString("etiqueta", getArguments().getString("etiqueta"));
                bundle.putString("descricao", getArguments().getString("descricao"));
                bundle.putString("id", getArguments().getString("id"));

                dialogSobre.setArguments(bundle);

                dialogSobre.show(getFragmentManager(), null);
            }
        });

        Button btnEditDescricao = (Button) view.findViewById(R.id.buttonEditDescricao);

        btnEditDescricao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjetivoDescricaoDialog dialogDescricao = new ObjetivoDescricaoDialog();

                Bundle bundle = new Bundle();
                bundle.putString("nome", getArguments().getString("nome"));
                bundle.putString("dia", getArguments().getString("dia"));
                bundle.putString("etiqueta", getArguments().getString("etiqueta"));
                bundle.putString("descricao", getArguments().getString("descricao"));
                bundle.putString("id", getArguments().getString("id"));

                dialogDescricao.setArguments(bundle);

                dialogDescricao.show(getFragmentManager(), null);
            }
        });

        return view;
    }

}
