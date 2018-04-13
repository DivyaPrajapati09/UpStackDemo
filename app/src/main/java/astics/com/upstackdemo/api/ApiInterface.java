package astics.com.upstackdemo.api;

import astics.com.upstackdemo.model.ResponseModel;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface ApiInterface {

    @GET("/v1/gifs/search")
    Observable<ResponseModel> getSearchedGifs(@Query("q") String query,
                                              @Query("api_key") String apiKey);
}
