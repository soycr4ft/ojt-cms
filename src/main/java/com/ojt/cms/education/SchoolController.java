package com.ojt.cms.education;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/school")
public class SchoolController {
	private final RestTemplate restTemplate = new RestTemplate();
	private final String key = "89d20ba8027b485099fcbd934b435dec";
	
	@GetMapping("/high") 
	public List<Map<String, String>> getHighSchools(String cityValue) {
        String url = "https://open.neis.go.kr/hub/schoolInfo?KEY=" + key + "&Type=json&pIndex=1&pSize=1000";
        Map<String,Object> result = restTemplate.getForObject(url, Map.class);
        
        List<Map<String, Object>> rows = (List<Map<String, Object>>) ((Map) ((List) result.get("schoolInfo")).get(1)).get("row");
        //ATPT_OFCDC_SC_CODE 시도교육청코드
        //LCTN_SC_NM 시도명
        //SCHUL_NM 학교명
        return rows.stream()
        		.filter(s->"고등학교".equals(s.get("SCHUL_KND_SC_NM")))
        		.filter(s->cityValue.equals(s.get("LCTN_SC_NM")))
        		.map(s-> Map.of(
        				"name", (String) s.get("SCHUL_NM")
        				))
        		.toList();
	}
	
}
