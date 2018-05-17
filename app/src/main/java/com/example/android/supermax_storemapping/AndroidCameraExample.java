package com.example.android.supermax_storemapping;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.drawable.ColorDrawable;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.PictureCallback;
import android.os.Bundle;
import android.view.Surface;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class AndroidCameraExample extends Activity {
	private Camera mCamera;
	private CameraPreview mPreview;
	private PictureCallback mPicture;
	private Button capture, switchCamera;
	private Context myContext;
	private LinearLayout cameraPreview;
	private boolean cameraFront = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		myContext = this;
		initialize();
	}

	private int findFrontFacingCamera() {
		int cameraId = -1;
		// Search for the front facing camera
		int numberOfCameras = Camera.getNumberOfCameras();
		for (int i = 0; i < numberOfCameras; i++) {
			CameraInfo info = new CameraInfo();
			Camera.getCameraInfo(i, info);
			if (info.facing == CameraInfo.CAMERA_FACING_FRONT) {
				cameraId = i;
				cameraFront = true;
				break;
			}
		}
		return cameraId;
	}

	private int findBackFacingCamera() {
		int cameraId = -1;
		//Search for the back facing camera
		//get the number of cameras
		int numberOfCameras = Camera.getNumberOfCameras();
		//for every camera check
		for (int i = 0; i < numberOfCameras; i++) {
			CameraInfo info = new CameraInfo();
			Camera.getCameraInfo(i, info);
			if (info.facing == CameraInfo.CAMERA_FACING_BACK) {
				cameraId = i;
				cameraFront = false;
				break;
			}
		}
		return cameraId;
	}

	public void onResume() {
		super.onResume();
		if (!hasCamera(myContext)) {
			Toast toast = Toast.makeText(myContext, "Sorry, your phone does not have a camera!", Toast.LENGTH_LONG);
			toast.show();
			finish();
		}
		if (mCamera == null) {
			//if the front facing camera does not exist
			if (findFrontFacingCamera() < 0) {
				Toast.makeText(this, "No front facing camera found.", Toast.LENGTH_LONG).show();
				switchCamera.setVisibility(View.GONE);
			}			
			mCamera = Camera.open(findBackFacingCamera());
			Camera.Parameters params=	mCamera.getParameters();


			List<Camera.Size> sizes = params.getSupportedPictureSizes();
			Camera.Size size = sizes.get(0);
//Camera.Size size1 = sizes.get(0);
			for(int i=0;i<sizes.size();i++)
			{

				if(sizes.get(i).width > size.width)
					size = sizes.get(i);


			}

//System.out.println(size.width + "mm" + size.height);
			params.setPictureSize(size.width, size.height);
			params.setFlashMode(Camera.Parameters.FLASH_MODE_AUTO);
			params.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
			params.setSceneMode(Camera.Parameters.SCENE_MODE_AUTO);
			params.setWhiteBalance(Camera.Parameters.WHITE_BALANCE_AUTO);
			params.setExposureCompensation(0);
			params.setPictureFormat(ImageFormat.JPEG);
			params.setJpegQuality(70);
			params.setRotation(90);
			mCamera.setParameters(params);
			setCameraDisplayOrientation(AndroidCameraExample.this,CameraInfo.CAMERA_FACING_BACK,mCamera);
			mPicture = getPictureCallback();
			mPreview.refreshCamera(mCamera);
		}
	}

	public void initialize() {
		cameraPreview = (LinearLayout) findViewById(R.id.camera_preview);
		mPreview = new CameraPreview(myContext, mCamera);
		cameraPreview.addView(mPreview);

		capture = (Button) findViewById(R.id.button_capture);
		capture.setOnClickListener(captrureListener);

		switchCamera = (Button) findViewById(R.id.button_ChangeCamera);
		switchCamera.setOnClickListener(switchCameraListener);
	}

	OnClickListener switchCameraListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			//get the number of cameras
			int camerasNumber = Camera.getNumberOfCameras();
			if (camerasNumber > 1) {
				//release the old camera instance
				//switch camera, from the front and the back and vice versa
				
				releaseCamera();
				chooseCamera();
			} else {
				Toast toast = Toast.makeText(myContext, "Sorry, your phone has only one camera!", Toast.LENGTH_LONG);
				toast.show();
			}
		}
	};

	public void chooseCamera() {
		//if the camera preview is the front
		if (cameraFront) {
			int cameraId = findBackFacingCamera();
			if (cameraId >= 0) {
				//open the backFacingCamera
				//set a picture callback
				//refresh the preview
				
				mCamera = Camera.open(cameraId);
				//improve quality

				Camera.Parameters params=	mCamera.getParameters();


				List<Camera.Size> sizes = params.getSupportedPictureSizes();
				Camera.Size size = sizes.get(0);
//Camera.Size size1 = sizes.get(0);
				for(int i=0;i<sizes.size();i++)
				{

					if(sizes.get(i).width > size.width)
						size = sizes.get(i);


				}

//System.out.println(size.width + "mm" + size.height);
				params.setPictureSize(size.width, size.height);
				params.setFlashMode(Camera.Parameters.FLASH_MODE_AUTO);
				params.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
				params.setSceneMode(Camera.Parameters.SCENE_MODE_AUTO);
				params.setWhiteBalance(Camera.Parameters.WHITE_BALANCE_AUTO);
				params.setExposureCompensation(0);
				params.setPictureFormat(ImageFormat.JPEG);
				params.setJpegQuality(70);
				params.setRotation(90);
				mCamera.setParameters(params);
				setCameraDisplayOrientation(AndroidCameraExample.this,CameraInfo.CAMERA_FACING_FRONT,mCamera);
				//Iprove quality


				mPicture = getPictureCallback();			
				mPreview.refreshCamera(mCamera);
			}
		} else {
			int cameraId = findFrontFacingCamera();
			if (cameraId >= 0) {
				//open the backFacingCamera
				//set a picture callback
				//refresh the preview
				
				mCamera = Camera.open(cameraId);
				//improve quality

				Camera.Parameters params=	mCamera.getParameters();


				List<Camera.Size> sizes = params.getSupportedPictureSizes();
				Camera.Size size = sizes.get(0);
//Camera.Size size1 = sizes.get(0);
				for(int i=0;i<sizes.size();i++)
				{

					if(sizes.get(i).width > size.width)
						size = sizes.get(i);


				}

//System.out.println(size.width + "mm" + size.height);
				params.setPictureSize(size.width, size.height);
				params.setFlashMode(Camera.Parameters.FLASH_MODE_AUTO);
				params.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
				params.setSceneMode(Camera.Parameters.SCENE_MODE_AUTO);
				params.setWhiteBalance(Camera.Parameters.WHITE_BALANCE_AUTO);
				params.setExposureCompensation(0);
				params.setPictureFormat(ImageFormat.JPEG);
				params.setJpegQuality(70);
				params.setRotation(0);
				mCamera.setParameters(params);
				setCameraDisplayOrientation(AndroidCameraExample.this,CameraInfo.CAMERA_FACING_BACK,mCamera);
				//Iprove quality
				mPicture = getPictureCallback();
				mPreview.refreshCamera(mCamera);
			}
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		//when on Pause, release camera in order to be used from other applications
		releaseCamera();
	}

	private boolean hasCamera(Context context) {
		//check if the device has camera
		if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
			return true;
		} else {
			return false;
		}
	}

	private PictureCallback getPictureCallback() {
		PictureCallback picture = new PictureCallback() {

			@Override
			public void onPictureTaken(byte[] data, Camera camera) {
				//make a new picture file
				File pictureFile = getOutputMediaFile();
				
				if (pictureFile == null) {
					return;
				}
				try {
					//write the file
					FileOutputStream fos = new FileOutputStream(pictureFile);
					fos.write(data);
					fos.close();

					Toast toast = Toast.makeText(myContext, "Picture saved: " + pictureFile.getName(), Toast.LENGTH_LONG);
					toast.show();
					final Dialog dialog = new Dialog(AndroidCameraExample.this);
					dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
					dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.WHITE));

					//dialog.setTitle("Calculation");
					dialog.setCancelable(false);
					dialog.setContentView(R.layout.image_layout);

					ImageView textView1=(ImageView) dialog.findViewById(R.id.imageicon);

					Bitmap bmp = BitmapFactory.decodeFile(pictureFile.getAbsolutePath());
					textView1.setImageBitmap(bmp);
					Button btncncle=(Button) dialog.findViewById(R.id.btnclose);
					btncncle.setVisibility(View.GONE);
					textView1.setOnClickListener(new OnClickListener()
					{
						@Override
						public void onClick(View v) {
							dialog.dismiss();
							//setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
						}
					});
					//setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
					dialog.show();

//Show dialog here
//...
//Hide dialog here

				} catch (FileNotFoundException e) {
				} catch (IOException e) {
				}
				
				//refresh camera to continue preview
				mPreview.refreshCamera(mCamera);
			}
		};
		return picture;
	}

	OnClickListener captrureListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			mCamera.takePicture(null, null, mPicture);
		}
	};

	//make picture and save to a folder
	private static File getOutputMediaFile() {
		//make a new file directory inside the "sdcard" folder
		File mediaStorageDir = new File("/sdcard/", "JCG Camera");
		
		//if this "JCGCamera folder does not exist
		if (!mediaStorageDir.exists()) {
			//if you cannot make this folder return
			if (!mediaStorageDir.mkdirs()) {
				return null;
			}
		}
		
		//take the current timeStamp
		String timeStamp = new SimpleDateFormat("yyyyMMMdd_HHmmss").format(new Date());
		File mediaFile;
		//and make a media file:
		mediaFile = new File(mediaStorageDir.getPath() + File.separator + "IMG_" + timeStamp + ".jpg");
		
		return mediaFile;
	}

	private void releaseCamera() {
		// stop and release camera
		if (mCamera != null) {
			mCamera.release();
			mCamera = null;
		}
	}
	public static Bitmap decodeSampledBitmapFromFile(String path, int reqWidth, int reqHeight)
	{ // BEST QUALITY MATCH

		//First decode with inJustDecodeBounds=true to check dimensions
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(path, options);

		// Calculate inSampleSize, Raw height and width of image
		final int height = options.outHeight;
		final int width = options.outWidth;
		options.inPreferredConfig = Bitmap.Config.RGB_565;
		int inSampleSize = 1;

		if (height > reqHeight)
		{
			inSampleSize = Math.round((float)height / (float)reqHeight);
		}
		int expectedWidth = width / inSampleSize;

		if (expectedWidth > reqWidth)
		{
			//if(Math.round((float)width / (float)reqWidth) > inSampleSize) // If bigger SampSize..
			inSampleSize = Math.round((float)width / (float)reqWidth);
		}

		options.inSampleSize = inSampleSize;

		// Decode bitmap with inSampleSize set
		options.inJustDecodeBounds = false;

		return BitmapFactory.decodeFile(path, options);
	}
	private void setCameraDisplayOrientation(Activity activity,
											 int cameraId, Camera camera) {
		CameraInfo info =
				new CameraInfo();
		Camera.getCameraInfo(cameraId, info);
		int rotation = activity.getWindowManager().getDefaultDisplay()
				.getRotation();
		int degrees = 0;
		switch (rotation) {
			case Surface.ROTATION_0: degrees = 0; break;
			case Surface.ROTATION_90: degrees = 90; break;
			case Surface.ROTATION_180: degrees = 180; break;
			case Surface.ROTATION_270: degrees = 270; break;
		}

		int result;
		if (info.facing == CameraInfo.CAMERA_FACING_FRONT) {
			result = (info.orientation + degrees) % 360;
			result = (360 - result) % 360;  // compensate the mirror
		} else {  // back-facing
			result = (info.orientation - degrees + 360) % 360;
		}
		camera.setDisplayOrientation(result);
	}

/*
	private void initCamera() {
		if(mCamera == null) {
			mCamera = Camera.open();
			setCameraDisplayOrientation(activity, CameraInfo.CAMERA_FACING_BACK, mCamera);
			mCamera.unlock();
		}
	}
*/
}