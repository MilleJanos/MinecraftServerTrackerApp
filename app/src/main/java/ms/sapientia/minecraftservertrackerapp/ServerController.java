package ms.sapientia.minecraftservertrackerapp;

import android.util.Log;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServerController {

    private static final String TAG = "ServerController";
    private static final String BASE_API = "https:/api.mcsrvstat.us/";
    private static ServerController mInstance;
    private MCServerApi mApi;

    private ServerController(MCServerApi api){
        mApi = api;
    }

    public static ServerController getInstance() {
        if(mInstance == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_API)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            mInstance = new ServerController(retrofit.create(MCServerApi.class));
        }
        return mInstance;
    }

    public void getServer(final String serverIp, final OnGetServerCallback callback){

        mApi.getServer(serverIp)
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Server>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe: ");
                    }

                    @Override
                    public void onNext(Server server) {
                        Log.d(TAG, "onNext: get server.");
                        if(callback!=null){
                            callback.onSuccess(server);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: ");
                        if(callback!=null){
                            callback.onError("Error: can't get server with ip: " + serverIp);
                        }
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: ");
                    }
                });
    }

}
