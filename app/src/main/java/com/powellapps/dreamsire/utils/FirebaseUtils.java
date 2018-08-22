package com.powellapps.dreamsire.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.powellapps.dreamsire.R;
import com.powellapps.dreamsire.dao.DesejoDAO;
import com.powellapps.dreamsire.dao.UsuarioDao;
import com.powellapps.dreamsire.fragments.DesejosFragment;
import com.powellapps.dreamsire.model.Desejo;
import com.powellapps.dreamsire.model.DesejoFirebase;
import com.powellapps.dreamsire.model.Usuario;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by raphaelramos on 04/11/17.
 */

public class FirebaseUtils {
    public static void salva(Desejo desejo) {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("desejos");
        String id = myRef.push().getKey();
        desejo.setId(id);
        myRef.child(id).setValue(desejo);
    }

    public static void atualizaCurtidas(DesejoFirebase desejoFirebase) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("desejos");
        HashMap<String, Object> map = new HashMap<>();
        map.put("curtidas", desejoFirebase.getCurtidas());
        myRef.child(desejoFirebase.getId()).updateChildren(map);
    }

    public static void atualiza(DesejoFirebase desejoFirebase) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("desejos");
        HashMap<String, Object> map = new HashMap<>();
        map.put("titulo", desejoFirebase.getDesejo().getTitulo());
        map.put("descricao", desejoFirebase.getDesejo().getDescricao());
        map.put("status", desejoFirebase.getDesejo().getStatus());
        map.put("valor", desejoFirebase.getDesejo().getValor());
        myRef.child(desejoFirebase.getId()).child("desejo").updateChildren(map);
    }

    public static void remove(DesejoFirebase desejoFirebase) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("desejos");
        myRef.child(desejoFirebase.getId()).removeValue();
    }
}
