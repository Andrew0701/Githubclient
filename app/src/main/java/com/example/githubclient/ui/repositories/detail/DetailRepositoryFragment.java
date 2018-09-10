package com.example.githubclient.ui.repositories.detail;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.githubclient.R;
import com.example.githubclient.data.remote.model.Repository;
import com.example.githubclient.presentation.repositories.detail.DetailRepositoryPresenter;
import com.example.githubclient.presentation.repositories.detail.DetailRepositoryView;
import com.example.githubclient.ui.base.BaseFragment;
import com.example.githubclient.ui.main.router.MainActivityRouter;

import androidx.appcompat.app.ActionBar;
import butterknife.BindView;

public class DetailRepositoryFragment extends BaseFragment implements DetailRepositoryView {

    private static final String KEY_REPOSITORY = "key_repository";

    @BindView(R.id.tv_name)
    TextView tvName;

    @BindView(R.id.tv_description)
    TextView tvDescription;

    @BindView(R.id.tv_language)
    TextView tvLanguage;

    @InjectPresenter
    DetailRepositoryPresenter presenter;

    @ProvidePresenter
    DetailRepositoryPresenter provideDetailRepositoryPresenter() {
        return new DetailRepositoryPresenter(getActivity(), new MainActivityRouter(getBaseActivity()));
    }

    public static DetailRepositoryFragment newInstance(Repository repository) {
        Bundle args = new Bundle();

        args.putParcelable(KEY_REPOSITORY, repository);

        DetailRepositoryFragment fragment = new DetailRepositoryFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_detail_repository;
    }

    @Override
    public void setRepositoryInfo(String name, String description, String language) {
        tvName.setText(name);

        if (!TextUtils.isEmpty(description)) {
            tvDescription.setText(description);
        }

        if (!TextUtils.isEmpty(language)) {
            tvLanguage.setText(language);
        }
    }

    @Override
    protected void setupActionBar() {
        super.setupActionBar();

        ActionBar actionBar = getBaseActivity().getSupportActionBar();

        if (actionBar != null) {
            actionBar.setTitle(R.string.repository_info);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_detail_repository, menu);

        super.onCreateOptionsMenu(menu,inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.edit) {
            presenter.onBtnEditClick();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getArgs();
    }

    private void getArgs() {
        Bundle arguments = getArguments();

        if (arguments != null) {
            Repository repository = arguments.getParcelable(KEY_REPOSITORY);

            presenter.setRepository(repository);
        }
    }
}
