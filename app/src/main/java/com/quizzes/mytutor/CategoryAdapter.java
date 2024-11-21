package com.quizzes.mytutor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class CategoryAdapter extends BaseAdapter {

    private List<CategoryModel> catlist;

    public CategoryAdapter(List<CategoryModel> catlist){
        this.catlist = catlist;
    }
    @Override
    public int getCount() {
        return catlist.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View conview;

        if(view == null){
            conview = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.category_item_layout,viewGroup,false);
        }else{
            conview = view;
        }

        TextView Cat_name = conview.findViewById(R.id.cat_name);
        TextView no_test = conview.findViewById(R.id.test_no);

        Cat_name.setText(catlist.get(i).getName());
        no_test.setText( String.valueOf(catlist.get(i).getNumtest())+ "Tests");

        return conview;
    }
}
