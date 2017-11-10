package br.com.medlider.medlider.control;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.medlider.medlider.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //não mostrar o teclado quando a tela é aberta
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        getSupportActionBar().setTitle("MedLíder");

        Button botaoEntrar = (Button) findViewById(R.id.tela_login_entrar);
        final Intent abrirTelaBase = new Intent(LoginActivity.this,TelaBaseActivity.class);


        botaoEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText campoUsuario = (EditText) findViewById(R.id.tela_login_usuario);
                EditText campoSenha = (EditText) findViewById(R.id.tela_login_senha);

                if(campoUsuario.getText().toString().trim().equals("a") && campoSenha.getText().toString().trim().equals("a")){
                    Toast.makeText(LoginActivity.this,"Usuário "+campoUsuario.getText().toString()+" acessou o sistema!",Toast.LENGTH_SHORT).show();
                    startActivity(abrirTelaBase);
                    finish();
                }else{
                    Toast.makeText(LoginActivity.this,"Usuário ou senha incorretos!",Toast.LENGTH_SHORT).show();
                }
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
