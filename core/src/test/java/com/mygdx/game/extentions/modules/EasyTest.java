/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.java.com.mygdx.game.extentions.modules;

import com.mygdx.game.TDGame;
import com.mygdx.game.defenseConstucts.DCFactory;
import com.mygdx.game.defenseConstucts.DefenseConstruction;
import com.mygdx.game.defenseConstucts.Tower;
import com.mygdx.game.extentions.modules.Easy;
import com.mygdx.game.mapAndOther.Cell;
import com.mygdx.game.mapAndOther.Map;
import com.mygdx.game.screen.LevelScreen;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Alex
 */
public class EasyTest {
    
    public EasyTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

 
    /**
     * Test of moveToTarget method, of class Easy.
     */
    @Test
    public void testMoveToTargetErorr() {
        System.out.println("moveToTarget Cell(0,0)");
         Easy instance = new Easy();
        
        Cell currentCell = new Cell(0,0);
    
        Cell targCell = new Cell(1,1);
        Cell want = new Cell(0,0);
   
        Cell result = instance.moveToTarget(currentCell,targCell);
        // TODO review the generated test code and remove the default call to fail.
        assertFalse(want.equals(result));
    }
     /**
     * Test of moveToTarget method, of class Easy.
     */
    @Test
    public void testMoveToTargetFirst() {
        System.out.println("moveToTargetFirst Cell(0,1)");
         Easy instance = new Easy();
        
        Cell currentCell = new Cell(0,0);
    
        Cell targCell = new Cell(1,1);
        Cell want = new Cell(0,1);
   
        Cell result = instance.moveToTarget(currentCell,targCell);
        // TODO review the generated test code and remove the default call to fail.
        assertFalse(want.equals(result));
    }
    
    /**
     * Test of moveToTarget method, of class Easy.
     */
    @Test
    public void testMoveToTargetPassSecond() {
        System.out.println("moveToTargetSecond Cell(1,0)");
         Easy instance = new Easy();
        
        Cell currentCell = new Cell(0,0);
        Cell targCell = new Cell(1,1);
        Cell want = new Cell(1,0);
   
        Cell result = instance.moveToTarget(currentCell,targCell);
        // TODO review the generated test code and remove the default call to fail.
        assertTrue(want.equals(result));
    }
    
     /**
     * Test of moveToTarget method, of class Easy.
     */
    @Test
    public void testMoveToTargetPass3() {
        System.out.println("moveToTargetSecond Cell(1,1)");
         Easy instance = new Easy();
        
        Cell currentCell = new Cell(0,0);
    
        Cell targCell = new Cell(1,1);
        Cell want = new Cell(1,1);
   
        Cell result = instance.moveToTarget(currentCell,targCell);
        // TODO review the generated test code and remove the default call to fail.
        assertFalse(want.equals(result));
    }

}
