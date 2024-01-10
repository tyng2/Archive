package com.main.comm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.SplittableRandom;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;

public class Common {

	private static Logger log = LoggerFactory.getLogger(Common.class);
	
	public static String nvl(String st) {
		return nvl(st, null);
	}
	
	public static String nvl(String st, String r) {
		String resultSt = "";
		if (st == null || st.trim().isEmpty()) {
			if (r == null || r.trim().isEmpty()) {
				resultSt = "";
			} else {
				resultSt = r;
			}
		} else {
			resultSt = st;
		}
		return resultSt;
	}
	
	public static int str2Int(String st) {
		int num = 0;
		try {
			num = (st != null) ? Integer.parseInt(st) : 0;
		} catch (NumberFormatException e) {
			log.info("str2Int >> {} is not integer", st);
			num = 0;
		}
		return num;
	}
	
	public static String getQueryString(Map<String, String> map) {
		AtomicInteger index = new AtomicInteger();
		StringBuffer sb	= new StringBuffer();
		sb.append("?");
		map.forEach((k, v)->{
			if (index.getAndIncrement() != 0) {
				sb.append("&");
			}
			sb.append(k);
			sb.append("=");
			sb.append(v);
		});
		return sb.toString();
	}
	
	/* message	== null : url로 리다이렉트 (url == null인 경우 잘못된 호출임)
	 * url		== null : alert 실행 후 이전페이지로 이동  */
	public static ResponseEntity<String> respEnt(String message, String url){
		log.info("<< ResponseEntity respEnt >>");
		
		HttpHeaders headers	= new HttpHeaders();
		
		if (message == null) {
			
			if (url == null) {
				return respEnt("잘못된 호출입니다.", null);
				
			} else {
				headers.add("Location", url);
				return new ResponseEntity<String>(headers, HttpStatus.FOUND);
			}
		}
		
		StringBuffer sb		= new StringBuffer();
		
		headers.add("Content-Type", "text/html; charset=UTF-8");
		
		sb.append("<script>");
		sb.append("alert('" + message + "');");
		
		if (url == null) {
			sb.append("history.back();");
		} else {
			sb.append("location.href='" + url + "';");
		}
		
		sb.append("</script>");
		log.info(sb.toString());
		
		return new ResponseEntity<String>(sb.toString(), headers, HttpStatus.OK);
	}
	
	public static String getClientIP() {
		HttpServletRequest request	= ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String ip = request.getHeader("X-Forwarded-For");
		
		if (ip == null) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	
	public static String getDevice(HttpServletRequest request) {
		String pc = "PC";
		String mb = "Mobile";
		String res= "";
		String userAgent = request.getHeader("User-Agent").toUpperCase();
		if (userAgent.indexOf("MOBI") > -1) {
			res = mb;
		} else {
			res = pc;
		}
		return res;
	}
	
	private static HttpURLConnection connect(String apiUrl){
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection) url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
	}
	
	public static String callAPI(String apiURL, String method, String header) {
		log.info("apiURL: {}, method: {}, header: {}", apiURL, method, header);
		
		String res = "";
		HttpURLConnection con = connect(apiURL);
		
		try {
//			URL url = new URL(apiURL);
//			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod(method);
			if (header != null) {
				if (!header.isBlank()) {
					con.setRequestProperty("Authorization", header);
				}
			}
			int responseCode	= con.getResponseCode();
			BufferedReader br	= null;
			
			if (responseCode == HttpURLConnection.HTTP_OK) {
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else {
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			
			String inputLine = "";
			StringBuffer response = new StringBuffer();
			while((inputLine = br.readLine()) != null) {
				response.append(inputLine);
			}
			br.close();
			res = response.toString();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.disconnect();
		}

		log.info("RESULT :: {}", res);
		return res;
	}
	
	// num/100 확률
	public static boolean calcProbability(int num) {
		return calcProbability(num, 100);
	}
	
	// num/maxNum 확률
	public static boolean calcProbability(int num, int maxNum) {
		boolean result = false;
		if (num > 0) {
			SplittableRandom random = new SplittableRandom();
			int calcNum = random.nextInt(0, maxNum);
			if (calcNum < num) {
				result = true;
			}
		}
		return result;
	}
	
	
	
}
