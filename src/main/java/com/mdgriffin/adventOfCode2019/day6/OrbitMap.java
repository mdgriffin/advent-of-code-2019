package com.mdgriffin.adventOfCode2019.day6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
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
			if (!orbittedPlanet.orbittedBy.contains(orbittingPlanet)) {
				orbittedPlanet.orbittedBy.add(orbittingPlanet);
			}
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

	public int getInterPlanetDistance(String sourcePlanetName, String destinationPlanetName) {
		LinkedList<Planet> queue = new LinkedList<Planet>();
		List<Planet> visited = new ArrayList<Planet>();
		Map<Planet, Integer> distance = new HashMap<Planet, Integer>();
		Planet sourcePlanet = namedPlanets.get(sourcePlanetName);

		visited.add(sourcePlanet);
		distance.put(sourcePlanet, 0);
		queue.addLast(sourcePlanet);

		while (queue.size() > 0) {
			Planet currentPlanet = queue.removeFirst();

			for (Planet adjacentPlanet : currentPlanet.getAdjacentPlanet()) {
				if (!visited.contains(adjacentPlanet)) {
					visited.add(adjacentPlanet);

					distance.put(adjacentPlanet, distance.get(currentPlanet) + 1);
					queue.addLast(adjacentPlanet);

					if (adjacentPlanet.name.equals(destinationPlanetName)) {
						return distance.get(currentPlanet);
					}
				}
			}
		}

		return -1;
	}

	private static class Planet {

		public Planet orbits;

		public List<Planet> orbittedBy = new ArrayList<Planet>();

		private String name;

		public Planet(String name) {
			this.name = name;
		}

		public List<Planet> getAdjacentPlanet() {
			List<Planet> merged = new ArrayList<Planet>(orbittedBy);

			if (orbits != null) {
				merged.add(orbits);
			}

			return merged;
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
			return name + "->" + (orbits != null ? orbits.name : null);
		}

	}

}
