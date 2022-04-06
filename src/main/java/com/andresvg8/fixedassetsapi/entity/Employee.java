/**
 * 
 */
package com.andresvg8.fixedassetsapi.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
public class Employee implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 3739189371974719587L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Getter	@Setter	private Long id;

	@NotBlank(message = "Name is mandatory")
	@Getter @Setter private String name;

	@OneToMany
	//@JoinColumn(name = "employee_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
	@Getter @Setter private List<FixedAsset> assets = new ArrayList<>();
}
