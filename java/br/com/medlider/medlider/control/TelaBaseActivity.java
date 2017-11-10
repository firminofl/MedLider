package br.com.medlider.medlider.control;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import br.com.medlider.medlider.R;

public class TelaBaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_base);

        getSupportActionBar().setTitle("MedLíder");

        Button botaoCadastraCliente = (Button) findViewById(R.id.tela_base_botao_cadastrar_cliente);
        final Intent abrirTelaCadastraCliente = new Intent(TelaBaseActivity.this,CadastroClienteActivity.class);

        botaoCadastraCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(abrirTelaCadastraCliente);
                finish();
            }
        });

        Button botaoCadastraRemedio = (Button) findViewById(R.id.tela_base_botao_cadastrar_remedio);
        final Intent abrirTelaCadastraRemedio = new Intent(TelaBaseActivity.this,CadastroRemedioActivity.class);

        botaoCadastraRemedio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(abrirTelaCadastraRemedio);
                finish();
            }
        });

        Button botaoListaCliente = (Button) findViewById(R.id.tela_base_botao_listar_cliente);
        final Intent abrirListaCliente = new Intent(TelaBaseActivity.this,ListarClienteActivity.class);

        botaoListaCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(abrirListaCliente);
                finish();
            }
        });

        Button botaoListaRemedio = (Button) findViewById(R.id.tela_base_botao_listar_remedio);
        final Intent abrirListaRemedio = new Intent(TelaBaseActivity.this,ListarRemedioActivity.class);

        botaoListaRemedio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(abrirListaRemedio);
                finish();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_sair,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { //Botão adicional na ToolBar
        switch (item.getItemId()) {
            case R.id.menu_login_sair:
                finish();
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
