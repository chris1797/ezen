package com.ezen.demo.kakao;



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/kakaomsg/rest")
@Slf4j
public class KakaoRestTestcontroller {

	@GetMapping("/rest")
	@ResponseBody
	public String index() {
		return "thymeleaf/kakao/index";
	}
	
	@GetMapping("/login/oauth")
	@ResponseBody
	public String auth_kakao_callback(@RequestParam(value="code", required=false)String code, Model model) throws Exception {
		log.debug("code : {}", code);
		String access_token = getAccessToken(code);
		log.debug("access_token : {}", access_token);
		sendMsg(access_token);
		
		return "access_token = " + access_token;
	}
	
	public void sendMsg(String access_token) {
		String reqURL = "https://kapi.kakao.com/v2/api/talk/memo/default/send";
		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			// 이제 conn을 카카오로 인식하면 됨
			
	         conn.setRequestMethod("POST");
	         conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	         conn.setRequestProperty("Authorization", "Bearer " + access_token);
	         conn.setDoOutput(true);
	         conn.setUseCaches(false);
	         
	         JSONObject jsObj = new JSONObject();
	         jsObj.put("object_type", "text");
	         jsObj.put("text", "안뇽");
	         
	         JSONObject linkObj = new JSONObject();
	         linkObj.put("web_url", "https://developers.kakao.com");
	         linkObj.put("mobile_web_url", "https://developers.kakao.com");
	         
	         jsObj.put("link", linkObj);
	         jsObj.put("button_title", "바로 확인");
	         
	         String msgData = "template_object=" + jsObj.toJSONString();
	         log.debug("msgData : {}", msgData);
	         
	         // 문자열 > byte 전송
	         BufferedWriter bw = new  BufferedWriter(new OutputStreamWriter(conn.getOutputStream())); // Writer: 문자열을 다룸
	         
	         bw.write(msgData);
	         bw.flush();
	         
	         int responseCode = conn.getResponseCode();
	         log.debug("SEND, responseCode : {}", responseCode);
	         
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private String getAccessToken(String auth_code) {
	      String access_token = null;
	      // 전달된 auth_code를 사용하여 다시 카카오서버에 요청하여 access_token을 발급 받는다.
	      String reqURL = "https://kauth.kakao.com/oauth/token";
	      try {
	         URL url = new URL(reqURL);
	         HttpURLConnection conn = (HttpURLConnection)url.openConnection();
	         conn.setRequestMethod("POST");
	         conn.setDoOutput(true);
	         
	         //access token을 발급받기 위한 카카오 서버로의 요청(출력스트림)
	         BufferedWriter bw = new  BufferedWriter(new OutputStreamWriter(conn.getOutputStream())); // Writer: 문자열을 다룸
	         StringBuilder sb = new StringBuilder();
	         sb.append("grant_type=authorization_code");
	         sb.append("&client_id=df843fd23d52978512aca1a41f47df9c"); // 각자의 REST API 키
	         sb.append("&redirect_uri=http://localhost/kakaomsg/rest/login/oauth"); // 등록된 Redirect URI
	         sb.append("&code=" + auth_code);
	         
	         bw.write(sb.toString());
	         bw.flush();
	         
	         int responseCode = conn.getResponseCode();
	         log.debug("responseCode: {}", responseCode); //200일 경우 AT 발급 성공
	         
	         // 발급된 access_token을 가져오기
	         BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	         String line = "";
	         String result = "";
	         
	         while((line=br.readLine())!=null) {
	            result += line;
	         }
	         log.debug("response body: {}", result); // access_token이 포함되어 있나 검사
	         
	         JSONParser parser = new JSONParser();
	         JSONObject jsObj = (JSONObject) parser.parse(result);
	         access_token = (String)jsObj.get("access_token");
	         log.info("access_token= {}", access_token);
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	      
	      return access_token;
	   }
}
