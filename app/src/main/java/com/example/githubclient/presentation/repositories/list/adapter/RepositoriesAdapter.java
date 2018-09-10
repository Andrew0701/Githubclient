package com.example.githubclient.presentation.repositories.list.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.githubclient.R;
import com.example.githubclient.data.remote.model.Repository;
import com.example.githubclient.presentation.repositories.list.adapter.callback.OnRepositoryClickListener;
import com.example.githubclient.presentation.repositories.list.adapter.holder.RepositoryViewHolder;
import com.example.githubclient.util.CollectionUtils;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

public class RepositoriesAdapter extends RecyclerView.Adapter<RepositoryViewHolder> {

    private Context context;
    private List<Repository> repositories;
    private OnRepositoryClickListener listener;

    public RepositoriesAdapter(Context context, List<Repository> repositories, OnRepositoryClickListener listener) {

        this.context = context;
        this.repositories = repositories;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RepositoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_repository, parent, false);

        return new RepositoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RepositoryViewHolder holder, int position) {
        Repository repository = repositories.get(position);

        holder.tvName.setText(repository.getName());
        bindDescription(holder, repository);
        bindIcon(holder, repository);

        holder.root.setOnClickListener(view -> listener.onRepositoryClick(repository));
    }

    private void bindIcon(RepositoryViewHolder holder, Repository repository) {
        if (repository.isPrivate()) {
            holder.ivPrivate.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.lock_closed));
        } else {
            holder.ivPrivate.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.lock_open));
        }
    }

    private void bindDescription(@NonNull RepositoryViewHolder holder, Repository repository) {
        if (!TextUtils.isEmpty(repository.getDescription())) {
            holder.tvDescription.setText(repository.getDescription());
        } else {
            holder.tvDescription.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return CollectionUtils.notNullAndNotEmpty(repositories) ? repositories.size() : 0;
    }
}
