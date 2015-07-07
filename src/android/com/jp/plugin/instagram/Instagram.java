package com.jp.plugin.instagram;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Intent;
import android.net.Uri;

import java.io.File;

public class Instagram extends CordovaPlugin implements ConnectionCallbacks, OnConnectionFailedListener {

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
						cordova.getActivity().startActivity(Intent.createChooser(share, "Share to"));
						callbackContext.error("success");
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
}

}
