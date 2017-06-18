/**   
 * 文件名： FileLoadController.java
 * 包名： me.xm2017.restful.controller 
 * 描述： []
 * 创建时间： 2017年4月25日 下午9:15:14 
 * @author FC
 */ 

package me.predictor.restful.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/** 
 * 类名： FileLoadController 
 * 公司名称： []
 * 描述：[]
 * 创建时间： 2017年4月25日 下午9:15:14 
 * @author FC
 */
@Controller
@RequestMapping("/file")
public class FileLoadController{
	
	@RequestMapping("/upload.do")
	public String fileupload(HttpServletRequest request){
		
		request.setAttribute("sss","sssasese");
		
		return "index";
	}

}
