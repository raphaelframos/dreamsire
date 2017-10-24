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

    private ArrayList<Desejo> desejos;
    private DesejoDAO desejoDAO;
    private UsuarioDao usuarioDao;

    public DesejosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_desejos, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        RecyclerView recyclerViewDesejos = (RecyclerView) getView().findViewById(R.id.recycler_desejos);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerViewDesejos.setLayoutManager(linearLayoutManager);
        AdapterDesejos adapterDesejos = new AdapterDesejos(getActivity());
        recyclerViewDesejos.setAdapter(adapterDesejos);
        getDesejos();
        adapterDesejos.atualiza(desejos);

    }

    private void getDesejos() {
        desejoDAO = new DesejoDAO(getContext());
        usuarioDao = new UsuarioDao(getContext());
        try {
            desejos = desejoDAO.getDesejos(usuarioDao.getUsuario().getIdRedeSocial());
        }catch (Exception e){
            desejos = new ArrayList<>();
            e.printStackTrace();
        }
    }
}
