package jjp.mh.viewpagerwithimageview;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import jjp.mh.viewpagerwithimageview.view.ScaleImageView;

public class MainActivity extends AppCompatActivity {

    private ViewPager img__vp;
    private List<String> imgUrlString;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = MainActivity.this;

        img__vp = (ViewPager)findViewById(R.id.img__vp);

        imgUrlString = new ArrayList<>();
        imgUrlString.add("http://pi.weather.com.cn/i/product/pic/l/sevp_aoc_rdcp_sldas_ebref_achn_l88_pi_20180301082400001.png");
        imgUrlString.add("http://pi.weather.com.cn/i/product/pic/l/sevp_aoc_rdcp_sldas_ebref_achn_l88_pi_20180301072400001.png");
        imgUrlString.add("http://pi.weather.com.cn/i/product/pic/l/sevp_aoc_rdcp_sldas_ebref_achn_l88_pi_20180301073000001.png");

        ImageViewPagerAdapter imageViewPagerAdapter = new ImageViewPagerAdapter(context,imgUrlString);
        img__vp.setAdapter(imageViewPagerAdapter);

    }


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
