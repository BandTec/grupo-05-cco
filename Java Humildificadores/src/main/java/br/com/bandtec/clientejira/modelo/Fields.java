package br.com.bandtec.clientejira.modelo;

import java.util.*;

public class Fields {

    private String summary;
    private IssueField issuetype = new IssueField(10006L);
    private IssueField project;
    private IssueField priority = new IssueField(2L);
    private Description description = new Description();

    private Set<String> labels = new LinkedHashSet<>();

    public void setSummary(String titulo) {
        this.summary = titulo;
    }

    public void setProjectKey(String projetoKey) {
        project = new IssueField(projetoKey);
    }

    public void setLabels(String... novasLabels) {
        labels.addAll(Arrays.asList(novasLabels));
    }

    public void setDescription(String descricao) {
        description.setDescricao(descricao);
    }

    public IssueField getIssuetype() {
        return issuetype;
    }

    public IssueField getProject() {
        return project;
    }

    public IssueField getPriority() {
        return priority;
    }

    public Description getDescription() {
        return description;
    }

    public Set<String> getLabels() {
        return labels;
    }
}
