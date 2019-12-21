package com.mdgriffin.adventOfCode2019.day6;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class OrbitMap {
	
	private Map<String, Planet> namedPlanets = new HashMap<String, Planet>();
	
	public OrbitMap(String orbitsDescriptors) {
		initializeNamedPlanets(orbitsDescriptors);
	}
	
	private void initializeNamedPlanets(String orbitsDescriptors) {
		String[] orbits = orbitsDescriptors.split("\\r?\\n");
		
		Arrays.stream(orbits).forEach(orbit -> {
			String[] orbitParts = orbit.split("\\)");
			String nameOfPlanetBeingOrbited = orbitParts[0];
			String nameOfOrbittingPlanet = orbitParts[1];
			Planet orbittedPlanet;
			Planet orbittingPlanet;
			
			
			if (namedPlanets.containsKey(nameOfPlanetBeingOrbited)) {
				orbittedPlanet = namedPlanets.get(nameOfPlanetBeingOrbited);
			} else {
				orbittedPlanet = new Planet(nameOfPlanetBeingOrbited);
				namedPlanets.put(nameOfPlanetBeingOrbited, orbittedPlanet);
				
			}
			
			if (namedPlanets.containsKey(nameOfOrbittingPlanet)) {
				orbittingPlanet = namedPlanets.get(nameOfOrbittingPlanet);
			} else {
				orbittingPlanet = new Planet(nameOfOrbittingPlanet);
				namedPlanets.put(nameOfOrbittingPlanet, orbittingPlanet);
			}
			
			
			orbittingPlanet.orbits = orbittedPlanet;
		});
	}
	
	public int getNumOrbits() {
		int numOrbits = 0;
		
		for (String key : namedPlanets.keySet()) {
			Planet currentPlanet = namedPlanets.get(key);
			
			while (currentPlanet != null) {
				currentPlanet = currentPlanet.orbits;
				
				if (currentPlanet != null) {
					numOrbits++;
				}
			}
		}
		
		return numOrbits;
	}
	
	private static class Planet {
		
		private Planet orbits;
		
		private String name;
		
		public Planet(String name) {
			this.name = name;
		}
		
		@Override
		public boolean equals(Object o) {
			if (o == this) {
				return true;
			}

			if (!(o instanceof Planet)) {
				return false;
			}

			Planet otherXY = (Planet) o;
			
			return otherXY.name.equals(this.name);
			
		}
		
		@Override
		public String toString() {
			return name + "->" + (orbits != null? orbits.name : null);
		}
		
		
	}

}
