package com.itheima.wechatdemo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.itheima.wechatdemo.model.Dynamic;
import com.itheima.wechatdemo.view.CircleImageView;
import com.itheima.wechatdemo.view.GridViewInScroll;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;


public class MainActivity extends Activity {

    private ListView listView;
    private List<Dynamic> datas;
    private DisplayImageOptions imageLodeOoptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initImageLoader();
        listView = (ListView)findViewById(R.id.listview);

        ListViewAdapter mAdapter = new ListViewAdapter(this,datas);
        listView.setAdapter(mAdapter);
    }


    private void initData(){
        // 头像
        String[] logoUrl = new String[]{ "http://b.hiphotos.baidu.com/image/h%3D200/sign=52b5924e8b5494ee982208191df4e0e1/c2fdfc039245d6887554a155a3c27d1ed31b24e8.jpg",
                "http://h.hiphotos.baidu.com/image/h%3D200/sign=e8dfbdc69a16fdfac76cc1ee848e8cea/738b4710b912c8fc8cfeb020fb039245d78821c9.jpg",
                "http://img4.imgtn.bdimg.com/it/u=142800770,59313854&fm=21&gp=0.jpg",
                "http://img4.imgtn.bdimg.com/it/u=142800770,59313854&fm=21&gp=0.jpg",
                "http://img4.imgtn.bdimg.com/it/u=142800770,59313854&fm=21&gp=0.jpg",
                "http://img4.imgtn.bdimg.com/it/u=142800770,59313854&fm=21&gp=0.jpg",
                "http://img4.imgtn.bdimg.com/it/u=142800770,59313854&fm=21&gp=0.jpg",
                "http://img4.imgtn.bdimg.com/it/u=142800770,59313854&fm=21&gp=0.jpg",
                "http://img4.imgtn.bdimg.com/it/u=142800770,59313854&fm=21&gp=0.jpg"};

        // 动态图片
        String[] imags = new String[]{"http://img4.imgtn.bdimg.com/it/u=142800770,59313854&fm=21&gp=0.jpg",
                "http://img4.imgtn.bdimg.com/it/u=142800770,59313854&fm=21&gp=0.jpg",
                "http://img4.imgtn.bdimg.com/it/u=142800770,59313854&fm=21&gp=0.jpg",
                "http://img4.imgtn.bdimg.com/it/u=142800770,59313854&fm=21&gp=0.jpg",
                "http://img4.imgtn.bdimg.com/it/u=142800770,59313854&fm=21&gp=0.jpg",
                "http://img4.imgtn.bdimg.com/it/u=142800770,59313854&fm=21&gp=0.jpg",
                "http://img4.imgtn.bdimg.com/it/u=142800770,59313854&fm=21&gp=0.jpg",
                "http://img4.imgtn.bdimg.com/it/u=142800770,59313854&fm=21&gp=0.jpg",
                "http://img4.imgtn.bdimg.com/it/u=142800770,59313854&fm=21&gp=0.jpg"};

        datas = new ArrayList<Dynamic>();
        for(int i = 0; i < 20; i ++){
            Dynamic dynamic = new Dynamic();
            dynamic.setLogoUrl(logoUrl[new Random().nextInt(logoUrl.length)]);// 随机取一张图片
            List<String> imageList = new ArrayList<String>();
            for(int j = 0; j < new Random().nextInt(imags.length) + 1; j ++ ){
                imageList.add(imags[new Random().nextInt(imags.length)]);// 随机取一张图片
            }
            dynamic.setImageList(imageList);
            datas.add(dynamic);
        }

    }

    private void initImageLoader() {
        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(this));
        imageLodeOoptions = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.drawable.default_background)
                .showImageOnFail(R.drawable.default_background)
                /*.resetViewBeforeLoading(true)*/
                .cacheOnDisk(true)
                .cacheInMemory(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .considerExifParams(true)
                .displayer(new FadeInBitmapDisplayer(100))
                .build();
    }

    /**dp转成px*/
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    class ListViewAdapter extends ArrayAdapter<Dynamic>{

        public ListViewAdapter(Context context, List<Dynamic> list) {
            super(context, 0, list);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.listview_item_layout, null);
            }
            final CircleImageView imageLogo = (CircleImageView)convertView.findViewById(R.id.imageLogo);
            int width = dip2px(getContext(), getContext().getResources().getDimension(R.dimen.logo_width));
            ImageSize imageSize = new ImageSize(width, width);// 设置图片大小
            ImageLoader.getInstance().loadImage(getItem(position).getLogoUrl(), imageSize, imageLodeOoptions, new SimpleImageLoadingListener(){
                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                    super.onLoadingComplete(imageUri, view, loadedImage);
                    imageLogo.setImageBitmap(loadedImage);
                }
            });
            
//            ImageLoader.getInstance().displayImage(getItem(position).getLogoUrl(), imageLogo);

            GridViewInScroll gridView = (GridViewInScroll)convertView.findViewById(R.id.gridView);
            GridViewAdapter mAdapter = new GridViewAdapter(getContext(), getItem(position).getImageList());
            gridView.setAdapter(mAdapter);
            return convertView;
        }
    }

    /**图片*/
    class GridViewAdapter extends ArrayAdapter<String>{
        private DisplayImageOptions imageLodeOoptions;
        public GridViewAdapter(Context context, List<String> datas) {
            super(context, 0, datas);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.gridview_item_layout, null);
            }
            String url = getItem(position);
            final ImageView imageview = (ImageView)convertView.findViewById(R.id.imageview);
            int width = dip2px(getContext(), getContext().getResources().getDimension(R.dimen.gridview_item_width));
            ImageSize imageSize = new ImageSize(width, width);// 设置图片大小
            ImageLoader.getInstance().displayImage(url, imageview);

//           ImageLoader.getInstance().loadImage(url, imageSize, imageLodeOoptions, new SimpleImageLoadingListener() {
//                @Override
//                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
//                    super.onLoadingComplete(imageUri, view, loadedImage);
//                    imageview.setImageBitmap(loadedImage);
//                }
//            });
            return convertView;
        }
    }
}
