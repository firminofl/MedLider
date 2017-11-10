package br.com.medlider.medlider.control;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

import br.com.medlider.medlider.Helper.ClienteHelper;
import br.com.medlider.medlider.DAO.MedLiderDAO;
import br.com.medlider.medlider.R;
import br.com.medlider.medlider.model.Cliente;

public class CadastroClienteActivity extends AppCompatActivity {

    private ClienteHelper clienteHelper;
    private Cliente cliente;
    private MedLiderDAO medLiderDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_cliente);

        //não mostrar o teclado quando a tela é aberta
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        //botão de voltar no canto superior esquerdo
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Mostrar o botão
        getSupportActionBar().setHomeButtonEnabled(true);      //Ativar o botão
        getSupportActionBar().setTitle("MedLíder");

        clienteHelper = new ClienteHelper(CadastroClienteActivity.this);

        Intent intent = getIntent();
        Cliente cliente = (Cliente) intent.getSerializableExtra("cliente");
        if(cliente != null){

            clienteHelper.PreencheCadastroCliente(cliente);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_cadastro_cliente,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { //Botão adicional na ToolBar
        switch (item.getItemId()) {
            case android.R.id.home:  //ID do seu botão (gerado automaticamente pelo android, usando como está, deve funcionar
                onBackPressed();
                break;
            case R.id.menu_cadastro_cliente:
                cliente = clienteHelper.PegaCliente();
                medLiderDAO = new MedLiderDAO(CadastroClienteActivity.this);

                if(cliente.getId() != null){
                    medLiderDAO.AlteraCliente(cliente);
                    Toast.makeText(CadastroClienteActivity.this,"Cliente: " + cliente.getNome() + " alterado!",Toast.LENGTH_SHORT).show();
                }else {
                    medLiderDAO.InsereCliente(cliente);
                    Toast.makeText(CadastroClienteActivity.this, "Cliente: " + cliente.getNome() + " salvo!", Toast.LENGTH_SHORT).show();
                }
                medLiderDAO.close();
                onBackPressed();
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(CadastroClienteActivity.this, TelaBaseActivity.class));
        finish();
    }
}
