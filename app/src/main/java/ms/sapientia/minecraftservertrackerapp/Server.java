package ms.sapientia.minecraftservertrackerapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Server {

    @SerializedName("online")
    @Expose
    private boolean isOnline;

    @SerializedName("ip")
    @Expose
    private String ip;

    @SerializedName("port")
    @Expose
    private int port;

//    @SerializedName("debug")
//    @Expose
//    private Debug debug;

    @SerializedName("motd")
    @Expose
    private MOTD motd;

    @SerializedName("players")
    @Expose
    private Players players;

    @SerializedName("version")          // Could include multiple versions or additional text
    @Expose
    private String version;

    @SerializedName("protocol")         // Only included when ping is used, see more here: http://wiki.vg/Protocol_version_numbers
    @Expose
    private int protocol;

    @SerializedName("hostname")         // Only included when a hostname is detected
    @Expose
    private String hostname;

    @SerializedName("icon")             // Only included when an icon is detected
    @Expose
    private String icon;

    @SerializedName("software")         // Only included when software is detected
    @Expose
    private String software;

    @SerializedName("map")              // Only included when the value is not "world"
    @Expose
    private String map;

//    @SerializedName("plugins")          // Only included when plugins are detected
//    @Expose
//    private Plugins plugins;

//    @SerializedName("mods")             // Only included when mods are detected
//    @Expose
//    private Mods mods;

//    @SerializedName("info")             // Only included when detecting that the player samples are used for information
//    @Expose
//    private Info info;


    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public MOTD getMotd() {
        return motd;
    }

    public void setMotd(MOTD motd) {
        this.motd = motd;
    }

    public Players getPlayers() {
        return players;
    }

    public void setPlayers(Players players) {
        this.players = players;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getProtocol() {
        return protocol;
    }

    public void setProtocol(int protocol) {
        this.protocol = protocol;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getSoftware() {
        return software;
    }

    public void setSoftware(String software) {
        this.software = software;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    @Override
    public String toString() {
        return "Server{" +
                "isOnline=" + isOnline +
                ", ip='" + ip + '\'' +
                ", port=" + port +
                ", motd=" + motd +
                ", players=" + players +
                ", version='" + version + '\'' +
                ", protocol=" + protocol +
                ", hostname='" + hostname + '\'' +
                ", icon='" + icon + '\'' +
                ", software='" + software + '\'' +
                ", map='" + map + '\'' +
                '}';
    }
}
