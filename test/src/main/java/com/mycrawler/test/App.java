package com.mycrawler.test;

import net.vidageek.crawler.Page;
import net.vidageek.crawler.PageCrawler;
import net.vidageek.crawler.PageVisitor;
import net.vidageek.crawler.Status;
import net.vidageek.crawler.Url;
import net.vidageek.crawler.config.CrawlerConfiguration;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        CrawlerConfiguration cfg = new CrawlerConfiguration("http://pan.baidu.com/disk/home");
        PageCrawler crawler = new PageCrawler(cfg);
        crawler.crawl(new PageVisitor()
        {
            
            public void visit(Page arg0)
            {
                // TODO Auto-generated method stub
                Document doc = Jsoup.parse(arg0.getContent());
                Elements magnetLinks = doc.select("a[href^=magnet]");
                for (Element link : magnetLinks)
                {
                 System.out.println(link.parentNode().childNode(0).attr("title"));
                 System.out.println(link.attr("href"));   
                }
                
                System.out.println("------------------end visit");
            }
            
            public void onError(Url arg0, Status arg1)
            {
                // TODO Auto-generated method stub
                System.out.println("onError");
            }
            
            public boolean followUrl(Url arg0)
            {
                // TODO Auto-generated method stub
//                System.out.println("followUrl");
                return false;
            }
        });
        
    }
}
