package com.processes.ag_bpmn_v1.controller;

import com.processes.ag_bpmn_v1.util.Result;
import com.processes.ag_bpmn_v1.util.XmlDO;
import com.processes.ag_bpmn_v1.util.XmlUtil;
import org.dom4j.Document;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;


import java.io.*;

import static com.processes.ag_bpmn_v1.util.constant.*;
import static com.processes.ag_bpmn_v1.util.constant.SUFFIX;

@RestController
@RequestMapping(value = "xml")
public class XmlController {

    /**
     * @Description
     * @Author jiangyongtao
     * @Date
     */
    @GetMapping(value = "/getXmlFile")
    public Result getXml(String pName) throws IOException {
        String filePath = PROCESS_PATH;
        pName += SUFFIX;
        String readPath = filePath + pName;
        InputStream inputStream = null;
        try {
            // 获取文件流
            inputStream = getTemplateStream(readPath);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(inputStream != null) {
                try {
                    int len = -1;//初始值，起标志位作用
                    byte buf[] = new byte[128];//缓冲区
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();//捕获内存缓冲区的数据转换为字节数组
                    while ((len=inputStream.read(buf))!=-1){//循环读取内容,将输入流的内容放进缓冲区中
                        baos.write(buf,0,len);//将缓冲区内容写进输出流，0是从起始偏移量，len是指定的字符个数
                    }
                    String result = new String(baos.toByteArray());//最终结果，将字节数组转换成字符串
                    inputStream.close();
                    return Result.ok(SUCCESS_CODE, SUCCESS_MSG, result);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static InputStream getTemplateStream(String url) throws IOException{
        //返回读取指定资源的输入流
        ClassPathResource cpr = new ClassPathResource(url);
        return cpr.getInputStream();
    }


}
