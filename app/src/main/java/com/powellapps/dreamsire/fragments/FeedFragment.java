package com.powellapps.dreamsire.fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.powellapps.dreamsire.R;
import com.powellapps.dreamsire.adapter.AdapterFeed;
import com.powellapps.dreamsire.dao.UsuarioDao;
import com.powellapps.dreamsire.model.Desejo;
import com.powellapps.dreamsire.model.DesejoFirebase;
import com.powellapps.dreamsire.model.Usuario;
import com.powellapps.dreamsire.ws.APIClient;
import com.powellapps.dreamsire.ws.APIService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class FeedFragment extends Fragment {


    public FeedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feed, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final RecyclerView recyclerView = getView().findViewById(R.id.recycler_feed);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(gridLayoutManager);

        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Buscando desejos...");
        progressDialog.show();
        FirebaseDatabase ref = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = ref.getReference("desejos");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                try {
                    GenericTypeIndicator<HashMap<String, Desejo>> objectsGTypeInd = new GenericTypeIndicator<HashMap<String, Desejo>>() {
                    };
                    Map<String, Desejo> objectHashMap = dataSnapshot.getValue(objectsGTypeInd);
                    ArrayList<Desejo> desejosFirebase = new ArrayList<>(objectHashMap.values());
                    AdapterFeed adapterFeed = new AdapterFeed(getActivity(), desejosFirebase);
                    recyclerView.setAdapter(adapterFeed);
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
