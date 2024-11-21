

import android.content.ClipData;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.starttest.QuestionActivity;
import com.example.starttest.R;

import java.util.List;


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView testNo;
        private TextView topScore;
        private ProgressBar progressBar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Initialize the views
            testNo = itemView.findViewById(R.id.test);
            topScore = itemView.findViewById(R.id.scoretext);
            progressBar = itemView.findViewById(R.id.testProgressbar);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(itemView.getContext(), QuestionActivity.class);
                    itemView.getContext().startActivity(intent);
                }
            });
        }
        private  void setData(int pos, int progress){
            testNo.setText("Test No :" + String.valueOf(pos + 1));
            topScore.setText(String.valueOf(progress) +"%" );
            progressBar.setProgress(progress);

            itemView.setOnClickListener(view -> {
                com.quizzes.mytutor.DbQuery.g_selected_test_index = pos;
                        Intent intent = new Intent(itemView.getContext(), com.mtc.exampractice.StartTestActivity.class);
                itemView.getContext().startActivity(intent);

            });
        }


    }

