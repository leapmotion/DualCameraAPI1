package samples.leapmotion.com.dualcamera;

import android.app.Activity;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.Window;
import android.widget.FrameLayout;

public class MainActivity extends Activity {

    private static final String TAG = "DualCamera";
    private CameraPreview mPreview0;
    private CameraPreview mPreview1;
    private Camera mCamera0;
    private Camera mCamera1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        int numCameras = Camera.getNumberOfCameras();
        Log.d(TAG, "number of cameras: " + numCameras);
        if (numCameras > 0)
            openPreview(0);
        if (numCameras > 1)
            openPreview(1);
    }

    void openPreview(int camid) {
        FrameLayout preview;
        if (camid != 0) {
            mCamera1 = getCameraInstance(camid);
            mPreview1 = new CameraPreview(this, mCamera1);
            preview = (FrameLayout) findViewById(R.id.camera_preview1);
            preview.addView(mPreview1);
        } else {
            mCamera0 = getCameraInstance(camid);
            mPreview0 = new CameraPreview(this, mCamera0);
            preview = (FrameLayout) findViewById(R.id.camera_preview0);
            preview.addView(mPreview0);
        }

    }
    public Camera getCameraInstance(int id){
        Camera c = null;
        try {
            c = Camera.open(id);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return c;
    }
}
