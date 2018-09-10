package com.example.githubclient.ui.repositories.edit;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.githubclient.R;
import com.example.githubclient.data.remote.model.Repository;
import com.example.githubclient.presentation.repositories.edit.EditRepositoryPresenter;
import com.example.githubclient.presentation.repositories.edit.EditRepositoryView;
import com.example.githubclient.ui.base.BaseFragment;
import com.example.githubclient.ui.main.router.MainActivityRouter;
import com.google.android.material.textfield.TextInputEditText;

import androidx.appcompat.app.ActionBar;
import butterknife.BindView;

public class EditRepositoryFragment extends BaseFragment implements EditRepositoryView {

    private static final String KEY_REPOSITORY = "key_repository";

    @BindView(R.id.et_name)
    TextInputEditText etName;

    @BindView(R.id.et_description)
    TextInputEditText etDescription;

    @InjectPresenter
    EditRepositoryPresenter presenter;

    @ProvidePresenter
    EditRepositoryPresenter provideEditRepositoryPresenter() {
        return new EditRepositoryPresenter(getActivity(), new MainActivityRouter(getBaseActivity()));
    }

    public static EditRepositoryFragment newInstance(Repository repository) {
        Bundle args = new Bundle();

        args.putParcelable(KEY_REPOSITORY, repository);

        EditRepositoryFragment fragment = new EditRepositoryFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void setupRepositoryInfo(String name, String description) {
        etName.setText(name);
        etDescription.setText(description);
    }

    @Override
    public void showEmptyNameError() {
        etName.setError(getString(R.string.empty_name_error));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_edit_repository, menu);

        super.onCreateOptionsMenu(menu,inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete:
                onDeleteClick();

                return true;

            case R.id.save:
                onSaveClick();

                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void onDeleteClick() {
        presenter.onBtnDeleteClick();
    }

    private void onSaveClick() {
        presenter.onBtnSaveClick(etName.getText().toString(), etDescription.getText().toString());
    }

    @Override
    protected void setupActionBar() {
        super.setupActionBar();

        ActionBar actionBar = getBaseActivity().getSupportActionBar();

        if (actionBar != null) {
            actionBar.setTitle(R.string.edit_repository);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        setHasOptionsMenu(true);
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

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_edit_repository;
    }
}
