package vn.sapo.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class JsonHelper {
    private final ObjectMapper objectMapper;

    public JsonHelper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public JsonNode getJsonNode(String rawString, String rootName) throws IOException {
        JsonNode tree = this.objectMapper.readTree(rawString);
        return tree.has(rootName) ? tree.path(rootName) : tree;
    }

    public boolean existField(JsonNode node, String key) {
        if (StringUtils.isBlank(key)) {
            return false;
        } else {
            boolean found;
            if (key.contains(".")) {
                String[] lstField = key.split("\\.");
                JsonNode tempNode = node.path(lstField[0]);
                found = !tempNode.isMissingNode();
                if (found && lstField.length > 1) {
                    for (int index = 1; found && index < lstField.length; ++index) {
                        tempNode = tempNode.path(lstField[index]);
                        found = !tempNode.isMissingNode();
                    }
                }
            } else {
                found = !node.path(key).isMissingNode();
            }

            return found;
        }
    }
}
