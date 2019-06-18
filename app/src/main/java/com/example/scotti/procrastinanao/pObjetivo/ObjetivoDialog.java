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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ObjetivoDialog extends DialogFragment {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    private static final String TAG = "ObjetivoDialog";

    EditText nomeObjeto;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_add_objetivo, container, false);

        Button btnAdd = view.findViewById(R.id.buttonDialogAdd);

        nomeObjeto = view.findViewById(R.id.editTextAddNome);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nomeObjeto.getText().toString().matches("")){

                    Toast.makeText(getContext(), "Qual o seu objetivo?", Toast.LENGTH_SHORT).show();

                }else{
                    addObjetivo(nomeObjeto.getText().toString());
                    dismiss();
                }
            }
        });

        return view;
    }

    private void addObjetivo(String nome){

        Date dia = Calendar.getInstance().getTime();
        SimpleDateFormat formato = new SimpleDateFormat("dd.MM.yy");
        String stringDia = formato.format(dia);

        Objetivo objetivo = new Objetivo();

        objetivo.setNome(nome);
        objetivo.setDia(stringDia);


        db.collection("objetivos")
                .add(objetivo)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });
    }


}
