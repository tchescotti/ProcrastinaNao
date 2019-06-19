package com.example.scotti.procrastinanao.pObjetivo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.scotti.procrastinanao.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

public class ObjetivoDescricaoDialog extends DialogFragment {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    EditText txtDescricao;

    String id, descricao;

    private static final String TAG = "ObjetivoDialog";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_edit_descricao, container, false);

        txtDescricao = view.findViewById(R.id.editTextAlterarDescricao);

        if(getArguments() != null){
            id = getArguments().getString("id");
            descricao = getArguments().getString("descricao");

            txtDescricao.setText(descricao);
        }

        Button btnAlterar = (Button) view.findViewById(R.id.buttonDialogDescricao);

        btnAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtDescricao.getText().toString().matches("")){

                    Toast.makeText(getContext(), "Dê uma descrição", Toast.LENGTH_SHORT).show();

                }else{
                    editDescricao(txtDescricao.getText().toString());
                    Toast.makeText(getContext(), "Descrição alterada", Toast.LENGTH_SHORT).show();

                    dismiss();
                    getFragmentManager().beginTransaction().replace(R.id.fragment_container, new ObjetivoFragment()).commit();
                }
            }
        });

        return view;
    }

    private void editDescricao(String descricao) {

        db.collection("objetivos").document(id)
                .update("descricao", descricao)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing document", e);
                    }
                });

    }
}
