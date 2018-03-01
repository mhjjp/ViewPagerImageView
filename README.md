#ViewPagerImageView

解决ViewPager 与 ImageView的滑动冲突


#####  gradle 添加

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

    compile 'com.github.mhjjp:HttpMhUtils:v1.0'


  #思路

  - 当图片放大后，滑动图片，图片的边界还没有出现屏幕中时，ImageView需要告诉父容器ViewPager,不去拦截传递的滑动事件，并且消费掉滑动事件。

  #实现

  1. 怎么判断边界是否要出现
  2. 怎么告诉父容器ViewPager不去拦截滑动事件，ImageView并消费掉事件。

  - 具体地看ScaleIamgeView 注释
