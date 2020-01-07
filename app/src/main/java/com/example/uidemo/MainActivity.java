package com.example.uidemo;



import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;

import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetView;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.holder.BadgeStyle;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;

import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.SimpleCircleButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;


import org.andresoviedo.util.android.ContentUtils;


import java.io.File;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity {
    private GridView gview;
    private List<Map<String, Object>> data_list;
    private SimpleAdapter sim_adapter;

    private int[] icon2= { R.drawable.cube, R.drawable.dog,
            R.drawable.chahu, R.drawable.plane, R.drawable.ship,
            R.drawable.qier, R.drawable.android, R.drawable.argentina,
            R.drawable.argentina, R.drawable.argentina, R.drawable.argentina,
            R.drawable.argentina };
    private String[] iconName = { "  魔 方", "  小 狗", "  茶 壶", " 飞 机", "  飞 船", "  企 鹅", "  andy",
            "设置", "语音", "天气", "浏览器", "视频" };

    private Map<String, Object> loadModelParameters = new HashMap<>();
    private static final int REQUEST_CODE_OPEN_FILE = 1101;
    private static final String REPO_URL = "https://github.com/andresoviedo/android-3D-model-viewer/raw/master/models/index";
    private static final int REQUEST_READ_EXTERNAL_STORAGE = 1000;
    private static final int REQUEST_INTERNET_ACCESS = 1001;

    private static final int REQUEST_CODE_OPEN_MATERIAL = 1102;
    private static final int REQUEST_CODE_OPEN_TEXTURE = 1103;
    private static final String SUPPORTED_FILE_TYPES_REGEX = "(?i).*\\.(obj|stl|dae)";
    String to="";
    private BoomMenuButton bmb;
  int[] icon= new int[]{R.drawable.cat,R.drawable.bee,R.drawable.bat,R.drawable.bear,R.drawable.butterfly,R.drawable.cat,R.drawable.bee,R.drawable.bat,R.drawable.bear};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        new DrawerBuilder().withActivity(this).build();
        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName("AR模型设置 ");
        PrimaryDrawerItem item5 = new PrimaryDrawerItem().withIdentifier(1).withName("AR模型设置 ");
        PrimaryDrawerItem item6 = new PrimaryDrawerItem().withIdentifier(1).withName("AR模型设置 ");
        PrimaryDrawerItem item7 = new PrimaryDrawerItem().withIdentifier(1).withName("AR模型设置 ");
        PrimaryDrawerItem item8 = new PrimaryDrawerItem().withIdentifier(1).withName("AR模型设置 ");
        PrimaryDrawerItem item4 = new PrimaryDrawerItem().withIdentifier(1).withName("AR模型设置 ");
        SecondaryDrawerItem item2 = new SecondaryDrawerItem().withIdentifier(2).withName("从文件管理器导入模型").withTextColor(Color.WHITE);
        item1.withName("3D模型").withIcon(R.drawable.threed).withBadge("19").withBadgeStyle(new BadgeStyle().withTextColor(Color.WHITE).withColorRes(R.color.md_red_700));
        PrimaryDrawerItem item3 = new PrimaryDrawerItem().withIdentifier(1).withName("识别图片设置 ");
        item3.withIcon(R.drawable.arimg).withName("AR认图").withTextColor(Color.WHITE).withBadge("7").withBadgeStyle(new BadgeStyle().withTextColor(Color.WHITE).withColorRes(R.color.md_red_700));
        item4.withIcon(R.drawable.speak).withName("听说读写").withTextColor(Color.WHITE).withBadge("6").withBadgeStyle(new BadgeStyle().withTextColor(Color.WHITE).withColorRes(R.color.md_red_700));
        item5.withIcon(R.drawable.emm).withName("AR探索").withTextColor(Color.WHITE).withBadge("8").withBadgeStyle(new BadgeStyle().withTextColor(Color.WHITE).withColorRes(R.color.md_red_700));
        item6.withIcon(R.drawable.cat).withName("回收站设置").withTextColor(Color.WHITE);
        item7.withIcon(R.drawable.gy).withName("帮助").withTextColor(Color.WHITE);
        item8.withIcon(R.drawable.sz).withName("关于").withTextColor(Color.WHITE);
        Drawer result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(myToolbar)
                .withSliderBackgroundColor(getColor(R.color.blue))
                .addDrawerItems(
                        item1,
                        new DividerDrawerItem(),
                        item2,
                        new SecondaryDrawerItem().withName("从SD卡导入模型").withTextColor(Color.WHITE),
                        item2,
                        new SecondaryDrawerItem().withName("从服务器更新模型库").withTextColor(Color.WHITE),
                        item3,
                        new DividerDrawerItem(),
                        item4,
                        new DividerDrawerItem(),
                        item5,
                        new DividerDrawerItem(),

                        item7,
                        new DividerDrawerItem(),
                        item8,
                        new DividerDrawerItem()

                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {

                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        return false;
                    }
                })
                .build();
        result.updateItem(item1);
        TapTargetView.showFor(this,                 // `this` is an Activity
                TapTarget.forView(findViewById(R.id.bmb), "点这里体验最新的3d和AR功能！", "3 Dimensions and  Augmented Reality")

                        .outerCircleColor(R.color.colorAccent)      // Specify a color for the outer circle
                        .outerCircleAlpha(0.96f)            // Specify the alpha amount for the outer circle
                        .targetCircleColor(R.color.white)   // Specify a color for the target circle
                        .titleTextSize(20)                  // Specify the size (in sp) of the title text
                        .titleTextColor(R.color.white)      // Specify the color of the title text
                        .descriptionTextSize(10)            // Specify the size (in sp) of the description text
                        .descriptionTextColor(R.color.colorBackground)  // Specify the color of the description text
                        .textColor(R.color.blue)            // Specify a color for both the title and description text
                        .textTypeface(Typeface.SANS_SERIF)  // Specify a typeface for the text
                        .dimColor(R.color.black)            // If set, will dim behind the view with 30% opacity of the given color
                        // .drawShadow(true)                   // Whether to draw a drop shadow or not
                        .cancelable(true)                  // Whether tapping outside the outer circle dismisses the view
                        .tintTarget(true)                   // Whether to tint the target view's color
                        .transparentTarget(false)           // Specify whether the target is transparent (displays the content underneath)
                        //.icon(drawable)                     // Specify a custom drawable to draw as the target
                        .targetRadius(60),                  // Specify the target radius (in dp)
                new TapTargetView.Listener() {          // The listener can listen for regular clicks, long clicks or cancels
                    @Override
                    public void onTargetClick(TapTargetView view) {
                        super.onTargetClick(view);      // This call is optional
                        //  doSomething();
                    }
                });
        gview = (GridView) findViewById(R.id.gview);
        //新建List
        data_list = new ArrayList<Map<String, Object>>();
        //获取数据
        getData();
        //新建适配器
        String [] from ={"image","text"};
        int [] to2 = {R.id.image,R.id.text};
        sim_adapter = new SimpleAdapter(this, data_list, R.layout.item, from, to2);
        //配置适配器
        gview.setAdapter(sim_adapter);
//        ImageView imageView=findViewById(R.id.img);
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final String items[] = {"中文", "英文",  "日语", "韩语", "文言文"};
//                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, 3);
//                builder.setTitle("单选");
////                builder.setIcon(R.mipmap.ic_launcher);
//                builder.setSingleChoiceItems(items, 0,
//                        new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                switch (which) {
//                                    case 0:
//
//                                        break;
//                                    case 1:
//
//                                        break;
//                                    case 2:
//
//                                        break;
//                                }
//
//                            }
//                        });
//                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                        Toast.makeText(MainActivity.this, "确定", Toast.LENGTH_SHORT)
//                                .show();
//                    }
//                });
//                builder.create().show();
//            }
//        });
        gview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        to = "cube.obj";
                        link3dmod();
                        break;
                    case 1:
                        to = "ship.obj";
                        link3dmod();
                        break;
                    case 2:
                        to = "penguin.obj";
                        link3dmod();
                        break;
                    case 3:
                        to="ToyPlane.obj";
                        link3dmod();
                        break;
                    case 4:
                        to="teapot.obj";
                        link3dmod();
                        break;
                }

            }
        });

        setStatusBarFullTransparent();
        setFitSystemWindow(true);

        setSupportActionBar(myToolbar);
//        getSupportActionBar().setTitle("");
        TextView txtNormal = (TextView) findViewById(R.id.text_title);
        TextView textView=findViewById(R.id.text);
        //Typeface typeface1 = ResourcesCompat.getFont(this, R.font.roboto_regular);

        Typeface typeface = ResourcesCompat.getFont(this, R.font.roboto_regular);
        txtNormal.setTypeface(typeface);
       // textView.setTypeface(typeface);

        // Button button=findViewById(R.id.bt1);
        BoomMenuButton bmb = (BoomMenuButton) findViewById(R.id.bmb);
        bmb.setButtonEnum(ButtonEnum.SimpleCircle);

        bmb.setPiecePlaceEnum(PiecePlaceEnum.DOT_9_3);
        bmb.setButtonPlaceEnum(ButtonPlaceEnum.SC_9_1);
        for (int i = 0; i < bmb.getPiecePlaceEnum().pieceNumber(); i++) {
            SimpleCircleButton.Builder builder = new SimpleCircleButton.Builder()
                    .listener(new OnBMClickListener() {
                        @Override
                        public void onBoomButtonClick(int index) {
                            switch (index) {
                                case 0:
                                    to = "cube.obj";
                                    link3dmod();
                                    break;
                                case 1:
                                    to = "ship.obj";
                                    link3dmod();
                                    break;
                                case 2:
                                    to = "penguin.obj";
                                    link3dmod();
                                    break;
                                case 3:
                                    to="ToyPlane.obj";
                                    link3dmod();
                                    break;
                                case 4:
                                    to="teapot.obj";
                                    link3dmod();
                                    break;
                            }
                            // When the boom-button corresponding this builder is clicked.
                          //  Toast.makeText(MainActivity.this, "Clicked " + index, Toast.LENGTH_SHORT).show();
                        }
                    }).normalImageRes(icon[i]) ;
            bmb.addBuilder(builder);
        }
        createFolder(getApplicationContext().getExternalFilesDir(""), "3d");



    }
    public void createFolder(File path, String name) {
        File Folder = new File(path + "/" + name);
        if (!Folder.exists())//判断文件夹是否存在，不存在则创建文件夹，已经存在则跳过
        {
            Folder.mkdir();//创建文件夹
        } else {
            Log.i("", "文件夹已存在");
        }
    }

    private void launchModelRendererActivity(Uri uri) {
        Log.i("Menu", "Launching renderer for '" + uri + "'");
        Intent intent = new Intent(getApplicationContext(), ModelActivity.class);
        intent.putExtra("uri", uri.toString());
        intent.putExtra("immersiveMode", "true");
        intent.putExtra("backgroundColor", "1 1 1 4");

        // content provider case
        if (!loadModelParameters.isEmpty()) {
            intent.putExtra("type", loadModelParameters.get("type").toString());
            loadModelParameters.clear();
        }

        startActivity(intent);
    }


public void link3dmod(){
    File file = new File(getApplicationContext().getExternalFilesDir("3d") + "/"+to);


    ContentUtils.setCurrentDir(file.getParentFile());
    launchModelRendererActivity(Uri.parse("file://" + file.getAbsolutePath()));
}
    /**
     * 全透状态栏
     */
    protected void setStatusBarFullTransparent() {
        if (Build.VERSION.SDK_INT >= 21) {//21表示5.0
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= 19) {//19表示4.4
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //虚拟键盘也透明
            //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    /**
     * 半透明状态栏
     */
    protected void setHalfTransparent() {

        if (Build.VERSION.SDK_INT >= 21) {//21表示5.0
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        } else if (Build.VERSION.SDK_INT >= 19) {//19表示4.4
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //虚拟键盘也透明
            // getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    /**
     * 如果需要内容紧贴着StatusBar
     * 应该在对应的xml布局文件中，设置根布局fitsSystemWindows=true。
     */
    private View contentViewGroup;

    protected void setFitSystemWindow(boolean fitSystemWindow) {
        if (contentViewGroup == null) {
            contentViewGroup = ((ViewGroup) findViewById(android.R.id.content)).getChildAt(0);
        }
        contentViewGroup.setFitsSystemWindows(fitSystemWindow);
    }

    /**
     * 为了兼容4.4的抽屉布局->透明状态栏
     */
    public List<Map<String, Object>> getData(){
        //cion和iconName的长度是相同的，这里任选其一都可以
        for(int i=0;i<icon2.length;i++){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", icon2[i]);
            map.put("text", iconName[i]);
            data_list.add(map);
        }

        return data_list;
    }

}
