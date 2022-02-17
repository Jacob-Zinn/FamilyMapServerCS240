package com.example.modellitpromx;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class SessionsActivity extends AppCompatActivity {

    private static final String TAG = "SessionsActivity";
    private Toolbar toolbar;
//    private Dialog sessionsMenuDialog;
    private Button bottomModalOpen;
    //TODO delete all instances of bottomModalOpen button

    private ImageView sessionsMenuIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sessions);

        //implement toolbar as actionbar
        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        if (null != getSupportActionBar()) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Menu button that launches the dialog fragment
        sessionsMenuIcon = findViewById(R.id.sessionsMenuIcon);
        sessionsMenuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchSessionsMenu();
            }
        });

        bottomModalOpen = findViewById(R.id.bottomModalOpen);
        bottomModalOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  ItemListDialogFragment.newInstance(Utils.getSessionsOptionsArray()).show(getSupportFragmentManager(), "dialog");
            }
        });
    }

    public void launchSessionsMenu() {
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(
                this, R.style.BottomSheetDialogTheme
        );
        View bottomSheetView = LayoutInflater.from(getApplicationContext())
                .inflate(
                        R.layout.sessions_dialog_fragment,
                        findViewById(R.id.bottomSheetContainer)
                );
        bottomSheetView.findViewById(R.id.gatedropAnalytics).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SessionsActivity.this, "Kille me if this doesn't work", Toast.LENGTH_SHORT).show();
                bottomSheetDialog.dismiss();
            }
        });
        bottomSheetView.findViewById(R.id.twelveWeekTarget).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SessionsActivity.this, "2", Toast.LENGTH_SHORT).show();
                bottomSheetDialog.dismiss();
            }
        });
        bottomSheetView.findViewById(R.id.clearFilter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SessionsActivity.this, "3", Toast.LENGTH_SHORT).show();
                bottomSheetDialog.dismiss();
            }
        });
        bottomSheetView.findViewById(R.id.showHiddenSessions).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SessionsActivity.this, "4", Toast.LENGTH_SHORT).show();
                bottomSheetDialog.dismiss();
            }
        });
        bottomSheetView.findViewById(R.id.cancelButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SessionsActivity.this, "5", Toast.LENGTH_SHORT).show();
                bottomSheetDialog.dismiss();
            }
        });
        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();

//        DialogFragment newFragment = new SessionsDialogFragment();
//        newFragment.show(getSupportFragmentManager(), "sessionsmenu");
    }

    //Implement back navigation icon
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Log.d(TAG, "onOptionsItemSelected: Gatedrop analytics");
                onBackPressed();
                break;
            default:
                Toast.makeText(this, "something went wrong", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onOptionsItemSelected: shouldn't reach this point");
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    //    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.sessions_menu, menu);
//        return true;
//    }

//    public void showPopup(View v) {
//        sessionsMenuDialog.setContentView(R.layout.));
//    }


//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.gatedropAnalytics:
//                Toast.makeText(this, "Gatedrop analytics", Toast.LENGTH_SHORT).show();
//                Log.d(TAG, "onOptionsItemSelected: Gatedrop analytics");
//                break;
//            case R.id.twelveWeekTarget:
//                Toast.makeText(this, "twelveWeekTarget", Toast.LENGTH_SHORT).show();
//                Log.d(TAG, "onOptionsItemSelected: twelve week");
//                break;
//            case R.id.clearFilter:
//                Toast.makeText(this, "clear filter", Toast.LENGTH_SHORT).show();
//                Log.d(TAG, "onOptionsItemSelected: clear filter");
//                break;
//            case R.id.showHiddenSessions:
//                Toast.makeText(this, "show hidden sessions", Toast.LENGTH_SHORT).show();
//                Log.d(TAG, "onOptionsItemSelected: show hidden sessions");
//                break;
//            default:
//                Toast.makeText(this, "something went wrong", Toast.LENGTH_SHORT).show();
//                Log.d(TAG, "onOptionsItemSelected: shouldn't reach this point");
//                //TODO delete
//                break;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    //Activity finish animation



}