package com.powellapps.dreamsire.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.powellapps.dreamsire.R;
import com.powellapps.dreamsire.adapter.AdapterDesejos;
import com.powellapps.dreamsire.dao.DesejoDAO;
import com.powellapps.dreamsire.dao.UsuarioDao;
import com.powellapps.dreamsire.model.Desejo;
import com.powellapps.dreamsire.model.Singleton;

import java.math.BigDecimal;
import java.util.ArrayList;

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
        try{
        getDesejos();
        adapterDesejos.atualiza(desejos);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void getDesejos() {

        try {
            desejos = desejoDAO.getDesejosOrdenados(usuarioDao.getUsuario().getIdRedeSocial());
        }catch (Exception e){
            desejos = new ArrayList<>();
            e.printStackTrace();
        }
    }

}
