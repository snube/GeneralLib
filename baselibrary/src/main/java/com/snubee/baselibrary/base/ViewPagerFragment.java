package com.snubee.baselibrary.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.snubee.baselibrary.R;


/**
 * ViewPager+Fragment 中Fragment,具有相同 fragment的继承该类
 *
 * @author snubee
 * @time 2016/9/21 9:46
 */
public abstract class ViewPagerFragment extends BaseFragment {
    public static final String BUNDLE_INDEX_KEY = "BUNDLE_INDEX_KEY";
    private FrameLayout viewContainer = null;
    private View base_progress_layout, noDataLayout;//全部加载圈圈和错误的布局
    private RelativeLayout proBarLayout = null;//圈圈布局
    private TextView tipTvw = null;//圈圈文字
    protected View bodyView;
    protected Bundle bundle;
    protected int curFragmentIndex;
    private ViewStub noDataViewStub;

    @Override
    public void onAttach(Context activity) {
        // TODO Auto-generated method stub
        super.onAttach(activity);
        bundle = getArguments();
        //判断是否包含索引
        if (bundle != null && bundle.containsKey(BUNDLE_INDEX_KEY)) {
            curFragmentIndex = bundle.getInt(BUNDLE_INDEX_KEY);
        }
    }

    /**
     * @Title: loadView
     * @Description: TODO(为创建 Fragment有进度条视图.oncreateView()中 返回该View  )
     * @param: resource
     * @param: root
     * @param: context
     * @return: View
     */
    protected View loadView(int resource, ViewGroup root, Context context) {
        return loadView(LayoutInflater.from(context).inflate(resource, root, false), context);
    }

    /**
     * @Title: loadView
     * @Description: TODO为创建 Fragment有进度条视图.oncreateView()中 返回该View  )
     * @param: resource
     * @param: root
     * @param: context
     * @return: View
     */
    protected View loadView(View bodyView, Context context) {
        if (bodyView == null) return null;
        this.bodyView = bodyView;
        viewContainer = new FrameLayout(context);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        viewContainer.addView(bodyView, params);

        base_progress_layout = LayoutInflater.from(context).inflate(R.layout.framement_base_progress_layout, null, true);
        proBarLayout = (RelativeLayout) base_progress_layout.findViewById(R.id.load_base_progress_layout);
        tipTvw = (TextView) base_progress_layout.findViewById(R.id.load_tishi_text);

        //无数据提示
        noDataViewStub = (ViewStub) base_progress_layout.findViewById(R.id.viewstub_nodata);


        viewContainer.addView(base_progress_layout);

        return viewContainer;
    }

    /**
     * 显示没有数据的view
     * noClickListener 没有数据提示操作的事件处理
     * noDataTishiResId 没有数据的显示内容
     * noDataTishIconId 没有数据的显示图片
     * nodataClickResId 需要操作的字符
     */
    public void showNodataView(final View.OnClickListener noClickListener, int noDataTishIconId, int noDataTishiResId, int... noDataClickResId) {
        if (noDataLayout == null) {
            noDataLayout = noDataViewStub.inflate();
            return;
        } else {
            noDataLayout.setVisibility(View.VISIBLE);
        }
        ImageView noDataTishIcon = (ImageView) noDataLayout.findViewById(R.id.no_data_tishi_icon);
        if (noDataTishIcon != null) {
            noDataTishIcon.setImageResource(noDataTishIconId);
        }
        TextView noDataTishiTextView = (TextView) noDataLayout.findViewById(R.id.no_data_tishi_text);
        if (null == noDataTishiTextView)
            return;
        noDataTishiTextView.setText(getString(noDataTishiResId));
        TextView nodataClickText = (TextView) noDataLayout.findViewById(R.id.no_data_click);
        if (noDataClickResId != null && noDataClickResId.length > 0) {
            nodataClickText.setVisibility(View.VISIBLE);
            nodataClickText.setText(getString(noDataClickResId[0]));
            nodataClickText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(noClickListener!=null)
                        noClickListener.onClick(v);
                }
            });
        } else {
            nodataClickText.setVisibility(View.GONE);
        }
        if (bodyView != null) {
            bodyView.setVisibility(View.GONE);
        }
    }

    /**
     * 隐藏没有数据提示的界面
     */
    protected void goneNoDataView() {
        if (base_progress_layout != null) {
            base_progress_layout.setVisibility(View.GONE);
        }
        if (bodyView != null) {
            bodyView.setVisibility(View.VISIBLE);
        }
    }

    /**
     * @Title: setLoadingProVisible
     * @Description: TODO(设置 ProBar是否显示。当为true时：显示 滚动条, loadingText显示提示内容
     * @param: isLoading true：显示；false:不显示
     * @param: loadingText  进度条文本
     * @return: void
     */
    protected void setLoadingProVisible(boolean isLoading, String loadingText) {
        if (viewContainer == null || tipTvw == null || bodyView == null) return;
        if (isLoading) {
            base_progress_layout.setVisibility(View.VISIBLE);
            proBarLayout.setVisibility(View.VISIBLE);
            bodyView.setVisibility(View.GONE);
            tipTvw.setText(loadingText);
        } else {
            base_progress_layout.setVisibility(View.GONE);
            bodyView.setVisibility(View.VISIBLE);
        }
    }
}

