package br.com.gfe.status;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * DTO Padronizado para o endpoint de status
 * 
 */
public class StatusDTO {

	private String status = "OK";
	private String appName;
	private String appBuildDate;
	private String javaVersion;

	private String instanceName;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "America/Recife")
	private Date started;

	public StatusDTO(Date started, String appName) {
		this.started = started;
		this.status = "OK";
		this.appName = appName;
	}

	public Date getStarted() {
		return started;
	}

	public String getStatus() {
		return status;
	}

	public String getAppName() {
		return appName;
	}

	public String getInstanceName() {
		return instanceName;
	}

	public void setInstanceName(String instanceName) {
		this.instanceName = instanceName;
	}

	public void setStarted(Date started) {
		this.started = started;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAppBuildDate() {
		return appBuildDate;
	}

	public void setAppBuildDate(String appBuildDate) {
		this.appBuildDate = appBuildDate;
	}

	public String getJavaVersion() {
		return javaVersion;
	}

	public void setJavaVersion(String javaVersion) {
		this.javaVersion = javaVersion;
	}

}