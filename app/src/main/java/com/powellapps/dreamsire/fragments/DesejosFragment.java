package com.powellapps.dreamsire.fragments;


import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.powellapps.dreamsire.R;
import com.powellapps.dreamsire.adapter.AdapterDesejos;
import com.powellapps.dreamsire.adapter.AdapterFeed;
import com.powellapps.dreamsire.dao.DesejoDAO;
import com.powellapps.dreamsire.dao.UsuarioDao;
import com.powellapps.dreamsire.model.Desejo;
import com.powellapps.dreamsire.model.DesejoFirebase;
import com.powellapps.dreamsire.model.Singleton;
import com.powellapps.dreamsire.model.Usuario;
import com.powellapps.dreamsire.utils.FirebaseUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class DesejosFragment extends Fragment {

    private ArrayList<Desejo> desejos;
    private DesejoDAO desejoDAO;
    private UsuarioDao usuarioDao;
    private AdapterDesejos adapterDesejos;

    public DesejosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_desejos, container, false);
        RecyclerView recyclerViewDesejos = (RecyclerView) view.findViewById(R.id.recycler_desejos);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerViewDesejos.setLayoutManager(linearLayoutManager);

        desejoDAO = new DesejoDAO(getContext());
        usuarioDao = new UsuarioDao(getContext());

        adapterDesejos = new AdapterDesejos(getActivity());
        recyclerViewDesejos.setAdapter(adapterDesejos);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        atualiza();
    }

    public void atualiza(){
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        try{
            progressDialog.setMessage("Buscando desejos...");
            progressDialog.show();
            adapterDesejos.atualiza(desejos);

            /*
            FirebaseDatabase ref = FirebaseDatabase.getInstance();
            final DatabaseReference databaseReference = ref.getReference("desejos");

            databaseReference.child("usuario").child("idRedeSocial").equalTo(usuarioDao.getUsuario().getIdRedeSocial());
            databaseReference.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    Log.v("dreamsire", "Data " + dataSnapshot  + " e " + s);
                    if(dataSnapshot.child("usuario").child("idRedeSocial").getValue() == usuarioDao.getUsuario().getIdRedeSocial()){
                        Log.v("dreamsire", "TO DE BOA ");

                    }else{
                        Log.v("dreamsire", "TO DE BOA no else");
                    }
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    try {
                        Log.v("", "ID " + usuarioDao.getUsuario().getIdRedeSocial());
                        Log.v("", "Data " + dataSnapshot);
                        GenericTypeIndicator<HashMap<String, DesejoFirebase>> objectsGTypeInd = new GenericTypeIndicator<HashMap<String, DesejoFirebase>>() {
                        };
                        Map<String, DesejoFirebase> objectHashMap = dataSnapshot.getValue(objectsGTypeInd);
                        ArrayList<DesejoFirebase> desejosFirebase = new ArrayList<>(objectHashMap.values());
                        adapterDesejos.atualiza(desejosFirebase);
                    }catch (Exception e){
                        e.printStackTrace();
                    }finally {
                        progressDialog.dismiss();
                    }
                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            */
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            progressDialog.dismiss();
        }

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.meus_desejos, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_backup:
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                alertDialog.setMessage("O que deseja fazer?");
                alertDialog.setTitle("Atenção");
                alertDialog.setPositiveButton("Exportar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.v("", "Desejos " + desejos.size());
                    }
                });


                alertDialog.setNegativeButton("Importar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

                if(usuarioDao.existeUsuario()) {
                    alertDialog.show();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
