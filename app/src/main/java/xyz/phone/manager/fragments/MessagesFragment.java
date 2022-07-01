package xyz.phone.manager.fragments;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Map;
import java.util.Set;

import xyz.phone.commons.model.Message;
import xyz.phone.commons.utils.Comparator;
import xyz.phone.commons.utils.GroupList;
import xyz.phone.manager.R;
import xyz.phone.manager.adapter.MessagesAdapter;
import xyz.phone.manager.base.BaseFragment;
import xyz.phone.providers.MessagesProvider;

public class MessagesFragment extends BaseFragment {

    private static final int FRAGMENT_LAYOUT = R.layout.fragment_messages;

    //INIT OBJECTS
    private final Comparator<Message> comparator = new Comparator<>();

    private GroupList<Message> groupList;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;
    private MessagesAdapter adapter;
    private Map<String, Set<Message>> map;

    public MessagesFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(FRAGMENT_LAYOUT, container, false);
        init(view);
        doReload();
        return view;
    }

    private void init(View view) {
        //OBJECTS
        groupList = new GroupList<>();

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

        //GROUP + SORT
        if (map == null || map.isEmpty()) {
            map = MessagesProvider.getAllGrouped(
                    getContext(),
                    Message::getAddress,
                    comparator.byNumberDesc(Message::getMessageDate)
            );
        }

        if (adapter == null)
            adapter = new MessagesAdapter(getContext(), map);
        if (recyclerView != null)
            recyclerView.setAdapter(adapter);
    }
}