package com.littlejerk.xpopup.demo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.widget.Button;
import android.widget.Toast;

import com.littlejerk.xpopup.DialogConfig;
import com.littlejerk.xpopup.DialogDelegate;
import com.littlejerk.xpopup.R;
import com.littlejerk.xpopup.XPopup;
import com.littlejerk.xpopup.XPopupCompat;
import com.littlejerk.xpopup.view.XPopupViewHolder;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @author HHotHeart
 * @date 2022/5/10 21:25
 * @desc 描述
 */
public class DialogDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_demo);
        setTitle("DialogDemo");
        findViewById(R.id.btn_dialog1).setOnClickListener(v -> {
            XPopupCompat.get().asDialog(DialogDemoActivity.this)
                    .themeStyle(R.style.XPopup_Dialog)    //主题样式
                    .view(R.layout.popup_test)            //可传View
                    .radius(50)                           //设置四个圆角
//                    //设置弹窗上（下、左、右）方圆角，
//                    .radiusSideTop(50)
                    .animStyle(R.style.PopupEnterExpandExitShrinkAnimation)  //动画
                    //宽高比
                    .widthInPercent(0.8f)
//                    .heightInPercent(0.8f)
//                    //最大宽高
//                    .maxWidth(800)
//                    .maxHeight(800)
//                    //框架默认是width=ViewGroup.LayoutParams.MATCH_PARENT，height=ViewGroup.LayoutParams.WRAP_CONTENT
//                    .width(ViewGroup.LayoutParams.MATCH_PARENT)
//                    .height(ViewGroup.LayoutParams.WRAP_CONTENT)
//                    .matchHeight()
//                    .matchWidth()
//                    .wrapHeight()
//                    .wrapWidth()
                    .cancelable(false)                   //返回键dismiss
                    .cancelableOutside(false)            //点击外部区域dismiss
                    .autoDismissTime(5000)               //5秒后自动dismiss
                    .gravity(Gravity.CENTER)             //设置显示位置
                    //添加View点击事件，也支持添加多个View的点击事件
                    .clickListener(R.id.btn_left, (popupInterface, view, holder) -> {
                        popupInterface.dismiss();
                        Toast.makeText(
                                DialogDemoActivity.this,
                                "点击了" + ((Button) holder.getView(R.id.btn_left)).getText(),
                                Toast.LENGTH_SHORT)
                                .show();
                    })
                    //添加多个View长按事件，也支持添加单个View的点击事件
                    .longClickIds(R.id.btn_left, R.id.btn_right)
                    .longClickIdsListener((popupInterface, view, holder) -> {
                        if (view.getId() == R.id.btn_left) {
                            Toast.makeText(
                                    DialogDemoActivity.this,
                                    "长按了" + ((Button) holder.getView(R.id.btn_left)).getText(),
                                    Toast.LENGTH_SHORT)
                                    .show();
                        } else if (view.getId() == R.id.btn_right) {
                            Toast.makeText(
                                    DialogDemoActivity.this,
                                    "长按了" + ((Button) holder.getView(R.id.btn_right)).getText(),
                                    Toast.LENGTH_SHORT)
                                    .show();
                        }
                        return true;
                    })
                    //关闭事件监听
                    .dismissListener(popupInterface ->
                            Toast.makeText(
                                    DialogDemoActivity.this,
                                    "消失监听",
                                    Toast.LENGTH_SHORT)
                                    .show())
                    //显示事件监听
                    .showListener(popupInterface ->
                            Toast.makeText(
                                    DialogDemoActivity.this,
                                    "显示监听",
                                    Toast.LENGTH_SHORT)
                                    .show())
                    //View绑定事件监听
                    .bindViewListener((XPopupViewHolder holder) -> {
                        //holder可以对象弹窗所有控件操作，在这里处理弹窗内部相关逻辑
                        holder.setText(R.id.tv_popup_title, "XPopup");
                    })
                    .create()
                    .show();
        });

        findViewById(R.id.btn_dialog2).setOnClickListener(v -> {
            XPopup<DialogConfig, DialogDelegate> xPopup = XPopupCompat.get().asDialog(DialogDemoActivity.this)
                    .view(R.layout.popup_test)
                    .gravity(Gravity.BOTTOM)
                    .autoDismissTime(10000)
                    .create();
            xPopup.show();
            new Handler(Looper.getMainLooper()).postDelayed(() ->
                    xPopup.getPopupViewHolder().setText(R.id.tv_popup_title, "获取xPopup对象，更新标题"), 5000);
        });
    }

}