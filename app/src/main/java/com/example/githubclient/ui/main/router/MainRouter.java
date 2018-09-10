package com.example.githubclient.ui.main.router;

import com.example.githubclient.data.remote.model.Repository;

public interface MainRouter {

    void navigateBack();

    void navigateToLogin();

    void navigateToRepositories();

    void navigateToDetailRepository(Repository repository);

    void navigateToDetailRepositoryWithoutBackStack(Repository repository);

    void navigateToEditRepository(Repository repository);

    void navigateBackToDetailRepository(Repository repository);
}
