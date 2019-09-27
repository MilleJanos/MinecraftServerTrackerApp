package ms.sapientia.minecraftservertrackerapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MOTD {

    @SerializedName("raw")
    @Expose
    private List<String> raw;

    @SerializedName("clean")
    @Expose
    private List<String> clean;

    @SerializedName("html")
    @Expose
    private List<String> html;


    public List<String> getRaw() {
        return raw;
    }

    public void setRaw(List<String> raw) {
        this.raw = raw;
    }

    public List<String> getClean() {
        return clean;
    }

    public void setClean(List<String> clean) {
        this.clean = clean;
    }

    public List<String> getHtml() {
        return html;
    }

    @Override
    public String toString() {
        return "MOTD{" +
                "raw=" + raw +
                ", clean=" + clean +
                ", html=" + html +
                '}';
    }

    public void setHtml(List<String> html) {
        this.html = html;
    }
}
