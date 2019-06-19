package com.example.scotti.procrastinanao;

import android.app.AppOpsManager;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Process;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.scotti.procrastinanao.pAtividade.Atividade;
import com.example.scotti.procrastinanao.pAtividade.AtividadeFragment;
import com.example.scotti.procrastinanao.pObjetivo.ObjetivoFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import static android.app.AppOpsManager.MODE_ALLOWED;
import static android.app.AppOpsManager.OPSTR_GET_USAGE_STATS;

public class MainActivity extends AppCompatActivity {

    public ArrayList<Atividade> listUsageStats = new ArrayList<>();

        //NAVEGAÇÃO MENU
        public BottomNavigationView navigation;
        private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment fragment = null;

            switch (item.getItemId()) {
                case R.id.navigation_inicio:
                    fragment = new ObjetivoFragment();
                    break;

                case R.id.navigation_atividade:
                    fragment = new AtividadeFragment();
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //USAGE
        listUsageStats = UsageStats();

        //NAVEGAÇÃO
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ObjetivoFragment()).commit();

    }

    public boolean checkForPermission(Context context) {
        AppOpsManager appOps = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
        int mode = appOps.checkOpNoThrow(OPSTR_GET_USAGE_STATS, Process.myUid(), context.getPackageName());
        return mode == MODE_ALLOWED;
    }

    public ArrayList<Atividade> UsageStats(){

        PackageManager packageManager = getApplicationContext().getPackageManager();
        ApplicationInfo applicationInfo ;

        String packageName, tempoString, nomeApp;
        long TimeInforground, time = System.currentTimeMillis();
        int minutes, seconds, hours;
        Drawable icon = null;

        @SuppressWarnings("ResourceType")
        UsageStatsManager mUsageStatsManager = (UsageStatsManager) getApplicationContext().getSystemService("usagestats");

        List<UsageStats> stats = mUsageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, time - 1000*10, time);

        ArrayList<Atividade> listAtividade = new ArrayList<>();

        if(stats != null) {
            SortedMap<Long, UsageStats> mySortedMap = new TreeMap<Long, UsageStats>();

            listAtividade.clear();

            for (UsageStats usageStats : stats) {

                TimeInforground = usageStats.getTotalTimeInForeground();

                packageName = usageStats.getPackageName();

                try {
                    applicationInfo  = packageManager.getApplicationInfo(packageName,0);
                    icon = getPackageManager().getApplicationIcon(packageName);
                } catch (final PackageManager.NameNotFoundException e) {
                    applicationInfo = null;
                }

                nomeApp = (String) (applicationInfo != null ? packageManager.getApplicationLabel(applicationInfo) : "?");

                minutes = (int) ((TimeInforground / (1000 * 60)) % 60);
                seconds = (int) (TimeInforground / 1000) % 60;
                hours = (int) ((TimeInforground / (1000 * 60 * 60)) % 24);
                tempoString = hours + "h" + minutes + "m" + seconds + "s";

                Atividade atividade = new Atividade(nomeApp, tempoString, icon);

                if(!tempoString.equals("0h0m0s")){
                    listAtividade.add(atividade);
                }

            }
        }

        return listAtividade;
    }


}