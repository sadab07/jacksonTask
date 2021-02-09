package com.example.jacksontask.controller;

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

	@GetMapping("/view-res")
	
	public ResponseEntity<?> viewRes() throws FileNotFoundException, IOException, ParseException {
		List<Map<String, Object>> mainlist = new ArrayList<Map<String, Object>>();
		Set<Object> set = new LinkedHashSet<Object>();
		JSONParser parser = new JSONParser();

		Object object = parser.parse(new FileReader("src/main/resources/JsonFile.json"));
		JSONArray jsonObject = (JSONArray) object;
		ObjectMapper om = new ObjectMapper();
		JsonNode nodes = om.readTree(jsonObject.toJSONString());
		
		for (String port : nodes.findValuesAsText("destTransPort")) set.add(port);

		for (Object i : set) {
			Map<String, Object> map=new LinkedHashMap<String, Object>();
			//int s = Integer.valueOf((String) i);
			List<Object> list = new LinkedList<Object>();
			for (JsonNode node : nodes) {
				
				if (node.get("destTransPort").asInt() == Integer.valueOf((String) i))
					{
					list.add(node);
					}
			}
			map.put("destTransport", i);
			map.put("topHits", list);
			mainlist.add(map);
		}
		return ResponseEntity.ok(mainlist);
	}
}