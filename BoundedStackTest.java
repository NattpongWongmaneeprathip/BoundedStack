import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.HashSet;
import java.util.Set;
/**
 * Test class for BoundedStack.
 * This class contains unit tests to verify the functionality of the BoundedStack class.
 */
public class BoundedStackTest {
    // Test methods will be added here to test the functionality of BoundedStack.
    private static int passed = 0;
    private static int failed = 0;

    /** helper กลาง — พิมพ์ PASS/FAIL และนับผลให้เอง */
    private static void check(String name, boolean condition) {
        if (condition) {
            passed++;
            System.out.println("[PASS] " + name);
        } else {
            failed++;
            System.out.println("[FAIL] " + name);
        }
    }
    
    public static void main(String[] args) {
        boolean assertsOn = false;
        assert assertsOn = true;
        if (!assertsOn) {
            System.out.println("WARNING: assertions disabled"
                    + " - re-run with: java -ea BoundedStackTest\n");
        }

        System.out.println("=== BoundedStack Test Suite ===\n");

        testCreators();
        testAdd();
        testRemove();
        testObservers();
        testProducer();
        testExposure();

        System.out.println("\n=== Summary ===");
        System.out.println("Passed: " + passed);
        System.out.println("Failed: " + failed);
        System.out.println("Total : " + (passed + failed));
        System.out.println(failed == 0 ? "ALL TESTS PASSED" : "SOME TESTS FAILED");

        if (failed > 0) {
            System.exit(1);
        }
    }

    // --- Partition: มีรายชื่อ มีเลขประจำตัว / input ที่ผิดเงื่อนไข
    private static void testCreators() {
        System.out.println("=== testCreators ===");
        // Test creating a BoundedStack with valid inputs
        BoundedStack stack1 = new BoundedStack();
        check("Create empty BoundedStack", stack1.size() == 0);

        // Test creating a BoundedStack with initial names and num_ids
        List<String> names = new ArrayList<>();
        names.add("John Doe");
        List<String> num_ids = new ArrayList<>();
        num_ids.add("1234567890123");
        BoundedStack stack2 = new BoundedStack(names, num_ids);
        check("Create BoundedStack with initial names and num_ids", stack2.size() == 1);

         // boundary: list ว่างคือขอบล่างที่ถูกต้อง
         BoundedStack stack3 = new BoundedStack(Collections.emptyList(), Collections.emptyList());
         check("Create BoundedStack with empty lists", stack3.size() == 0);

        // input ที่ผิดเงื่อนไขต้องโยน exception ไม่ใช่ปล่อยผ่าน
        boolean threwNull = false;
        try {
            new BoundedStack(null, num_ids);
        } catch (IllegalArgumentException e) {
            threwNull = true;
        }
        check("Create BoundedStack with null names -> throws IllegalArgumentException", threwNull);

        boolean threwDuplicate = false;
        try {
            List<String> duplicateNames = new ArrayList<>();
            duplicateNames.add("John Doe");
            duplicateNames.add("John Doe"); // Duplicate name
            new BoundedStack(duplicateNames, num_ids);
        } catch (IllegalArgumentException e) {
            threwDuplicate = true;
        }
        check("Create BoundedStack with duplicate names -> throws IllegalArgumentException", threwDuplicate);

        boolean threwExceedLimit = false;
        try {
            List<String> longNames = new ArrayList<>();
            for (int i = 0; i < 51; i++) {
                longNames.add("Name" + i); // Exceeding MAX_NAME
            }
            new BoundedStack(longNames, num_ids);
        } catch (IllegalArgumentException e) {
            threwExceedLimit = true;
        }
        check("Create BoundedStack with names exceeding limit -> throws IllegalArgumentException", threwExceedLimit);

        boolean threwExceedNumIdLimit = false;
        try {
            List<String> longNumIds = new ArrayList<>();
            for (int i = 0; i < 51; i++) {
                longNumIds.add("NumId" + i); // Exceeding MAX_NUM_ID
            }
            new BoundedStack(names, longNumIds);
        } catch (IllegalArgumentException e) {
            threwExceedNumIdLimit = true;
        }
        check("Create BoundedStack with num_ids exceeding limit -> throws IllegalArgumentException", threwExceedNumIdLimit);
    }
       // --- Mutator: add ต้องรักษาลำดับและกันรายชื่อและเลขประจำตัวซ้ำ ---
    private static void testAdd() {
        System.out.println("=== testAdd ===");
      // adding elements to BoundedStack
        BoundedStack stack = new BoundedStack();
        stack.add("Alice", "1111111111111");
        check("Add element to BoundedStack", stack.size() == 1);
        // รายชื่อและเลขประจำตัวซ้ำ — คืน false เฉย ๆ
        boolean addedDuplicate = stack.add("Alice", "1111111111111");
        check("Add duplicate element to BoundedStack returns false", !addedDuplicate);
        // input ที่ผิดเงื่อนไขต้องโยน exception
        boolean threwEmpty = false;
        try {
            stack.add("", "2222222222222");
        } catch (IllegalArgumentException e) {
            threwEmpty = true;
        }
        check("add(empty name) -> throws IllegalArgumentException", threwEmpty);

        boolean threwNull = false;
        try {
            stack.add(null, "2222222222222");
        } catch (IllegalArgumentException e) {
            threwNull = true;
        }
        check("add(null name) -> throws IllegalArgumentException", threwNull);

        check("failed adds leave stack unchanged", stack.size() == 1);

        // boundary: 
        
      //--- Mutator: remove ทั้งกรณีพบและไม่พบ ---

    }
    
    private static void testRemove() {
        System.out.println("=== testRemove ===");
        // Test removing elements from BoundedStack
    }

    private static void testObservers() {
        System.out.println("=== testObservers ===");
        // Test observer methods
    }

    private static void testProducer() {
        System.out.println("=== testProducer ===");
        // Test producer functionality
    }

    private static void testExposure() {
        System.out.println("=== testExposure ===");
        // Test exposure/encapsulation
    }
}
   