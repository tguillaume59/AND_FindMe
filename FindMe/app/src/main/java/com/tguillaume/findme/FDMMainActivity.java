package com.tguillaume.findme;

import android.Manifest;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.tguillaume.findme.common.mainClass.FDMMainFragment;
import com.tguillaume.findme.common.manager.implementation.FDMFragmentCreatorImpl;
import com.tguillaume.findme.common.manager.interfaces.FDMFragmentListener;
import com.tguillaume.findme.common.manager.key.FDMEnum;

import static com.tguillaume.findme.common.manager.key.FDMValues.RC_HANDLE_CAMERA_PERM;

public class FDMMainActivity extends AppCompatActivity implements FDMFragmentListener {

    private static final String TAG = FDMMainActivity.class.getSimpleName();
    private FDMMainFragment mCurrentFragment;


    //==============================================================================================
    // Activity Methods
    //==============================================================================================

    /**
     * Initializes the UI and initiates the creation of a face detector.
     */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_main);

        showFragment(FDMEnum.EnumNameFragment.PLAY_VIEW);
    }

    /**
     * Handles the requesting of the camera permission.  This includes
     * showing a "Snackbar" message of why the permission is needed then
     * sending the request.
     */
    private void requestCameraPermission() {
        Log.w(TAG, "Camera permission is not granted. Requesting permission");

        final String[] permissions = new String[]{Manifest.permission.CAMERA};

        if (!ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.CAMERA)) {
            ActivityCompat.requestPermissions(this, permissions, RC_HANDLE_CAMERA_PERM);
            return;
        }

        final AppCompatActivity thisActivity = (AppCompatActivity) this;

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(thisActivity, permissions,
                        RC_HANDLE_CAMERA_PERM);
            }
        };
    }


    @Override
    public void showFragment(FDMEnum.EnumNameFragment sNameFragment) {
        showFragment(sNameFragment,null);
    }

    @Override
    public void showFragment(final FDMEnum.EnumNameFragment sNameFragment, Bundle sBundle) {
        final android.support.v4.app.FragmentManager sFragmentManager = getSupportFragmentManager();
        FDMMainFragment tFragment = FDMFragmentCreatorImpl.getInstance().createFragmentWithEnum(sNameFragment);
        Log.d(TAG, "showFragment(Fragment bundle) fragment :" + tFragment.getClass().getName());

        if (tFragment != null) {
            mCurrentFragment = tFragment;

            if (sBundle != null) {
                mCurrentFragment.setArguments(sBundle);
            }
            FDMMainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    FragmentTransaction tFragmentTransaction;
                    tFragmentTransaction = sFragmentManager.beginTransaction();
                    tFragmentTransaction.addToBackStack(mCurrentFragment.getClass().getName());
                    tFragmentTransaction.replace(R.id.activity_main_container, mCurrentFragment, sNameFragment.toString());
                    tFragmentTransaction.commit();
                }
            });
        }
    }

    @Override
    public void onRequestPermission() {
        requestCameraPermission();
    }
}
