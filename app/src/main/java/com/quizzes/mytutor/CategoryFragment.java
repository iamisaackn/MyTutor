package com.quizzes.mytutor;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class CategoryAdapter extends BaseAdapter {

    private List<CategoryModel> catlist;

    // Constructor to initialize the category list
    public CategoryAdapter(List<CategoryModel> catlist) {
        this.catlist = catlist;
    }

    @Override
    public int getCount() {
        // Returns the total number of items in the list
        return catlist.size();
    }

    @Override
    public Object getItem(int i) {
        // Returns the item at the specified position
        return catlist.get(i);
    }

    @Override
    public long getItemId(int i) {
        // Returns the ID of the item at the specified position
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        View conview;

        // Inflate the layout if the view is null
        if (view == null) {
            conview = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.category_item_layout, viewGroup, false);
        } else {
            conview = view;
        }

        // Set onClickListener to navigate to TestActivity when a category is clicked
        conview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), TestActivity.class);
                intent.putExtra("CAT_INDEX", i); // Pass category index to TestActivity
                view.getContext().startActivity(intent);
            }
        });

        // Get references to the TextViews in the layout
        TextView Cat_name = conview.findViewById(R.id.cat_name);
        TextView no_test = conview.findViewById(R.id.test_no);

        // Set the category name and number of tests
        Cat_name.setText(catlist.get(i).getName());
        no_test.setText(String.valueOf(catlist.get(i).getNumtest()) + " Tests");

        return conview;
    }
}