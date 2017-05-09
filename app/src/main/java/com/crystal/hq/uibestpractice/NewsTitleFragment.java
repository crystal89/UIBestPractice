package com.crystal.hq.uibestpractice;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Crystal on 2017/5/8.
 */

public class NewsTitleFragment extends Fragment {

    private ListView news_title_list_view;
    private List<News> newsList = new ArrayList<News>();
    private NewsAdapter newsAdapter;
    private boolean isTwoPane;
    private View view;

    //在newsMainActivity建立与fragment的关联时调用
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        newsList = initNews();
        newsAdapter = new NewsAdapter(context, R.layout.news_title_layout, newsList);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.news_title_fragment_layout, container, false);
        news_title_list_view = (ListView) view.findViewById(R.id.news_title_list_view);
        news_title_list_view.setAdapter(newsAdapter);
        news_title_list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                News news = newsList.get(position);
                if (isTwoPane) {
                    //双页，刷新newsContentFragment中的内容
                    NewsContentFragment ncf = (NewsContentFragment) getFragmentManager().findFragmentById(R.id.news_content_fragment);
                    ncf.onRefresh(news.getTitle(), news.getContent());

                } else {
                    //单页，直接启动newsContentActivity
                    try {
                        NewsContentActivity.actionStart(getActivity(), news.getTitle(), news.getContent());
                    } catch (Exception e) {
                        Log.e("NCA_ActionStart", e.toString());
                    }
                }
            }
        });
        return view;
    }

    //当newsMainActivity被创建完成时调用
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity().findViewById(R.id.news_content_frame_layout) != null) {
            //找到newsContent布局，为双页模式
            isTwoPane = true;
            if (newsList.size() > 0) {
                NewsContentFragment ncf = (NewsContentFragment) getFragmentManager().findFragmentById(R.id.news_content_fragment);
                ncf.onRefresh(newsList.get(0).getTitle(), newsList.get(0).getContent());
            }
        } else
            //未找到newsContent布局，为非双页模式
            isTwoPane = false;
    }

    private List<News> initNews() {
        List<News> news_list = new ArrayList<News>();
        News news1 = new News();
        news1.setTitle("习近平指挥“一带一路”交响乐之华彩篇");
        news1.setContent("　　时光荏苒，沧海桑田。当悠远的驼铃声穿越历史的星空，昔日的落雁孤烟幻化成一条条蜿蜒的铁轨，古老的丝绸之路再一次熠熠生辉。\n" +
                "\n" +
                "　　2013年9月7日，习近平在哈萨克斯坦纳扎尔巴耶夫大学发表演讲，提出了共同建设“丝绸之路经济带”的畅想。一个月后，习近平出访东盟，提出共同建设“21世纪海上丝绸之路”。由此，“一带一路”走入世界视野，开启了一段穿越时间与空间的新旅途。\n" +
                "\n" +
                "　　这是一条贸易之路，它深入中亚、联通中欧，惠及全球，在新的时代背景下完美阐释了“经济全球化”这一世界性课题。\n" +
                "\n" + "　　这是一条友谊之路，它唤起了沿线国家的历史记忆。在中华民族同其他民族的友好交往中，逐步形成了以和平合作、开放包容、互学互鉴、互利共赢为特征的“丝路精神”。\n" +
                "\n" + "　　4载春华秋实，由习近平提出的这份中国方案已结出累累硕果。4轮春夏秋冬，互利共赢、守望相助的中国主张应者云集，获得世界点赞；绿色、健康、智力、和平的中国方案在“一带一路”沿线国家创造了一个又一个发展奇迹。4年间，中国以倡议者和实践者的形象活跃在“一带一路”的舞台上，与世界各国共同奏响了这个时代的华彩乐章。");
        news_list.add(news1);
        News news2 = new News();
        news2.setTitle("日本遭遇1967年以来首次沙尘暴 日媒：中国吹来的");
        news2.setContent("　　日媒称，日本气象厅表示，7日观测到沙尘暴来袭，范围覆盖从日本西部到北部的广大地区，还包括关东地区的部分地区。\n" +
                "\n" +
                "　　共同社5月7日报道称，日本气象厅表示，从中国吹来的大风携带着黄沙，持续到8日，主要是在日本西部。气象厅警告，由于能见度低，可能会对运输造成干扰。\n" +
                "\n" +
                "　　日本气象厅说，在熊本市，7日凌晨3点到6点的能见度已经下降到8公里。能见度下降到不到10公里时，从远处就能清楚地看到雾霾。\n" +
                "\n" +
                "　　7日，在北海道、东北部、中部、关西地区和九州，都能观测到携带沙尘的空气。在群马县和茨城县也看到了沙尘空气。\n" +
                "\n" +
                "　　6日在日本西部观测到的扬沙是今年来的首次。这是自1967年日本有沙尘记录以来最新检测到的扬沙天气。");
        news_list.add(news2);
        return news_list;
    }
}
