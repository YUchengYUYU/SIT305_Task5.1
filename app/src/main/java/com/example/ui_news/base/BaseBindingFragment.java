package com.example.ui_news.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewbinding.ViewBinding;

import com.bumptech.glide.Glide;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


public abstract class BaseBindingFragment<T extends ViewBinding> extends Fragment {
    protected T viewBinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initViewBinding(inflater, container);
        initListener();
        initData();
        return viewBinder.getRoot();
    }

    private void initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        Type type = getClass().getGenericSuperclass();
        Type[] actualTypeArguments = ((ParameterizedType) type).getActualTypeArguments();
        Class<T> tClass = (Class<T>) actualTypeArguments[0];
        try {
            Method method = tClass.getMethod("inflate", LayoutInflater.class, ViewGroup.class, boolean.class);
            viewBinder = (T) method.invoke(null, inflater, container, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void loadImage(int url, ImageView view){
        Glide.with(this)
                .load(url)
                .into(view);
    }
    protected abstract void initData();
    protected abstract void initListener();

    protected void toast(String msg) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show();
    }

}
