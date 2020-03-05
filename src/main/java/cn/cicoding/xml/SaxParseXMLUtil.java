package cn.cicoding.xml;

import cn.cicoding.other.PropertyUtil;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangpan on 2016/9/23.
 */
public class SaxParseXMLUtil extends DefaultHandler{


    private static final String XML_URL = "user.xml";
    //存储正在解析的元素的数据
    private Map<String,String> map=null;
    //存储所有解析的元素的数据
    private List<Map<String,String>> list=null;
    //正在解析的元素的名字
    String currentTag=null;
    //正在解析的元素的元素值
    String currentValue=null;
    //开始解析的元素
    String nodeName=null;


    public SaxParseXMLUtil(String nodeName) {
        // TODO Auto-generated constructor stub
        this.nodeName=nodeName;
    }

    public SaxParseXMLUtil() {

    }

    public List<Map<String, String>> getList() {
        return list;
    }

    //开始解析文档，即开始解析XML根元素时调用该方法
    @Override
    public void startDocument() throws SAXException {
        // TODO Auto-generated method stub
        //初始化Map
        list=new ArrayList<Map<String,String>>();
    }

    //开始解析每个元素时都会调用该方法
    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
        // TODO Auto-generated method stub
        //判断正在解析的元素是不是开始解析的元素

        if(qName.equals(nodeName)){
            map=new HashMap<String, String>();
        }

        //判断正在解析的元素是否有属性值,如果有则将其全部取出并保存到map对象中，如:<person id="00001"></person>
        if(attributes!=null&&map!=null){
            for(int i=0;i<attributes.getLength();i++){
                map.put(attributes.getQName(i), attributes.getValue(i));
            }
        }
        currentTag=qName;  //正在解析的元素
    }

    //解析到每个元素的内容时会调用此方法
    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        // TODO Auto-generated method stub
        if(currentTag!=null&&map!=null){
            currentValue=new String(ch,start,length);
            //如果内容不为空和空格，也不是换行符则将该元素名和值和存入map中
            if(currentValue!=null&&!currentValue.trim().equals("")&&!currentValue.trim().equals("\n")){
                map.put(currentTag, currentValue);
            }
            //当前的元素已解析过，将其置空用于下一个元素的解析
            currentTag=null;
            currentValue=null;
        }
    }

    //每个元素结束的时候都会调用该方法
    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        // TODO Auto-generated method stub
        //判断是否为一个节点结束的元素标签
        if(qName.equals(nodeName)){
            list.add(map);
            map=null;
        }
    }

    //结束解析文档，即解析根元素结束标签时调用该方法
    @Override
    public void endDocument() throws SAXException {
        // TODO Auto-generated method stub
        super.endDocument();
    }


    public static List<Map<String,String>> ReadXML(String uri,String NodeName){
        try {
            //创建一个解析XML的工厂对象
            SAXParserFactory parserFactory=SAXParserFactory.newInstance();
            //创建一个解析XML的对象
            SAXParser parser=parserFactory.newSAXParser();
            //创建一个解析助手类
            SaxParseXMLUtil myhandler=new SaxParseXMLUtil("user");
            parser.parse(uri, myhandler);
            return myhandler.getList();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{

        }
        return null;

    }

    /*public static void main(String[] args) {
        // TODO Auto-generated method stub
        String profilepath = PropertyUtil.class.getClassLoader().getResource("").getPath()+XML_URL;
        ArrayList<Map<String, String>> list=(ArrayList<Map<String, String>>) SaxParseXMLUtil.ReadXML(profilepath,"class");
        for(int i=0;i<list.size();i++){
            HashMap<String, String> temp=(HashMap<String, String>) list.get(i);
                Iterator<String> iterator=temp.keySet().iterator();
                while(iterator.hasNext()){
                    String key=iterator.next().toString();
                    String value=temp.get(key);
                    System.out.print(key+" "+value+"--");
                }
        }
        System.out.println(list.toString());
    }*/
    //java -jar C:\Users\pan\Desktop\java\config\work.jar
    public List<Map<String,String>> parseXml(){
        String profilepath = PropertyUtil.class.getClassLoader().getResource("user.xml").getPath();
        ArrayList<Map<String, String>> list=(ArrayList<Map<String, String>>) SaxParseXMLUtil.ReadXML(profilepath,"class");
        return list;
    }
    
    public static void main(String[] args) {
    	String a = System.getProperty("user.dir");
    	System.out.println(a);
	}
}
