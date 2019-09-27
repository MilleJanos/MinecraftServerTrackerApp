package ms.sapientia.minecraftservertrackerapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ServerListFragment extends Fragment {

    private static final String TAG = "ServerListFragment";
    private List<String> mServerIds = null;
    private ServerAdapter mAdapter;
    private ServerController mController;

    @BindView(R.id.server_list_recycler_view)
    RecyclerView mRecyclerView;

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

        mController = ServerController.getInstance();

        for(final String serverId : mServerIds ){
            mController.getServer(serverId, new OnGetServerCallback() {
                @Override
                public void onSuccess(Server server) {
                    setListAdapter(server);
                }

                @Override
                public void onError(String msg) {
                    Toast.makeText( getContext(), "Can't load server (" + serverId + ")", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "onError: " + msg );
                }
            });
        }
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
