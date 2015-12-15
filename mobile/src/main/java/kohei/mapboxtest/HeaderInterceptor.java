package kohei.mapboxtest;

import android.content.Context;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by kohei on 15/12/11.
 */
public class HeaderInterceptor implements Interceptor {

    private Context context;

    public HeaderInterceptor(Context context) {
        super();
        this.context = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {


        Request originalRequest = chain.request();

        Request.Builder builder = originalRequest.newBuilder()
                .removeHeader("User-Agent")
                .addHeader("User-Agent", "MapboxGL/1.0");

        Request request = builder.build();

        return chain.proceed(request);
    }

}