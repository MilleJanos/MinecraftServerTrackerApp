package ms.sapientia.minecraftservertrackerapp.utils;

import android.content.Context;
import android.telecom.Call;
import android.util.Log;
import android.widget.BaseAdapter;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

public class Utils {

    private static final String TAG = "Utils";

    /**
     * Returns the website HTML code.
     * @param context application context
     * @param url website url
     * @param callback callback after finishing work (use null to inacticate)
     */
    public static void GetHtmlFromWebsite(Context context, String url, Callback<String> callback){
        final Callback<String> fCallback = callback;
        try {
            Ion.with(context)
                    .load(url)
                    .asString()
                    .setCallback(new FutureCallback<String>() {
                        @Override
                        public void onCompleted(Exception e, String result) {
                            Log.i(TAG, "GetHtmlFromWebsite:OnCompleted");
                            if(fCallback != null) {
                                fCallback.Success(result);
                            }
                        }
                    });
        } catch (Exception e) {
            Log.e("GetHtmlFromWebsite", "Failed:" + e);
            if(fCallback != null) {
                fCallback.Failure(e.toString());
            }
        }
    }

}
