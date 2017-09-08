package xyz.kfdykme.mobistudi.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import chinaykc.mobistudi.R;
import xyz.kfdykme.mobistudi.question.QuestionActivity;
import xyz.kfdykme.mobistudi.bean.StudyCourse;

public class QuestionFragment extends BaseFragment
	{

        View mView;

        StudyCourse mStudyCourse;

        public void setStudyCourse(StudyCourse studyCourse) {
            this.mStudyCourse = studyCourse;
        }

        @Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
		{

            mView = inflater.inflate(R.layout.fragment_questions,container,false);
            TextView mTvStart = (TextView) mView.findViewById(R.id.fragment_question_start_do_text_view);
            mTvStart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(getContext(), QuestionActivity.class);
                    getContext().startActivity(i);
                    EventBus.getDefault().postSticky(mStudyCourse);
                }
            });

			return mView;
		}



		public static QuestionFragment newInstance(StudyCourse studyCourse)
		{

			QuestionFragment fragment = new QuestionFragment();

			fragment.setStudyCourse(studyCourse);

			return fragment;
		}




	}
