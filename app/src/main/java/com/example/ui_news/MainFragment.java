package com.example.ui_news;

import androidx.navigation.Navigation;
import android.view.View;
import android.view.ViewGroup;

import com.example.ui_news.base.BaseBindingFragment;
import com.example.ui_news.base.BindAdapter;
import com.example.ui_news.databinding.FragmentMainBinding;
import com.example.ui_news.databinding.ItemImageBinding;
import com.example.ui_news.databinding.ItemNewsBinding;


public class MainFragment extends BaseBindingFragment<FragmentMainBinding> {

    // Create a BindAdapter for displaying a list of images at the top of the fragment.
    private BindAdapter<ItemImageBinding, Integer> adapter = new BindAdapter<ItemImageBinding, Integer>() {
        @Override
        public ItemImageBinding createHolder(ViewGroup parent) {
            return ItemImageBinding.inflate(getLayoutInflater(), parent, false);
        }

        @Override
        public void bind(ItemImageBinding item, Integer data, int position) {
            loadImage(data, item.getRoot());
        }
    };

    // Create a BindAdapter for displaying a list of news items below the images.
    private BindAdapter<ItemNewsBinding, News> newsAdapter = new BindAdapter<ItemNewsBinding, News>() {
        @Override
        public ItemNewsBinding createHolder(ViewGroup parent) {
            return ItemNewsBinding.inflate(getLayoutInflater(), parent, false);
        }

        @Override
        public void bind(ItemNewsBinding item, News data, int position) {
            // Set the title and content of the news item.
            item.tvTitle.setText(data.title);
            item.tvContent.setText(data.content);
            // Load the image for the news item.
            loadImage(data.getId(), item.ivImage);
            // Set a click listener for the news item that navigates to the DetailFragment with the news item data.
            item.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Navigation.findNavController(v).navigate(
                            MainFragmentDirections.actionDetailFragmentToMainFragment(data.title, data.content, data.id));
                }
            });
        }
    };

    @Override
    protected void initData() {
        // This method is left empty because the data is initialized in the initListener method.
    }

    @Override
    protected void initListener() {
        // Clear the data in both adapters.
        adapter.getData().clear();
        newsAdapter.getData().clear();

        // Add images to the top adapter.
        adapter.getData().add(R.drawable.img9);
        adapter.getData().add(R.drawable.img11);
        adapter.getData().add(R.drawable.img10);
        adapter.getData().add(R.drawable.img12);
        adapter.getData().add(R.drawable.img5);
        adapter.getData().add(R.drawable.img6);
        adapter.getData().add(R.drawable.img7);
        adapter.getData().add(R.drawable.img8);

        // Set the top adapter to the RecyclerView for the images.
        viewBinder.rvTop.setAdapter(adapter);

        // Add news items to the bottom adapter.
        int i = 0;
        for (Integer datum : adapter.getData()) {
            i++;
            newsAdapter.getData().add(new News("News" + i, "news details" + i, datum));
        }

        // Set the bottom adapter to the RecyclerView for the news items.
        viewBinder.rvNews.setAdapter(newsAdapter);
    }
}