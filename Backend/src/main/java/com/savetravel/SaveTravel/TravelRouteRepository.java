package com.savetravel.SaveTravel;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.savetravel.SaveTravel.AllInfoObject.AllInfoObject;

@Repository
@Qualifier("travelRouteRepository")
public interface TravelRouteRepository extends JpaRepository<AllInfoObject, Integer> {

}
