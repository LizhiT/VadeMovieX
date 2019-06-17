package com.lee.vademovies.view.fragment.welcome;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lee.vademovies.R;

/**
 * Created :  LiZhIX
 * Date :  2019/6/12 16:37
 * Description  :  引导页第二个页面
 */
public class WelcomeTwoFragmnet extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.welcome_two, container, false);


        return view;
    }

}