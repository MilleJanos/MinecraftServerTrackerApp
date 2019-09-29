package mj.minecraft.servertracker.api;

public interface OnPingServerCallback {

    void onSuccess(long pingMs);

    void onError(String msg);

}