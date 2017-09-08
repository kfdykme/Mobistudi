package chinaykc.mobistudi;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by chinaykc on 17-6-3.
 */

public class SeriesFragment extends Fragment {




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.series,container,false);

        Glide.with(this)
                .load(R.drawable.zhuanti0)
                //.bitmapTransform(new BlurTransformation(mContext,15,2)) // “23”：设置模糊度(在0.0到25.0之间)，默认”25";"4":图片缩放比例,默认“1”。
                .into((ImageView)view.findViewById(R.id.zhuanti0));











        return  view;
    }

}
