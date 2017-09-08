package xyz.kfdykme.mobistudi.question.adapter;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.*;
import android.util.Log;
import android.view.*;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;


import chinaykc.mobistudi.R;
import xyz.kfdykme.mobistudi.bean.Answer;
import xyz.kfdykme.mobistudi.bean.StudyQuestion;

public class AnswerAdapter extends RecyclerView.Adapter<AnswerAdapter.AnswerViewHolder>
{
	StudyQuestion mStudyQuesion;

	List<Answer> mAnswers = new ArrayList<Answer>();
	
	Context context;
	
	LayoutInflater Inflater;

	ItemOnClickListener mItemOnClickListener;

	int currentAnswerPosition = 0;

	enum Status {Choosed,UnChoosed};


	public interface ItemOnClickListener{
		void onClick(View view, int position);
	}

	public void setItemOnClickListener(ItemOnClickListener itemOnClickListener){
		mItemOnClickListener = itemOnClickListener;
	}

	public AnswerAdapter(Context context, StudyQuestion StudyQuestion){

		this.mStudyQuesion = StudyQuestion;
		this.mAnswers = StudyQuestion.getSeletableAnswers();
		this.context  = context;
		Inflater = LayoutInflater.from(context);
	}
	
	@Override
	public AnswerAdapter.AnswerViewHolder onCreateViewHolder(ViewGroup p1, int p2)
	{
		View view = Inflater.inflate(R.layout.rv_item_question_answer,p1,false);
		AnswerViewHolder holder = new AnswerViewHolder(view);
		
		return holder;
	}

	@Override
	public void onBindViewHolder(final AnswerAdapter.AnswerViewHolder holder, final int position)
	{
		holder.mTvDescription.setText(mAnswers.get(position).getDescription());



		holder.itemView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
                if(mItemOnClickListener != null)
                {
                    mItemOnClickListener.onClick(v,position);
                }
                Log.d("AnswerAdapter","click itemView");

				switch (holder.mStatus){
					case Choosed:

						Log.d("AnswerAdapter","UnChoosed");

						holder.mStatus = Status.UnChoosed;
						holder.mView.setBackgroundColor(holder.unchooseColor);
                        holder.mTvNumber.setTextColor(holder.unchooseColor);
						break;
					case UnChoosed:
						Log.d("AnswerAdapter","Choosed");
						holder.mStatus = Status.Choosed;
                        holder.mView.setBackgroundColor(holder.chooseColor);
                        holder.mTvNumber.setTextColor(holder.chooseColor);
						break;

				}
			}
		});

		String number = "";
		switch (position){
			case 0: number = "A";break;
			case 1: number = "B";break;
			case 2: number = "C";break;
			case 3: number = "D";break;
		}

		holder.mTvNumber.setText(number);
	}

	@Override
	public int getItemCount()
	{
		
		return mAnswers.size();
	}
	

	public boolean  chooseAAnswer(View view,int position){
		String text;

		Boolean isCorrect;
		Answer cAnswer = mStudyQuesion.getSeletableAnswers().get(position);
		if(cAnswer.getId().equals(mStudyQuesion.getCorrectAnswerId())) {
			text = "Yes, you are correct.";
			isCorrect =  true;
		}else {
			text = "Sorry, you are wrong.";
			isCorrect = false;
		}Snackbar.make(view,text,Snackbar.LENGTH_SHORT).show();

		return isCorrect;
	}


	public int getCurrentAnswerPosition() {
		return currentAnswerPosition;
	}

	public void setCurrentAnswerPosition(int currentAnswerPosition) {
		this.currentAnswerPosition = currentAnswerPosition;
	}


	public class AnswerViewHolder extends RecyclerView.ViewHolder{
		TextView mTvNumber;
		TextView mTvDescription;
		Status mStatus = Status.UnChoosed;
        View mView;
        int chooseColor;
        int unchooseColor;
		public AnswerViewHolder(View view){
			super(view);
			mTvNumber = (TextView)view.findViewById(R.id.rv_item_question_answer_number);
			mTvDescription = (TextView)view.findViewById(R.id.rv_item_question_answer_item);
			mView = view.findViewById(R.id.view_block);
            chooseColor = context.getResources().getColor(R.color.colorAccent);
            unchooseColor = context.getResources().getColor(R.color.colorPrimary);
		}
	}
}
