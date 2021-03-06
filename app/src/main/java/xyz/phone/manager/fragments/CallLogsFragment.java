package xyz.phone.manager.fragments;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import xyz.android.library.recyclerview.adapters.HeaderAdapter;
import xyz.phone.commons.model.Call;
import xyz.phone.commons.utils.Comparator;
import xyz.phone.commons.utils.Converter;
import xyz.phone.commons.utils.DateTimeUtil;
import xyz.phone.commons.utils.GroupList;
import xyz.phone.manager.R;
import xyz.phone.manager.base.BaseFragment;
import xyz.phone.providers.CallLogsProvider;

public class CallLogsFragment extends BaseFragment {

    private static final int FRAGMENT_LAYOUT = R.layout.fragment_call_logs;

    //INIT OBJECTS
    private final Comparator<Call> comparator = new Comparator<>();

    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;
    private HeaderAdapter adapter;
    private Map<String, Set<Call>> map;

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
        if (getContext() == null) return;

        //GROUP + SORT
        if (map == null || map.isEmpty()) {
            map = CallLogsProvider.getAllGrouped(
                    getContext(),
                    Call::getCallDate,
                    comparator.byNumberDesc(Call::getCallDate)
            );
        }

        if (adapter == null)
            adapter = new HeaderAdapter(getContext(), map);
        if (recyclerView != null)
            recyclerView.setAdapter(adapter);
    }
}