/**
 * 
 */
package com.andresvg8.fixedassetsapi.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author ANDRES-1
 *
 */
@Entity
@NoArgsConstructor
@NamedQueries({
		@NamedQuery(name = "FixedAsset.findByType", query = "SELECT a FROM FixedAsset a WHERE a.type LIKE :type"),
		@NamedQuery(name = "FixedAsset.findByPurchaseDate", query = "SELECT a FROM FixedAsset a WHERE a.purchaseDate LIKE :purchaseDate"),
		@NamedQuery(name = "FixedAsset.findBySerial", query = "SELECT a FROM FixedAsset a WHERE a.serial LIKE :serial")
})
public class FixedAsset implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4730000637726589128L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Getter @Setter private Long id;

	@Getter @Setter private String name;			//nombre,
	@Getter @Setter private String description;		//descripción, 
	@Getter @Setter private String type;			//tipo, 
	@Getter @Setter private String serial;			//serial, 
	@Getter @Setter private String inventoryNumber;	//numero interno de inventario, 
	@Getter @Setter private Double weight;			//peso, 
	@Getter @Setter private Double height;			//alto, 
	@Getter @Setter private Double width;			//ancho, 
	@Getter @Setter private Double longitude;		//largo, 
	@Getter @Setter private Double purchasePrice;	//valor compra,
	@Getter @Setter private LocalDate purchaseDate; 		//fecha de compra

	@ManyToOne(fetch = FetchType.EAGER)
	@Getter @Setter private Employee employee;

	@ManyToOne(fetch = FetchType.EAGER)
	@Getter @Setter private CompanyArea companyArea;
}
