package mj.minecraft.servertracker.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import mj.minecraft.servertracker.model.Players;

public class PingResponse {

    @SerializedName("description")
    @Expose
    private Description description;

    @SerializedName("players")
    @Expose
    private Players players;

    @SerializedName("version")
    @Expose
    private Version version;


    public class Description{

        @SerializedName("text")
        @Expose
        public String text;
    }

    public class Version{

        @SerializedName("text")
        @Expose
        public String name;

        @SerializedName("protocol")
        @Expose
        public int protocol;
    }

}
