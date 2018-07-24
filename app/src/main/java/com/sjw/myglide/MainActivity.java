package com.sjw.myglide;

import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.animation.ViewPropertyAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.ViewTarget;
import com.sjw.myglide.transformation.RotateTransformation;
import com.sjw.myglide.transformation.RoundTransformation;

public class MainActivity extends AppCompatActivity {
    ImageView simpleIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //（1）普通的使用
        String url = "http://img1.dzwww.com:8080/tupian_pl/20150813/16/7858995348613407436.jpg";
        ImageView imageView = (ImageView) findViewById(R.id.normal_iv);
        Glide.with(this)
                .load(url)
                .placeholder(R.mipmap.ic_launcher)//图片加载出来前，显示的图片
                .error(R.mipmap.ic_launcher)//图片加载失败后，显示的图片
//                .thumbnail( 0.2f )//原始图片的倍数
                .override(100, 100)//这5里的单位是px
                .crossFade(2000)//或者使用 dontAnimate() 关闭动画
                .fitCenter()//CenterCrop() 方法是将图片按比例缩放到足矣填充 ImageView 的尺寸，但是图片可能会显示不完整；而 FitCenter() 则是图片缩放到小于等于 ImageView 的尺寸，这样图片是显示完整了，但是 ImageView 就可能不会填满了。
                .into(imageView);

//        skipMemoryCache(true) 告诉 Glide 跳过内存缓存
//.diskCacheStrategy( DiskCacheStrategy.NONE )关闭磁盘缓存
      /*  DiskCacheStrategy.NONE 什么都不缓存
        DiskCacheStrategy.SOURCE 只缓存全尺寸图
        DiskCacheStrategy.RESULT 只缓存最终的加载图
        DiskCacheStrategy.ALL 缓存所有版本图（默认行为）*/

//        //设置 HIGH 优先级
//        Glide.with( context )
//                .load( highPriorityImageUrl )
//                .priority (Priority.HIGH )
//                .into( imageView );
////设置 LOW 优先级
//        Glide.with( context )
//                .load( lowPriorityImageUrl )
//                .priority( Priority.LOW )
//                .into( imageView );


//        显示 Gif 和 Video
//        String gifUrl = "http://i2.mhimg.com/M00/0E/AE/CgAAilTPWJ2Aa_EIACcMxiZi5xE299.gif";
//        Glide.with( context )
//                .load( gifUrl )
//                .placeholder（ R.drawable.default ）
//    .error( R.drawable.error )
//                .into( imageView );

//        String filePath = "/storrage/emulated/0/Pictures/video.mp4";
//        Glide.with( context )
//                .load( Uri.fromFile( new File( filePath ) ) )
//                .into( imageView );
        //（2）获取到bitmap
        String mUrl = "http://img1.dzwww.com:8080/tupian_pl/20150813/16/7858995348613407436.jpg";
        simpleIv = (ImageView) findViewById(R.id.simple_iv);
        Glide.with(this)
                .load(mUrl)
                .asBitmap()
                .fitCenter()
                .into(mSimpleTarget);


        //（3）自定义view使用图片


        CustomView mCustomView = (CustomView) findViewById(R.id.viewtarget_iv);

        ViewTarget viewTarget = new ViewTarget<CustomView, GlideDrawable>(mCustomView) {
            @Override
            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                this.view.setImage(resource);
            }
        };

        Glide.with(this)
                .load(mUrl)
                .override(100, 100)
                .into(viewTarget);

        //(4)对图片进行圆角  旋转等的效果
        ImageView tranformtion_iv = (ImageView) findViewById(R.id.tranformtion_iv);
        Glide.with(this)
                .load(mUrl)
                .override(100, 100)
                .transform(new RoundTransformation(this, 20), new RotateTransformation(this, 40f))
                .into(tranformtion_iv);

        //(5)常规带动画
        ImageView animate_iv = (ImageView) findViewById(R.id.animate_iv);
        Glide.with(this)
                .load(mUrl)
                .override(100, 100)
                .transform(new RoundTransformation(this, 20))
                .animate(R.anim.zoom_in)
                .into(animate_iv);
        //(6)自定义控件带动画
        CustomView animate2_iv = (CustomView) findViewById(R.id.animate2_iv);
        ViewTarget viewTarget2 = new ViewTarget<CustomView, GlideDrawable>(animate2_iv) {
            @Override
            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                this.view.setImage(resource);
            }
        };


        Glide.with(this)
                .load(mUrl)
                .animate(animator)
                .into(viewTarget2);

    }


    private SimpleTarget<Bitmap> mSimpleTarget = new SimpleTarget<Bitmap>(100, 100) {
        @Override
        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> animation) {
            simpleIv.setImageBitmap(resource);
        }
    };


    ViewPropertyAnimation.Animator animator = new ViewPropertyAnimation.Animator() {
        @Override
        public void animate(View view) {
            view.setAlpha(0f);

            ObjectAnimator fadeAnim = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f);
            fadeAnim.setDuration(2500);
            fadeAnim.start();
        }
    };


}
