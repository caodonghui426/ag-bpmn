package com.processes.ag_bpmn_v1.util;

import org.dom4j.*;
import org.dom4j.io.*;
import java.io.*;

public class XmlUtil {
    /**
     * 获取xml文档
     */
    public static Document getDocument(String filename) {
        File xmlFile = new File(filename);
        Document document = null;
        if (xmlFile.exists()) {
            try {
                SAXReader saxReader = new SAXReader();
                document = saxReader.read(xmlFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return document;
    }

    /**
     * 写入xml节点，没有就新建，存在就覆盖
     */
    public static void coverElement(String filePath, Document document) throws Exception {
        if (document != null) {
            OutputFormat format = OutputFormat.createPrettyPrint();
            File xmlFile = new File(filePath);
            format.setEncoding("UTF-8");
            XMLWriter writer = new XMLWriter(new FileOutputStream(xmlFile), format);
            writer.write(document);
            writer.close();
        }
    }
}