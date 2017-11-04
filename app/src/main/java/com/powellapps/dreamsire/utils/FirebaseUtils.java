package com.powellapps.dreamsire.utils;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.powellapps.dreamsire.model.Desejo;
import com.powellapps.dreamsire.model.DesejoFirebase;
import com.powellapps.dreamsire.model.Usuario;

import java.util.HashMap;

/**
 * Created by raphaelramos on 04/11/17.
 */

public class FirebaseUtils {
    public static void salva(Usuario usuario, Desejo desejo) {
        DesejoFirebase desejoFirebase = new DesejoFirebase();
        desejoFirebase.setDesejo(desejo);
        desejoFirebase.setUsuario(usuario);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("desejos");
        myRef.child(desejoFirebase.retornaKey()).setValue(desejoFirebase).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(Task<Void> task) {
               // finaliza();
            }
        });
    }

    public static void atualiza(DesejoFirebase desejoFirebase) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("desejos");
        HashMap<String, Object> map = new HashMap<>();
        map.put("curtidas", desejoFirebase.getCurtidas());
        myRef.child(desejoFirebase.retornaKey()).updateChildren(map);
    }
}
