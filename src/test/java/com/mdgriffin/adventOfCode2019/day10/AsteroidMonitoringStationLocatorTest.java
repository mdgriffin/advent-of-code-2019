package com.mdgriffin.adventOfCode2019.day10;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.mdgriffin.adventOfCode2019.common.FileReader;

public class AsteroidMonitoringStationLocatorTest {
	
	private static final String PROVIDED_INPUT = FileReader.readFile("/test-input/day10.txt");
	
	private static final String EXAMPLE_INPUT_1 =   ".#..#\n" + 
													".....\n" + 
													"#####\n" + 
													"....#\n" + 
													"...##";
	
	private static final String EXAMPLE_INPUT_2 =   "#.........\n" + 
													"...#......\n" + 
													"...#..#...\n" + 
													".####....a\n" + 
													"..#.#.#...\n" + 
													".....#....\n" + 
													"..###.#.##\n" + 
													".......#..\n" + 
													"....#...#.\n" + 
													"...#..#..#";
	
	private static final String EXAMPLE_INPUT_3 =   "......#.#.\n" + 
													"#..#.#....\n" + 
													"..#######.\n" + 
													".#.#.###..\n" + 
													".#..#.....\n" + 
													"..#....#.#\n" + 
													"#..#....#.\n" + 
													".##.#..###\n" + 
													"##...#..#.\n" + 
													".#....####";
	
	private static final String EXAMPLE_INPUT_4 =   "#.#...#.#.\n" + 
													".###....#.\n" + 
													".#....#...\n" + 
													"##.#.#.#.#\n" + 
													"....#.#.#.\n" + 
													".##..###.#\n" + 
													"..#...##..\n" + 
													"..##....##\n" + 
													"......#...\n" + 
													".####.###.";
	
	private static final String EXAMPLE_INPUT_5 =  ".#..#..###\n" + 
													"####.###.#\n" + 
													"....###.#.\n" + 
													"..###.##.#\n" + 
													"##.##.#.#.\n" + 
													"....###..#\n" + 
													"..#.#..#.#\n" + 
													"#..#.#.###\n" + 
													".##...##.#\n" + 
													".....#.#..";
	
	private static final String EXAMPLE_INPUT_6 =  ".#..##.###...#######\n" + 
													"##.############..##.\n" + 
													".#.######.########.#\n" + 
													".###.#######.####.#.\n" + 
													"#####.##.#.##.###.##\n" + 
													"..#####..#.#########\n" + 
													"####################\n" + 
													"#.####....###.#.#.##\n" + 
													"##.#################\n" + 
													"#####.##.###..####..\n" + 
													"..######..##.#######\n" + 
													"####.##.####...##..#\n" + 
													".#####..#.######.###\n" + 
													"##...#.##########...\n" + 
													"#.##########.#######\n" + 
													".####.#.###.###.#.##\n" + 
													"....##.##.###..#####\n" + 
													".#.#.###########.###\n" + 
													"#.#.#.#####.####.###\n" + 
													"###.##.####.##.#..##";
	
	@Test
	public void solutionTest() {
		AsteroidMonitoringStationLocator locator = new AsteroidMonitoringStationLocator(PROVIDED_INPUT);
	
		assertEquals(24, locator .width);
		assertEquals(24, locator.height);
		assertEquals(24, locator.map.length);
		assertEquals(24, locator.map[0].length);

		System.out.println(AsteroidMonitoringStationLocator.printNumVisibleAsteroisMap(locator.map));
		
		MapPosition mp = locator.getBestObservationPosition();
		
		assertEquals(280, mp.numVisibleAsteroids);
		assertEquals(20, mp.x, 0.01);
		assertEquals(18, mp.y, 0.01);
	}

	@Test
	public void withExampleInput1_correctSolution () {
		AsteroidMonitoringStationLocator locator = new AsteroidMonitoringStationLocator(EXAMPLE_INPUT_1);
	
		assertEquals(5, locator .width);
		assertEquals(5, locator.height);
		assertEquals(5, locator.map.length);
		assertEquals(5, locator.map[0].length);

		System.out.println(AsteroidMonitoringStationLocator.printNumVisibleAsteroisMap(locator.map));
		
		MapPosition mp = locator.getBestObservationPosition();
		
		assertEquals(3, mp.x, 0.01);
		assertEquals(4, mp.y, 0.01);
	}
	
	@Test
	public void getNumVisibleAsteroidsFromSource () {
		AsteroidMonitoringStationLocator locator = new AsteroidMonitoringStationLocator(EXAMPLE_INPUT_1);
		
		assertEquals(5, locator .width);
		assertEquals(5, locator.height);
		assertEquals(5, locator.map.length);
		assertEquals(5, locator.map[0].length);
		
		assertEquals(7, locator.getNumVisibleAsteroidsFromSource(locator.map[0][1]));
	}
	
	@Test
	public void getPointsOnPath() {
		AsteroidMonitoringStationLocator locator = new AsteroidMonitoringStationLocator(EXAMPLE_INPUT_2);
		
		assertEquals(10, locator .width);
		assertEquals(10, locator.height);
		assertEquals(10, locator.map.length);
		assertEquals(10, locator.map[0].length);
		
		MapPosition sourcePosition = locator.map[0][0];
		MapPosition destPosition = locator.map[9][9];
		
		List<MapPosition> path = locator.getPointsOnPath(sourcePosition, destPosition);
		
		System.out.println(AsteroidMonitoringStationLocator.printNumVisibleAsteroisMap(locator.map));
		
		assertEquals(8, path.size());
		
	}
	
	@Test
	public void withExampleInput3_correctSolution () {
		AsteroidMonitoringStationLocator locator = new AsteroidMonitoringStationLocator(EXAMPLE_INPUT_3);
		
		assertEquals(10, locator .width);
		assertEquals(10, locator.height);
		assertEquals(10, locator.map.length);
		assertEquals(10, locator.map[0].length);
		
		System.out.println(AsteroidMonitoringStationLocator.printNumVisibleAsteroisMap(locator.map));
		
		MapPosition mp = locator.getBestObservationPosition();
		
		assertEquals(33, mp.numVisibleAsteroids);
		assertEquals(5, mp.x, 0.01);
		assertEquals(8, mp.y, 0.01);
	}
	
	@Test
	public void withExampleInput4_correctSolution () {
		AsteroidMonitoringStationLocator locator = new AsteroidMonitoringStationLocator(EXAMPLE_INPUT_4);
		
		assertEquals(10, locator .width);
		assertEquals(10, locator.height);
		assertEquals(10, locator.map.length);
		assertEquals(10, locator.map[0].length);
		
		System.out.println(AsteroidMonitoringStationLocator.printNumVisibleAsteroisMap(locator.map));
		
		MapPosition mp = locator.getBestObservationPosition();
		
		assertEquals(35, mp.numVisibleAsteroids);
		assertEquals(1, mp.x, 0.01);
		assertEquals(2, mp.y, 0.01);
	}
	
	@Test
	public void withExampleInput5_correctSolution () {
		AsteroidMonitoringStationLocator locator = new AsteroidMonitoringStationLocator(EXAMPLE_INPUT_5);
		
		assertEquals(10, locator .width);
		assertEquals(10, locator.height);
		assertEquals(10, locator.map.length);
		assertEquals(10, locator.map[0].length);
		
		System.out.println(AsteroidMonitoringStationLocator.printNumVisibleAsteroisMap(locator.map));
		
		MapPosition mp = locator.getBestObservationPosition();
		
		assertEquals(41, mp.numVisibleAsteroids);
		assertEquals(6, mp.x, 0.01);
		assertEquals(3, mp.y, 0.01);
	}
	
	@Test
	public void withExampleInput6_correctSolution () {
		AsteroidMonitoringStationLocator locator = new AsteroidMonitoringStationLocator(EXAMPLE_INPUT_6);
		
		assertEquals(20, locator .width);
		assertEquals(20, locator.height);
		assertEquals(20, locator.map.length);
		assertEquals(20, locator.map[0].length);
		
		System.out.println(AsteroidMonitoringStationLocator.printNumVisibleAsteroisMap(locator.map));
		
		MapPosition mp = locator.getBestObservationPosition();
		
		assertEquals(210, mp.numVisibleAsteroids);
		assertEquals(11, mp.x, 0.01);
		assertEquals(13, mp.y, 0.01);
	}

	@Test
	public void isOnPath() {
		assertTrue(AsteroidMonitoringStationLocator.isOnPath(new MapPosition(0,0), new MapPosition(0,0), new MapPosition(0,0)));
		assertTrue(AsteroidMonitoringStationLocator.isOnPath(new MapPosition(0,0), new MapPosition(9,9), new MapPosition(8,8)));
	}
	
	@Test
	public void cartiseanDistance() {
		assertEquals(11.18d, AsteroidMonitoringStationLocator.cartiseanDistance(new MapPosition(-5,-5), new MapPosition(0,5)), 5);
		assertEquals(26d, AsteroidMonitoringStationLocator.cartiseanDistance(new MapPosition(-7,-4), new MapPosition(17,6)), 5);
	}
	
	@Test
	public void areEqualToPrecision() {
		assertTrue(AsteroidMonitoringStationLocator.areEqualToPrecision(0.00001d, 0.000011d, 6));
		assertFalse(AsteroidMonitoringStationLocator.areEqualToPrecision(0.00001d, 0.000011d, 7));
	}
	
	@Test
	public void getEdgePoints() {
		AsteroidMonitoringStationLocator locator = new AsteroidMonitoringStationLocator(EXAMPLE_INPUT_2);
	
		List<MapPosition> points = locator.getEdgePoints(new MapPosition(5, 5));
		
		assertEquals(720001, points.size());
	}
	
	@Test
	public void pulveriseAsteroids_withProvidedInput() {
		AsteroidMonitoringStationLocator locator = new AsteroidMonitoringStationLocator(PROVIDED_INPUT);
	
		List<MapPosition> destroyedAsteroids = locator.pulveriseAsteroids();
		
		assertEquals(398, destroyedAsteroids.size()); // 399
		
		MapPosition twoHundrethAsteroidDestryoed = destroyedAsteroids.get(199);
		
		assertEquals(7, twoHundrethAsteroidDestryoed.x, 0.01);
		assertEquals(6, twoHundrethAsteroidDestryoed.y, 0.01);
	}

	@Test
	public void pulveriseAsteroids_withExampleInput6() {
		AsteroidMonitoringStationLocator locator = new AsteroidMonitoringStationLocator(EXAMPLE_INPUT_6);
		
		List<MapPosition> destroyedAsteroids = locator.pulveriseAsteroids();
		
		assertEquals(299, destroyedAsteroids.size());
		
		assertEquals(11, destroyedAsteroids.get(0).x, 0.01);
		assertEquals(12, destroyedAsteroids.get(0).y, 0.01);
		
		assertEquals(12, destroyedAsteroids.get(1).x, 0.01);
		assertEquals(1, destroyedAsteroids.get(1).y, 0.01);
		
		assertEquals(12, destroyedAsteroids.get(2).x, 0.01);
		assertEquals(2, destroyedAsteroids.get(2).y, 0.01);
		
		assertEquals(12, destroyedAsteroids.get(9).x, 0.01);
		assertEquals(8, destroyedAsteroids.get(9).y, 0.01);
		
		assertEquals(16, destroyedAsteroids.get(19).x, 0.01);
		assertEquals(0, destroyedAsteroids.get(19).y, 0.01);
		
		assertEquals(16, destroyedAsteroids.get(49).x, 0.01);
		assertEquals(9, destroyedAsteroids.get(49).y, 0.01);
		
		assertEquals(10, destroyedAsteroids.get(99).x, 0.01);
		assertEquals(16, destroyedAsteroids.get(99).y, 0.01);
		
		assertEquals(9, destroyedAsteroids.get(198).x, 0.01);
		assertEquals(6, destroyedAsteroids.get(198).y, 0.01);
		
		assertEquals(8, destroyedAsteroids.get(199).x, 0.01);
		assertEquals(2, destroyedAsteroids.get(199).y, 0.01);
		
		assertEquals(10, destroyedAsteroids.get(200).x, 0.01);
		assertEquals(9, destroyedAsteroids.get(200).y, 0.01);
		
		assertEquals(11, destroyedAsteroids.get(298).x, 0.01);
		assertEquals(1, destroyedAsteroids.get(298).y, 0.01);
	}

}
