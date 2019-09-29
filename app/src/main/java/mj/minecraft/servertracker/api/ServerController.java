package mj.minecraft.servertracker.api;

import android.util.Log;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import mj.minecraft.servertracker.model.Server;
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
                        Log.d(TAG, "getServer: onSubscribe: ");
                    }

                    @Override
                    public void onNext(Server server) {
                        Log.d(TAG, "getServer: onNext: get server.");
                        if(callback!=null){
                            callback.onSuccess(server);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "getServer: onError: ");
                        if(callback!=null){
                            callback.onError("Error: can't get server with ip: " + serverIp);
                        }
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "getServer: onComplete: ");
                    }
                });
    }

    public void pingServer(final String serverIp, final OnPingServerCallback callback ){
        final long startTime = System.currentTimeMillis();
        mApi.pingServer(serverIp)
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<PingResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "pingServer: onSubscribe: ");
                    }

                    @Override
                    public void onNext(PingResponse response) {
                        Log.d(TAG, "pingServer: onNext: ");
                        if(callback!=null){
                            callback.onSuccess( System.currentTimeMillis() - startTime );
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "pingServer: onError: ");
                        if(callback!=null){
                            callback.onError("Error: can't ping server with ip: " + serverIp);
                        }
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "pingServer: onComplete: ");
                    }
                });
    }

}
