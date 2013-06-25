package br.com.infoliver.sigtap;

import static br.com.infoliver.sigtap.SigtapHelper.CREATE_SCHEMA;
import static br.com.infoliver.sigtap.SigtapHelper.DROP_SCHEMA_IF_EXISTS;
import static br.com.infoliver.sigtap.SigtapHelper.DROP_TABLE_IF_EXISTS;
import static br.com.infoliver.sigtap.SigtapHelper.ISO_8859_1;
import static br.com.infoliver.sigtap.SigtapHelper.NOME_ARQUIVO_SIGTAP_SQL;
import static br.com.infoliver.sigtap.SigtapHelper.NOME_SCHEMA_E_PONTO;
import static br.com.infoliver.sigtap.SigtapHelper.PONTO_E_VIRGULA;
import static br.com.infoliver.sigtap.SigtapHelper.PONTO_TXT;
import static br.com.infoliver.sigtap.SigtapHelper.RL_UNDERLINE_LOWER_CASE;
import static br.com.infoliver.sigtap.SigtapHelper.RL_UNDERLINE_UPPER_CASE;
import static br.com.infoliver.sigtap.SigtapHelper.SIGTAP_DIR;
import static br.com.infoliver.sigtap.SigtapHelper.TB_UNDERLINE_LOWER_CASE;
import static br.com.infoliver.sigtap.SigtapHelper.TB_UNDERLINE_UPPER_CASE;
import static br.com.infoliver.sigtap.SigtapHelper.UTF_8;
import static br.com.infoliver.sigtap.SigtapHelper.VIRGULA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class SigtapUpdate {
	
//	public static final String dir = "C:/Users/prata/Desktop/infoliver/TabelaUnificada_201207_v1207031734";
	
	//TODO: Fazer método para fazer download do arquivo no FTP, verificar a questão do usuário e senha
	//TODO: Agendar task para gerar diariamente o comando SQL para a competência do arquivo disponível
//	public void recuperarUltimaVersao() {
//		try {
//			FTPClient client = new FTPClient();
//			client.connect(URL_FTP);
//			final FTPFile[] files = client.listFiles();
//			for (FTPFile ftpFile : files) {
//				System.out.println(ftpFile.getName());
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
//	public void extrairEsquema(String SIGTAP_DIR) throws Exception {
	public void extrairEsquema() throws Exception {
		final String nomeArquivoLayout = SIGTAP_DIR + File.separator + "layout.txt";
		
		try {
			BufferedWriter sqlScriptWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(NOME_ARQUIVO_SIGTAP_SQL)), UTF_8));
			
			// criar esquema, se não existir
			sqlScriptWriter.write(DROP_SCHEMA_IF_EXISTS);
			sqlScriptWriter.newLine();
			sqlScriptWriter.write(CREATE_SCHEMA);
			sqlScriptWriter.newLine();
			
			BufferedReader layoutReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(nomeArquivoLayout)), ISO_8859_1));
			String layoutLine = layoutReader.readLine();
			while (layoutLine != null) {
				// se começar com tb_ ou rl_, então é uma tabela
				if (layoutLine.startsWith(TB_UNDERLINE_LOWER_CASE) || layoutLine.startsWith(RL_UNDERLINE_LOWER_CASE) || layoutLine.startsWith(TB_UNDERLINE_UPPER_CASE) || layoutLine.startsWith(RL_UNDERLINE_UPPER_CASE) ) {
					Tabela tabela = extrairTabela(layoutReader, layoutLine);
					
					// excluir tabela, se existir
					sqlScriptWriter.write(DROP_TABLE_IF_EXISTS + NOME_SCHEMA_E_PONTO + tabela.getNome() + PONTO_E_VIRGULA);
					sqlScriptWriter.newLine();
					
					// criar tabela
					sqlScriptWriter.write(tabela.toSQL());
					sqlScriptWriter.newLine();
					
					// popular tabela
					String nomeArquivoRegistros = SIGTAP_DIR + File.separator + tabela.getNome() + PONTO_TXT;
					BufferedReader registrosReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(nomeArquivoRegistros)), ISO_8859_1));
					String registro = registrosReader.readLine();
					while (registro != null) {
						sqlScriptWriter.write(tabela.prepareInsertSQL(registro));
						sqlScriptWriter.newLine();
						registro = registrosReader.readLine();
					}
					registrosReader.close();
				}
				layoutLine = layoutReader.readLine();
			}
			layoutReader.close();
			sqlScriptWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(SigtapHelper.MENSAGEM_ERRO_EXTRAIR_TABELAS_ARQUIVO_LAYOUT);
		}
	}

	private Tabela extrairTabela(BufferedReader br, String line) throws IOException {
		Tabela tabela = new Tabela(line);
		
		br.readLine(); // pular a linha de descricao do csv
		
		// cada linha é uma coluna até que se encontre uma linha vazia
		line = br.readLine();
		while (!line.equals("")) {
			Coluna coluna = extrairColuna(line);
			tabela.adicionarColuna(coluna);
			
			line = br.readLine();
		}
		
		return tabela;
	}
	
	private Coluna extrairColuna(String line) {
		Coluna coluna = new Coluna();
		
		String[] splittedLine = line.split(VIRGULA);
		
		coluna.setColuna(splittedLine[0]);
		coluna.setTamanho(Integer.parseInt(splittedLine[1]));
		coluna.setInicio(Integer.parseInt(splittedLine[2]));
		coluna.setFim(Integer.parseInt(splittedLine[3]));
		
		String datatype = SigtapHelper.getDatatype(splittedLine[4]);
		coluna.setTipo(datatype);
		
		return coluna;
	}

	public static void main(String[] args) throws Exception {
		SigtapUpdate sigtapUpdate = new SigtapUpdate();
//		sigtapUpdate.extrairEsquema("C:/Users/prata/Desktop/infoliver/TabelaUnificada_201207_v1207031734");
		sigtapUpdate.extrairEsquema();
//		sigtapUpdate.recuperarUltimaVersao();
	}

}
