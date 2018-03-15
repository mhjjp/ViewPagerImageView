package jjp.mh.viewpagerwithimageview;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.provider.SyncStateContract;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import jjp.mh.viewpagerwithimageview.view.ScaleImageView;
import jjp.mh.viewpagerwithimageview.view.UnMoveViewPager;

public class MainActivity extends AppCompatActivity {

    private UnMoveViewPager img__vp;
    private List<String> imgUrlString;
    private Context context;
    int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = MainActivity.this;

        img__vp = (UnMoveViewPager)findViewById(R.id.img__vp);
        img__vp.setSlide(false);

        imgUrlString = new ArrayList<>();
        imgUrlString.add("http://pi.weather.com.cn/i/product/pic/l/sevp_nmc_stfc_sfer_er24_achn_l88_p9_20180307070002400.jpg");
        imgUrlString.add("http://pi.weather.com.cn/i/product/pic/l/sevp_nmc_stfc_sfer_er24_achn_l88_p9_20180307010004800.jpg");
        imgUrlString.add("http://pi.weather.com.cn/i/product/pic/l/sevp_nmc_stfc_sfer_er24_achn_l88_p9_20180307010007200.jpg");

        ImageViewPagerAdapter imageViewPagerAdapter = new ImageViewPagerAdapter(context,imgUrlString);
        img__vp.setAdapter(imageViewPagerAdapter);

        Timer timer = new Timer();


        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                Message message = new Message();
                message.what = 1;
                handler.sendMessage(message);


            }
        }, 1000, 1000);

    }


    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {

            if(msg.what == 1) {
                img__vp.setCurrentItem(i, true);
                i++;
                if (i == 2) {
                    i = 0;
                }
            }
        }
    };


    private class ImageViewPagerAdapter extends PagerAdapter{

        private List<String> urlImgList = new ArrayList<>();
        private Context context;

        public ImageViewPagerAdapter(Context context, List<String> urlImgList){
            this.urlImgList = urlImgList;
            this.context = context;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            ScaleImageView scaleImageView = new ScaleImageView(context);
            Glide.with(context).load(urlImgList.get(position)).into(scaleImageView);

            container.addView(scaleImageView);

            return scaleImageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

            container.removeView((View) object);
        }

        @Override
        public int getCount() {
            return urlImgList.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }
    }

}
