package br.com.bandtec.clientejira;

import br.com.bandtec.clientejira.modelo.Issue;
import com.google.gson.Gson;
import okhttp3.*;

import java.io.IOException;
import java.util.Base64;

public class ClienteJiraApi {

    private String urlBaseJira;
    private String usuario;
    private String token;

    /**
     * Cria uma nova instância de {@link ClienteJiraApi}.
     * @param urlBaseJira URL base do seu Jira. Ex: <b>meuapp.atlassian.net</b>
     * @param usuario Seu usuário de acesso ao Jira
     * @param token Seu token de acesso ao Jira.
     *              Pode ser gerado em <a href='https://id.atlassian.com/manage-profile/security/api-tokens'>
     *              https://id.atlassian.com/manage-profile/security/api-tokens</a>
     */
    public ClienteJiraApi(String urlBaseJira, String usuario, String token) {
        this.urlBaseJira = urlBaseJira.startsWith("https://")?urlBaseJira:"https://"+urlBaseJira;
        this.usuario = usuario;
        this.token = token;
    }

    /**
    Cria um chamado (Issue) e, se tudo der certo, atualiza os "id" e "key" do objeto do tipo Issue enviado
    @param novaIssue Objeto com os dados do novo chamado (Issue)
     **/
    public void criarIssue(Issue novaIssue) throws IOException {
        Gson gson = new Gson();

        RequestBody issueBody = RequestBody.create(gson.toJson(novaIssue), MediaType.get("application/json;charset=UTF-8"));
        Request request = criarRequestBuilder()
                .url(String.format("%s/rest/api/3/issue", urlBaseJira)).method("POST", issueBody)
                .build();

        System.out.println(gson.toJson(novaIssue));
        
        Response response = criarCliente().newCall(request).execute();
        
        System.out.println("Code: "+ response.code());
        
        Issue issueCriada = gson.fromJson(response.body().string(), Issue.class);

        novaIssue.setId(issueCriada.getId());
        novaIssue.setKey(issueCriada.getKey());
    }

    /**
     * Recupera um chamado (Issue) por valor da chave (key)
     * @param key
     * @return
     * @throws IOException
     */
    public Issue getIssue(String key) throws IOException {
        Request request = criarRequestBuilder()
                .url(String.format("%s/rest/api/3/issue/%s", urlBaseJira, key)).method("GET", null)
                .build();

        Response response = criarCliente().newCall(request).execute();
        System.out.println("Code: "+ response.code());

        Issue issue = new Gson().fromJson(response.body().string(), Issue.class);

        return issue;
    }

    private OkHttpClient criarCliente() {
        return new OkHttpClient().newBuilder().build();
    }

    private Request.Builder criarRequestBuilder() {
        String autenticacaoBasic = Base64.getEncoder().encodeToString(String.format("%s:%s", usuario, token).getBytes());

        return new Request.Builder().addHeader("Authorization", String.format("Basic %s", autenticacaoBasic));
    }

    void criarIssue() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
