package ms.sapientia.minecraftservertrackerapp;

public interface OnGetServerCallback {

    void onSuccess(Server server);

    void onError(String msg);

}
