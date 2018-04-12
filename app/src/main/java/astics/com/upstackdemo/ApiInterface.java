package astics.com.upstackdemo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("/v1/gifs/search")
    Call<ResponseModel> getSearchedGifs(@Query("q") String query,
                                       @Query("api_key") String apiKey);
}
