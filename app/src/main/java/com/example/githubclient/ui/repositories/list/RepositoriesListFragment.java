package com.example.githubclient.ui.repositories.list;

import android.view.View;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.githubclient.R;
import com.example.githubclient.presentation.repositories.list.RepositoriesListPresenter;
import com.example.githubclient.presentation.repositories.list.RepositoriesListView;
import com.example.githubclient.ui.base.BaseFragment;
import com.example.githubclient.ui.main.router.MainActivityRouter;

import androidx.appcompat.app.ActionBar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class RepositoriesListFragment extends BaseFragment implements RepositoriesListView {

    @BindView(R.id.rv_repositories)
    RecyclerView rvRepositories;

    @BindView(R.id.tv_empty_repositories)
    TextView tvEmptyRepositories;

    @InjectPresenter
    RepositoriesListPresenter presenter;

    @ProvidePresenter
    RepositoriesListPresenter provideRepositoriesListPresenter() {
        return new RepositoriesListPresenter(getActivity(), new MainActivityRouter(getBaseActivity()));
    }

    public static RepositoriesListFragment newInstance() {
        return new RepositoriesListFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_repositories_list;
    }

    @Override
    public void setRepositoriesAdapter(RecyclerView.Adapter adapter) {
        rvRepositories.setAdapter(adapter);
        rvRepositories.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvRepositories.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
    }

    @Override
    public void onStart() {
        super.onStart();

        presenter.init();
    }

    @Override
    public void showEmptyRepositoriesMessage() {
        tvEmptyRepositories.setVisibility(View.VISIBLE);
        rvRepositories.setVisibility(View.GONE);
    }

    @Override
    protected void setupActionBar() {
        super.setupActionBar();

        ActionBar actionBar = getBaseActivity().getSupportActionBar();

        if (actionBar != null) {
            actionBar.setTitle(R.string.repositories);
            actionBar.setDisplayHomeAsUpEnabled(false);
        }
    }
}
