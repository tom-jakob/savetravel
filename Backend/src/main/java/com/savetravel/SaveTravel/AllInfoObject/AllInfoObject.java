package com.savetravel.SaveTravel.AllInfoObject;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table
public class AllInfoObject {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private Integer userId = 444;
	private String countryName;
	private String countryCode;
	private String lastUpdateTW;
	private Double travelWarningScore;
	private Double coronaScore;
	private Double population;
	private String coronaByPopulation;
	private Double coronaActiveCases;
	private Boolean cocoExistsInCorona = false;
	private String[] timezone;
	
	
	
	
	

}