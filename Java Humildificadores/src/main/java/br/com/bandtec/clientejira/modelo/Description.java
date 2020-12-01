package br.com.bandtec.clientejira.modelo;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Description {

    private String type = "doc";
    private int version = 1;

    private List<Map<String, Object>> content = new ArrayList<>();

    public Description() {
        Map<String, Object> contentRaizUnico = new LinkedHashMap<>();
        contentRaizUnico.put("type", "paragraph");

        List<Map<String, Object>> contentInterno = new ArrayList<>();

        Map<String, Object> contentInternoUnico = new LinkedHashMap<>();
        contentInternoUnico.put("type", "text");

        contentInterno.add(contentInternoUnico);

        contentRaizUnico.put("content", contentInterno);

        content.add(contentRaizUnico);
    }

    public void setDescricao(String descricao) {
        ((List<Map<String,Object>>)content.get(0).get("content")).get(0).put("text", descricao);
    }

    public String getType() {
        return type;
    }

    public int getVersion() {
        return version;
    }

    public List<Map<String, Object>> getContent() {
        return content;
    }
}
