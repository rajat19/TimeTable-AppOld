package in.akgec.timetable;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by Rajat on 07-Feb-17.
 */
public class RequestController extends Application{
    private static RequestController instance;
    private RequestQueue requestQueue;
    private static Context ctx;
    public static final String TAG = "Volley";

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        ctx = getApplicationContext();
    }

    public RequestController() {

    }

    private RequestController(Context context) {
        ctx = context;
        requestQueue = getRequestQueue();
    }

    public static synchronized RequestController getInstance(Context context) {
        if(instance == null) {
            instance = new RequestController(context);
        }
        return instance;
    }

    public RequestQueue getRequestQueue() {
        if(requestQueue == null) {
            requestQueue = Volley.newRequestQueue(ctx.getApplicationContext());
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests() {
        if(requestQueue != null) {
            requestQueue.cancelAll(TAG);
        }
    }
}
