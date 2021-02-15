package com.example.jacksontask.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class JacksonController {
	
	
	@GetMapping("/view-jackson")
	
public ResponseEntity<?> viewRes() throws FileNotFoundException, IOException, ParseException {
	
		List<Map<String, Object>> mainlist = new ArrayList<Map<String, Object>>();
		Set<Object> set = new LinkedHashSet<Object>();

		JsonNode nodes = new  ObjectMapper().readTree(new File("src/main/resources/JsonFile.json"));

		for (String port : nodes.findValuesAsText("destTransPort")) set.add(port);

		for (Object i : set) {
			Map<String, Object> map=new LinkedHashMap<String, Object>();
			List<Object> list1 = new LinkedList<Object>();
			
			for (JsonNode node : nodes) {
				
				if (node.get("destTransPort").asInt() == Integer.valueOf((String) i))
					{
					list1.add(node);
					}
			}
			map.put("destTransport", i);
			map.put("topHits", list1);
			mainlist.add(map);
		}
		return ResponseEntity.ok(mainlist);
	}
}

//	@GetMapping("/view-res")
//	public ResponseEntity<?> viewRes() throws FileNotFoundException, IOException, ParseException {
//		
//		
//		ObjectMapper objectMapper = new ObjectMapper();
//		
//		List<Map<String, Object>> mainlo = new ArrayList<Map<String, Object>>();
//		
//		LinkedHashSet<Object> set = new LinkedHashSet<Object>();
//		LinkedHashSet<Object> nameset = new LinkedHashSet<Object>();
//		
//		
//		JSONParser parser = new JSONParser();
//			Object object = parser.parse(new FileReader("src/main/resources/JsonFile.json"));
//		JSONArray jsonObject = (JSONArray) object;
//		
//		JsonNode node = objectMapper.readTree(jsonObject.toJSONString());
//		
//		for(String s : node.findValuesAsText("destTransPort")) {
//			set.add(s);
//		}
//		for (String s : node.findValuesAsText("destInterfaceName")) {
//			nameset.add(s);
//		}
//		
//		for(Object obname : nameset) {	
//			for(Object obj :set) {
//				Map<String, Object> map=new LinkedHashMap<String, Object>();
//				List<Object> list = new LinkedList<Object>();
//				int in = Integer.valueOf((String) obj);
//			
//				for(JsonNode nodes:node) {
//					if(nodes.get("destTransPort").asInt()==in) {
//						if(nodes.get("destInterfaceName").asText().equals(obname)) {
//							list.add(nodes);
//						}
//					}
//				}	
//				if(!(list.isEmpty())) {
//					map.put("destTransPort", in);
//					map.put("destInterfaceName",obname);
//					map.put("topHits", list);
//					mainlo.add(map);
//				}
//			}
//		}
//		return ResponseEntity.ok(mainlo);
//	}
//}
	