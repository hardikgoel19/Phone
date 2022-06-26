package xyz.phone.manager.fragments;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import xyz.phone.manager.R;
import xyz.phone.manager.adapter.MessagesAdapter;
import xyz.phone.manager.base.BaseFragment;
import xyz.phone.manager.core.MessagesProvider;
import xyz.phone.manager.model.Message;

public class MessagesFragment extends BaseFragment {

    private static final int FRAGMENT_LAYOUT = R.layout.fragment_messages;

    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;
    private MessagesAdapter adapter;
    private List<Message> messageList;

    public MessagesFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(FRAGMENT_LAYOUT, container, false);
        initViews(view);
        doReload();
        return view;
    }

    private void initViews(View view) {
        //LAYOUT MANAGER
        layoutManager = new LinearLayoutManager(getContext());

        //RECYCLER VIEW
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void doReload() {
        if (getContext() == null)
            return;
        if (messageList == null || messageList.isEmpty())
            messageList = MessagesProvider.getAll(getContext());
        List<List<Message>> message = new ArrayList<>();
        messageList.forEach(m -> {
            List<Message> m1 = new ArrayList<Message>();
            m1.add(m);
            message.add(m1);
        });
        if (adapter == null)
            adapter = new MessagesAdapter(getContext(), message);
        if (recyclerView != null)
            recyclerView.setAdapter(adapter);
    }
}