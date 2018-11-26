package br.com.mydancer.mydancer.ui.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.com.mydancer.mydancer.R;
import br.com.mydancer.mydancer.model.Event;
import br.com.mydancer.mydancer.retrofit.RetrofitInicializador;
import br.com.mydancer.mydancer.ui.recyclerview.adapter.EventAdapter;
import br.com.mydancer.mydancer.ui.recyclerview.adapter.listener.OnItemClickListener;
import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventTabActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private static AlertDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_tab);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        progress = new SpotsDialog(this, R.style.Custom);
        progress.setTitle("Carregando");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public static class PlaceholderFragment extends Fragment {

        private EventAdapter adapter;

        private static final String ARG_SECTION_DATE = "section_date";

        public PlaceholderFragment() {
        }

        public static PlaceholderFragment newInstance(String date) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putString(ARG_SECTION_DATE, date);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            final View rootView = inflater.inflate(R.layout.fragment_event_tab, container, false);

            Call<List<Event>> call = new RetrofitInicializador().getEventService().getDate(getArguments().getString(ARG_SECTION_DATE));
            call.enqueue(new Callback<List<Event>>() {
                @Override
                public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                    final List<Event> events = response.body();
                    final RecyclerView listEvents = rootView.findViewById(R.id.list_event_recyclerview2);

                    adapter = new EventAdapter(events, getActivity());
                    listEvents.setAdapter(adapter);
                    adapter.setOnItemClickListener(new OnItemClickListener() {
                        @Override
                        public void OnItemClick(Event event, int posicao) {
                            Intent intent = new Intent(getContext(), ConfirmEventActivity.class);
                            intent.putExtra("nomeEvento", events.get(posicao));
                            startActivity(intent);
                        }
                    });

                    EventTabActivity.progress.dismiss();
                }

                @Override
                public void onFailure(Call<List<Event>> call, Throwable t) {
                    Log.e("onFailure chamado", t.getMessage());
                    EventTabActivity.progress.dismiss();
                }
            });

            return rootView;
        }

    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return PlaceholderFragment.newInstance(getDateString(0));
            } else if (position == 1) {
                return PlaceholderFragment.newInstance(getDateString(1));
            } else {
                return PlaceholderFragment.newInstance(getDateString(7));
            }
        }

        @Override
        public int getCount() {
            return 3;
        }

        private Date getPreviousDate(int day) {
            final Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, day);
            return cal.getTime();
        }

        private String getDateString(int day) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return dateFormat.format(getPreviousDate(day));
        }
    }
}
