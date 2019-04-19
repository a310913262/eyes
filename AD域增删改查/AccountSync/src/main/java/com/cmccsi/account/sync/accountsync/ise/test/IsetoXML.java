package com.cmccsi.account.sync.accountsync.ise.test;

import com.cmccsi.account.sync.accountsync.ise.pojo.*;
import com.cmccsi.account.sync.accountsync.ise.utils.JaxbXmlUtil;
import net.sf.json.JSONObject;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



public class IsetoXML {

	
	public static void main(String[] args) {
		
		String xmlData="";

		ReqHeader addHeader = new ReqHeader();
		ReqHeader addHeader1 = new ReqHeader();

		ReqEntity addReqEntity = new ReqEntity();
		addHeader.setKey("A");
		addHeader.setValue("1");
		addHeader1.setKey("B");
		addHeader1.setValue("2");
		addReqEntity.setEmail("lifupeng@163.com");
		addReqEntity.setEnabled("true");
		addReqEntity.setFirstName("li");
		addReqEntity.setLastName("fupeng");
		addReqEntity.setIdentityGroups("83fb7800-86e0-11e5-800e-005056941420");
		addReqEntity.setPassword("123456");
		
		addReqEntity.setId("11");
		addReqEntity.setName("lifupeng");
		addReqEntity.setDescription("描述");
		
		
		List<ReqHeader> arrayList = new ArrayList<>();
		arrayList.add(addHeader);
		arrayList.add(addHeader1);
		addReqEntity.setCustomAttributes(arrayList);
		//addReqEntity.setBody(addReqBody);
		JSONObject jsonobject = JSONObject.fromObject(addReqEntity);
		System.out.println(jsonobject);
		try {
		//	xmlData     = JaxbXmlUtil.convertToXml(addReqEntity);
			System.out.println(xmlData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
/*
public static void main(String[] args) {
	
	   String xmlData="";
	    ResBean resBean = new ResBean();
	    UpdatedField updatedFieldlast = new UpdatedField();
	    UpdatedField updatedFieldgroup = new UpdatedField();
	    List<UpdatedField> arrayList = new ArrayList<>();
	    
	    
	    updatedFieldlast.setNewValue("new");
	    updatedFieldlast.setOldValue("old");
	    updatedFieldlast.setName("lastname");
	    
	    updatedFieldgroup.setNewValue("identityGroups");
	    updatedFieldgroup.setOldValue("zzz");
	    updatedFieldgroup.setName("identityGroups");
	    
	    arrayList.add(updatedFieldlast);
	    arrayList.add(updatedFieldgroup);
	    resBean.setUpdatedField(arrayList);
		try {
			xmlData     = JaxbXmlUtil.convertToXml(resBean);
		//	System.out.println(xmlData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//测试解析xml 
		
		String xml= "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
			+ "+<ns2:updatedFields xmlns:ns2=\"ers.ise.cisco.com\">"
			+ "<UpdatedField field=\"lastname\">"
			+ "   <NEWVALUE>new</NEWVALUE>"
			+ "   <OLDVALUE>old</OLDVALUE>"
			+ " </UpdatedField>"
			+ " <UpdatedField field=\"identityGroups\">"
			+ "   <NEWVALUE>identityGroups</NEWVALUE>"
			+ "   <OLDVALUE>zzz</OLDVALUE>"
			+ " </UpdatedField>"
			+ "</ns2:updatedFields>";



		String xmls="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
				+ "<ns2:searchResult total=\"2\" xmlns:ns2=\"ers.ise.cisco.com\">"
				+ "<resources>"
				+ "<ns2:resource description=\"description1\" name=\"name1\" id=\"id1\"/>"
				+ "<ns2:resource description=\"description2\" name=\"name2\" id=\"id2\"/>"
				+ "</resources>"
				+ "</ns2:searchResult>";
		
		try {
			 */
/*   ResBean convertToJavaBean = JaxbXmlUtil.converyToJavaBean(xmlData, ResBean.class);
			    System.out.println("##################"+convertToJavaBean);*//*

          */
/*  //这是一个符合xml格式的字符串
            String   xmls = "<xmn> <people><name>zs</name><age>22</age></people> <people><name>ls</name><age>23</age></people> </xmn>";

            //将string 转化为 XmlDocument对象的xml格式
            XmlDocument xmlDoc = new XmlDocument();
            xmlDoc.LoadXml(xml);

            //查找单节点SelectSingleNode,如果存在多个相同节点，默认查找第一个
            XmlNode nameNode = xmlDoc.SelectSingleNode("//name");
            String name = nameNode.InnerText;   //zs

            //查找所有节点
            XmlNodeList nameNodeList = xmlDoc.SelectNodes("//people");

            //遍历取值
            foreach (XmlNode nameInfo in nameNodeList)
            {
                string nameValue = (nameInfo.SelectSingleNode("name")).InnerText;
            }*//*

//得到根节点的值
		*/
/*	System.out.println(xmls);

			SAXReader saxReader = new SAXReader();
			Document document = null;
			String rootName = null;

            document = DocumentHelper.parseText(xmls);
            //获取根节点元素对象  
            Element root = document.getRootElement();
           //获取子节点
            System.out.println("当前节点的名称：" + root.getName());
               //
            Element data = root.element("resources");//获取子节点

            System.out.println(data.attributeValue("description"));

            List nodes = data.elements("resource");

            //创建返回的集合
            ArrayList<UserBean> userBeans = new ArrayList<>();
            for (Iterator it = nodes.iterator(); it.hasNext();) {
                Element elm = (Element) it.next();
                System.out.println(elm.attributeValue("description")+"***"+elm.attributeValue("id")+"****"+elm.attributeValue("name"));
                UserBean userbean =   new UserBean();
                userbean.setDescription(elm.attributeValue("description"));
                userbean.setId(elm.attributeValue("id"));
                userbean.setName(elm.attributeValue("name"));
                userBeans.add(userbean);
            }
            System.out.println(userBeans.size());*//*


            //########################################################更新返回报文取值测试##########################################

            String xmlss="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                    "<ns2:updatedFields xmlns:ns2=\"ers.ise.cisco.com\">\n" +
                    "    <updatedField field=\"lastname\">\n" +
                    "        <newValue>Doe</newValue>\n" +
                    "        <oldValue>name</oldValue>\n" +
                    "    </updatedField>\n" +
                    "    <updatedField field=\"identityGroups\">\n" +
                    "        <newValue>identityGroups</newValue>\n" +
                    "        <oldValue>zzz</oldValue>\n" +
                    "    </updatedField>\n" +
                    "</ns2:updatedFields>";

            System.out.println(xmlss);

            SAXReader saxReader = new SAXReader();
            Document document = null;
            String rootName = null;

            document = DocumentHelper.parseText(xmlss);
            //获取根节点元素对象  
            Element root = document.getRootElement();
            //获取子节点
            System.out.println("当前节点的名称：" + root.getName());
            //
            Element data= root.element("updatedFields");//获取子节点

         // System.out.println(data.attributeValue("field"));

            Element el2  =  root.element("updatedField");
            System.out.println(el2);
          //  List nodes= data.elements("updatedField");

          */
/* for (Iterator it = nodes.iterator(); it.hasNext();) {
                Element elm = (Element) it.next();
                System.out.println(elm.attributeValue("field")+"***"+elm.elements("newValue")+"**********"+elm.elements("oldValue"));

            }*//*

          int a=0;
            for(Iterator it=root.elementIterator();it.hasNext();){
                Element elm = (Element) it.next();
                Element newValue = elm.element("newValue");//取标签
                Element oldValue = elm.element("oldValue");
                System.out.println(newValue.getText());//取标签内的节点
                System.out.println(oldValue.getText());
                System.out.println(elm.attributeValue("field"));//取节点内的属性
               // System.out.println(elm.attributeValue("field")+"***"+elm.element("newValue")+"**********"+elm.element("oldValue"));

              if(newValue.getText().equals(oldValue.getText())){
              	a++;
			  }
            }
            System.out.println(a);







		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
*/




}
