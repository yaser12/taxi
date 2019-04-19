package database.entity;

import android.app.IntentService;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;

import utility.HttpHelper;

public class OrderTaxiJsonFetcherService extends IntentService
{

    public static final String TAG = "OrderTaxiJsonService";

    public OrderTaxiJsonFetcherService()
    {
        super("OrderTaxiJsonFetcherService");
    }
    ////////////////////////   ADD_NEW_TAXI //////////////////////////
    public static  final String ADD_NEW_ORDER_TAXI_JSON_URL="http://syriataxi.000webhostapp.com/add_new_taxi.php/?";
    public static final String ADD_NEW_ORDER_TAXI_MESSAGE = "ADD_NEW_TAXI_Message";
    public static final String ADD_NEW_ORDER_TAXI_PAYLOAD = "ADD_NEW_TAXI_Payload";


    @Override
    protected void onHandleIntent(Intent intent) {
        Uri uri = intent.getData();
        Log.i(TAG, "onHandleIntent: " + uri.toString());

        String response;
        try {
            response = HttpHelper.downloadUrl(uri.toString());
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Gson gson = new Gson();
        OrderTaxi[] dataItems = gson.fromJson(response, OrderTaxi[].class);

        Intent messageIntent = new Intent(ADD_NEW_ORDER_TAXI_MESSAGE);
        messageIntent.putExtra(ADD_NEW_ORDER_TAXI_PAYLOAD, dataItems);
        LocalBroadcastManager manager =
                LocalBroadcastManager.getInstance(getApplicationContext());
        manager.sendBroadcast(messageIntent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }
}