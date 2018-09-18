package bean;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.sun.org.apache.xml.internal.serializer.utils.Messages;

import dao.UploadDAO;
import entity.Upload;
import util.exception.SystemError;

@ManagedBean
@ViewScoped
public class UploadBean {

	private List<Upload> uploads;
	private UploadedFile file;
	private Upload upload = new Upload();
	private UploadDAO uploadDAO = new UploadDAO();
	
	public List<Upload> getUploads() {
		return uploads;
	}

	public void setUploads(List<Upload> uploads) {
		this.uploads = uploads;
	}

	
	public Upload getUpload() {
		return upload;
	}

	public void setUpload(Upload upload) {
		this.upload = upload;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}
	

	@SuppressWarnings("unchecked")
	public void salvar() {	

		try {	
			UploadDAO uploadDAO = new UploadDAO();
			uploadDAO.salvar(upload);
			
			
			
			

			adicionarMensagem("Salvo com sucesso", FacesMessage.SEVERITY_INFO);
		} catch (SystemError e) {

			adicionarMensagem(e.getMessage(), FacesMessage.SEVERITY_ERROR);
			e.printStackTrace();
		}
		
	}

	public void editar(Upload upload) {
		this.upload = upload;

	}	

	@SuppressWarnings("unchecked")
	public void deletar(Upload upload) {
		try {
			uploadDAO.deletar(upload);
			uploads.remove(uploads);
			adicionarMensagem("Excluído com sucesso", FacesMessage.SEVERITY_INFO);
		} catch (SystemError e) {

			adicionarMensagem(e.getMessage(), FacesMessage.SEVERITY_ERROR);
			e.printStackTrace();
		}
	}
	
	public void adicionarMensagem(String message, FacesMessage.Severity typeError) {
		FacesMessage fmsg = new FacesMessage(typeError, message, null);
		FacesContext.getCurrentInstance().addMessage(null, fmsg);
	}
	@SuppressWarnings("resource")
	public byte[] toByteArrayUsingJava(InputStream is) throws IOException{
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int reads = is.read();
		while(reads != -1){
			baos.write(reads);
			reads = is.read();
		}
		
		return baos.toByteArray();
	}
	
	

	public void upload(FileUploadEvent evento) throws IOException, SystemError{
		try{
			UploadedFile arquivoUpload = evento.getFile();
			Path arquivoTemp = Files.createTempFile(null, null);
			Files.copy(arquivoUpload.getInputstream(), arquivoTemp, StandardCopyOption.REPLACE_EXISTING);
			upload.setCaminho(arquivoTemp.toString());
			
		}catch (Exception e) {
			adicionarMensagem(e.getMessage(), FacesMessage.SEVERITY_ERROR);
			e.printStackTrace();
		}
	}

	public void preProcessPDF(Object document) throws IOException, BadElementException, DocumentException {
		Document pdf = (Document) document;
		pdf.open();
		pdf.setPageSize(PageSize.A4.rectangle(297, 210));

		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		String logo = externalContext.getRealPath("") + File.separator + "resources" + File.separator + "imagens"
				+ File.separator + "camp6.png";

		pdf.add(Image.getInstance(logo));
	}

}
