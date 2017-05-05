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
 * @category ��һ����������Ĳ���
 * 
 */
public class SpiderUtils {
	
	/**
	 * @author Fapha
	 * @throws Exception��ȡ������������Ͽ��׳��쳣
	 * @param url-��ַ
	 * @param encoding-��ַ���뼯
	 * @return string-������վhtml����
	 * @category ��������-��ȡhtml�ĵ�
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
	 * @param htmlDoc-����html��Դ����
	 * @return ������Ҫ��ȡ�����ݼ���
	 * @category ��ȡhtmlԪ�ؼ���
	 */
	
	public static List<String> getHtmlContent(String htmlDoc){
		//װ��html�ĵ�
		Document document = Jsoup.parse(htmlDoc);
		List<String> list = new ArrayList<String>();
		//�õ�classΪmenu��Ԫ�ؼ�
		Elements elements = document.getElementsByClass("menu");
		if(elements!=null)
		for(Element e:elements){
			//�õ�menu��a��ǩ�ļ���
			Elements aHref = e.getElementsByTag("a");
			if(aHref!=null&&!aHref.isEmpty())
				for(Element a:aHref){
					//�ѵ�ǰ��ǩ�ڵ�������ȡ��������List
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
		//������ȡCSDN�����ͷ��
		String html = SpiderUtils.getHtmlResource("http://www.csdn.net/","UTF-8");
		System.out.println(SpiderUtils.getHtmlContent(html));
	}
	
	
}
