/**
 * 
 */

var casper = require("casper").create({
	viewportSize : {
		width : 1440,
		height : 900
	},
	verbose : true,
	logLevel : 'debug'
});

phantom.outputEncoding="GBK";
phantom.scriptEncoding="GBK";

var fs = require('fs');
var data = fs.read("cookies/cookie_bd_success");
phantom.cookies = JSON.parse(data);

casper
		.userAgent('Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36');

casper.start("http://pan.baidu.com", function() {
	this.captureSelector("capture/capture.png", "html");
	this.echo("save screenshot of " + this.getCurrentUrl());
});

casper.then(function() {
	this.waitForSelector('a[node-type="btn-offline-download"]', function() {
		this.click('a[node-type="btn-offline-download"]');

		

	})

});

casper.then(function(){
	this.waitForSelector('em[class="icon-offline-normal"]', function() {
		this.captureSelector("capture/downloadoffline.png", "html");
		this.echo("save screenshot of " + this.getCurrentUrl());

		this.click('em[class="icon-offline-normal"]');

	}, function() {
		this.captureSelector(
				"capture/error.png",
				"html");
		this.die('Timeout reached. can not download offline').exit();
	}, 20000);
});

casper
		.then(function() {
			this
					.waitForSelector(
							'input[id="share-offline-link"]',
							function() {
								this.captureSelector(
										"capture/downloadofflineNext.png",
										"html");
								this.echo("save screenshot of "
										+ this.getCurrentUrl());

								this
										.fillSelectors(
												'form[onsubmit="return false"]',
												{
													'input[id="share-offline-link"]' : 'magnet:?xt=urn:btih:038BB0584EA2F95747E23297FDBEAC3A8BCF255E&dn=%E9%92%A2%E9%93%81%E4%BE%A03.MP4.720x404.%E5%9B%BD%E8%AF%AD%E4%B8%AD%E5%AD%97'
												}, false);
								this
										.click('a[class="sbtn global-float-right"]');
								
								

							},
							function() {
								this.captureSelector(
										"capture/error.png",
										"html");
								this
										.die(
												'Timeout reached. can not download offlinenext')
										.exit();
							}, 20000);

		});

casper.then(function() {
	//The element A is different in different user-agent
	this
	.waitForSelector('a[id="_disk_id_24"]',
			function() {
			require('utils').dump(this.getElementInfo('a[id="_disk_id_24"]'));
			this.click('a[id="_disk_id_24"]');
				
			},
			function() {
				this.captureSelector(
						"capture/error.png",
						"html");
				this.debugHTML();
				this
						.die(
								'Timeout reached. can not download final')
						.exit();
			}, 20000);
});

casper.then(function() {
	this.wait(5000, function() {
		this.debugHTML();
//		this.click('a[class="sbtn okay"]');
		this
		.captureSelector(
				"capture/final.png",
				"html");
	});
});

casper.run();