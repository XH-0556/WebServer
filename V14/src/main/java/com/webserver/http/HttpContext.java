package com.webserver.http;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 该类维护所有Http协议中固定不变的内容
 */
public class HttpContext {
    //保存所有Content-Type头的值与资源后缀的对应关系
    private static Map<String, String> mimeMapping = new HashMap<>();
    static {
        //初始化所有静态资源
        initMimeMapping();
    }

    private static void initMimeMapping(){
        try {
//            SAXReader reader = new SAXReader();
//            Document doc = reader.read("./config/web.xml");
//            Element root = doc.getRootElement();
//            List<Element> list = root.elements("mime-mapping");
//            for(Element empEle: list){
//                String key = empEle.elementText("extension");
//                String value = empEle.elementText("mime-Type");
//                mimeMapping.put(key, value);
//            }
            List<Element> list = new SAXReader().read("./config/web.xml").getRootElement().elements("mime-mapping");
            for(Element empEle: list)mimeMapping.put(empEle.elementText("extension"),empEle.elementText("mime-Type"));
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("mimeMapping.size():"+mimeMapping.size());


    }



    /**
     * 根据资源后缀名获取对应的Content-Type的值
     * @param ext    资源的后缀名
     * @return       Content-Type头的值
     */
    public static String getMimeType(String ext){
        return mimeMapping.get(ext);
    }


}
