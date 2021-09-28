package com.example.opengltitik1;

//import javax.microedition.khronos.egl.EGLConfig;
//import javax.microedition.khronos.opengles.GL10;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.app.Activity;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {
    /** The OpenGL View */
    private GLSurfaceView glSurfaceView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // requesting to turn the title OFF
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // making it full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        glSurfaceView = new GLSurfaceView(this);
        glSurfaceView.setRenderer(new ESRender());
        setContentView(glSurfaceView);
        //setContentView(R.layout.activity_main);
    }


    /**
     * Remember to resume the glSurface
     */
    @Override
    protected void onResume() {
        super.onResume();
        glSurfaceView.onResume();
    }
    /**
     * Also pause the glSurface
     */
    @Override
    protected void onPause() {
        super.onPause();
        glSurfaceView.onPause();
    }
}
