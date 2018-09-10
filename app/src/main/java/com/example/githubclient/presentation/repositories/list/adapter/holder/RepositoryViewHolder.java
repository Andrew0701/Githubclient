package com.example.githubclient.presentation.repositories.list.adapter.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.githubclient.R;
import com.example.githubclient.ui.base.BaseViewHolder;

import androidx.annotation.NonNull;
import butterknife.BindView;

public class RepositoryViewHolder extends BaseViewHolder {

    @BindView(R.id.tv_name)
    public TextView tvName;

    @BindView(R.id.tv_description)
    public TextView tvDescription;

    @BindView(R.id.iv_private)
    public ImageView ivPrivate;

    public View root;

    public RepositoryViewHolder(@NonNull View itemView) {
        super(itemView);

        root = itemView;
    }
}
