package dao;


import entity.Upload;
import util.ConexionFactory;
import util.exception.SystemError;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class UploadDAO{

	
	
	public void salvar(Upload upload) throws SystemError {
		try {
			Connection conexao = ConexionFactory.getConexao();
			PreparedStatement pst;
			if (upload == null) {
				pst = conexao.prepareStatement("INSERT INTO anexo (anx_anexo,anx_descricao) VALUES (?,?)");
			
			pst.setBytes(1,upload.getAnx_anexo());	
			pst.setString(2,upload.getAnx_descricao());
				
			
				System.out.println(pst);
			
				pst.executeUpdate();
				ConexionFactory.desconectar();

			} 
		}catch (SQLException ex) {
				throw new SystemError("Falha ao salvar o anexo", ex);
			}
			
		
	}

	
	public void deletar(Upload upload) throws SystemError {
		try {
			Connection conexao = ConexionFactory.getConexao();
			PreparedStatement pst;

			pst = conexao.prepareStatement("delete from anexo where id=?");
			pst.setInt(1, upload.getId());
			pst.execute();
		} catch (SQLException ex) {
			throw new SystemError("Falha ao excluir o anexo", ex);
		}		
		
	}

	
	public List<Upload> procurar() throws SystemError {
		try {
			Connection conexao = ConexionFactory.getConexao();
			PreparedStatement pst = conexao.prepareStatement("select * from anexo");
			ResultSet rs = pst.executeQuery();
			List<Upload> uploads = new ArrayList<>();
			while (rs.next()) {
				Upload upload = new Upload();
				upload.setId(rs.getInt("id"));
				upload.setAnx_anexo(rs.getBytes("anx_anexo"));
				upload.setAnx_descricao(rs.getString("anx_descricao"));
				

				uploads.add(upload);
			}
			ConexionFactory.desconectar();
			return uploads;
		} catch (SQLException ex) {
			throw new SystemError("Falha ao procuraro anexo", ex);
		}
	}
	
	public Upload getUpload(int id) throws SystemError {
		try {
			Connection conexao = ConexionFactory.getConexao();
			PreparedStatement pst = conexao.prepareStatement("select * from anexo  where id="+id);
			ResultSet rs = pst.executeQuery();
			Upload upload = new Upload();
			while (rs.next()) {
			
				upload.setId(rs.getInt("id"));
				upload.setAnx_anexo(rs.getBytes("anx_anexo"));
				upload.setAnx_descricao(rs.getString("anx_descricao"));

				
			}
			ConexionFactory.desconectar();
			return upload;
		} catch (SQLException ex) {
			throw new SystemError("Falha ao procurar o anexo", ex);
		}
	}

	
}
