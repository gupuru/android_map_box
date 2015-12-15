package kohei.mapboxtest;

import android.os.Handler;
import android.os.Looper;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public abstract class GetMapData {
    private OkHttpClient client;
    private HttpUrl url;

    public GetMapData(){
        super();
        this.client = new OkHttpClient();

        HttpUrl.Builder builder = new HttpUrl.Builder()
                .scheme("http")
                .host("a.tiles.mapbox.com")
                .addPathSegment("v4").addPathSegment("mapbox.mapbox-streets-v6").addPathSegment("14")
                .addPathSegment("4823").addPathSegment("6160.vector.pbf").addQueryParameter("access_token", "pk.eyJ1Ijoic2VmdXJpa29oZWkiLCJhIjoiY2loenV6bjVxMDRrcXVra290ZW83ZjA0NSJ9.xhF8kOKgGhuE0TCSg90VwA");
        this.url = builder.build();
    }

    public GetMapData run(){
        Request request = new Request.Builder().url(url).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(final Request request, final IOException e){
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        onFail(request.toString());
                    }
                });
            }

            @Override
            public void onResponse(final Response response) throws IOException {
                if (!response.isSuccessful() || response.body() == null) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            onFail(response.toString());
                        }
                    });
                    return;
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        onSuccess(response);
                    }
                });
            }
            private void runOnUiThread(Runnable task) {
                new Handler(Looper.getMainLooper()).post(task);
            }
        });
        return null;
    }

    abstract public void onFail(String data);

    abstract public void onSuccess(Response data);

}