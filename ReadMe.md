# *************Cordova : Instagram***************** #

By using this plugin you can share your images and videos on instagram


# Install this plugin using:

cordova plugin add com.jp.plugin.instagram



# Remove Plugins :

cordova plugin remove com.jp.plugin.instagram



# Put the below code in your javascript code: 

instagram.share(
	function(success)
	{
            console.log(success);
        }, 
	function(error)
	{
            console.log(error);
        },
	caption, mediaPath
    );



* caption   :   String data format
* mediaPath :   Path of your file



*GitHub URL:   https://github.com/jaypratapsingh/Instagram

*npm url :     https://www.npmjs.com/package/com.jp.plugin.instagram
