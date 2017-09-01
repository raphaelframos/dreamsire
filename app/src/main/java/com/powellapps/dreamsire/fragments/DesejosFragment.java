package com.powellapps.dreamsire.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.powellapps.dreamsire.R;
import com.powellapps.dreamsire.adapter.AdapterDesejos;
import com.powellapps.dreamsire.model.Desejo;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class DesejosFragment extends Fragment {

    private ArrayList<Desejo> desejos = new ArrayList<>();

    public DesejosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_desejos, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        RecyclerView recyclerViewDesejos = (RecyclerView) getView().findViewById(R.id.recycler_desejos);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerViewDesejos.setLayoutManager(linearLayoutManager);
        AdapterDesejos adapterDesejos = new AdapterDesejos();
        recyclerViewDesejos.setAdapter(adapterDesejos);
        Desejo desejo = new Desejo();
        desejo.setTitulo("Save the world");
        desejo.setStatus("Status");
        desejo.setTipo("Tipo");
        desejo.setValor(BigDecimal.TEN);
        desejos.add(desejo);
        desejos.add(desejo);

        desejos.add(desejo);desejos.add(desejo);desejos.add(desejo);desejos.add(desejo);desejos.add(desejo);desejos.add(desejo);desejos.add(desejo);desejos.add(desejo);
        adapterDesejos.atualiza(desejos);

    }
}
