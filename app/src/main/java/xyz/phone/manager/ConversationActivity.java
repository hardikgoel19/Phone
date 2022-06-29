package xyz.phone.manager;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

import xyz.phone.commons.model.Message;
import xyz.phone.manager.adapter.ConversationsAdapter;
import xyz.phone.manager.constants.Title;

@SuppressWarnings("All")
public class ConversationActivity extends AppCompatActivity {

    public static final String MESSAGES = "messages";

    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;
    private ConversationsAdapter adapter;
    private ArrayList<Message> messages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);

        initData();

        initViews();

        doReload();
    }

    private void doReload() {
        if (messages == null || messages.isEmpty()) return;
        if (adapter == null)
            adapter = new ConversationsAdapter(this, messages);
        if (recyclerView != null)
            recyclerView.setAdapter(adapter);
    }

    private void initViews() {
        //SET TITLE
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(Title.MESSAGES_TITLE);
        }

        //LAYOUT MANAGER
        layoutManager = new LinearLayoutManager(this);

        //RECYCLER VIEW
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void initData() {
        Intent intent = getIntent();
        if (intent == null) return;
        if (!intent.hasExtra(MESSAGES)) return;
        messages = (ArrayList<Message>) intent.getSerializableExtra(MESSAGES);
    }
}