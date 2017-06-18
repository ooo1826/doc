package me.predictor.restful.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.utils.csv.CsvUtils;

import me.predictor.restful.model.Message;
import me.predictor.restful.pojo.StockData;
import me.predictor.restful.service.NumbersService;
import me.predictor.restful.utils.PropertiesUtil;
import me.predictor.restful.utils.liunx.RemoteExecuteCommand;

@RestController
public class HelloWorldRestController{
	
	private static final Logger	log=Logger.getLogger(HelloWorldRestController.class);
								   
	@Autowired
	@Qualifier("numbersServiceImpl")
	private NumbersService		numbersService;
								
	@RequestMapping(value="/")
	public String welcome(){// Welcome page, non-rest
		return "welcome!";
	}
	
	@RequestMapping("/more/{status}/{dir}")
	public Message more(@PathVariable String status,@PathVariable String dir) throws IOException{// REST Endpoint.
		Message msg=new Message();
		long count=0;
		List<String> list=null;
		long startTime=System.currentTimeMillis();
		if("2".equals(status)){
			list = new RemoteExecuteCommand().executeSuccess("ls /data/date");
		}else{
			list = new RemoteExecuteCommand().executeSuccess("ls /data/stockdata");
		}
		
		msg.setFileAmount(list.size());
		for(String string:list){
			try{
				Thread.sleep(Long.parseLong(PropertiesUtil.getValue("dormant")));
			}catch(InterruptedException e){
				e.printStackTrace();
			}
			int i=0;
			List<Object> stockDatas=new ArrayList<Object>();
			if("2".equals(status)){
				CsvUtils.readCsv(PropertiesUtil.getValue("filepath_date")+string,stockDatas,StockData.class);
			}else if("1".equals(status)){
				CsvUtils.readCsv(PropertiesUtil.getValue("filepath")+string,stockDatas,StockData.class,300L);
			}
			for(Object stockData:stockDatas){
				count++;
				i++;
				numbersService.addHis((StockData)stockData,null);
			}
			log.info(((StockData)stockDatas.get(0)).getCode()+"------i="+i);
			if("2".equals(status)){
				new RemoteExecuteCommand().executeSuccess("mv /data/date/"+string+" /data/back/");
			}
		}
		long endTime=System.currentTimeMillis();
		msg.setTime(endTime-startTime);
		msg.setMes("success");
		log.info("count="+count);
		log.info("success-"+list.size());
		return msg;
	}
	
}