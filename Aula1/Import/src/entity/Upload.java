
package entity;

/**
 *
 * @author jadir
 */
@SuppressWarnings("serial")
public class Upload{
	private Integer id;
	private byte[] anx_anexo;
	private String anx_descricao;
	private String caminho;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public byte[] getAnx_anexo() {
		return anx_anexo;
	}

	public void setAnx_anexo(byte[] anx_anexo) {
		this.anx_anexo = anx_anexo;
	}

	public String getAnx_descricao() {
		return anx_descricao;
	}

	public void setAnx_descricao(String anx_descricao) {
		this.anx_descricao = anx_descricao;
	}

	public String getCaminho() {
		return caminho;
	}

	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}

}
