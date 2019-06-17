package com.lee.vademovies.util;

import android.text.InputFilter;
import android.text.Spanned;
import android.widget.EditText;

/**
 * Created :  LiZhIX
 * Date :  2019/6/13 17:12
 * Description  :  输入框工具类
 */
public class EditTextUtils {
    /**
     * 禁止EditText输入空格
     *
     * @param editText
     */
    public static void setEditTextInhibitInputSpace(EditText editText) {
        InputFilter filter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                if (source.equals(" ")) {
                    return "";
                } else {
                    return null;
                }

            }
        };
        editText.setFilters(new InputFilter[]{filter});
    }
}
