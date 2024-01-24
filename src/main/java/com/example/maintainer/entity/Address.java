package com.example.maintainer.entity;

import java.util.Objects;

import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.GenericGenerator;

import com.example.maintainer.entity.enumeration.AddressType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)

@Entity
@Table(name = "global_address",
uniqueConstraints = {
				@UniqueConstraint(name = "uk_address", columnNames = {"user_id, address_type"})
})
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private Long id;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false, name = "user_id", foreignKey = @ForeignKey(name = "fk_user_id_address"))
	private User user;

	@NotNull
	@Column(name = "address_type", nullable = false, length = 100)
    @Enumerated(EnumType.STRING)
	private AddressType addressType;

	@Column(name = "country")
	private String country;

	@Column(name = "city")
	private String city;

	@Column(name = "place_name")
	private String placeName;

	@Column(name = "street_number")
	private String streetNumber;
	
	@Override
	public String toString() {
		return "Address [id=" + id + ", addressType=" + addressType + ", country=" + country + ", city=" + city
				+ ", placeName=" + placeName + ", streetNumber=" + streetNumber + "]";
	}

}
