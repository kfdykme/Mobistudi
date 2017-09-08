package xyz.kfdykme.mobistudi.question.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import chinaykc.mobistudi.R;
import xyz.kfdykme.mobistudi.bean.StudyQuestion;

/**
 * Created by kf on 2017/6/10.
 */

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder>  {

    List<StudyQuestion> questions = new ArrayList<StudyQuestion>();

    Context context;

    LayoutInflater inflater;

    ItemOnClickListener mItemOnClickListener;

    public QuestionAdapter(List<StudyQuestion> questions, Context context) {
        this.questions = questions;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public interface ItemOnClickListener{
        void onClick(View view, int position);
    }

    public void setItemOnClickListener(ItemOnClickListener itemOnClickListener){
        mItemOnClickListener = itemOnClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.rv_item_question_questions,parent,false);


        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.mTv.setText("<"+(position+1)+">");
        holder.mTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mItemOnClickListener != null)
                    mItemOnClickListener.onClick(view,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView mTv;

        public ViewHolder(View itemView) {
            super(itemView);
            mTv = (TextView) itemView.findViewById(R.id.rv_item_question_questions_text);

        }
    }
}
