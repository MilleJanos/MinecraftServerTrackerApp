package ms.sapientia.minecraftservertrackerapp;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MCServerApi {

    @GET("2/{ip}")
    Flowable<Server> getServer(
            @Path("ip") String serverIp
    );

}
