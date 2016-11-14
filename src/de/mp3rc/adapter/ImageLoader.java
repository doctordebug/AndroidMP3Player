package de.mp3rc.adapter;

import java.lang.ref.WeakReference;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.widget.ImageView;

public class ImageLoader{
	
	
	private int size;

	public ImageLoader(int size){
		this.size = size;
	}
	 private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
		    // Raw height and width of image
		    final int height = options.outHeight;
		    final int width = options.outWidth;
		    int inSampleSize = 1;

		    if (height > reqHeight || width > reqWidth) {

		        // Calculate ratios of height and width to requested height and width
		        final int heightRatio = Math.round((float) height / (float) reqHeight);
		        final int widthRatio = Math.round((float) width / (float) reqWidth);
		        // Choose the smallest ratio as inSampleSize value, this will guarantee
		        // a final image with both dimensions larger than or equal to the
		        // requested height and width.
		        inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
		    }

		    return inSampleSize;
		}
		    
	 private static Bitmap extractThumbnail(String pathName, int newHeight, int newWidth) {
		    	 // First decode with inJustDecodeBounds=true to check dimensions
		        final BitmapFactory.Options options = new BitmapFactory.Options();
		        options.inJustDecodeBounds = true;
		        BitmapFactory.decodeFile(pathName, options);

		        // Calculate inSampleSize
		        options.inSampleSize = calculateInSampleSize(options, newWidth, newHeight);

		        // Decode bitmap with inSampleSize set
		        options.inJustDecodeBounds = false;
		        return  BitmapFactory.decodeFile(pathName, options);
		    }
	
	 static class LoadedDrawable extends ColorDrawable {
	    private final WeakReference<ImageLoaderTask> bitmapDownloaderTaskReference;

	    public LoadedDrawable(ImageLoaderTask imageLoaderTask) {
	        super(Color.WHITE);
	        bitmapDownloaderTaskReference = new WeakReference<ImageLoaderTask>(imageLoaderTask);
	    }

	    public ImageLoaderTask getLoaderTask() {
	        return bitmapDownloaderTaskReference.get();
	    }
	}
	
	 public void load(String path, ImageView imageView) {
	     if (cancelPotentialDownload(path, imageView)) {
	         ImageLoaderTask task = new ImageLoaderTask(size, imageView);
	         LoadedDrawable downloadedDrawable = new LoadedDrawable(task);
	         imageView.setImageDrawable(downloadedDrawable);
	         task.execute(path);
	     }
	}
	 
	 private static boolean cancelPotentialDownload(String path, ImageView imageView) {
		    ImageLoaderTask bitmapDownloaderTask = getBitmapDownloaderTask(imageView);

		    if (bitmapDownloaderTask != null) {
		        String bitmapUrl = bitmapDownloaderTask.path;
		        if ((bitmapUrl == null) || (!bitmapUrl.equals(path))) {
		            bitmapDownloaderTask.cancel(true);
		        } else {
		            // The same URL is already being downloaded.
		            return false;
		        }
		    }
		    return true;
		}
	 
	 private static ImageLoaderTask getBitmapDownloaderTask(ImageView imageView) {
		    if (imageView != null) {
		        Drawable drawable = imageView.getDrawable();
		        if (drawable instanceof LoadedDrawable) {
		            LoadedDrawable downloadedDrawable = (LoadedDrawable)drawable;
		            return downloadedDrawable.getLoaderTask();
		        }
		    }
		    return null;
		}
	 
	public class ImageLoaderTask extends AsyncTask<String, Void, Bitmap> {
	
	public String path;
	private final WeakReference<ImageView> imageViewReference;
	private int size;
	
	
	
    public ImageLoaderTask(int size, ImageView img){
        imageViewReference = new WeakReference<ImageView>(img);
        this.size = size;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        return extractThumbnail(params[0], size,size);
    }

    @Override
    protected void onPostExecute(Bitmap result) {
    	if (imageViewReference != null) {
    	    ImageView imageView = imageViewReference.get();
    	    ImageLoaderTask bitmapDownloaderTask = getBitmapDownloaderTask(imageView);
    	    // Change bitmap only if this process is still associated with it
    	    if (this == bitmapDownloaderTask) {
    	        imageView.setImageBitmap(result);
    	    }
    
	}
    }
	}
	}

