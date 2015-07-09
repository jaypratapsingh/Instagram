package com.jp.plugin.instagram;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;

import java.io.File;

public class Instagram extends CordovaPlugin {

	String caption = "", mediaPath = "";

	public boolean execute(String arg, JSONArray jsonArray, CallbackContext callbackContext) {

	        try {
	            JSONObject jsonObject = jsonArray.getJSONObject(0);
	            caption = jsonObject.getString("caption");
	            mediaPath = jsonObject.getString("mediaPath");
	        }
	        catch(Exception e)
	        {
	            e.printStackTrace();
        	}

			if(arg.equalsIgnoreCase("instagram"))
			{
				try
				{
					Intent share = new Intent(Intent.ACTION_SEND);
					share.setType("image/* video/*");

					// Create the URI from the media
					mediaPath = mediaPath.replace("file:///","");

					File media = new File(mediaPath);

					Uri uri = Uri.fromFile(media);

					// Add the URI and the caption to the Intent.
					share.putExtra(Intent.EXTRA_STREAM, uri);
					share.putExtra(Intent.EXTRA_TEXT, caption);
					share.setPackage("com.instagram.android");

					try
					{
                        if(isPackageInstalled("com.instagram.android", cordova.getActivity()) == true)
                        {
                            cordova.getActivity().startActivity(Intent.createChooser(share, "Share to"));
                            callbackContext.error("success");
                        }
                        else
                        {
                            callbackContext.error("Instagram not installed");
                        }
					}
					catch(Exception e)
					{
						e.printStackTrace();
						callbackContext.error("Instagram not installed");
					}
					return true;
				}
				catch(Exception e)
				{
					e.printStackTrace();
					return false;
				}
			}
			else {
				callbackContext.error("Invalid Selection");
				return false;
			}
	}

    private boolean isPackageInstalled(String packagename, Context context) {
        PackageManager pm = context.getPackageManager();
        try {
            pm.getPackageInfo(packagename, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }
}
