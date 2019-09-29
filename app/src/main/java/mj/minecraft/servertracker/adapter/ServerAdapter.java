package mj.minecraft.servertracker.adapter;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import mj.minecraft.servertracker.R;
import mj.minecraft.servertracker.api.CustomCallback;
import mj.minecraft.servertracker.api.OnPingServerCallback;
import mj.minecraft.servertracker.api.ServerController;
import mj.minecraft.servertracker.model.Server;

public class ServerAdapter extends RecyclerView.Adapter<ServerAdapter.ServerViewHolder> {


    private static final String TAG = "GenreAdapter";
    private List<Server> mServers = new ArrayList<>();
    private Context mContext;
    private CustomCallback mCallback = null;
    private ServerController mController;

    public ServerAdapter(Context context, Server server){
        this.mContext = context;
        this.mServers.add(server);
        mController = ServerController.getInstance();
    }

    public ServerAdapter(Context context, List<Server> servers){
        this.mContext = context;
        this.mServers = servers;
        mController = ServerController.getInstance();
    }

    public ServerAdapter(Context context, List<Server> servers, CustomCallback callback){
        this.mContext = context;
        this.mServers = servers;
        this.mCallback = callback;
        mController = ServerController.getInstance();
    }


    @Override
    public ServerViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_server, parent, false);
        return new ServerViewHolder(view);
    }

    @Override
    public void onBindViewHolder( ServerViewHolder holder, int position) {
        holder.bind(mServers.get(position));

    }

    @Override
    public int getItemCount() {
        if( mServers == null ){
            return 0;
        }
        return mServers.size();
    }

    public void addAllItems(List<Server> servers){
        mServers.addAll(servers);
    }

    public void addItem(Server server){
        mServers.add(server);
    }

    class ServerViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.icon_image_view)
        ImageView icon;
        @BindView(R.id.ping_image_view)
        ImageView ping;
        @BindView(R.id.type_image_view)
        ImageView remove;
        @BindView(R.id.custom_name_text_view)
        TextView customName;
        @BindView(R.id.players_text_view)
        TextView players;
        @BindView(R.id.motd_text_view)
        TextView motd;
        @BindView(R.id.ping_progress_bar)
        ProgressBar progressBar;

        public ServerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(final Server server){

            if( server.isIpFound() ){

                customName.setText( server.getHostname() );
                players.setText( server.getPlayers().getOnline() + "/" + server.getPlayers().getMax() );

                String text = server.getMotd().getHtml().toString();
                text = text.substring(1, text.length());
                text = text.substring(0,text.length()-1);

                motd.setText( Html.fromHtml(text,  Html.FROM_HTML_MODE_LEGACY), TextView.BufferType.SPANNABLE );

                progressBar.setVisibility( View.VISIBLE );

                mController.pingServer(server.getHostname(), new OnPingServerCallback() {
                    @Override
                    public void onSuccess(final long pingMs) {

                        server.setPing(pingMs);
                        progressBar.setVisibility( View.INVISIBLE );
                        ping.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(mContext, server.getPing() + "ms", Toast.LENGTH_SHORT).show();
                            }
                        });

                        if( 0 <= pingMs && pingMs <= 99 ){
                            ping.setImageResource(R.mipmap.ic_server_status_5_background);

                        }else if( 100 <= pingMs && pingMs <= 199 ){
                            ping.setImageResource(R.mipmap.ic_server_status_4_background);

                        }else if( 200 <= pingMs && pingMs <= 299){
                            ping.setImageResource(R.mipmap.ic_server_status_3_background);

                        }else if( 300 <= pingMs && pingMs <= 999){
                            ping.setImageResource(R.mipmap.ic_server_status_2_background);
                        }else
                            // 1000+
                            ping.setImageResource(R.mipmap.ic_server_status_1_background);
                    }

                    @Override
                    public void onError(String msg) {
                        Log.e(TAG, "onError: " + msg );

                        ping.setImageResource(R.mipmap.ic_server_status_na_background);
                        ping.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(mContext, "Can't ping server", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
                return;

            }else{

                String errorMsg = "<span style=\"color: #FF0000\">-Server not found-</span>";
                motd.setText( Html.fromHtml(errorMsg,  Html.FROM_HTML_MODE_LEGACY), TextView.BufferType.SPANNABLE );
                customName.setText( server.getHostname() );
                players.setText( "- / -" );

                if( server.isOnline() ){
                    ping.setImageResource(R.mipmap.ic_server_status_0_background);
                }else{
                    ping.setImageResource(R.mipmap.ic_server_status_na_background);
                }

                ping.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(mContext, "Can't ping server", Toast.LENGTH_SHORT).show();
                    }
                });

            }

        }





    }

}
