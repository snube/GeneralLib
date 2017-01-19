package com.snubee.baselibrary.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.Toast;

/**
 * fragment的基类
 *
 * @author snubee
 * @time 2016/9/21 9:20
 */
public class BaseFragment extends Fragment {

    protected Context mContext;

    public static <T extends BaseFragment> BaseFragment newInstance(Bundle paramBundle, Class<T> paramClass) {
        BaseFragment fragment = null;
        try {
            fragment = (BaseFragment) paramClass.newInstance();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        fragment.setArguments(paramBundle);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }


    @Override
    public void onLowMemory() {
        // TODO Auto-generated method stub
        super.onLowMemory();
    }

    public void showToast(String txt) {
        Toast.makeText(mContext, txt, Toast.LENGTH_LONG).show();
    }


}