package br.com.medlider.medlider.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import br.com.medlider.medlider.model.Cliente;
import br.com.medlider.medlider.model.Remedio;
import br.com.medlider.medlider.model.Venda;

/**
 * Created by Filipe Firmino on 31/10/2017.
 */

public class MedLiderDAO extends SQLiteOpenHelper {


    public MedLiderDAO(Context context) {
        super(context, "Medlider", null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String sql1 = "CREATE TABLE Cliente (id INTEGER PRIMARY KEY, nome TEXT, cpf TEXT, rg TEXT, " +
                "telefone TEXT, endereco TEXT,sexo TEXT, idade TEXT);";

        String sql2 = "CREATE TABLE Remedio (id INTEGER PRIMARY KEY, nome TEXT, principioAtivo TEXT, tipoDosagem TEXT, " +
                "quantidade INTEGER, dataValidade TEXT,lote TEXT, tipo TEXT,preco TEXT);";
        String sql3 = "CREATE TABLE Venda (id INTEGER PRIMARY KEY, nomeRVenda TEXT, tipoRVenda TEXT, precoRVenda TEXT" +
                "qtRVenda INTEGER, totalRVenda TEXT, nomeCVenda TEXT, cpfCVenda TEXT);";

        sqLiteDatabase.execSQL(sql1);
        sqLiteDatabase.execSQL(sql2);
        sqLiteDatabase.execSQL(sql3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        String sql1 =  "DROP TABLE IF EXISTS Cliente";
        String sql2 =  "DROP TABLE IF EXISTS Remedio";
        String sql3 =  "DROP TABLE IF EXISTS Venda";
        sqLiteDatabase.execSQL(sql1);
        sqLiteDatabase.execSQL(sql2);
        sqLiteDatabase.execSQL(sql3);
        onCreate(sqLiteDatabase);
    }

    // CLIENTE
    public void InsereCliente(Cliente cliente) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dadosCliente = PegaDadosCliente(cliente);

        db.insert("Cliente",null,dadosCliente);
    }

    // REMEDIO
    public void InsereRemedio(Remedio remedio) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dadosRemedio = PegaDadosRemedio(remedio);
        db.insert("Remedio",null,dadosRemedio);
    }

    // VENDA
    public void InsereVenda(Venda venda) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dadosVenda = PegaDadosVenda(venda);
        db.insert("Venda",null,dadosVenda);
    }

    // CLIENTE
    private ContentValues PegaDadosCliente(Cliente cliente) {

        ContentValues dadosCliente = new ContentValues();

        dadosCliente.put("nome",cliente.getNome());
        dadosCliente.put("cpf",cliente.getCPF());
        dadosCliente.put("rg",cliente.getRG());
        dadosCliente.put("telefone",cliente.getTelefone());
        dadosCliente.put("endereco",cliente.getEndereco());
        dadosCliente.put("sexo",cliente.getSexo());
        dadosCliente.put("idade",cliente.getIdade());
        return dadosCliente;
    }

    // REMEDIO
    private ContentValues PegaDadosRemedio(Remedio remedio) {
        ContentValues dadosRemedio = new ContentValues();

        dadosRemedio.put("nome",remedio.getNome());
        dadosRemedio.put("principioAtivo",remedio.getPrincipioAtivo());
        dadosRemedio.put("tipoDosagem",remedio.getTipoDosagem());
        dadosRemedio.put("quantidade",remedio.getQuantidade());
        dadosRemedio.put("dataValidade",remedio.getDataValidade());
        dadosRemedio.put("lote",remedio.getLote());
        dadosRemedio.put("tipo",remedio.getTipo());
        dadosRemedio.put("preco",remedio.getPreco());

        return dadosRemedio;
    }

    // VENDA
    private ContentValues PegaDadosVenda(Venda venda) {
        ContentValues dadosVenda = new ContentValues();

        dadosVenda.put("nomeRVenda",venda.getVendaNomeRemedio());
        dadosVenda.put("tipoRVenda",venda.getVendaTipoRemedio());
        dadosVenda.put("precoRVenda",venda.getVendaPrecoRemedio());
        dadosVenda.put("qtRVenda",venda.getVendaQuantidadeRemedio());
        dadosVenda.put("totalRVenda",venda.getVendaTotalVenda());
        dadosVenda.put("nomeCVenda",venda.getVendaNomeCliente());
        dadosVenda.put("cpfCVenda",venda.getVendaCPFCliente());
        return dadosVenda;
    }

    // CLIENTE
    public List<Cliente> BuscaClientes() {

        String sql = "SELECT * FROM Cliente;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);
        List<Cliente> clientes = new ArrayList<Cliente>();

        while(c.moveToNext()){
            Cliente cliente = new Cliente();

            cliente.setId(c.getLong(c.getColumnIndex("id")));
            cliente.setNome(c.getString(c.getColumnIndex("nome")));
            cliente.setCPF(c.getString(c.getColumnIndex("cpf")));
            cliente.setRG(c.getString(c.getColumnIndex("rg")));
            cliente.setTelefone(c.getString(c.getColumnIndex("telefone")));
            cliente.setEndereco(c.getString(c.getColumnIndex("endereco")));
            cliente.setSexo(c.getString(c.getColumnIndex("sexo")));
            cliente.setIdade(c.getString(c.getColumnIndex("idade")));

            clientes.add(cliente);
        }

        c.close();
        return clientes;
    }

    // REMEDIO
    public List<Remedio> BuscaRemedios(){

        String sql = "SELECT * FROM Remedio;";
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery(sql,null);
        List<Remedio> remedios = new ArrayList<Remedio>();

        while (c.moveToNext()){
            Remedio remedio = new Remedio();

            remedio.setId(c.getLong(c.getColumnIndex("id")));
            remedio.setNome(c.getString(c.getColumnIndex("nome")));
            remedio.setPrincipioAtivo(c.getString(c.getColumnIndex("principioAtivo")));
            remedio.setTipoDosagem(c.getString(c.getColumnIndex("tipoDosagem")));
            remedio.setQuantidade(c.getString(c.getColumnIndex("quantidade")));
            remedio.setDataValidade(c.getString(c.getColumnIndex("dataValidade")));
            remedio.setLote(c.getString(c.getColumnIndex("lote")));
            remedio.setTipo(c.getString(c.getColumnIndex("tipo")));
            remedio.setPreco(c.getString(c.getColumnIndex("preco")));


            remedios.add(remedio);
        }
        c.close();
        return remedios;
    }

    // VENDA
    public List<Venda> BuscaVendas(){

        String sql = "SELECT * FROM Venda;";
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery(sql,null);
        List<Venda> vendas = new ArrayList<Venda>();

        while (c.moveToNext()){
            Venda venda = new Venda();

            venda.setId(c.getLong(c.getColumnIndex("id")));
            venda.setVendaNomeRemedio(c.getString(c.getColumnIndex("nomeRVenda")));
            venda.setVendaTipoRemedio(c.getString(c.getColumnIndex("tipoRVenda")));
            venda.setVendaPrecoRemedio(c.getString(c.getColumnIndex("precoRVenda")));
            venda.setVendaQuantidadeRemedio(c.getInt(c.getColumnIndex("qtRVenda")));
            venda.setVendaTotalVenda(c.getString(c.getColumnIndex("totalRVenda")));
            venda.setVendaNomeCliente(c.getString(c.getColumnIndex("nomeCVenda")));
            venda.setVendaCPFCliente(c.getString(c.getColumnIndex("cpfCVenda")));

            vendas.add(venda);
        }
        c.close();
        return vendas ;
    }

    public void AlteraCliente(Cliente cliente) {

        SQLiteDatabase db = getWritableDatabase();//ALTERADO
        ContentValues dadosCliente = PegaDadosCliente(cliente);

        String[] params = {cliente.getId().toString()};
        db.update("Cliente",dadosCliente,"id = ?", params);
    }

    public void AlteraRemedio(Remedio remedio){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dadosRemedio = PegaDadosRemedio(remedio);

        String[] params = {remedio.getId().toString()};

        db.update("Remedio",dadosRemedio,"id = ?",params);
    }

    public void DeletaCliente(Cliente cliente) {

        SQLiteDatabase db = getWritableDatabase();
        String[] params = {cliente.getId().toString()};

        db.delete("Cliente","id = ?",params);
    }

    public void DeletaRemedio(Remedio remedio){
        SQLiteDatabase db = getWritableDatabase();
        String[] params = {remedio.getId().toString()};

        db.delete("Remedio","id = ?",params);
    }


}
