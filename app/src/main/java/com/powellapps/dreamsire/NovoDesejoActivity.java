package com.powellapps.dreamsire;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.powellapps.dreamsire.dao.DesejoDAO;
import com.powellapps.dreamsire.model.Desejo;

public class NovoDesejoActivity extends AppCompatActivity {

    private static final String NOVO_DESEJO = "novo_desejo";
    private EditText editTextTitulo;
    private EditText editTextValor;
    private Spinner spinnerTipos;
    private Spinner spinnerStatus;
    private Button buttonSalvar;
    private DesejoDAO desejoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_desejo);
        desejoDAO = new DesejoDAO(this);
        definindoViews();
        buttonSalvar.setOnClickListener(salva);

    }

    private View.OnClickListener salva = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.v(NOVO_DESEJO, "Quantidade de desejos no banco "
                    + desejoDAO.getQuantidadeDeDesejos());
            Desejo desejo = capturaDados();
            desejoDAO.salva(desejo);
            Log.v(NOVO_DESEJO, "Quantidade de desejos no banco "
                    + desejoDAO.getQuantidadeDeDesejos());
        }
    };

    private Desejo capturaDados() {
        Desejo desejo = new Desejo();
        String titulo = editTextTitulo.getText().toString();
        String valor = editTextValor.getText().toString();
        String status = (String) spinnerStatus.getSelectedItem();
        String tipo = spinnerTipos.getSelectedItem().toString();
        desejo.setValor(valor);
        desejo.setTipo(tipo);
        desejo.setStatus(status);
        desejo.setTitulo(titulo);
        return desejo;
    }

    private void definindoViews() {
        editTextTitulo = (EditText) findViewById(R.id.edit_titulo);
        editTextValor = (EditText) findViewById(R.id.edit_valor);
        spinnerStatus = (Spinner) findViewById(R.id.spinner_status);
        spinnerTipos = (Spinner) findViewById(R.id.spinner_tipos);
        buttonSalvar = (Button) findViewById(R.id.button_salvar);
    }
}
