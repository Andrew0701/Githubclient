package com.example.githubclient.ui.main.router;

import com.example.githubclient.data.remote.model.Repository;
import com.example.githubclient.ui.base.BaseActivity;
import com.example.githubclient.ui.base.BaseRouter;
import com.example.githubclient.ui.login.LoginFragment;
import com.example.githubclient.ui.repositories.detail.DetailRepositoryFragment;
import com.example.githubclient.ui.repositories.edit.EditRepositoryFragment;
import com.example.githubclient.ui.repositories.list.RepositoriesListFragment;

public class MainActivityRouter extends BaseRouter implements MainRouter {

    public MainActivityRouter(BaseActivity activity) {
        super(activity);
    }

    @Override
    public void navigateBack() {
        popBackStack();
    }

    @Override
    public void navigateToLogin() {
        show(LoginFragment.newInstance(), null);
    }

    @Override
    public void navigateToRepositories() {
        popAllBackStack();
        show(RepositoriesListFragment.newInstance(), null);
    }

    @Override
    public void navigateToDetailRepository(Repository repository) {
        show(DetailRepositoryFragment.newInstance(repository), DetailRepositoryFragment.class.getSimpleName());
    }

    @Override
    public void navigateToDetailRepositoryWithoutBackStack(Repository repository) {
        show(DetailRepositoryFragment.newInstance(repository), null);
    }

    @Override
    public void navigateToEditRepository(Repository repository) {
        show(EditRepositoryFragment.newInstance(repository), EditRepositoryFragment.class.getSimpleName());
    }

    @Override
    public void navigateBackToDetailRepository(Repository repository) {
        popBackStack(DetailRepositoryFragment.class.getSimpleName());
        show(DetailRepositoryFragment.newInstance(repository), DetailRepositoryFragment.class.getSimpleName());
    }
}
