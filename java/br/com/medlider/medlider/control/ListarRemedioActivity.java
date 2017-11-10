package br.com.medlider.medlider.control;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.com.medlider.medlider.DAO.MedLiderDAO;
import br.com.medlider.medlider.R;
import br.com.medlider.medlider.model.Cliente;
import br.com.medlider.medlider.model.Remedio;

public class ListarRemedioActivity extends AppCompatActivity {

    private ListView listaRemedios;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_remedio);

        //não mostrar o teclado quando a tela é aberta
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        //botão de voltar no canto superior esquerdo
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Mostrar o botão
        getSupportActionBar().setHomeButtonEnabled(true);      //Ativar o botão
        getSupportActionBar().setTitle("MedLíder");


        listaRemedios = (ListView) findViewById(R.id.tela_listar_remedio);//view listar remedios

        carregaLista();

        listaRemedios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View view, int posicao, long id) {
                Remedio remedio = (Remedio) listaRemedios.getItemAtPosition(posicao);

                Intent abreCadastroRemedio = new Intent(ListarRemedioActivity.this, CadastroRemedioActivity.class);
                abreCadastroRemedio.putExtra("remedio", remedio);
                startActivity(abreCadastroRemedio);
                finish();
            }
        });

        registerForContextMenu(listaRemedios);

    }

    private void carregaLista() {
        MedLiderDAO medLiderDAO = new MedLiderDAO(this);
        List<Remedio> remedios = medLiderDAO.BuscaRemedios();
        medLiderDAO.close();

        ArrayAdapter<Remedio> adapter = new ArrayAdapter<Remedio>(ListarRemedioActivity.this, android.R.layout.simple_list_item_1, remedios);
        listaRemedios.setAdapter(adapter);
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
        final Remedio remedio = (Remedio) listaRemedios.getItemAtPosition(info.position);

        MenuItem menuDeletar = menu.add("Deletar");

        menuDeletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                MedLiderDAO medLiderDAO = new MedLiderDAO(ListarRemedioActivity.this);
                medLiderDAO.DeletaRemedio(remedio);
                medLiderDAO.close();

                carregaLista();

                Toast.makeText(ListarRemedioActivity.this,"Remédio:  "+remedio.getNome()+" excluído!",Toast.LENGTH_SHORT).show();

                return false;
            }
        });

        MenuItem menuVender = menu.add("Vender");

        menuVender.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                Intent abreVendaRemedio = new Intent(ListarRemedioActivity.this, ListarClienteActivity.class);
                abreVendaRemedio.putExtra("remedio", remedio);
                startActivity(abreVendaRemedio);
                finish();

                return false;
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(ListarRemedioActivity.this, TelaBaseActivity.class));
        finish();
    }
}
