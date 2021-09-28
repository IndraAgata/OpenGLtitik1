package com.example.opengltitik1;

import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
public class ESRender implements Renderer {
    private Create_Points points_object; // the object to be drawn
    private float coord_x,coord_y,coord_z;
    float x,y;
    float step_loop=0.2f;
    float x1,y1;
    float x2,y2;
    float m;
    //private int loop_line,loop_line_color;
    /** Constructor to set the handed over context */
    public ESRender() {
        this.points_object = new Create_Points();
        // ============ set parameter to generate vertices to create dynamic point ==========================
        x1 = -1.0f; y1 = -1.0f;
        x2= 1.0f; y2 = 1.0f;
        m = (y2-y1)/(x2-x1); // count gradient
    }
    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f); // set background with white color
        //gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f); // set background with black color
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT); // clear Screen and Depth Buffer
        // display drawing points
        gl.glPushMatrix(); // start freeze state/event to each object
        gl.glTranslatef(0.0f, 0.0f, -5.0f);
        gl.glPointSize(3);
        gl.glEnable(GL10.GL_POINT_SMOOTH);
        points_object.draw_points(gl);
        gl.glPopMatrix(); // end freeze state/event to each object
        // display drawing points with dynamic size
        float size_point=1;
        for(x=x1;x<=x2;x+=step_loop) {
            coord_x=(float) (x);
            coord_y=(float) (m * (x - x1) + y1);
            coord_z=0.0f;
            gl.glPushMatrix(); // start freeze state/event to each object
            gl.glTranslatef(0.0f, 0.0f, -5.0f);
            gl.glEnable(GL10.GL_POINT_SMOOTH);
            gl.glPointSize(size_point);
            size_point+=1;

            points_object.draw_points2(gl,coord_x,coord_y,coord_z);
            gl.glPopMatrix(); // end freeze state/event to each object
        }
    }
    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        if (height == 0)
            height = 1; // To prevent divide by zero
        float aspect = (float) width / height;
        // Set the viewport (display area) to cover the entire window
        gl.glViewport(0, 0, width, height);
        // Setup perspective projection, with aspect ratio matches viewport
        gl.glMatrixMode(GL10.GL_PROJECTION); // Select projection matrix
        gl.glLoadIdentity(); // Reset projection matrix
        // Use perspective projection
        GLU.gluPerspective(gl, 45, aspect, 0.1f, 100.f);
        gl.glMatrixMode(GL10.GL_MODELVIEW); // Select model-view matrix
        gl.glLoadIdentity(); // Reset
    }
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        points_object = new Create_Points();
    }
}
