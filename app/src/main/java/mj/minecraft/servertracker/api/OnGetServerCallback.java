package mj.minecraft.servertracker.api;

import mj.minecraft.servertracker.model.Server;

public interface OnGetServerCallback {

    void onSuccess(Server server);

    void onError(String msg);

}
