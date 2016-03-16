package com.lundui.manage.model;

import java.io.Serializable;

/**
 * 资源表
 * @author Administrator
 *
 */
public class Resource implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	/**
	 * 资源名
	 */
	private String resourceName;
	
	/**
	 * 资源URL
	 */
	private String resourceURL;
	
	/**
	 * 父级资源
	 */
	private Resource parentResource;
	
	private String path;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getResourceURL() {
		return resourceURL;
	}

	public void setResourceURL(String resourceURL) {
		this.resourceURL = resourceURL;
	}

	public Resource getParentResource() {
		return parentResource;
	}

	public void setParentResource(Resource parentResource) {
		this.parentResource = parentResource;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
