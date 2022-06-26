package xyz.phone.manager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import xyz.phone.manager.fragments.CallLogsFragment;
import xyz.phone.manager.fragments.MessagesFragment;
import xyz.phone.manager.utils.Title;

public class MainActivity extends AppCompatActivity
        implements NavigationBarView.OnItemSelectedListener {

    //CONSTANTS
    private static final int DEFAULT_FRAGMENT = R.id.callLogs;

    private BottomNavigationView bottomNavigationView;
    private FragmentTransaction transaction;
    private ActionBar actionBar;

    //DECLARE FRAGMENTS
    private CallLogsFragment callLogsFragment;
    private MessagesFragment messagesFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        openFragment(DEFAULT_FRAGMENT);
    }

    private void initViews() {
        //FIND VIEWS
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        //LISTENERS
        bottomNavigationView.setOnItemSelectedListener(this);

        //GET SUPPORT ACTION BAR
        actionBar = getSupportActionBar();
    }


    private void openFragment(int id) {
        transaction = getSupportFragmentManager().beginTransaction();
        if (id == R.id.callLogs) handleCallLogsFragment();
        else if (id == R.id.messages) handleMessagesFragment();
    }

    private void handleMessagesFragment() {
        if (messagesFragment == null)
            messagesFragment = new MessagesFragment();
        if (transaction != null) {
            transaction.replace(R.id.frame_container, messagesFragment);
            transaction.commit();
            if (actionBar != null)
                actionBar.setTitle(Title.MESSAGES_TITLE);
        }
    }


    private void handleCallLogsFragment() {
        if (callLogsFragment == null)
            callLogsFragment = new CallLogsFragment();
        if (transaction != null) {
            transaction.replace(R.id.frame_container, callLogsFragment);
            transaction.commit();
            if (actionBar != null)
                actionBar.setTitle(Title.CALL_LOGS_TITLE);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        openFragment(item.getItemId());
        return true;
    }
}