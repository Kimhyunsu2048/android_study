package org.koreait.day08_4;

import android.content.Context;
import android.hardware.Camera;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import org.koreait.day08_4.constants.ApiURL;
import org.koreait.day08_4.constants.Menus;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class PhotoFragment extends Fragment {

    private FrameLayout previewFrame;
    private Button takePhotoBtn;
    private Button photoListBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup viewGroup = (ViewGroup)inflater.inflate(R.layout.fragment_photo, container, false);

        previewFrame = viewGroup.findViewById(R.id.previewFrame);
        CameraSurfaceView surfaceView = new CameraSurfaceView(getContext());
        previewFrame.addView(surfaceView);

        MainActivity mainActivity = (MainActivity) getActivity();

        takePhotoBtn = viewGroup.findViewById(R.id.takePhotoBtn);

        takePhotoBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                surfaceView.capture(new Camera.PictureCallback() {

                    @Override
                    public void onPictureTaken(byte[] bytes, Camera camera) {
                         String imageData = Base64.encodeToString(bytes, Base64.NO_WRAP);
                        // Log.d("PHOTO_IMAGE_DATA", imageData);

                        Map<String, String> params = new HashMap<>();
                        params.put("imageData", imageData);

                        mainActivity.request(ApiURL.PHOTO_URL, "POST", params, new RequestCallback() {
                            @Override
                            public void process(String response) {
                                // 포토 목록 이동
                                mainActivity.changeMenu(Menus.PHOTO_LIST);
                            }
                        });

                    }
                });
            }
        });

        photoListBtn = viewGroup.findViewById(R.id.photoListBtn);
        photoListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.changeMenu(Menus.PHOTO_LIST);
            }
        });

        return viewGroup;
    }

    class CameraSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

        private SurfaceHolder mHolder;
        private Camera camera = null;

        public CameraSurfaceView(Context context) {
            super(context);

            mHolder = getHolder();
            mHolder.addCallback(this);
        }

        @Override
        public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
            camera = Camera.open();

            try {
                camera.setPreviewDisplay(mHolder);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {
            camera.startPreview();
        }

        @Override
        public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
            camera.stopPreview();
            camera.release();
            camera = null;
        }

        public boolean capture(Camera.PictureCallback handler) {
            if (camera == null) {
                return false;
            }

            camera.takePicture(null, null, handler);

            return true;
        }
    }
}