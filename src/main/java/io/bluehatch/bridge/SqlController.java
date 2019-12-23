package io.bluehatch.bridge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * SqlController
 *  
 * @author sungjae
 */
@RestController
@RequestMapping(value="/sql")
public class SqlController {

	@Autowired
	SqlExecutor sqlExecutor;
	
    @PostMapping(value="/query")
    public JsonNode query(@RequestBody String sql) {
        return sqlExecutor.query(sql);
    }
    
}
