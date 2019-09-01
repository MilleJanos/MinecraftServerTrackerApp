package ms.sapientia.minecraftservertrackerapp.utils;

public interface Callback<T> {

    public void Success(T result);
    public void Failure(String errorMessage);

}
