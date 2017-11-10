package br.com.medlider.medlider.control;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import br.com.medlider.medlider.DAO.MedLiderDAO;
import br.com.medlider.medlider.Helper.RemedioHelper;
import br.com.medlider.medlider.R;
import br.com.medlider.medlider.model.Remedio;

public class CadastroRemedioActivity extends AppCompatActivity {

    private TextView remedioDataValidade;
    private Calendar cal = Calendar.getInstance();

    private RemedioHelper remedioHelper;
    private MedLiderDAO medLiderDAO;
    private Remedio remedio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_remedio);

        //não mostrar o teclado quando a tela é aberta
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        //botão de voltar no canto superior esquerdo
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Mostrar o botão
        getSupportActionBar().setHomeButtonEnabled(true);      //Ativar o botão
        getSupportActionBar().setTitle("MedLíder");

        remedioDataValidade = (TextView)findViewById(R.id.tela_cadastro_remedio_data_validade);
        remedioDataValidade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateDate();
            }
        });

        remedioHelper = new RemedioHelper(CadastroRemedioActivity.this);

        Intent intent = getIntent();
        remedio = (Remedio) intent.getSerializableExtra("remedio");
        if(remedio != null){

            remedioHelper.PreencheCadastroRemedio(remedio);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_cadastro_remedio,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { //Botão adicional na ToolBar
        switch (item.getItemId()) {
            case android.R.id.home:  //ID do seu botão (gerado automaticamente pelo android, usando como está, deve funcionar
                onBackPressed();
                break;
            case R.id.menu_cadastro_remedio:
                remedio = remedioHelper.PegaRemedio();
                medLiderDAO = new MedLiderDAO(CadastroRemedioActivity.this);

                if(remedio.getId() != null){
                    medLiderDAO.AlteraRemedio(remedio);
                    Toast.makeText(CadastroRemedioActivity.this,"Remédio: " + remedio.getNome() + " alterado!",Toast.LENGTH_SHORT).show();
                }else {
                    medLiderDAO.InsereRemedio(remedio);
                    Toast.makeText(CadastroRemedioActivity.this, "Remédio: " + remedio.getNome() + " salvo!", Toast.LENGTH_SHORT).show();
                }
                medLiderDAO.close();
                onBackPressed();
                break;
            default:
                break;
        }
        return true;
    }

    private void updateDate() {
        new DatePickerDialog(this, d, cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH)).show();
    }

    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
            cal.set(Calendar.YEAR, year);
            cal.set(Calendar.MONTH, monthOfYear);
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            remedioDataValidade.setText(dayOfMonth+"/"+(monthOfYear+1)+"/"+year);
        }
    };

    @Override
    public void onBackPressed() {
        startActivity(new Intent(CadastroRemedioActivity.this, TelaBaseActivity.class));
        finish();
    }
}
