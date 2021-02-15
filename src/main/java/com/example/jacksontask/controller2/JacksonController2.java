package com.example.jacksontask.controller2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
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
public class JacksonController2 {

	@GetMapping("/view-jackson1")
	
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
		Iterator<JsonNode> it = nodes.elements();
		while(it.hasNext()) {
			
		
			ObjectNode profile = (ObjectNode)it.next();
			
			profile.remove("message");
			profile.remove("@timestamp");
			profile.remove("srcUserId");
			profile.remove("srcTransPort");
			profile.remove("syslog5424_pri");
			profile.remove("destInterfaceName");
			profile.remove("@version");
			profile.remove("host");
			profile.remove("connectionId");
			profile.remove("destTransIp");
			profile.remove("id");
			profile.remove("srcInterfaceName");
			profile.remove("srcUser");
		
		}

		return ResponseEntity.ok(mainlist);
	}
}









	
	