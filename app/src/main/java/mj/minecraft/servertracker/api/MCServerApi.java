package mj.minecraft.servertracker.api;

import io.reactivex.Flowable;
import mj.minecraft.servertracker.model.Server;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MCServerApi {

    @GET("2/{ip}")
    Flowable<Server> getServer(
            @Path("ip") String serverIp
    );

    @GET("ping/{ip}")
    Flowable<PingResponse> pingServer(
            @Path("ip") String serverIp
    );

}
