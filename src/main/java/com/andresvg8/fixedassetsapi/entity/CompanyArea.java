/**
 * 
 */
package com.andresvg8.fixedassetsapi.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author ANDRES-1
 *
 */
@Entity
@NoArgsConstructor
public class CompanyArea implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 6166781064773503471L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Getter	@Setter	private Long id;

	@NotBlank(message = "Name is mandatory")
	@Getter @Setter private String name;

	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(
			name = "Cities_Company_Areas",
			joinColumns = { @JoinColumn(name = "company_area_id") },
			inverseJoinColumns = { @JoinColumn(name = "city_id") }
	)
	@Getter @Setter
	List<City> cities = new ArrayList<>();

	public String toString(){
		return "CompanyArea:\tid: "+this.getId()+"\tname: "+this.getName()+"\tcities: "+this.getCities();
	}
}
