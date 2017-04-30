package com.tguillaume.findme.modules.home.fragment;
/**
 * @Project : AND_FindMe
 *
 * FDMHomeFragment.java
 *
 * Created by TARTARA Guillaume on 04/04/2017.
 * Copyright © 2017 tguillaume. All rights reserved.
 */

import android.Manifest;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.MultiProcessor;
import com.google.android.gms.vision.Tracker;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.FaceDetector;
import com.tguillaume.findme.FDMMainActivity;
import com.tguillaume.findme.common.entity.FDMUser;
import com.tguillaume.findme.common.manager.key.FDMSharedPrefKey;
import com.tguillaume.findme.views.FDMSearchingInfos;
import com.tguillaume.findme.views.FaceGraphic;
import com.tguillaume.findme.R;
import com.tguillaume.findme.common.mainClass.FDMMainFragment;
import com.tguillaume.findme.common.ui.camera.CameraSourcePreview;
import com.tguillaume.findme.common.ui.camera.GraphicOverlay;

import java.io.IOException;

import static com.tguillaume.findme.common.manager.key.FDMValues.RC_HANDLE_CAMERA_PERM;
import static com.tguillaume.findme.common.manager.key.FDMValues.RC_HANDLE_GMS;


public class FDMHomeFragment extends FDMMainFragment  implements View.OnClickListener{

    private static final String TAG = FDMMainActivity.class.getSimpleName();

    private CameraSource mCameraSource = null;
    private FaceGraphic mFaceGraphic;
    private CameraSourcePreview mPreview;
    private ImageButton mGetClueBtn;
    private ImageButton mChatBtn;
    private Button mFindItBtn;
    private Button mActivateScanBtn;
    private Button mUnableScanBtn;
    private ImageView mScanActiveImageView;

    private GraphicOverlay mGraphicOverlay;
    private FDMSearchingInfos mSearchLayout;
    private FDMUser mCurrentUser;
    private FDMUser mUserToFind;

    private boolean mClueIsDispo = true;
    private final int TIME_TO_WAIT = 10;
    private int mTimer = 0;
    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {

        @Override
        public void run() {
            mTimer++;

            if(mTimer < TIME_TO_WAIT) {
                mClueIsDispo = false;
                timerHandler.postDelayed(this, 1000);
                mGetClueBtn.setColorFilter(mContext.getResources().getColor(R.color.fdm_white));
            }else {
                mClueIsDispo = true;
                timerHandler.removeCallbacks(timerRunnable);
                mGetClueBtn.setColorFilter(mContext.getResources().getColor(R.color.fdm_red));
            }
        }
    };



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        init();
        View rView = inflater.inflate(R.layout.fragment_play, container, false);

        mPreview = (CameraSourcePreview) rView.findViewById(R.id.preview);
        mGraphicOverlay = (GraphicOverlay) rView.findViewById(R.id.faceOverlay);
        mSearchLayout = (FDMSearchingInfos) rView.findViewById(R.id.fragment_play_searching_layout);
        mGetClueBtn = (ImageButton)rView.findViewById(R.id.fragment_play_get_clues_btn);
        mChatBtn = (ImageButton)rView.findViewById(R.id.fragment_play_messages_btn);
        mFindItBtn = (Button)rView.findViewById(R.id.fragment_play_find_btn);
        mActivateScanBtn = (Button)rView.findViewById(R.id.fragment_play_activate_scan_btn);
        mUnableScanBtn = (Button)rView.findViewById(R.id.fragment_play_unable_scan_btn);
        mScanActiveImageView = (ImageView)rView.findViewById(R.id.fragment_play_scan_active_imageview);

        mGetClueBtn.setOnClickListener(this);
        mChatBtn.setOnClickListener(this);
        mFindItBtn.setOnClickListener(this);
        mActivateScanBtn.setOnClickListener(this);
        mUnableScanBtn.setOnClickListener(this);

        // Check for the camera permission before accessing the camera.  If the
        // permission is not granted yet, request permission.
        int rc = ActivityCompat.checkSelfPermission(mContext, Manifest.permission.CAMERA);
        if (rc == PackageManager.PERMISSION_GRANTED) {
            createCameraSource();
        } else {
            mFragmentListener.onRequestPermission();
        }

        getNewUser();
        mSearchLayout.setName(mUserToFind.getName());
        mClueIsDispo = true;
        getNewClue();

        return rView;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fragment_play_get_clues_btn:
                getNewClue();
                break;
            case R.id.fragment_play_messages_btn:
                break;

            case R.id.fragment_play_find_btn:
                //verifier si le joueur est le bon
                break;
            case R.id.fragment_play_activate_scan_btn:
                mSharedPrefManager.putBoolean(FDMSharedPrefKey.SCAN_IS_ACTIVE,true);
                mScanActiveImageView.setVisibility(View.VISIBLE);
                break;
            case R.id.fragment_play_unable_scan_btn:
                mSharedPrefManager.putBoolean(FDMSharedPrefKey.SCAN_IS_ACTIVE,false);
                mScanActiveImageView.setVisibility(View.GONE);
                break;
        }
    }

    private void getNewUser(){
        mUserToFind = new FDMUser("Dorian");
        mUserToFind.addClue("Lunettes");
        mUserToFind.addClue("Barbe");
        mUserToFind.addClue("Yeux bleus");
        mUserToFind.addClue("Peau blanche");
        mUserToFind.addClue("Chatain");
    }
    private void getNewClue(){
        Log.i(TAG,"getNewClue");
        if(mClueIsDispo){
            timerHandler.postDelayed(timerRunnable, 0);
            mTimer = 0;
            String tClue = mUserToFind.getNextClue();
            if(!tClue.equals("")){
                mSearchLayout.addClues(tClue);
            }
        }else {
            Toast.makeText(mContext, "Nouvel indice dispo dans " + (TIME_TO_WAIT - mTimer) + "secondes", Toast.LENGTH_SHORT).show();
        }

    }



    /**
     * Creates and starts the camera.  Note that this uses a higher resolution in comparison
     * to other detection examples to enable the barcode detector to detect small barcodes
     * at long distances.
     */
    private void createCameraSource() {

        FaceDetector detector = new FaceDetector.Builder(mContext)
                .setClassificationType(FaceDetector.ALL_CLASSIFICATIONS)
                .build();

        detector.setProcessor(
                new MultiProcessor.Builder<>(new GraphicFaceTrackerFactory())
                        .build());

        if (!detector.isOperational()) {
            // Note: The first time that an app using face API is installed on a device, GMS will
            // download a native library to the device in order to do detection.  Usually this
            // completes before the app is run for the first time.  But if that download has not yet
            // completed, then the above call will not detect any faces.
            //
            // isOperational() can be used to check if the required native library is currently
            // available.  The detector will automatically become operational once the library
            // download completes on device.
            Log.w(TAG, "Face detector dependencies are not yet available.");
        }

        mCameraSource = new CameraSource.Builder(mContext, detector)
                .setRequestedPreviewSize(1600, 1024)
                .setFacing(CameraSource.CAMERA_FACING_BACK)
                .setRequestedFps(30.0f)
                .build();
    }

    /**
     * Restarts the camera.
     */
    @Override
    public void onResume() {
        super.onResume();

        startCameraSource();
    }

    /**
     * Stops the camera.
     */
    @Override
    public void onPause() {
        super.onPause();
        mPreview.stop();
    }

    /**
     * Releases the resources associated with the camera source, the associated detector, and the
     * rest of the processing pipeline.
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mCameraSource != null) {
            mCameraSource.release();
        }
    }

    /**
     * Callback for the result from requesting permissions. This method
     * is invoked for every call on {@link #requestPermissions(String[], int)}.
     * <p>
     * <strong>Note:</strong> It is possible that the permissions request interaction
     * with the user is interrupted. In this case you will receive empty permissions
     * and results arrays which should be treated as a cancellation.
     * </p>
     *
     * @param requestCode  The request code passed in {@link #requestPermissions(String[], int)}.
     * @param permissions  The requested permissions. Never null.
     * @param grantResults The grant results for the corresponding permissions
     *                     which is either {@link PackageManager#PERMISSION_GRANTED}
     *                     or {@link PackageManager#PERMISSION_DENIED}. Never null.
     * @see #requestPermissions(String[], int)
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode != RC_HANDLE_CAMERA_PERM) {
            Log.d(TAG, "Got unexpected permission result: " + requestCode);
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            return;
        }

        if (grantResults.length != 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "Camera permission granted - initialize the camera source");
            // we have permission, so create the camerasource
            createCameraSource();
            return;
        }

        Log.e(TAG, "Permission not granted: results len = " + grantResults.length +
                " Result code = " + (grantResults.length > 0 ? grantResults[0] : "(empty)"));

        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                AppCompatActivity tActivity = (AppCompatActivity)mContext;
                tActivity.finish();
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("Face Tracker sample")
                .setMessage("Vous devez autoriser la camera")
                .setPositiveButton("ok", listener)
                .show();
    }

    //==============================================================================================
    // Camera Source Preview
    //==============================================================================================

    /**
     * Starts or restarts the camera source, if it exists.  If the camera source doesn't exist yet
     * (e.g., because onResume was called before the camera source was created), this will be called
     * again when the camera source is created.
     */
    private void startCameraSource() {

        AppCompatActivity tActivity = (AppCompatActivity)mContext;
        // check that the device has play services available.
        int code = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(
                mContext);
        if (code != ConnectionResult.SUCCESS) {
            Dialog dlg =
                    GoogleApiAvailability.getInstance().getErrorDialog(tActivity, code, RC_HANDLE_GMS);
            dlg.show();
        }

        if (mCameraSource != null) {
            try {
                mPreview.start(mCameraSource, mGraphicOverlay);
            } catch (IOException e) {
                Log.e(TAG, "Unable to start camera source.", e);
                mCameraSource.release();
                mCameraSource = null;
            }
        }
    }

    //==============================================================================================
    // Graphic Face Tracker
    //==============================================================================================

    /**
     * Factory for creating a face tracker to be associated with a new face.  The multiprocessor
     * uses this factory to create face trackers as needed -- one for each individual.
     */
    private class GraphicFaceTrackerFactory implements MultiProcessor.Factory<Face> {
        @Override
        public Tracker<Face> create(Face face) {
            return new GraphicFaceTracker(mGraphicOverlay);
        }
    }

    /**
     * Face tracker for each detected individual. This maintains a face graphic within the app's
     * associated face overlay.
     */
    private class GraphicFaceTracker extends Tracker<Face> {
        private GraphicOverlay mOverlay;
        private FaceGraphic mFaceGraphic;

        GraphicFaceTracker(GraphicOverlay overlay) {
            mOverlay = overlay;
            mFaceGraphic = new FaceGraphic(mContext,overlay);
        }

        /**
         * Start tracking the detected face instance within the face overlay.
         */
        @Override
        public void onNewItem(int faceId, Face item) {
            mFaceGraphic.setId(faceId);
        }

        /**
         * Update the position/characteristics of the face within the overlay.
         */
        @Override
        public void onUpdate(FaceDetector.Detections<Face> detectionResults, Face face) {
            mOverlay.add(mFaceGraphic);
            mFaceGraphic.updateFace(face);
        }

        /**
         * Hide the graphic when the corresponding face was not detected.  This can happen for
         * intermediate frames temporarily (e.g., if the face was momentarily blocked from
         * view).
         */
        @Override
        public void onMissing(FaceDetector.Detections<Face> detectionResults) {
            mOverlay.remove(mFaceGraphic);
        }

        /**
         * Called when the face is assumed to be gone for good. Remove the graphic annotation from
         * the overlay.
         */
        @Override
        public void onDone() {
            mOverlay.remove(mFaceGraphic);
        }
    }



}
