package br.com.mojumob.preencheendereco;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    //Atributos
    private EditText edtCep;
    private Button btnCarregar, btnNovo;
    private TextView txtLogradouro, txtBairro, txtCidade, txtEstado;
    private Retrofit retrofit;
    private List<Endereco> endereco;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inicializações
        edtCep        = findViewById(R.id.edtCep);
        btnCarregar   = findViewById(R.id.btnCarregar);
        btnNovo       = findViewById(R.id.btnNovo);
        txtLogradouro = findViewById(R.id.txtLogradouro);
        txtBairro     = findViewById(R.id.txtBairro);
        txtCidade     = findViewById(R.id.txtCidade);
        txtEstado     = findViewById(R.id.txtEstado);

        //Iniciando o Retrofit
        retrofit = new Retrofit.Builder()
                .baseUrl("https://viacep.com.br/ws/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        btnCarregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recuperaListaRetrofit();
            }
        });
    }

    private void recuperaListaRetrofit() {

    }


}
