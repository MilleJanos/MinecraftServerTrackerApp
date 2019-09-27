package ms.sapientia.minecraftservertrackerapp;

import android.content.Context;
import android.graphics.Movie;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ServerAdapter extends RecyclerView.Adapter<ServerAdapter.ServerViewHolder> {


    private static final String TAG = "GenreAdapter";
    private List<Server> mServers = new ArrayList<>();
    private Context mContext;
    private CustomCallback mCallback = null;

    public ServerAdapter(Context context, Server server){
        this.mContext = context;
        this.mServers.add(server);
    }

    public ServerAdapter(Context context, List<Server> servers){
        this.mContext = context;
        this.mServers = servers;
    }

    public ServerAdapter(Context context, List<Server> servers, CustomCallback callback){
        this.mContext = context;
        this.mServers = servers;
        this.mCallback = callback;
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
        ImageView type;
        @BindView(R.id.remove_image_view)
        ImageView remove;
        @BindView(R.id.custom_name_text_view)
        TextView customName;
        @BindView(R.id.players_text_view)
        TextView players;
        @BindView(R.id.motd_text_view)
        TextView motd;

        public ServerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(Server server){

            // Set genre name title:
            players.setText( server.getPlayers().getOnline() + "/" + server.getPlayers().getMax() );

        }





    }

}
