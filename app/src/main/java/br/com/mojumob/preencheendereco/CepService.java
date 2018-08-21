package br.com.mojumob.preencheendereco;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CepService {


    @GET("{cep}/json")
    Call<Endereco> recuperaCep(@Path("cep") String cep);


}
