package com.ch.picrvdemo;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.aigestudio.wheelpicker.widgets.WheelMonthPicker;
import com.aigestudio.wheelpicker.widgets.WheelYearPicker;
import com.ch.picrvdemo.widget.OnStickyChangeListener;
import com.ch.picrvdemo.widget.SpaceDecoration;
import com.ch.picrvdemo.widget.StickyHeadContainer;
import com.ch.picrvdemo.widget.StickyItemDecoration;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonToken;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rv_pictrues)
    RecyclerView rvPictrues;
    @BindView(R.id.tv_picture_time)
    TextView tvPictureTime;
    @BindView(R.id.shc_pictrues)
    StickyHeadContainer shcPictrues;
    private PictureAdapter pictureAdapter;
    private String json = "[{\"date\":\"2019-01\",\"children\":[{\"id\":\"30\",\"title\":\"迪拜之旅1\",\"date_time\":\"1546617600\",\"create_time\":" +
            "\"1545716408\",\"picture_count\":\"32\",\"status\":\"1\",\"date\":\"2019-01-05\"," +
            "\"cover_image\":\"http:\\/\\/chuangfen.oss-cn-hangzhou.aliyuncs.com\\/public\\/attachment\\/201812\\/25\\/13\\/5c21c2b73a93d.jpg\"}]}," +
            "{\"date\":\"2018-12\",\"children\":[{\"id\":\"31\",\"title\":\"长沙会议图集5\",\"date_time\":\"1545753600\"," +
            "\"create_time\":\"1545812893\",\"picture_count\":\"70\",\"status\":\"1\",\"date\":\"2018-12-26\"," +
            "\"cover_image\":\"http:\\/\\/chuangfen.oss-cn-hangzhou.aliyuncs.com\\/public\\/attachment\\/201812\\/26\\/16\\/5c233b9a23f5a.jpg\"}," +
            "{\"id\":\"29\",\"title\":\"长沙会议图集4\",\"date_time\":\"1545667200\",\"create_time\":\"1545710364\"," +
            "\"picture_count\":\"61\",\"status\":\"1\",\"date\":\"2018-12-25\"," +
            "\"cover_image\":\"http:\\/\\/chuangfen.oss-cn-hangzhou.aliyuncs.com\\/public\\/attachment\\/201812\\/25\\/11\\/5c21ab1b9263d.jpg\"}," +
            "{\"id\":\"24\",\"title\":\"长沙会议图集3\",\"date_time\":\"1543939200\",\"create_time\":\"1544605755\"," +
            "\"picture_count\":\"118\",\"status\":\"1\",\"date\":\"2018-12-05\"," +
            "\"cover_image\":\"http:\\/\\/chuangfen.oss-cn-hangzhou.aliyuncs.com\\/public\\/attachment\\/201812\\/12\\/17\\/5c10d0bc9ea4a.jpg\"}," +
            "{\"id\":\"23\",\"title\":\"长沙会议图集2\",\"date_time\":\"1543766400\",\"create_time\":\"1544605483\"," +
            "\"picture_count\":\"52\",\"status\":\"1\",\"date\":\"2018-12-03\"," +
            "\"cover_image\":\"http:\\/\\/chuangfen.oss-cn-hangzhou.aliyuncs.com\\/public\\/attachment\\/201812\\/12\\/17\\/5c10cf66f0d77.jpg\"}," +
            "{\"id\":\"22\",\"title\":\"长沙会议图集\",\"date_time\":\"1543593600\",\"create_time\":\"1544605082\"," +
            "\"picture_count\":\"9\",\"status\":\"1\",\"date\":\"2018-12-01\"," +
            "\"cover_image\":\"http:\\/\\/chuangfen.oss-cn-hangzhou.aliyuncs.com\\/public\\/attachment\\/201812\\/12\\/17\\/5c10ce98db254.jpg\"}]}" +
            ",{\"date\":\"2018-11\",\"children\":[{\"id\":\"10\",\"title\":\"测试10月\",\"date_time\":\"1543248000\"," +
            "\"create_time\":\"1543281219\",\"picture_count\":\"6\",\"status\":\"1\",\"date\":\"2018-11-27\"," +
            "\"cover_image\":\"http:\\/\\/chuangfen.oss-cn-hangzhou.aliyuncs.com\\/public\\/attachment\\/201811\\/27\\/09\\/5bfc9a3af1a57.jpg\"}]}," +
            "{\"date\":\"2018-10\",\"children\":[{\"id\":\"9\",\"title\":\"测试图集\",\"date_time\":\"1539100800\"," +
            "\"create_time\":\"1543222989\",\"picture_count\":\"9\",\"status\":\"1\",\"date\":\"2018-10-10\"," +
            "\"cover_image\":\"http:\\/\\/chuangfen.oss-cn-hangzhou.aliyuncs.com\\/public\\/attachment\\/201811\\/26\\/17\\/5bfbb669cd528.jpg\"}]}," +
            "{\"date\":\"2018-09\",\"children\":[{\"id\":\"12\",\"title\":\"测试9月\",\"date_time\":\"1536163200\"," +
            "\"create_time\":\"1543282534\",\"picture_count\":\"8\",\"status\":\"1\",\"date\":\"2018-09-06\"," +
            "\"cover_image\":\"http:\\/\\/chuangfen.oss-cn-hangzhou.aliyuncs.com\\/public\\/attachment\\/201811\\/27\\/09\\/5bfc9f651c4c5.jpg\"}]}," +
            "{\"date\":\"2018-08\",\"children\":[{\"id\":\"26\",\"title\":\"迪拜之旅\",\"date_time\":\"1534262400\"," +
            "\"create_time\":\"1544606585\",\"picture_count\":\"6\",\"status\":\"1\",\"date\":\"2018-08-15\"," +
            "\"cover_image\":\"http:\\/\\/chuangfen.oss-cn-hangzhou.aliyuncs.com\\/public\\/attachment\\/201812\\/12\\/17\\/5c10d378174d6.jpg\"}]}]";
    private PopUtils popPicture;
    private WheelYearPicker wp_year;
    private WheelMonthPicker wp_month;
    private TextView tv_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        initPop();
        addListener();
    }

    private void addListener() {
        shcPictrues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popPicture.getPopWindow().showAsDropDown(shcPictrues);
            }
        });
    }

    private void initView() {
        StickyItemDecoration stickyItemDecoration = new StickyItemDecoration(shcPictrues, PictureModel.PICTURE_TITLE);
        stickyItemDecoration.setOnStickyChangeListener(new OnStickyChangeListener() {
            @Override
            public void onScrollable(int offset) {
                shcPictrues.scrollChild(offset);
                shcPictrues.setVisibility(View.VISIBLE);
            }

            @Override
            public void onInVisible() {
                shcPictrues.reset();
                shcPictrues.setVisibility(View.INVISIBLE);
            }
        });

        shcPictrues.setDataCallback(new StickyHeadContainer.DataCallback() {
            @Override
            public void onDataChange(int pos) {
                List<PictureModel> listModels = pictureAdapter.getData();
                if (listModels.size() > pos) {
                    tvPictureTime.setText(listModels.get(pos).getDate());
                }
            }
        });

        List<PictureListModel> modelList = new Gson().fromJson(json, new TypeToken<List<PictureListModel>>() {
        }.getType());

        List<PictureModel> pictureModelList = new ArrayList<>();

        for (PictureListModel model : modelList) {
            PictureModel title = new PictureModel(PictureModel.PICTURE_TITLE);
            title.setDate(model.getDate());
            //先添加title
            pictureModelList.add(title);
            //再添加数据
            pictureModelList.addAll(model.getChildren());
        }
        pictureAdapter = new PictureAdapter(pictureModelList);

        rvPictrues.setLayoutManager(new GridLayoutManager(this, 2));
        rvPictrues.addItemDecoration(stickyItemDecoration);
        SpaceDecoration spaceDecoration = new SpaceDecoration(DensityUtils.dp2px(this, 10));
        spaceDecoration.setPaddingStart(false);
        rvPictrues.addItemDecoration(spaceDecoration);
        rvPictrues.setAdapter(pictureAdapter);
        pictureAdapter.bindToRecyclerView(rvPictrues);
    }


    private void initPop() {
        View view = getViewByRes(R.layout.pop_picture);
        popPicture = new PopUtils(view, false);

        wp_year = view.findViewById(R.id.wp_year);
        wp_month = view.findViewById(R.id.wp_month);
        wp_year.setVisibleItemCount(4);
        wp_month.setVisibleItemCount(4);
        tv_submit = view.findViewById(R.id.tv_submit);

        wp_year.setYearFrame(2000, Calendar.getInstance().get(Calendar.YEAR));
    }


    /**
     * 通过资源res获得view
     *
     * @param res
     * @return
     */
    public View getViewByRes(@LayoutRes int res) {
        return LayoutInflater.from(this).inflate(res, null);
    }
}
