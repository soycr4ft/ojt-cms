package com.ojt.cms.education;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/school")
public class SchoolController {

    private final ModelMapper modelMapper;
	private final RestTemplate restTemplate = new RestTemplate();
	private final String key = "89d20ba8027b485099fcbd934b435dec";

    SchoolController(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
	
	@GetMapping("/high")
	public List<Map<String, String>> getHighSchools(
	        @RequestParam(name = "name", required = false, defaultValue = "") String name,
	        @RequestParam(name = "city", required = false, defaultValue = "all") String city) {

	    try {
	        String url = "https://open.neis.go.kr/hub/schoolInfo?KEY=" + key +
	                "&Type=json&pIndex=1&pSize=1000" +
	                "&ATPT_OFCDC_SC_CODE=" + city ;
	        
	        if (!name.isEmpty()) {
	            url += "&SCHUL_NM=" + name;
	        }
	        String rawResponse = restTemplate.getForObject(url, String.class);
	        System.out.println(""+rawResponse);
	        // 혹시 HTML(에러 페이지)일 경우 → 빈 리스트 반환
	        if (rawResponse != null && rawResponse.startsWith("<!DOCTYPE")) {
		        System.out.println("여기로온다");
	        	return Collections.emptyList();
	        }

	        // JSON일 경우만 파싱
	        Map<String, Object> response = new ObjectMapper().readValue(rawResponse, Map.class);
	        if (response.containsKey("schoolInfo")) {
		        System.out.println("여기로오나??");
		        System.out.println(response);
	        	List<Map<String, Object>> schoolInfo = (List<Map<String, Object>>) response.get("schoolInfo");
	        	System.out.println("schoolInfo"+schoolInfo);
	            if (schoolInfo.size() > 1) {
	            	System.out.println("리텁이 이상하ㄴ다"+(List<Map<String, String>>) schoolInfo.get(1).get("row"));
	                return (List<Map<String, String>>) schoolInfo.get(1).get("row");
	            }
	        }
	        return Collections.emptyList();

	    } catch (Exception e) {
	        // 결과가 있어도 500 때문에 들어올 수 있음 → 그냥 빈 리스트 반환
	        return Collections.emptyList();
	    }
	}


}
