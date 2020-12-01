package br.com.bandtec.clientejira.modelo;

public class Issue {

    private Long id;
    private String key;
    private Fields fields = new Fields();

    /**
     * Indica a chave (key) do projeto no qual será aberto o chamado
     * @param projetoKey Chave (key) do projeto. O projeto já deve existir no seu Jira
     */
    public void setProjectKey(String projetoKey) {
        fields.setProjectKey(projetoKey);
    }

    /**
     * Indica o título (summary) do chamado
     */
    public void setSummary(String titulo) {
        this.fields.setSummary(titulo);
    }

    /**
     * Indica a descrição (description) do chamado
     */
    public void setDescription(String descricao) {
        this.fields.setDescription(descricao);
    }

    /**
     * Indica as labels (palavras-chave) associadas ao chamdo
     */
    public void setLabels(String... labels) {
        this.fields.setLabels(labels);
    }

    public Fields getFields() {
        return fields;
    }

    public Long getId() {
        return id;
    }

    public String getKey() {
        return key;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
