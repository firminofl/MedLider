package br.com.medlider.medlider.control;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import br.com.medlider.medlider.DAO.MedLiderDAO;
import br.com.medlider.medlider.Helper.VendaHelper;
import br.com.medlider.medlider.R;
import br.com.medlider.medlider.model.Cliente;
import br.com.medlider.medlider.model.Remedio;
import br.com.medlider.medlider.model.Venda;

public class VenderRemedioActivity extends AppCompatActivity {

    private Remedio remedio;
    private Cliente cliente;
    private Venda venda;
    private MedLiderDAO medLiderDAO;
    private VendaHelper vendaHelper;
    private TextView campoTotalVenda;
    private EditText campoPrecoRemedio;
    private EditText campoQuantidadeRemedio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vender_remedio);

        //não mostrar o teclado quando a tela é aberta
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        //botão de voltar no canto superior esquerdo
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Mostrar o botão
        getSupportActionBar().setHomeButtonEnabled(true);      //Ativar o botão
        getSupportActionBar().setTitle("MedLíder");


        campoTotalVenda = (TextView) findViewById(R.id.tela_concluir_venda_total);
        campoQuantidadeRemedio = (EditText) findViewById(R.id.tela_concluir_venda_quantidade_remedio);

        vendaHelper = new VendaHelper(VenderRemedioActivity.this);

        mostraTelaVenda();

        final Double preco = Double.parseDouble(remedio.getPreco());

        campoQuantidadeRemedio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int quantidade = Integer.parseInt(campoQuantidadeRemedio.getText().toString());
                campoTotalVenda.setText(""+preco*quantidade);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_vender,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { //Botão adicional na ToolBar
        switch (item.getItemId()) {
            case android.R.id.home:  //ID do seu botão (gerado automaticamente pelo android, usando como está, deve funcionar
                onBackPressed();
                break;
            case R.id.menu_vender:
                venda = vendaHelper.PegaVenda();
                medLiderDAO = new MedLiderDAO(VenderRemedioActivity.this);

                medLiderDAO.InsereCliente(cliente);
                Toast.makeText(VenderRemedioActivity.this, "Venda efetuada com sucesso!", Toast.LENGTH_SHORT).show();

                medLiderDAO.close();
                onBackPressed();
                
                break;
            default:
                break;
        }
        return true;
    }

    /*
        @Override
        protected void onStop() {
            super.onStop();
            Intent intent = getIntent();
            remedio = (Remedio) intent.getSerializableExtra("remedio");

            if(remedio != null ){
                vendaHelper.PreencheVendaRemedio(remedio);
            }
        }
    */

    private void mostraTelaVenda() {

        Intent intent = getIntent();
        remedio = (Remedio) intent.getSerializableExtra("remedio");

        if(remedio != null ){
            vendaHelper.PreencheVendaRemedio(remedio);
        }

        cliente = (Cliente) intent.getSerializableExtra("cliente");
        if(cliente != null){
            vendaHelper.PreencheInfoCliente(cliente);
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(VenderRemedioActivity.this, TelaBaseActivity.class));
        finish();
    }
}
