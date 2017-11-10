package br.com.medlider.medlider.control;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.com.medlider.medlider.DAO.MedLiderDAO;
import br.com.medlider.medlider.Helper.VendaHelper;
import br.com.medlider.medlider.R;
import br.com.medlider.medlider.model.Cliente;
import br.com.medlider.medlider.model.Remedio;

public class ListarClienteActivity extends AppCompatActivity {
    private ListView listaClientes;
    private VendaHelper vendaHelper;
    private Remedio remedio;
    private VenderRemedioActivity venderRemedioActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_cliente);

        //não mostrar o teclado quando a tela é aberta
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        //botão de voltar no canto superior esquerdo
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Mostrar o botão
        getSupportActionBar().setHomeButtonEnabled(true);      //Ativar o botão

        getSupportActionBar().setTitle("MedLíder");


        listaClientes = (ListView) findViewById(R.id.tela_listar_cliente);//view listar clientes

        carregaLista();

        listaClientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View item, int posicao, long id) {
                Cliente cliente = (Cliente) listaClientes.getItemAtPosition(posicao);

                Intent abreCadastroCliente = new Intent(ListarClienteActivity.this, CadastroClienteActivity.class);
                abreCadastroCliente.putExtra("cliente", cliente);
                startActivity(abreCadastroCliente);
                finish();
            }
        });

        registerForContextMenu(listaClientes);
    }

    private void carregaLista() {
        MedLiderDAO medLiderDAO = new MedLiderDAO(this);
        List<Cliente> clientes = medLiderDAO.BuscaClientes();
        medLiderDAO.close();

        ArrayAdapter<Cliente> adapter = new ArrayAdapter<Cliente>(ListarClienteActivity.this, android.R.layout.simple_list_item_1, clientes);
        listaClientes.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { //Botão adicional na ToolBar
        switch (item.getItemId()) {
            case android.R.id.home:  //ID do seu botão (gerado automaticamente pelo android, usando como está, deve funcionar
                onBackPressed();
                break;

            default:
                break;
        }
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        final Cliente cliente = (Cliente) listaClientes.getItemAtPosition(info.position);

        MenuItem menuDeletar = menu.add("Deletar");

        menuDeletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                MedLiderDAO medLiderDAO = new MedLiderDAO(ListarClienteActivity.this);
                medLiderDAO.DeletaCliente(cliente);
                medLiderDAO.close();

                carregaLista();

                Toast.makeText(ListarClienteActivity.this,"Cliente:  "+cliente.getNome()+" excluído!",Toast.LENGTH_SHORT).show();

                return false;
            }
        });

        MenuItem menuSelecionar = menu.add("Selecionar para venda");

        menuSelecionar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                Intent intent = getIntent();
                remedio = (Remedio) intent.getSerializableExtra("remedio");

                Intent abreVendaRemedio = new Intent(ListarClienteActivity.this, VenderRemedioActivity.class);
                if(remedio != null ){
                  // vendaHelper.PreencheVendaRemedio(remedio);
                    abreVendaRemedio.putExtra("remedio",remedio);
                }


                abreVendaRemedio.putExtra("cliente", cliente);

                startActivity(abreVendaRemedio);
                finish();

                return false;
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(ListarClienteActivity.this, TelaBaseActivity.class));
        finish();
    }
}

