package br.com.mouzetech.clinicamedica.api.exceptionhandler;
public enum ProblemType {

	MENSAGEM_NAO_COMPREENDIDA("/mensagem-nao-compreendida", "Mensagem incompreensível"),
	ENTIDADE_NAO_ENCONTRADA("/entidade-nao-encontrada", "Entidade não encontrada"),
	ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"),
	PARAMETRO_INVALIDO("/parametro-invalido", "Parâmetro inválido"),
	ERRO_DE_SISTEMA("/erro-de-sistema", "Erro de sistema"),
	DADOS_INVÁLIDOS("/dados-invalidos", "Dados inválidos"),
	ERRO_NEGOCIO("/erro-negocio", "Erro de negócio");
	
	private String type;
	private String title;
	
	private ProblemType(String type, String title) {
		this.title = title;
		this.type = "https://clinicamedica.com.br" + type;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getType() {
		return type;
	}
}