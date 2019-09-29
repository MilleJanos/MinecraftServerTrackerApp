package mj.minecraft.servertracker.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import mj.minecraft.servertracker.R;
import mj.minecraft.servertracker.fragment.ServerListFragment;
import mj.minecraft.servertracker.navigation.FragmentNavigation;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        FragmentNavigation.getInstance(this).showFragment(R.id.main_fragment_container, new ServerListFragment());
    }
    
}