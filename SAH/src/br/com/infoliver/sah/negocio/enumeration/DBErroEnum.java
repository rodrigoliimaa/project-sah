package br.com.infoliver.sah.negocio.enumeration;

public enum DBErroEnum {
	//---------------------------------------------------------------
	ak_arquivo_01("ak_arquivo_01","Documento # já cadastrado."),	 
	ak_grupo_01("ak_grupo_01","Grupo # já cadastrado."),	
	pk_paciente("pk_paciente","Paciente já cadastrado."),
	ak_paciente_01("ak_paciente_01","CNS já cadastrado."),
	ak_paciente_02("ak_paciente_02","CPF já cadastrado."),
	ak_paciente_03("ak_paciente_03","Paciente já cadastrado."),
	ak_usuario_01("ak_usuario_01","Este Login não pode ser usado."),
	pk_grupo_laudo_paciente("pk_grupo_laudo_paciente","Este Paciente já está cadastrado nesse Grupo.");
	//---------------------------------------------------------------
	
	private String mensagem;

	private String key;

	private DBErroEnum(String key, String mensagem) {
		this.key = key;
		this.mensagem = mensagem;
	}

	public String getMensagem() {
		return mensagem;
	}

	public String getKey() {
		return key;
	}

	public static String builder(String key) {
		String mensagem = null;
		for (DBErroEnum oracleErroEnum : values()) {
			if (oracleErroEnum.key.equals(key)){
				mensagem = oracleErroEnum.mensagem;
				break;
			}
		}
		return mensagem;
	}
}