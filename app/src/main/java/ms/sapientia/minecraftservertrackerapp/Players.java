package ms.sapientia.minecraftservertrackerapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

class Players {

    @SerializedName("online")
    @Expose
    private int online;

    @SerializedName("max")
    @Expose
    private int max;

    @SerializedName("list")                 // Only included when there are any players
    @Expose
    private List<String> list;

    public int getOnline() {
        return online;
    }

    public void setOnline(int online) {
        this.online = online;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "Players{" +
                "online=" + online +
                ", max=" + max +
                ", list=" + list +
                '}';
    }
}
