package br.com.mojumob.preencheendereco;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    //Atributos
    private EditText edtCep;
    private Button btnCarregar;
    private TextView txtLogradouro, txtBairro, txtCidade, txtEstado;
    private Retrofit retrofit;
    private List<Endereco> endereco;
    private Boolean isPesquisar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializaComponentes();

        //Iniciando o Retrofit
        retrofit = new Retrofit.Builder()
                .baseUrl("https://viacep.com.br/ws/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();



        btnCarregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isPesquisar){
                    recuperaCepRetrofit();
                    btnCarregar.setText("Nova Consulta");
                    isPesquisar = false;
                    edtCep.requestFocus();
                }else{
                    limparCampos();
                    btnCarregar.setText("Pesquisar");
                    isPesquisar = true;
                }
            }
        });
    }

    private void inicializaComponentes() {
        //Inicializações
        edtCep        = findViewById(R.id.edtCep);
        btnCarregar   = findViewById(R.id.btnCarregar);
        txtLogradouro = findViewById(R.id.txtLogradouro);
        txtBairro     = findViewById(R.id.txtBairro);
        txtCidade     = findViewById(R.id.txtCidade);
        txtEstado     = findViewById(R.id.txtEstado);
        isPesquisar   = true;
    }

    private void limparCampos() {
        txtLogradouro.setText("");
        txtBairro.setText("");
        txtCidade.setText("");
        txtEstado.setText("");
        edtCep.setText("");
    }

    private void recuperaCepRetrofit() {

        CepService cepService = retrofit.create(CepService.class);
        Call<Endereco> call = cepService.recuperaCep(edtCep.getText().toString());

        call.enqueue(new Callback<Endereco>() {
            @Override
            public void onResponse(Call<Endereco> call, Response<Endereco> response) {
                if(response.isSuccessful()){
                    Endereco endereco = response.body();
                    txtLogradouro.setText(endereco.getLogradouro());
                    txtBairro.setText(endereco.getBairro());
                    txtCidade.setText(endereco.getLogradouro());
                    txtEstado.setText(endereco.getUf());
                }
            }

            @Override
            public void onFailure(Call<Endereco> call, Throwable t) {

            }
        });

    }
}
