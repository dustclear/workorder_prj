/**
 * 
 */

var casper = require("casper").create({
	viewportSize : {
		width : 1920,
		height : 1080
	},
	verbose : true,
	logLevel : 'debug'
});

phantom.outputEncoding="gb2312";
//phantom.scriptEncoding="GBK";

var fs = require('fs');
/*var data = fs.read("cookies/cookie_115_success");
phantom.cookies = JSON.parse(data);*/

casper
		.userAgent('Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36 115Browser/5.1.6');

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
	this.waitForSelector('a[class="cnv-file focus"]', function() {
		this.click('a[class="cnv-file focus"]');

		

	}, function() {
        this.die('Timeout reached. Fail whale?111').exit();             //失败时调用的函数,输出一个消息,并退出
    }, 20000);    

});

/*casper.then(function(){
	this.waitForSelector('//*[@id="js_panel_nav_box"]/a[4]', function() {
		this.captureSelector("capture/downloadoffline.png", "html");
		this.echo("save screenshot of " + this.getCurrentUrl());
		//save cookies
		var cookies = JSON.stringify(phantom.cookies);
		fs.write("cookies/cookie_115", cookies, 'w');
		
		this.click(__utils__.getElementByXPath('//*[@id="js_panel_nav_box"]/a[4]'));

	}, function() {
		var cookies = JSON.stringify(phantom.cookies);
		fs.write("cookies/cookie_115", cookies, 'w');
		this.debugHTML();
		this.captureSelector(
				"capture/error1.png",
				"html");
		this.die('Timeout reached. can not download offline').exit();
	}, 20000);
});
*/
////点击离线下载
//casper.waitFor(function check() {
//	  return this.evaluate(function() {
//	    return __utils__.getElementByXPath('//*[@id="js_panel_nav_box"]/a[4]').length > 0;
//	  });
//	}, function then() {
//		this.debugHTML();
//	  this.captureSelector('yoursitelist.png', 'html');
//	}, function timeout() { // step to execute if check has failed
//		this.debugHTML();
//		this.captureSelector('capture/yoursitelist.png', 'html');
//		  this.echo("I can't haz my screenshot.").exit();
//	});

casper.withFrame(3, function() {
	//点击离线下载
	this.debugHTML();
		    this.click('a[href="/?ac=offline_tpl&is_wl_tpl=1"]');
		    
		    this
			.waitForSelector(
					'a[id="js_link_pro_btn"]',
					function() {
						this.captureSelector(
								"capture/finnnal.png",
								"html");
						this.click('a[id="js_link_pro_btn"]');

					},
					function() {
						this.captureSelector(
								"capture/error4.png",
								"html");
						this
								.die(
										'Timeout reached. can not download offlinenext')
								.exit();
					}, 20000);
		    
		    
	

  });


casper.then(function() {
	this
	.waitForSelector('a[data-btn="start"]',
			function() {
		this.captureSelector(
				"capture/downloadofflineNext.png",
				"html");
		this.echo("save screenshot of "
				+ this.getCurrentUrl());

		this
				.fillSelectors(
						'textarea[id="js_offline_new_add"]',
						{
							'textarea[id="js_offline_new_add"]' : 'magnet:?xt=urn:btih:038BB0584EA2F95747E23297FDBEAC3A8BCF255E&dn=%E9%92%A2%E9%93%81%E4%BE%A03.MP4.720x404.%E5%9B%BD%E8%AF%AD%E4%B8%AD%E5%AD%97'
						}, false);
		
		/*this.evaluate(function() {
			document.querySelector('textarea[id="js_offline_new_add"]').innerText='magnet:?xt=urn:btih:038BB0584EA2F95747E23297FDBEAC3A8BCF255E&dn=%E9%92%A2%E9%93%81%E4%BE%A03.MP4.720x404.%E5%9B%BD%E8%AF%AD%E4%B8%AD%E5%AD%97';
		  });*/
		  
		this
				.click('a[data-btn="start"]');
				
			},
			function() {
				this.captureSelector(
						"capture/error5.png",
						"html");
				this.debugHTML();
				this
						.die(
								'Timeout reached. can not download final')
						.exit();
			}, 20000);
    
    this.wait(5000, function() {
//		this.debugHTML();
//		this.click('a[class="sbtn okay"]');
		this
		.captureSelector(
				"capture/final.png",
				"html");
	});
})


casper.run();