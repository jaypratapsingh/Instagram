var instagram = {
    share: function(successCallback, errorCallback, caption, mediaPath) {
		cordova.exec( successCallback,
		            errorCallback,
					"Instagram",
					"instagram",
        			[{"caption":caption,"mediaPath":mediaPath}]
        		);
    }
}

module.exports = instagram;

