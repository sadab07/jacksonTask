package com.example.jacksontask.controller2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;


import org.json.simple.parser.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
public class JacksonFinal {

	@GetMapping("/view-jackson2")
	
public ResponseEntity<?> viewRes() throws FileNotFoundException, IOException, ParseException {
	
		List<Map<String, Object>> mainlist = new ArrayList<Map<String, Object>>();
		Set<Object> set = new LinkedHashSet<Object>();

		JsonNode nodes = new  ObjectMapper().readTree(new File("src/main/resources/JsonFile.json"));

		for (String port : nodes.findValuesAsText("destTransPort")) set.add(port);

		for (Object i : set) {
			Map<String, Object> map=new LinkedHashMap<String, Object>();
			List<Object> list1 = new LinkedList<Object>();
			map.put("destTransport", i);
			map.put("topHits", list1);

			for (JsonNode node : nodes) {
				
				if (node.get("destTransPort").asInt() == Integer.valueOf((String) i))
					{
					Map<String , Object> maps=new HashMap<>();
					
					maps.put("destIp", node.get("destIp"));
					maps.put("msg", node.get("msg"));
					maps.put("srcTransIp",node.get("srcTransIp"));
					maps.put("eventId",node.get("eventId"));
					maps.put("srcIp",node.get("srcIp"));
					maps.put("product",node.get("product"));
					maps.put("destTransPort", node.get("destTransPort"));
					maps.put("index",node.get("index"));
					maps.put("srcPort",node.get("srcPort"));
					list1.add(maps);
					
					}
			}
						mainlist.add(map);
		}


		return ResponseEntity.ok(mainlist);
	}
}









	
	