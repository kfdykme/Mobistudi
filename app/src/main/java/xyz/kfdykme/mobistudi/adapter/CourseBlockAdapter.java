package xyz.kfdykme.mobistudi.adapter;


import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import chinaykc.mobistudi.R;
import xyz.kfdykme.mobistudi.bean.StudyCourse;

public class CourseBlockAdapter extends RecyclerView.Adapter<CourseBlockAdapter.ViewHolder>
{
	private List<StudyCourse> courses;

	private LayoutInflater Inflater;
	
	private Context context;

	ItemClickListener mItemClickListener;

	public void setItemClickListener(ItemClickListener ItemClickListener) {
		this.mItemClickListener = ItemClickListener;
	}

	public interface ItemClickListener{
		void onClick(View view,int position);
	}
	
	public CourseBlockAdapter(Context context, List<StudyCourse> courses){
		this.context = context;
		this.courses  = courses;
		Inflater = LayoutInflater.from(context);
	}
	
	@Override
	public CourseBlockAdapter.ViewHolder onCreateViewHolder(ViewGroup p1, int p2)
	{ 
		View view = Inflater.inflate(R.layout.rv_item_course_block,p1,false);
		
		ViewHolder holder = new ViewHolder(view);
	
		return holder;
	}

	@Override
	public void onBindViewHolder(CourseBlockAdapter.ViewHolder holder, final int position)
	{
		holder.textView.setText(courses.get(position).getTitle());
		holder.textView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if(mItemClickListener != null)
					mItemClickListener.onClick(view,position);
			}
		});

	}

	@Override
	public int getItemCount()
	{
		
		return courses.size();
	}
	
	
	
	public class ViewHolder extends RecyclerView.ViewHolder{
		public TextView textView;

		public ViewHolder(View view){
			super(view);

			textView = (TextView)view.findViewById(R.id.rv_item_course_block_textview);
		}
	}
	
	public void upData(List<StudyCourse> courses){
		this.courses =courses;
		notifyDataSetChanged();
	}
}
