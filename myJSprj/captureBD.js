/**
 * 
 */

var casper = require("casper").create({
	viewportSize: {
		width: 1440,
		height: 900
	}
});

casper.userAgent('Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0)');

casper.start("http://pan.baidu.com", function() {
	this.captureSelector("capture/capture.png", "html");
	this.echo("save screenshot of "+this.getCurrentUrl());
	
	 this.waitForSelector('form[id="TANGRAM__PSP_4__form"]', function() {                  //等到'.tweet-row'选择器匹配的元素出现时再执行回调函数
		 this.fill('form[id="TANGRAM__PSP_4__form"]', {
				"userName" : "",
				"password" : ""
			}, false);                 //成功时调用的函数,给整个页面截图
		 	this.click('input[id="TANGRAM__PSP_4__password"]');
		 	this.click('input[id="TANGRAM__PSP_4__submit"]');  
	        this.echo('clicked...');  
	    }, function() {
	        this.die('Timeout reached. Fail whale?').exit();             //失败时调用的函数,输出一个消息,并退出
	    }, 20000);                               
	
	
	
});

casper.then(function() {
	this.wait(5000, function() {
		this.captureSelector("capture/capture1.png", "html");
		this.echo("save screenshot of "+this.getCurrentUrl());
	})
	
});

casper.run();