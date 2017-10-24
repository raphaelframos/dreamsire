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
import com.powellapps.dreamsire.dao.DesejoDAO;
import com.powellapps.dreamsire.dao.UsuarioDao;
import com.powellapps.dreamsire.model.Desejo;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class DesejosFragment extends Fragment {

    private ArrayList<Desejo> desejos = new ArrayList<>();
    private DesejoDAO desejoDAO;
    private UsuarioDao usuarioDao;

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
        AdapterDesejos adapterDesejos = new AdapterDesejos(getActivity());
        recyclerViewDesejos.setAdapter(adapterDesejos);
        desejoDAO = new DesejoDAO(getContext());
        usuarioDao = new UsuarioDao(getContext());
        desejos = desejoDAO.getDesejos(usuarioDao.getUsuario().getIdRedeSocial());
        adapterDesejos.atualiza(desejos);

    }
}
