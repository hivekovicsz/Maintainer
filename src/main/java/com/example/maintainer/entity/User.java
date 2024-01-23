package com.example.maintainer.entity;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)

@Entity
@Table(name = "global_user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + "]";
	}		

}
