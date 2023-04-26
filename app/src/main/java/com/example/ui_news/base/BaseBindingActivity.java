package com.example.ui_news.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;

import com.bumptech.glide.Glide;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


public abstract class BaseBindingActivity<T extends ViewBinding> extends AppCompatActivity {
    protected T viewBinder;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViewBinder();
        beforeSetContentView();
        setContentView(viewBinder.getRoot());
        initListener();
        initData();
    }

    private void initViewBinder() {
        Type type = getClass().getGenericSuperclass();
        Type[] actualTypeArguments = ((ParameterizedType) type).getActualTypeArguments();
        Class<T> tClass = (Class<T>) actualTypeArguments[0];
        try {
            Method method = tClass.getMethod("inflate", LayoutInflater.class);
            viewBinder = (T) method.invoke(null, getLayoutInflater());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void loadImage(String url, ImageView view) {
        Glide.with(this)
                .load(url)
                .into(view);
    }

    public void beforeSetContentView() {
    }

    protected abstract void initListener();

    protected abstract void initData();


    protected void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}
