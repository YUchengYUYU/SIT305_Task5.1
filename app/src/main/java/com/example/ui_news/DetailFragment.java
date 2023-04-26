package com.example.ui_news;
import androidx.navigation.Navigation;

import android.view.View;
import android.view.ViewGroup;

import com.example.ui_news.base.BaseBindingFragment;
import com.example.ui_news.base.BindAdapter;
import com.example.ui_news.databinding.FragmentDetailBinding;
import com.example.ui_news.databinding.ItemNews1Binding;


public class DetailFragment extends BaseBindingFragment<FragmentDetailBinding> {

    // Create a BindAdapter for the ItemNews1Binding and News classes.
    private BindAdapter<ItemNews1Binding, News> newsAdapter = new BindAdapter<ItemNews1Binding, News>() {

        // Override the createHolder() method to create a view holder for the news items.
        @Override
        public ItemNews1Binding createHolder(ViewGroup parent) {
            return ItemNews1Binding.inflate(getLayoutInflater(), parent, false);
        }

        // Override the bind() method to bind data to the view holder.
        @Override
        public void bind(ItemNews1Binding item, News data, int position) {
            // Set the title and content of the news item.
            item.tvTitle.setText(data.title);
            item.tvContent.setText(data.content);
            // Load the image of the news item.
            loadImage(data.getId(), item.ivImage);
            // Set a click listener for the news item.
            item.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Navigate to the MainFragment with the selected news item's title, content and id.
                    Navigation.findNavController(v).navigate(
                            MainFragmentDirections.actionDetailFragmentToMainFragment(data.title, data.content, data.id));
                }
            });
        }
    };

    // Override the initData() method to initialize the data for the view.
    @Override
    protected void initData() {
        // Add sample news items to the data list of the news adapter.
        newsAdapter.getData().add(new News("News" + 1, "Hello SIT305" + 2, R.drawable.img5));
        newsAdapter.getData().add(new News("News" + 1, "Content" + 2, R.drawable.img6));
        newsAdapter.getData().add(new News("News" + 1, "Content" + 2, R.drawable.img7));
        newsAdapter.getData().add(new News("News" + 1, "Content" + 2, R.drawable.img8));
        // Set the news adapter to the recycler view.
        viewBinder.rvNews.setAdapter(newsAdapter);

        // Set the title, content and image of the news item to the respective views.
        viewBinder.tvTitle.setText(DetailFragmentArgs.fromBundle(getArguments()).getTitle());
        viewBinder.tvContent.setText(DetailFragmentArgs.fromBundle(getArguments()).getContent());
        loadImage(DetailFragmentArgs.fromBundle(getArguments()).getId(),viewBinder.ivImage);
    }

    // Override the initListener() method to initialize the listeners for the view.
    @Override
    protected void initListener() {

    }

}