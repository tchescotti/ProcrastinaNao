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

import com.example.scotti.procrastinanao.InicioFragment;
import com.example.scotti.procrastinanao.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

public class ObjetivoSobreDialog extends DialogFragment {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    EditText nomeEtiqueta;

    String id, etiqueta;

    private static final String TAG = "ObjetivoDialog";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_edit_sobre, container, false);

        nomeEtiqueta = view.findViewById(R.id.editTextAlterarEtiqueta);

        if(getArguments() != null){
            id = getArguments().getString("id");
            etiqueta = getArguments().getString("etiqueta");

            nomeEtiqueta.setText(etiqueta);
        }

        Button btnAlterar = (Button) view.findViewById(R.id.buttonDialogSobre);

        btnAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nomeEtiqueta.getText().toString().matches("")){

                    Toast.makeText(getContext(), "Dê um nome para a etiqueta", Toast.LENGTH_SHORT).show();

                }else{
                    editEtiqueta(nomeEtiqueta.getText().toString());
                    Toast.makeText(getContext(), "Etiqueta alterada", Toast.LENGTH_SHORT).show();

                    dismiss();
                    getFragmentManager().beginTransaction().replace(R.id.fragment_container, new InicioFragment()).commit();
                }
            }
        });

        return view;
    }

    private void editEtiqueta(String etiqueta) {

        db.collection("objetivos").document(id)
                .update("etiqueta", etiqueta)
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
