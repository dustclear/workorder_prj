/**
 * 
 */

var casper = require("casper").create({
	viewportSize: {
		width: 1600,
		height: 900
	}
});

var fs = require('fs');
//var data = fs.read("cookies/cookie_115");
//phantom.cookies = JSON.parse(data);

casper.userAgent('Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36');

casper.start("http://www.115.com", function() {
	this.captureSelector("capture/capture.png", "html");
	this.echo("save screenshot of "+this.getCurrentUrl());
	
	 this.waitForSelector('form[id="js-login_form"]', function() {                  //等到'.tweet-row'选择器匹配的元素出现时再执行回调函数
		 this.fill('form[id="js-login_form"]', {
				"account" : "",
				"passwd" : ""
			}, false);                 //成功时调用的函数,给整个页面截图
		 	this.click('a[id="js-submit"]');
	        this.echo('clicked...'+new Date());  
	    }, function() {
	        this.die('Timeout reached. Fail whale?').exit();             //失败时调用的函数,输出一个消息,并退出
	    }, 20000);                               
});

casper.then(function() {
	this.wait(5000, function() {
		this.captureSelector("capture/capture1.png", "html");
		this.echo("save screenshot of "+this.getCurrentUrl());
		
		//save cookies
		var cookies = JSON.stringify(phantom.cookies);
		fs.write("cookies/cookie_115", cookies, 'w');
	})
	
});

casper.run();