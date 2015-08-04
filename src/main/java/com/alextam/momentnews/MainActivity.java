package com.alextam.momentnews;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.alextam.momentnews.widget.ClickTextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView tv_content;

    private String content
            = "Saint-Denis, Reunion Island (CNN)Investigators and volunteers are scouring the shoreline of Reunion in the western Indian Ocean for more debris that could be linked to missing Malaysia Airlines Flight 370.\n"
            + "\n"
            + "Objects that wash ashore on the island are being scrutinized.\n"
            + "\n"
            + "Farther out across the ocean, authorities on other islands -- Mauritius and the Seychelles -- say they are on the lookout for items bobbing in the waves that might be from the lost jetliner.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init()
    {
        tv_content = (TextView) findViewById(R.id.tv_content_main);
        tv_content.setTextSize(18);
        tv_content.setText(content, TextView.BufferType.SPANNABLE);

        //点击每个单词响应
        getEachWord(tv_content);
        tv_content.setMovementMethod(LinkMovementMethod.getInstance());
    }

    public void getEachWord(TextView textView){
        Spannable spans = (Spannable)textView.getText();
        Integer[] indices = getIndices(
                textView.getText().toString().trim() + " ", ' ');
        int start = 0;
        int end = 0;
        // to cater last/only word loop will run equal to the length of indices.length
        for (int i = 0; i <= indices.length; i++) {
            ClickableSpan clickSpan = getClickableSpan();
            // to cater last/only word
            end = (i < indices.length ? indices[i] : spans.length());
            if(end > start)
            {
                spans.setSpan(clickSpan, start, end,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                start = end + 1;
            }
        }
        //改变选中文本的高亮颜色
        textView.setHighlightColor(Color.BLUE);
    }
    private ClickableSpan getClickableSpan(){
        return new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                TextView tv = (TextView) widget;
                String s = tv
                        .getText()
                        .subSequence(tv.getSelectionStart(),
                                tv.getSelectionEnd()).toString();
                Log.d("tapped on:", s);
            }
            @Override
            public void updateDrawState(TextPaint ds) {
                ds.setColor(Color.BLACK);
                ds.setUnderlineText(false);
            }
        };
    }

    public static Integer[] getIndices(String s, char c) {
        int pos = s.indexOf(c, 0);
        List<Integer> indices = new ArrayList<Integer>();
        while (pos != -1) {
            indices.add(pos);
            pos = s.indexOf(c, pos + 1);
        }
        return (Integer[]) indices.toArray(new Integer[0]);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
