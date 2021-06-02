package com.example.editlistdemo;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.apkfuns.logutils.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class EditAdapter extends BaseQuickAdapter<NumberBean, BaseViewHolder> {
    public EditAdapter(@Nullable List<NumberBean> data) {
        super(R.layout.item, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, NumberBean bean) {
        EditText editText = holder.findView(R.id.etContent);
        //        通过tag判断当前editText是否已经设置监听，有监听的话，移除监听再给editText赋值
        if (editText.getTag() instanceof TextWatcher) {
            editText.removeTextChangedListener((TextWatcher) editText.getTag());
        }
        //        必须在判断tag后给editText赋值，否则会数据错乱
        holder.setText(R.id.etContent, bean.getNumber());

        final TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                LogUtils.d("adapter " + "  afterTextChanged  -->" + s.toString());

                bean.setNumber(s.toString());
            }
        };
        editText.addTextChangedListener(watcher);
        editText.setTag(watcher);
    }
}
