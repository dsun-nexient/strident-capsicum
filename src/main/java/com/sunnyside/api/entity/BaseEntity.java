package com.sunnyside.api.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.sunnyside.api.jsonview.BaseView;


@MappedSuperclass
public abstract class BaseEntity {
	
	@Id
	@JsonView(BaseView.class)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@JsonProperty
	public Integer getId() {
		return id;
	}

	@JsonIgnore
	public void setId(Integer id) {
		this.id = id;
	}
	
	
}
