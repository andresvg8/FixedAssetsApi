package com.andresvg8.fixedassetsapi.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
public class City implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 6907697045089520620L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter private Long id;

    @NotBlank(message = "Name is mandatory")
    @Getter @Setter private String name;

    public String toString(){
        return "id: "+this.getId()+"\tname: "+this.getName();
    }

    /*@ManyToMany(mappedBy = "cities")
    @Getter @Setter private Set<CompanyArea> companyAreas = new HashSet<CompanyArea>();*/
}
