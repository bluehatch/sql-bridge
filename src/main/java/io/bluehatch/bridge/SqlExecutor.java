package io.bluehatch.bridge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;

import lombok.extern.slf4j.Slf4j;

/**
 * SqlExecutor
 *  
 * @author sungjae
 */
@Component
@Slf4j
public class SqlExecutor {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public JsonNode query(String sql) {
		try {
			log.debug("Execute query: {}", sql);
			return jdbcTemplate.query(sql, new JsonResultSetExtractor());
		}catch(RuntimeException ex) {
			log.error(ex.getMessage(), ex);
			throw ex;
		}
	}
}
