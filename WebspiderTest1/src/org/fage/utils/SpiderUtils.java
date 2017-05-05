package org.fage.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 
 * @author Fapha
 * @version v1.0
 * @category 第一次网络爬虫的测试
 * 
 */
public class SpiderUtils {
	
	/**
	 * @author Fapha
	 * @throws Exception读取不到或者网络断开抛出异常
	 * @param url-网址
	 * @param encoding-网址编码集
	 * @return string-返回网站html代码
	 * @category 网络爬虫-获取html文档
	 */
	public static String getHtmlResource(String url,String encoding){
		InputStreamReader in=null;
		BufferedReader bf=null;
		StringBuffer sb = null; 
		try {
			URL htmlUrl = new URL(url);
			URLConnection urlConn = htmlUrl.openConnection();
			in = new InputStreamReader(urlConn.getInputStream(),encoding);
			bf = new BufferedReader(in);
			String data = "";
			sb = new StringBuffer();
			while((data=bf.readLine())!=null){
				sb.append(data+"\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			
				try {
					if(bf!=null)
						bf.close();
					if(null!=in)
						in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return sb.toString();
	}
	
	/**
	 * @author Fapha
	 * @param htmlDoc-给出html的源代码
	 * @return 返回需要爬取得数据集合
	 * @category 获取html元素集合
	 */
	
	public static List<String> getHtmlContent(String htmlDoc){
		//装载html文档
		Document document = Jsoup.parse(htmlDoc);
		List<String> list = new ArrayList<String>();
		//得到class为menu的元素集
		Elements elements = document.getElementsByClass("menu");
		if(elements!=null)
		for(Element e:elements){
			//得到menu下a标签的集合
			Elements aHref = e.getElementsByTag("a");
			if(aHref!=null&&!aHref.isEmpty())
				for(Element a:aHref){
					//把当前标签内的内容提取出来放入List
					list.add(a.text());
				}
			else{
				break;
			}
		}
		
		return list;
	}
	
	public static void main(String[] args){
//		System.out.println(SpiderUtils.getHtmlResource("http://www.csdn.net/", "UTF-8"));
		//爬虫爬取CSDN上面的头条
		String html = SpiderUtils.getHtmlResource("http://www.csdn.net/","UTF-8");
		System.out.println(SpiderUtils.getHtmlContent(html));
	}
	
	
}
