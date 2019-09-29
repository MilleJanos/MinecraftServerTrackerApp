package mj.minecraft.servertracker.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import mj.minecraft.servertracker.R;
import mj.minecraft.servertracker.adapter.ServerAdapter;
import mj.minecraft.servertracker.api.OnGetServerCallback;
import mj.minecraft.servertracker.api.ServerController;
import mj.minecraft.servertracker.model.Server;


public class ServerListFragment extends Fragment {

    private static final String TAG = "ServerListFragment";
    private List<String> mServerIds = null;
    private ServerAdapter mAdapter;
    private ServerController mController;

    @BindView(R.id.server_list_recycler_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.background_image_view)
    ImageView mBackgroundImageView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_server_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        init();
    }

    private void init() {
        mServerIds = new ArrayList<>();
        mServerIds.add("server20.clans.hu:25636");
        mServerIds.add("hypixel.net");
        mServerIds.add("mccentral.org");
        mServerIds.add("notExistingServer.not");
        mServerIds.add("play.extremecraft.net");
        mServerIds.add("pixel.mc-complex.com");
        mServerIds.add("play.manacube.net");
        mServerIds.add("mineheroes.net");
        mServerIds.add("play.applecraft.org");
        mServerIds.add("MC.Performium.net");
        mServerIds.add("play.pvpwars.net");
        mServerIds.add("play.thedestinymc.com");
        mServerIds.add("pokecentral.org");

        mController = ServerController.getInstance();

        for(final String serverId : mServerIds ){
            mController.getServer(serverId, new OnGetServerCallback() {
                @Override
                public void onSuccess(Server server) {
                    server.setIpFound(true);
                    server.setHostname(serverId);
                    setListAdapter(server);
                }

                @Override
                public void onError(String msg) {
                    Server server = new Server();
                    server.setIpFound(false);
                    server.setHostname(serverId);
                    setListAdapter(server);
                }
            });
        }

        mBackgroundImageView.setImageResource(R.mipmap.mc_background_background);
    }

    private void setListAdapter(Server server) {
        if(mAdapter == null){
            mAdapter = new ServerAdapter(getContext(), server);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            mRecyclerView.setAdapter(mAdapter);
        }else {
            mAdapter.addItem(server);
        }
        mAdapter.notifyDataSetChanged();
    }

}
