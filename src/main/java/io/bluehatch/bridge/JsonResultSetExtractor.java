package io.bluehatch.bridge;

import java.sql.Blob;
import java.sql.Clob;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.apache.commons.io.IOUtils;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.BinaryNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;

import lombok.extern.slf4j.Slf4j;

/**
 * JsonResultSetExtractor
 *  
 * @author sungjae
 */
@Slf4j
public class JsonResultSetExtractor implements ResultSetExtractor<JsonNode> {
	private static final ObjectMapper objectMapper = new ObjectMapper();
	static {
		//Lob 타입 에러를 방지하기 위한 옵션
		objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
	}
	
	@Override
	public JsonNode extractData(final ResultSet rs) {
		try {
			ArrayNode array = objectMapper.createArrayNode();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			while (rs.next()) {
				ObjectNode row = objectMapper.createObjectNode();
				array.add(row);
				for (int i = 1; i <= columnCount; i++) {
					try {
						row.set(rsmd.getColumnLabel(i), toJsonNode(rs.getObject(i)));
					}catch(Exception e) {
						log.warn(e.getMessage(), e);
					}
				}
			}
			return array;
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	private JsonNode toJsonNode(Object object) throws Exception {
		if(object instanceof Clob) {
			return TextNode.valueOf(IOUtils.toString(((Clob)object).getCharacterStream()));
		}
		if(object instanceof Blob) {
			return BinaryNode.valueOf(IOUtils.toByteArray(((Blob)object).getBinaryStream()));
		}
		return objectMapper.valueToTree(object);
	}


}
