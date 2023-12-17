package com.study.defense.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.study.defense.msg.ResInfo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

@ServerEndpoint("/websocket/defense")
@RestController
@CrossOrigin(origins = "*")
public class WebSocketController {

	private static final Logger log = LoggerFactory.getLogger(WebSocketSever.class);

	public static final Map mapData = new HashMap();
	public static final Map<String,Object> mapTopo = new HashMap();
	public static final Map<String,Object> mapGis = new HashMap();
	public static final Map<String,Object> mapStatistics = new HashMap();
	
	public static final List allList = new ArrayList();

	/**
	 * 所有在线的人数的
	 */
	private static final Map<String, Session> allSession = new HashMap<String, Session>();

	/**
	 * websocket被打开的方法
	 *
	 * @param session 会话
	 */
	@OnOpen
	public void open(Session session) {
		log.info("------open--------");
		// 获取sessionId，让后将session存入到Map中
		final String id = session.getId();
		allSession.put(id, session);
		this.sendMessage(session);
	}

	/**
	 * websocket被退出
	 *
	 * @param session 会话
	 */
	@OnClose
	public void close(Session session) {
		log.info("------close--------");
		// 将session从缓存当中移除
		final String id = session.getId();
		allSession.remove(id);
	}

	/**
	 * 当收到客户端发送过来的消息时触发
	 *
	 * @param message 客户端发送过来的消息
	 * @param session 客户端的session会话
	 */
	@OnMessage
	public void message(String message, Session session) throws IOException {
		// 将消息发送到所有的客户端
		log.info("------message--------");
//		this.sendMessage(1, message);
	}

	/**
	 * 发送消息给所有用户
	 *
	 * @param type    消息类型；0=更新在线人数,1=发送的消息
	 * @param message 消息实体
	 */
	private void sendMessage(Session session) {
			try {
				session.getBasicRemote().sendText(JSON.toJSONString(mapData));
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	private void sendAllMessage() {
		// 遍历并发给所有的客户端
		allSession.values().forEach(session -> {
			try {
				session.getBasicRemote().sendText(JSON.toJSONString(mapData));
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		mapData.clear();
	}
	@RequestMapping(value = "/pushMsg", method = RequestMethod.GET)
	public ResInfo pushMsg(HttpServletRequest request) throws Exception {
		ResInfo resInfo = new ResInfo();
		String userId = request.getParameter("userId");
		log.info("------userId--------:"+userId);
		mapData.put(userId, new Date());
		this.sendAllMessage();
		return resInfo;
	}
	
	@RequestMapping(value = "/getList", method = RequestMethod.GET)
	public ResInfo getList(HttpServletRequest request) throws Exception {
		ResInfo resInfo = new ResInfo();
		allList.add(mapTopo);
		resInfo.setRes(allList);
		return resInfo;
	}
	@RequestMapping(value = "/getTopo", method = RequestMethod.GET)
	public ResInfo getTopo(HttpServletRequest request) throws Exception {
		ResInfo resInfo = new ResInfo();
		resInfo.setRes(mapTopo);
		return resInfo;
	}
	@RequestMapping(value = "/getGis", method = RequestMethod.GET)
	public ResInfo getGis(HttpServletRequest request) throws Exception {
		ResInfo resInfo = new ResInfo();
		resInfo.setRes(mapGis);
		return resInfo;
	}
	@RequestMapping(value = "/getStatistics", method = RequestMethod.GET)
	public ResInfo getStatistics(HttpServletRequest request) throws Exception {
		ResInfo resInfo = new ResInfo();
		resInfo.setRes(mapStatistics);
		return resInfo;
	}
	
	@RequestMapping(value = "/pushHistory", method = RequestMethod.POST)
	public ResInfo pushDefense(HttpServletRequest request,@RequestBody JSONArray list) throws Exception {
		log.info(""+list.size());
		ResInfo resInfo = new ResInfo();
		list.stream().forEach(element -> {
			allList.add(element);
		});
		
		return resInfo;
	}
	
	@RequestMapping(value = "/pushDefense", method = RequestMethod.POST)
	public ResInfo pushDefense(HttpServletRequest request,@RequestBody Map<String,Object> map) throws Exception {
		ResInfo resInfo = new ResInfo();

		log.info("------map--------:"+JSON.toJSONString(map));
		if(allList.size()>50) {
			allList.remove(0);
		}
		if(map.containsKey("topo")) {
			mapTopo.putAll(map);
			mapData.putAll(map);
		}else if(map.containsKey("gis")){
			mapGis.putAll(map);
			mapData.putAll(map);
		}else if(map.containsKey("statistics")){
			Integer regularValue = (Integer)((Map)map.get("statistics")).get("regularValue");
			Integer anomalyValue = (Integer)((Map)map.get("statistics")).get("anomalyValue");
			if(mapStatistics.containsKey("statistics")) {
				Integer regularValueHis = (Integer)((Map)mapStatistics.get("statistics")).get("regularValue");
				Integer anomalyValueHis = (Integer)((Map)mapStatistics.get("statistics")).get("anomalyValue");
				((Map)map.get("statistics")).put("regularValue", regularValue+regularValueHis);
				((Map)map.get("statistics")).put("anomalyValue", anomalyValue+anomalyValueHis);
			}
			mapStatistics.putAll(map);
			mapData.putAll(map);
		}else {
			allList.add(JSON.parse(JSON.toJSONString(map)));
			mapData.putAll(map);
		}
		
		this.sendAllMessage();
		return resInfo;
	}
	
	// 每5秒执行一次
//	  @Scheduled(cron = "0/5 * * * * ? ")
	  public void test() {

	  }
}