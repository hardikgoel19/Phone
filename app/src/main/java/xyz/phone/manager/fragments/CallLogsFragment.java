package xyz.phone.manager.fragments;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import xyz.phone.manager.R;
import xyz.phone.manager.base.BaseFragment;
import xyz.phone.manager.adapter.CallLogsAdapter;
import xyz.phone.manager.core.CallLogsProvider;
import xyz.phone.manager.model.Call;

public class CallLogsFragment extends BaseFragment {

    private static final int FRAGMENT_LAYOUT = R.layout.fragment_call_logs;

    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;
    private CallLogsAdapter adapter;
    private List<Call> callsList;

    public CallLogsFragment() {
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
        if (callsList == null || callsList.isEmpty())
            callsList = CallLogsProvider.getAll(getContext());
        if (adapter == null)
            adapter = new CallLogsAdapter(getContext(), callsList);
        if (recyclerView != null)
            recyclerView.setAdapter(adapter);
    }
}